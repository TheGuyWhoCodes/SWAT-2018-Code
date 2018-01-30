package org.usfirst.frc.team1806.robot.subsystems;

import java.awt.Robot;
import java.util.HashSet;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.usfirst.frc.team1806.robot.Constants;
import org.usfirst.frc.team1806.robot.Kinematics;
import org.usfirst.frc.team1806.robot.RobotMap;
import org.usfirst.frc.team1806.robot.RobotState;
import org.usfirst.frc.team1806.robot.loop.Loop;
import org.usfirst.frc.team1806.robot.loop.Looper;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.path.PathFollower;
import org.usfirst.frc.team1806.robot.util.DriveSignal;
import org.usfirst.frc.team1806.robot.util.Lookahead;
import org.usfirst.frc.team1806.robot.util.NavX;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Twist2d;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI;

/**
 * The DrivetrainSubsystem deals with all of the drivetrain
 * code used on the robot.
 */
public class DriveTrainSubsystem implements Subsystem{

	public enum DriveStates{
		DRIVING, // Ya old normal dirivng
		CREEP, // Creep for percise movement
		VISION, // Vision tracking? //TODO do vision tracking lmao
		TURN_TO_HEADING, // Turn using PID
		DRIVE_TO_POSITION, // Drive to Position using SRX PID
		PATH_FOLLOWING,
		VELOCITY_SETPOINT,
		NOTHING // Used on init
	}
    public enum WantedDriveState{
		DRIVING,
		CREEP,
		VISION,
		TURN_TO_THETA,
		DRIVE_TO_POSITION
	}
    private static DriveTrainSubsystem mDriveTrainSubsystem = new DriveTrainSubsystem();
	private static final int kLowGearPositionControlSlot = 0;
	private static final int kHighGearVelocityControlSlot = 1;
	public static DriveTrainSubsystem getInstance() {
		return mDriveTrainSubsystem;
	}
    private static double inchesPerSecondToCountsPerTenthOfSecond(double inches_per_second) {
         return (inches_per_second / Constants.kDriveInchesPerCount) / 10; //TODO check times
     }
    private static double inchesPerSecondToRpm(double inches_per_second) {
        return inchesToRotations(inches_per_second) * 60;
    }
    private static double inchesToCounts(double inches) {
         return inches / Constants.kDriveInchesPerCount;
     }
    
    private static double inchesToRotations(double inches) {
        return inches / (Constants.kDriveWheelDiameterInches * Math.PI);
    }
    /**
     * Check if the drive talons are configured for position control
     */
    protected static boolean usesTalonPositionControl(DriveStates state) {
        if (state == DriveStates.DRIVE_TO_POSITION ||
                state == DriveStates.TURN_TO_HEADING) {
            return true;
        }
        return false;
    } 
    protected static boolean usesTalonVelocityControl(DriveStates state) {
        if (state == DriveStates.VELOCITY_SETPOINT || state == DriveStates.PATH_FOLLOWING) {
            return true;
        }
        return false;
    }
    //Initialize all of the drive motors
    private TalonSRX masterLeft, masterRight, leftA, leftC, rightA, rightC;
    private DoubleSolenoid shifter;
    private NavX navx;
    private PathFollower mPathFollower;
    private Rotation2d mTargetHeading = new Rotation2d();
    private boolean mIsOnTarget = false;
    //TODO:Remove these
    private int leftLowGearMaxVel = 0;
    
    
	private int rightLowGearMaxVel = 0;
	private int leftLastVel = 0;
	private int rightLastVel = 0;
	private long leftMaxAccel = 0;
	private long rightMaxAccel = 0;
	private int leftHighGearMaxVel = 0;
	private int rightHighGearMaxVel = 0;
	private double currentTimeStamp;
	private double lastTimeStamp;
	// This HashSet is used for PDP usage in our modified Talon code
	private HashSet<Integer> rightSidePDP = new HashSet<Integer>() {{
		int[] PDPValues = {13,14,15};
		add(PDPValues[0]);
		add(PDPValues[1]);
		add(PDPValues[2]);
	}};
	private HashSet<Integer> leftSidePDP = new HashSet<Integer>() {{
		int[] PDPValues = {0,1,2};
		add(PDPValues[0]);
		add(PDPValues[1]);
		add(PDPValues[2]);
	}};

	// State Control
	private DriveStates mDriveStates;
	
