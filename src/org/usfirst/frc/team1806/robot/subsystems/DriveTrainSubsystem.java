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

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.SPI;

/**
 * The DrivetrainSubsystem deals with all of the drivetrain
 * code used on the robot.
 */
public class DriveTrainSubsystem extends Subsystem{

	private static DriveTrainSubsystem mDriveTrainSubsystem = new DriveTrainSubsystem();
    private static final int kLowGearPositionControlSlot = 0;
    private static final int kHighGearVelocityControlSlot = 1;
	//Initialize all of the drive motors
	private TalonSRX masterLeft, masterRight, leftA, leftC, rightA, rightC;
	private DoubleSolenoid shifter;
	private NavX navx;
    private PathFollower mPathFollower;
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
	public static DriveTrainSubsystem getInstance() {
		return mDriveTrainSubsystem;
	}
	public enum DriveStates{
		DRIVING, // Ya old normal dirivng
		CREEP, // Creep for percise movement
		VISION, // Vision tracking? //TODO do vision tracking lmao
		TURN_TO_THETA, // Turn using PID
		DRIVE_TO_POSITION, // Drive to Position using SRX PID
		PATH_FOLLOWING,
		VELOCITY_SETPOINT
	}
	public enum WantedDriveState{
		DRIVING,
		CREEP,
		VISION,
		TURN_TO_THETA,
		DRIVE_TO_POSITION
	}
	// State Control
	private DriveStates mDriveStates;
	private RobotState mRobotState = RobotState.getInstance();
	private Path mCurrentPath = null;
	private boolean mIsHighGear = false;
	private boolean mIsBrakeMode = false;
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
		//Invert the left side
		leftA.setInverted(true);
		masterLeft.setInverted(true);
		leftC.setInverted(true);
		