	private RobotState mRobotState = RobotState.getInstance();
	private Path mCurrentPath = null;
	private boolean mIsHighGear = false;
	
	private boolean mIsBrakeMode = false;
	
	private Loop mLoop = new Loop() {
		
		@Override
		public void onLoop(double timestamp) {
			// TODO Auto-generated method stub
			lastTimeStamp = currentTimeStamp;
			currentTimeStamp = timestamp;
			synchronized (DriveTrainSubsystem.this) {
				switch (mDriveStates) {
				case CREEP:
					return;
				case DRIVE_TO_POSITION:
					return;
				case DRIVING:
					return;
				case NOTHING:
					return;
				case PATH_FOLLOWING:
					if(mPathFollower != null) {
						updatePathFollower(timestamp);
					}
					return;
				case TURN_TO_HEADING:
						updateTurnToHeading(timestamp);
					return;
				case VELOCITY_SETPOINT:
					return;
				case VISION:
					return;
				default:
					return;
					
				}
			}

		}
		
		@Override
		public void onStart(double timestamp) {
			synchronized (DriveTrainSubsystem.this) {
				setOpenLoop(DriveSignal.NEUTRAL);
				setNeutralMode(false);
				navx.reset();
			}
		}
		
		@Override
		public void onStop(double timestamp) {
	        setOpenLoop(DriveSignal.NEUTRAL);
			
		}
	};
	public DriveTrainSubsystem() {
		//init the all of the motor controllers
		masterLeft = new TalonSRX(RobotMap.masterLeft);
		masterRight = new TalonSRX(RobotMap.masterRight);
				
		leftA = new TalonSRX(RobotMap.leftA);
		leftC = new TalonSRX(RobotMap.leftC);
		rightA = new TalonSRX(RobotMap.rightA);
		rightC = new TalonSRX(RobotMap.rightC);
		
		//Follow for right side 
		rightA.set(com.ctre.phoenix.motorcontrol.ControlMode.Follower, RobotMap.masterRight);
		rightC.set(com.ctre.phoenix.motorcontrol.ControlMode.Follower, RobotMap.masterRight);
		// Follow for left side
		leftA.set(com.ctre.phoenix.motorcontrol.ControlMode.Follower, RobotMap.masterLeft);
		leftC.set(com.ctre.phoenix.motorcontrol.ControlMode.Follower, RobotMap.masterLeft);
		
		//Set Encoders for each side of the talon
		masterLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		masterRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		
		
//		//Invert the left side
		leftA.setInverted(true);
		masterLeft.setInverted(true);
		leftC.setInverted(true);
		
		// init solenoids
		shifter = new DoubleSolenoid(RobotMap.shiftLow, RobotMap.shiftHigh);
		//init navx
		navx = new NavX(SPI.Port.kMXP);
		
		reloadGains();
		mDriveStates = DriveStates.NOTHING;
	}
	public void arcadeDrive(double power, double turn){
		leftDrive(power + turn); //verify signs
		rightDrive(power - turn);
	}
	private void configureTalonsForPositionControl() {
        if (usesTalonPositionControl(mDriveStates)) {
            // We entered a position control state.
            System.out.println("Configuring position control");
            masterRight.selectProfileSlot(kLowGearPositionControlSlot, 0);
            masterLeft.selectProfileSlot(kLowGearPositionControlSlot, 0);
            masterLeft.setIntegralAccumulator(0, 0, 10);
            masterRight.setIntegralAccumulator(0, 0, 10);
            setBrakeMode();
        } else {
        	System.out.println("Oh no! DIdn't set Position control");
        }
    }
	private void configureTalonsForSpeedControl() {
        if (!usesTalonVelocityControl(mDriveStates)) {
            // We entered a velocity control state.
            System.out.println("Configuring speed control");
            masterLeft.selectProfileSlot(kHighGearVelocityControlSlot, 0);
            masterRight.selectProfileSlot(kHighGearVelocityControlSlot, 0);
            setBrakeMode();
        }
    }
    public synchronized void forceDoneWithPath() {
        if (mDriveStates == DriveStates.PATH_FOLLOWING && mPathFollower != null) {
            mPathFollower.forceFinish();
        } else {
            System.out.println("Robot is not in path following mode");
        }
    }
    
     public synchronized Rotation2d getGyroAngle() {
        return navx.getYaw();
    }
    public double getLeftDistanceInches() {
         return masterLeft.getSelectedSensorPosition(0) * Constants.kDriveInchesPerCount;
     }