		// init solenoids
		shifter = new DoubleSolenoid(RobotMap.shiftLow, RobotMap.shiftHigh);
		//init navx
		navx = new NavX(SPI.Port.kMXP);

	}

	@Override
	public void outputToSmartDashboard() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void zeroSensors() {
   	 masterLeft.set(ControlMode.Position, 0);
   	 navx.zeroYaw();		
	}
	@Override
	public void registerEnabledLoops(Looper enabledLooper) {
		enabledLooper.register(mLoop);
		
	}
	
	private Loop mLoop = new Loop() {
		
		@Override
		public void onStop(double timestamp) {
	        setOpenLoop(DriveSignal.NEUTRAL);
			
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
		public void onLoop(double timestamp) {
			// TODO Auto-generated method stub
			
		}
	};
	
	//////
    public synchronized void setGyroAngle(Rotation2d angle) {
        navx.reset();
        navx.setAngleAdjustment(angle);
    }
	public void leftDrive(double output) {
		masterLeft.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, output);
	}
	public void rightDrive(double output) {
		masterRight.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, output);
	}
	public void arcadeDrive(double power, double turn){
		leftDrive(power + turn); //verify signs
		rightDrive(power - turn);
	}
    public synchronized void resetYaw(){
    	navx.zeroYaw();
     }
    
     public synchronized void resetNavx(){
    	navx.reset();
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
     private static double rotationsToInches(double rotations) {
         return rotations * (Constants.kDriveWheelDiameterInches * Math.PI);
     }
     private static double rpmToInchesPerSecond(double rpm) {
         return rotationsToInches(rpm) / 60;
     }

     private static double inchesToRotations(double inches) {
         return inches / (Constants.kDriveWheelDiameterInches * Math.PI);
     }

     private static double inchesPerSecondToRpm(double inches_per_second) {
         return inchesToRotations(inches_per_second) * 60;
     }

     public double getLeftDistanceInches() {
         return rotationsToInches(masterLeft.getSelectedSensorPosition(0));
     }

     public double getRightDistanceInches() {
         return rotationsToInches(masterRight.getSelectedSensorPosition(0));
     }

     public double getLeftVelocityInchesPerSec() {
         return rpmToInchesPerSecond(masterLeft.getSelectedSensorPosition(0));
     }

     public double getRightVelocityInchesPerSec() {
         return rpmToInchesPerSecond(masterRight.getSelectedSensorPosition(0));
     }
     public boolean isPositionControl(DriveStates state) {
    	if(state == DriveStates.DRIVE_TO_POSITION || 
    			state == DriveStates.TURN_TO_THETA) {
    		return true;
    	} else {
    		return false;
    	}
     }
     public synchronized void setOpenLoop(DriveSignal signal) {
         if (mDriveStates != DriveStates.DRIVING) {
             mDriveStates = DriveStates.DRIVING;
             setNeutralMode(false);
         }

         masterRight.set(ControlMode.PercentOutput, signal.getRight());
         masterLeft.set(ControlMode.PercentOutput, signal.getLeft());
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
    public synchronized void setCreepMode(DriveSignal signal) {
    	if(mDriveStates != DriveStates.CREEP) {
    		mDriveStates = DriveStates.CREEP;
    	}
    	masterLeft.set(ControlMode.PercentOutput, signal.getLeft() / 3);
    	masterRight.set(ControlMode.PercentOutput, signal.getRight() / 3);
    }
    public boolean isHighGear() {
        return mIsHighGear;
    }

    public synchronized void setHighGear(boolean wantsHighGear) {
        if (wantsHighGear != mIsHighGear) {
            mIsHighGear = wantsHighGear;
            shifter.set(wantsHighGear ? Value.kForward : Value.kReverse);
        }
    }
    public boolean isCreeping() {
    	return mDriveStates == DriveStates.CREEP;
    }
    public synchronized void setWantDrivePath(Path path, boolean reversed) {
        if (mCurrentPath != path || mDriveStates != DriveStates.PATH_FOLLOWING) {
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
            setVelocitySetpoint(0, 0);
        }
    }
    public synchronized void setVelocitySetpoint(double left_inches_per_sec, double right_inches_per_sec) {
        configureTalonsForSpeedControl();
        mDriveStates = DriveStates.VELOCITY_SETPOINT;
        updateVelocitySetpoint(left_inches_per_sec, right_inches_per_sec);
    }
    /**
     * Check if the drive talons are configured for position control
     */
    protected static boolean usesTalonPositionControl(DriveStates state) {
        if (state == DriveStates.DRIVE_TO_POSITION ||
                state == DriveStates.TURN_TO_THETA) {
            return true;
        }
        return false;
    }
    /**
     * Configures talons for position control
     */
//    private void configureTalonsForPositionControl() {
//        if (!usesTalonPositionControl(mDriveStates)) {
//            // We entered a position control state.
//            masterLeft.changeControlMode(CANTalon.TalonControlMode.MotionMagic);
//            masterLeft.setNominalClosedLoopVoltage(12.0);
//            masterLeft.setProfile(kLowGearPositionControlSlot);
//            masterLeft.configNominalOutputVoltage(Constants.kDriveLowGearNominalOutput,
//                    -Constants.kDriveLowGearNominalOutput);
//            mRightMaster.changeControlMode(CANTalon.TalonControlMode.MotionMagic);
//            mRightMaster.setNominalClosedLoopVoltage(12.0);
//            mRightMaster.setProfile(kLowGearPositionControlSlot);
//            mRightMaster.configNominalOutputVoltage(Constants.kDriveLowGearNominalOutput,
//                    -Constants.kDriveLowGearNominalOutput);
//            setBrakeMode();
//        }
//    } //TODO Fix plz
    private void configureTalonsForSpeedControl() {
        if (!usesTalonVelocityControl(mDriveStates)) {
            // We entered a velocity control state.
            masterLeft.selectProfileSlot(kHighGearVelocityControlSlot, 0);
            masterRight.selectProfileSlot(kHighGearVelocityControlSlot, 0);
            setBrakeMode();
            //TOOD fix this plz, fix brake/ coast mode thing
        }
    }
    protected static boolean usesTalonVelocityControl(DriveStates state) {
        if (state == DriveStates.VELOCITY_SETPOINT || state == DriveStates.PATH_FOLLOWING) {
            return true;
        }
        return false;
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
    private synchronized void updateVelocitySetpoint(double left_inches_per_sec, double right_inches_per_sec) {
        if (usesTalonVelocityControl(mDriveStates)) {
            final double max_desired = Math.max(Math.abs(left_inches_per_sec), Math.abs(right_inches_per_sec));
            final double scale = max_desired > Constants.kDriveHighGearMaxSetpoint
                    ? Constants.kDriveHighGearMaxSetpoint / max_desired : 1.0;
            masterLeft.set(ControlMode.Velocity, inchesPerSecondToRpm(left_inches_per_sec * scale));
            masterRight.set(ControlMode.Velocity, inchesPerSecondToRpm(right_inches_per_sec * scale));
        } else {
            System.out.println("Hit a bad velocity control state");
            masterLeft.set(ControlMode.PercentOutput, 0);
            masterRight.set(ControlMode.PercentOutput, 0);
        }
    }
}