     public double getLeftVelocityInchesPerSec() {
         return masterLeft.getSelectedSensorVelocity(0) * Constants.kDriveInchesPerCount * 10;
     }
     public double getRightDistanceInches() {
         return masterRight.getSelectedSensorPosition(0) * Constants.kDriveInchesPerCount;
     }
     public double getRightVelocityInchesPerSec() {
         return masterRight.getSelectedSensorVelocity(0) * Constants.kDriveInchesPerCount * 10;
     }

     public boolean isCreeping() {
    	return mDriveStates == DriveStates.CREEP;
    }

     public synchronized boolean isDoneWithPath() {
        if (mDriveStates == DriveStates.PATH_FOLLOWING && mPathFollower != null) {
            return mPathFollower.isFinished();
        } else {
            System.out.println("Robot is not in path following mode");
            return true;
        }
    }

     public synchronized boolean isDoneWithTurn() {
        if (mDriveStates == DriveStates.TURN_TO_HEADING) {
            return mIsOnTarget;
        } else {
            System.out.println("Robot is not in turn to heading mode");
            return false;
        }
    }

     public boolean isHighGear() {
        return mIsHighGear;
    }

     public boolean isPositionControl(DriveStates state) {
    	if(state == DriveStates.DRIVE_TO_POSITION || 
    			state == DriveStates.TURN_TO_HEADING) {
    		return true;
    	} else {
    		return false;
    	}
     }
     public void leftDrive(double output) {
		masterLeft.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, output);
	}
     @Override
	public void outputToSmartDashboard() {
		SmartDashboard.putBoolean("HighGear?", isHighGear());
		SmartDashboard.putNumber("driveLeftPosition", getLeftDistanceInches());
		SmartDashboard.putNumber("driveRightPosition", getRightDistanceInches());
		SmartDashboard.putNumber("driveLeftVelocity", getLeftVelocityInchesPerSec());
		SmartDashboard.putNumber("driveRightVelocity", getRightVelocityInchesPerSec());
		 SmartDashboard.putNumber("Left Side", masterLeft.getSelectedSensorPosition(0));
		 SmartDashboard.putNumber("Right Side: ", masterRight.getSelectedSensorPosition(0));
		if(!isHighGear()) {
			if(masterLeft.getSelectedSensorVelocity(0) > leftLowGearMaxVel) {
				leftLowGearMaxVel = masterLeft.getSelectedSensorVelocity(0);
			}
			if(masterRight.getSelectedSensorVelocity(0) > rightLowGearMaxVel) {
				rightLowGearMaxVel = masterRight.getSelectedSensorVelocity(0);
			}
			double dt = (currentTimeStamp - lastTimeStamp);
			long currentLeftAccel = Math.round((Math.abs(masterLeft.getSelectedSensorVelocity(0)) - Math.abs(leftLastVel)) / dt);
			long currentRightAccel = Math.round((Math.abs(masterRight.getSelectedSensorVelocity(0)) - Math.abs(rightLastVel)) / dt);
			
			if(currentLeftAccel > leftMaxAccel) {
				leftMaxAccel = currentLeftAccel;
			}
			
			if(currentRightAccel > rightMaxAccel) {
				rightMaxAccel = currentRightAccel;
			}
		}
		else {
			if(masterLeft.getSelectedSensorVelocity(0) > leftHighGearMaxVel) {
				leftHighGearMaxVel = masterLeft.getSelectedSensorVelocity(0);
			}
			if(masterRight.getSelectedSensorVelocity(0) > rightHighGearMaxVel) {
				rightHighGearMaxVel = masterRight.getSelectedSensorVelocity(0);
			}
		}
		
		leftLastVel = masterLeft.getSelectedSensorVelocity(0);
        masterLeft.configGetParameter(ParamEnum.eProfileParamSlot_P, 0, 10);
		rightLastVel = masterRight.getSelectedSensorVelocity(0);	
		SmartDashboard.putNumber("leftMaxAccel", leftMaxAccel);
		SmartDashboard.putNumber("rightMaxAccel", rightMaxAccel);
		SmartDashboard.putNumber("leftLowGearMaxVel", leftLowGearMaxVel);
		SmartDashboard.putNumber("rightLowGearMaxVel", rightLowGearMaxVel);
		SmartDashboard.putNumber("leftHighGearMaxVel", leftHighGearMaxVel);
		SmartDashboard.putNumber("rightHighGearMaxVel", rightHighGearMaxVel);
		SmartDashboard.putString("Drive State", returnDriveState());
		SmartDashboard.putNumber("NavX", getGyroAngle().getDegrees());
		SmartDashboard.putBoolean("Are we in brake mode", mIsBrakeMode);

	}
     
     
     @Override
	public void registerEnabledLoops(Looper enabledLooper) {
		enabledLooper.register(mLoop);
		
	}
    public synchronized void reloadGains() {
    	reloadLowGearPositionGains();
    	reloadHighGearVelocityGains();
    }
    public synchronized void reloadHighGearPositionGainsForController(BaseMotorController motorController) {
		motorController.config_kP(kHighGearVelocityControlSlot, Constants.kDriveHighGearVelocityKp, Constants.kDriveTrainPIDSetTimeout);
		motorController.config_kI(kHighGearVelocityControlSlot, Constants.kDriveHighGearVelocityKi, Constants.kDriveTrainPIDSetTimeout);
		motorController.config_kD(kHighGearVelocityControlSlot, Constants.kDriveHighGearVelocityKd, Constants.kDriveTrainPIDSetTimeout);
		motorController.config_kF(kHighGearVelocityControlSlot, Constants.kDriveHighGearVelocityKf, Constants.kDriveTrainPIDSetTimeout);
		motorController.config_IntegralZone(kHighGearVelocityControlSlot, Constants.kDriveHighGearVelocityIZone, Constants.kDriveTrainPIDSetTimeout);
		motorController.configClosedloopRamp(Constants.kDriveHighGearVelocityRampRate, Constants.kDriveTrainPIDSetTimeout);
    }

    public synchronized void reloadHighGearVelocityGains() {
    	reloadHighGearPositionGainsForController(masterLeft);
    	reloadHighGearPositionGainsForController(masterRight);
    }
    public synchronized void reloadLowGearPositionGains() {
    	reloadLowGearPositionGainsForController(masterLeft);
    	reloadLowGearPositionGainsForController(masterRight);
    }
    public synchronized void reloadLowGearPositionGainsForController(BaseMotorController motorController) {
		motorController.config_kP(kLowGearPositionControlSlot, Constants.kDriveLowGearPositionKp, Constants.kDriveTrainPIDSetTimeout);
		motorController.config_kI(kLowGearPositionControlSlot, Constants.kDriveLowGearPositionKi, Constants.kDriveTrainPIDSetTimeout);
		motorController.config_kD(kLowGearPositionControlSlot, Constants.kDriveLowGearPositionKd, Constants.kDriveTrainPIDSetTimeout);
		motorController.config_kF(kLowGearPositionControlSlot, Constants.kDriveLowGearPositionKf, Constants.kDriveTrainPIDSetTimeout);
		motorController.config_IntegralZone(kLowGearPositionControlSlot, Constants.kDriveLowGearPositionIZone, Constants.kDriveTrainPIDSetTimeout);
		motorController.configMotionCruiseVelocity(kLowGearPositionControlSlot, Constants.kDriveLowGearMaxVelocity);
		motorController.configMotionAcceleration(Constants.kDriveLowGearMaxAccel, Constants.kDriveTrainPIDSetTimeout);
		motorController.configClosedloopRamp(Constants.kDriveLowGearPositionRampRate, Constants.kDriveTrainPIDSetTimeout);

    }
    public synchronized void resetNavx(){
    	navx.reset();
     }
    public synchronized void resetYaw(){
    	navx.zeroYaw();
     }
    public String returnDriveState() {
    	return mDriveStates.toString();
    }

    public void rightDrive(double output) {
		masterRight.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, output);
	}
    public void setBrakeMode() {
    	 //set for auto
    	 masterLeft.setNeutralMode(NeutralMode.Brake);
    	 masterRight.setNeutralMode(NeutralMode.Brake);
     }
    public void setCoastMode() {
    	 // set for driving
    	 masterLeft.setNeutralMode(NeutralMode.Coast);
    	 masterRight.setNeutralMode(NeutralMode.Coast);
     }
    public synchronized void setCreepMode(DriveSignal signal) {
    	if(mDriveStates != DriveStates.CREEP) {
    		mDriveStates = DriveStates.CREEP;
    		System.out.println("CREEPE");
    	}
    	masterLeft.set(ControlMode.PercentOutput, signal.getLeft() / 3);
    	masterRight.set(ControlMode.PercentOutput, signal.getRight() / 3);
    }

    //////
    public synchronized void setGyroAngle(Rotation2d angle) {
        navx.reset();
        navx.setAngleAdjustment(angle);
    }
    
    public synchronized void setHighGear(boolean wantsHighGear) {
        if (wantsHighGear != mIsHighGear) {
            mIsHighGear = wantsHighGear;
            shifter.set(wantsHighGear ? Value.kForward : Value.kReverse);
        }
    }
    
    /**
      * Sets the neutral mode for the drive train.
     * @param brake if 1, the drive train will go into brake mode, 0 will put it into coast mode
     */
    public synchronized void setNeutralMode(boolean brake) {
             mIsBrakeMode = brake;
             NeutralMode currentMode = brake?NeutralMode.Brake:NeutralMode.Coast;
             masterRight.setNeutralMode(currentMode);
             rightA.setNeutralMode(currentMode);
             rightC.setNeutralMode(currentMode);
             masterLeft.setNeutralMode(currentMode);
             leftA.setNeutralMode(currentMode);
             leftC.setNeutralMode(currentMode);
     }
    public synchronized void setOpenLoop(DriveSignal signal) {
         if (mDriveStates != DriveStates.DRIVING) {
             mDriveStates = DriveStates.DRIVING;
             setNeutralMode(false);
         }

         masterRight.set(ControlMode.PercentOutput, signal.getRight());
         masterLeft.set(ControlMode.PercentOutput, signal.getLeft());
     }

    public synchronized void setVelocitySetpoint(double left_inches_per_sec, double right_inches_per_sec) {
        configureTalonsForSpeedControl();
        mDriveStates = DriveStates.VELOCITY_SETPOINT;
        updateVelocitySetpoint(left_inches_per_sec, right_inches_per_sec);
    }
    public synchronized void setWantDrivePath(Path path, boolean reversed) {
        if (mCurrentPath != path || mDriveStates != DriveStates.PATH_FOLLOWING) {
        	System.out.println("Setting Path_Following");
            configureTalonsForSpeedControl();
            RobotState.getInstance().resetDistanceDriven();
            mPathFollower = new PathFollower(path, reversed,
                    new PathFollower.Parameters(
                            new Lookahead(Constants.kMinLookAhead, Constants.kMaxLookAhead,
                                    Constants.kMinLookAheadSpeed, Constants.kMaxLookAheadSpeed),
                            Constants.kInertiaSteeringGain, Constants.kPathFollowingProfileKp,
                            Constants.kPathFollowingProfileKi, Constants.kPathFollowingProfileKv,
                            Constants.kPathFollowingProfileKffv, Constants.kPathFollowingProfileKffa,
                            Constants.kPathFollowingMaxVel, Constants.kPathFollowingMaxAccel,
                            Constants.kPathFollowingGoalPosTolerance, Constants.kPathFollowingGoalVelTolerance,
                            Constants.kPathStopSteeringDistance));
            mDriveStates = DriveStates.PATH_FOLLOWING;
            mCurrentPath = path;
        } else {
        	System.out.println("setting velocity to 0");
            setVelocitySetpoint(0, 0);
        }
    }

    /**
     * Configures the drivebase to turn to a desired heading
     */
    public synchronized void setWantTurnToHeading(Rotation2d heading) {
        if (mDriveStates != DriveStates.TURN_TO_HEADING) {
            mDriveStates = DriveStates.TURN_TO_HEADING;
            configureTalonsForPositionControl();
            updatePositionSetpoint(getLeftDistanceInches(), getRightDistanceInches());
        }
        if (Math.abs(heading.inverse().rotateBy(mTargetHeading).getDegrees()) > 1E-3) {
            mTargetHeading = heading;
            mIsOnTarget = false;
        }
        setHighGear(false);
    }
    
    @Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
    
    public void stopDrive() {
		if(mDriveStates != DriveStates.DRIVING) {
			mDriveStates = DriveStates.DRIVING;
		}
		masterLeft.set(ControlMode.PercentOutput, 0);
		masterRight.set(ControlMode.PercentOutput, 0);
	}
    
    /**
     * Called periodically when the robot is in path following mode. Updates the path follower with the robots latest
     * pose, distance driven, and velocity, the updates the wheel velocity setpoints.
     */
    private void updatePathFollower(double timestamp) {
        RigidTransform2d robot_pose = mRobotState.getLatestFieldToVehicle().getValue();
        Twist2d command = mPathFollower.update(timestamp, robot_pose,
                RobotState.getInstance().getDistanceDriven(), RobotState.getInstance().getPredictedVelocity().dx);
        if (!mPathFollower.isFinished()) {
            Kinematics.DriveVelocity setpoint = Kinematics.inverseKinematics(command);
            updateVelocitySetpoint(setpoint.left, setpoint.right);
        } else {
            updateVelocitySetpoint(0, 0);
        }
    }
    
    private synchronized void updatePositionSetpoint(double left_position_inches, double right_position_inches) {
        if (usesTalonPositionControl(mDriveStates)) {
            masterLeft.set(ControlMode.Position, inchesToCounts(left_position_inches));
            masterRight.set(ControlMode.Position, inchesToCounts(right_position_inches));
        } else {
            System.out.println("Hit a bad position control state");
            masterLeft.set(ControlMode.PercentOutput,0);
            masterRight.set(ControlMode.PercentOutput, 0);
        }
    }
    private void updateTurnToHeading(double timestamp) {
        final Rotation2d field_to_robot = mRobotState.getLatestFieldToVehicle().getValue().getRotation();

        // Figure out the rotation necessary to turn to face the goal.
        final Rotation2d robot_to_target = field_to_robot.inverse().rotateBy(mTargetHeading);

        // Check if we are on target
        final double kGoalPosTolerance = 2; // degrees
        final double kGoalVelTolerance = 5.0; // inches per second
        if (Math.abs(robot_to_target.getDegrees()) < kGoalPosTolerance
                && Math.abs(getLeftVelocityInchesPerSec()) < kGoalVelTolerance
                && Math.abs(getRightVelocityInchesPerSec()) < kGoalVelTolerance) {
            // Use the current setpoint and base lock.
            mIsOnTarget = true;
            updatePositionSetpoint(getLeftDistanceInches(), getRightDistanceInches());
            return;
        }

        Kinematics.DriveVelocity wheel_delta = Kinematics
                .inverseKinematics(new Twist2d(0, 0, robot_to_target.getRadians()));
        updatePositionSetpoint(wheel_delta.left + getLeftDistanceInches(),
                wheel_delta.right + getRightDistanceInches());
    }
    
    private synchronized void updateVelocitySetpoint(double left_inches_per_sec, double right_inches_per_sec) {
        if (usesTalonVelocityControl(mDriveStates)) {
            final double max_desired = Math.max(Math.abs(left_inches_per_sec), Math.abs(right_inches_per_sec));
            final double scale = max_desired > Constants.kDriveHighGearMaxSetpoint
                    ? Constants.kDriveHighGearMaxSetpoint / max_desired : 1.0;
            masterLeft.set(ControlMode.Velocity, inchesPerSecondToCountsPerTenthOfSecond(left_inches_per_sec * scale));
            masterRight.set(ControlMode.Velocity, inchesPerSecondToCountsPerTenthOfSecond(right_inches_per_sec * scale));
            
           /* System.out.println("Left Side Velocity : "+ left_inches_per_sec+ "  " + 
            		"Right Side Veloctiy: "+ right_inches_per_sec);*/
      		

            SmartDashboard.putNumber("Left Side Velocity", masterLeft.getSelectedSensorVelocity(0));
            SmartDashboard.putNumber("Right Side Velocity", masterRight.getSelectedSensorVelocity(0));
        } else {
            System.out.println("Hit a bad velocity control state");
            masterLeft.set(ControlMode.PercentOutput, 0);
            masterRight.set(ControlMode.PercentOutput, 0);
        }
    }
    
    @Override
	public void zeroSensors() {
		System.out.println("Zeroing drivetrain sensors...");
   	 masterLeft.setSelectedSensorPosition(0, 0, Constants.kDriveTrainPIDSetTimeout);
   	 masterRight.setSelectedSensorPosition(0, 0, Constants.kDriveTrainPIDSetTimeout);
   	 navx.zeroYaw();		
   	 System.out.println("Drivetrain sensors zeroed!");
	}
	@Override
	public void writeToLog() {
		// TODO Auto-generated method stub
		
	}
}

