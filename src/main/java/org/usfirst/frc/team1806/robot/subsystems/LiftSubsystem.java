package org.usfirst.frc.team1806.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1806.robot.Constants;
import org.usfirst.frc.team1806.robot.RobotMap;
import org.usfirst.frc.team1806.robot.loop.Loop;
import org.usfirst.frc.team1806.robot.loop.Looper;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Whew, this subsystem is for our liftactions, we will later implement this into a superstructure
 * so we can interact with our intake for doing things like shooting!
 */
public class LiftSubsystem implements LiftInterface {
	public enum CubeLiftStates{
		POSITION_CONTROL,
		RESET_TO_BOTTOM,
		RESET_TO_TOP,
		HOLD_POSITION,
		MANUAL_CONTROL,
		IDLE
	}
	public enum CubePosition{
		SWITCH,
		NEUTRAL_SCALE,
		HIGH_SCALE,
		DROP_OFF,
		BOTTOM_LIMIT,
        TOP_LIMIT,
	}
	private TalonSRX cubeMaster, cubeSlave; //gotta have the power
	private DigitalInput bottomLimit, topLimit, cubeDetector;
	private boolean isBrakeMode = false;
	private boolean mIsOnTarget = false;
	private boolean mHaveCube = false;
	private boolean mIsManualMode = false;
	private int mLiftWantedPosition = 0;
	private CubeLiftStates mCubeLiftStates;
	private CubePosition mCubePosition;
	public LiftSubsystem() {
		cubeMaster = new TalonSRX(RobotMap.cubeMaster);
		cubeSlave = new TalonSRX(RobotMap.cubeSlave);
		cubeMaster.setInverted(true);
		cubeSlave.setInverted(true);
		cubeSlave.follow(cubeMaster);
		cubeMaster.configContinuousCurrentLimit(130, 10);
		bottomLimit = new DigitalInput(RobotMap.cubeBottomLimit);
		topLimit = new DigitalInput(RobotMap.cubeTopLimit);
		cubeDetector = new DigitalInput(RobotMap.cubeDetector);
		mCubeLiftStates = CubeLiftStates.IDLE;
		mCubePosition = CubePosition.BOTTOM_LIMIT;
		cubeMaster.configPeakOutputReverse(.2, 10);
		reloadGains();
	}

	@Override
	public void outputToSmartDashboard() {
        SmartDashboard.putString("Lift State: ", returnLiftStates().toString());
        SmartDashboard.putString("Lift Position", returnCubePosition().toString());
		SmartDashboard.putNumber("Lift Encoder Position", cubeMaster.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Lift Power Sending", cubeMaster.getMotorOutputPercent());
	}

	@Override
	public void stop() {
        setLiftIdle();
	}

	@Override
	public void zeroSensors() {
		cubeMaster.setSelectedSensorPosition(0,0,10);
	}
    public void zeroSensorsAtTop(){
        cubeMaster.setSelectedSensorPosition(Constants.kCubeTopLimitSwitchPosition, 0, 10);
        if(mCubeLiftStates != CubeLiftStates.POSITION_CONTROL) {
            mCubeLiftStates = CubeLiftStates.POSITION_CONTROL;
        }
    }
    public void zeroSensorsAtBottom(){
        cubeMaster.setSelectedSensorPosition(0, 0, 10);
        if(mCubeLiftStates != CubeLiftStates.POSITION_CONTROL) {
            mCubeLiftStates = CubeLiftStates.POSITION_CONTROL;
        }
    }
	@Override
	public void registerEnabledLoops(Looper enabledLooper) {
        enabledLooper.register(new Loop() {
            @Override
            public void onStop(double timestamp) {
                setLiftIdle();
            }

            @Override
            public void onStart(double timestamp) {
				setLiftIdle();
            }

            @Override
            public void onLoop(double timestamp) {
            	updateCubeDetector();
            	if(isAtPosition()){
            		mCubeLiftStates = CubeLiftStates.HOLD_POSITION;
				}
				cubePositionLoop();
				cubeLiftStateLoop();
            }

            private void cubePositionLoop(){
            	switch (mCubePosition){
					case SWITCH:
						return;
					case NEUTRAL_SCALE:
						return;
					case HIGH_SCALE:
						return;
					case DROP_OFF:
						return;
					case BOTTOM_LIMIT:
						return;
					case TOP_LIMIT:
						return;
				}
			}

			private void cubeLiftStateLoop(){
				switch(mCubeLiftStates) {
					case POSITION_CONTROL:
						updatePositionControl();
						return;
					case RESET_TO_BOTTOM:
						mIsOnTarget = false;
						//resetToBottom();
						return;
					case RESET_TO_TOP:
						mIsOnTarget = false;
						//resetToTop();
						return;
					case HOLD_POSITION:
						holdPosition();
						return;
					case MANUAL_CONTROL:
						return;
					case IDLE:
						return;
					default:
						return;

				}
			}
        });
	}

	@Override
	public void writeToLog() {

	}

	@Override
	public void goToSetpoint(int setpoint) {
		mLiftWantedPosition = setpoint;
		cubeMaster.set(ControlMode.Position, mLiftWantedPosition);
		System.out.println(mLiftWantedPosition + "  " + isReadyForSetpoint());
	}

	@Override
	public void zeroOnBottom() {
		// TODO Auto-generated method stub
		if(mCubeLiftStates != CubeLiftStates.RESET_TO_BOTTOM) {
			mCubeLiftStates = CubeLiftStates.RESET_TO_BOTTOM;
		}
	}

	@Override
	public void goToTop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getHeightInInches() {
		return getHeightInCounts() / Constants.kCountsPerInch;
	}

	@Override
	public int getHeightInCounts() {
		return cubeMaster.getSelectedSensorPosition(0);
	}

	@Override
	public boolean isOnTarget() {
		return mIsOnTarget;
	}
	public void setBrakeMode(){
		cubeMaster.setNeutralMode(NeutralMode.Brake);
		cubeSlave.setNeutralMode(NeutralMode.Brake);
		isBrakeMode = true;
		
	}
	public void setCoastMode() {
		cubeMaster.setNeutralMode(NeutralMode.Coast);
		cubeSlave.setNeutralMode(NeutralMode.Coast);
	}
	public boolean isInBrakeMode() {
		return isBrakeMode;
	}
	public void reloadGains() {
		cubeMaster.config_kP(Constants.kPositionControlPIDSlot, Constants.kCubePositionkP, Constants.kCubePositionPIDTimeout);
		cubeMaster.config_kI(Constants.kPositionControlPIDSlot, Constants.kCubePositionkI, Constants.kCubePositionPIDTimeout);
		cubeMaster.config_kD(Constants.kPositionControlPIDSlot, Constants.kCubePositionkD, Constants.kCubePositionPIDTimeout);
		cubeMaster.config_kF(Constants.kPositionControlPIDSlot, Constants.kCubePositionkF, Constants.kCubePositionPIDTimeout);
		cubeMaster.config_IntegralZone(Constants.kPositionControlPIDSlot, Constants.kCubePositionIZone, Constants.kCubePositionPIDTimeout);
		cubeMaster.configClosedloopRamp(Constants.kCubePositionRampRate, Constants.kCubePositionPIDTimeout);
	}
	
	public void resetToBottom() {
		if(!bottomLimit.get() || Math.abs(cubeMaster.getSelectedSensorPosition(0)) < Constants.kBottomLimitTolerance) {
		    if(mCubeLiftStates != CubeLiftStates.RESET_TO_BOTTOM){
		        mCubeLiftStates = CubeLiftStates.RESET_TO_BOTTOM;
		        mCubePosition = CubePosition.BOTTOM_LIMIT;
            }
			cubeMaster.set(ControlMode.Position, 0);
		} else {
//			zeroSensorsAtBottom();
		}
	}
	
	public void resetToTop() {
        if(!topLimit.get()) {
            if(mCubeLiftStates != CubeLiftStates.RESET_TO_TOP){
                mCubeLiftStates = CubeLiftStates.RESET_TO_TOP;
				mCubePosition = CubePosition.TOP_LIMIT;
			}
            cubeMaster.set(ControlMode.PercentOutput, Constants.kCubeMoveToLimitSwitchSpeed);
        } else {
            zeroSensorsAtTop();
        }
	}

	/**
	 * @return
	 * Returns whether or not the liftactions is ready to be held at position for a cube to be deposited
	 */
	public boolean isAtPosition() {
		return Math.abs(mLiftWantedPosition - cubeMaster.getSelectedSensorPosition(0)) < Constants.kCubePositionTolerance &&
				Math.abs(cubeMaster.getSelectedSensorVelocity(0)) < Constants.kCubeVelocityTolerance;
	}
	/**
	 * 
	 * @return
	 * returns current state of cube 
	 */
	public CubeLiftStates returnLiftStates() {
		return mCubeLiftStates;
	}

    /**
     * @return
     * returns the position of where the liftactions is eg: moving, scale
     */
	public CubePosition returnCubePosition() {
		return mCubePosition;
	}

    /**
     * Used to stop the manipulator from running. mostly ran on stop or when first setting the liftactions up
     */
	public void setLiftIdle(){
	    if(mCubeLiftStates != CubeLiftStates.IDLE){
	        mCubeLiftStates = CubeLiftStates.IDLE;
        }
        cubeMaster.set(ControlMode.PercentOutput, 0);
    }

    /**
     * Sets up the robot to accept position setpoints
     */
    public void updatePositionControl(){
	    if(mCubeLiftStates != CubeLiftStates.POSITION_CONTROL){
	        mCubeLiftStates = CubeLiftStates.POSITION_CONTROL;
        }
        setBrakeMode();
    }

    public void goToHighScaleSetpoint(){
    	updatePositionControl();
    	if(isReadyForSetpoint()){
			goToSetpoint(Constants.kHighScaleEncoderCount);
		}
    }
    public void goToSwitchSetpoint(){
		updatePositionControl();
		if(isReadyForSetpoint()){
			goToSetpoint(Constants.kSwitchEncoderCount);
		}
    }
    public void goToDropOffSetpoint(){
		updatePositionControl();
		if(isReadyForSetpoint()){
			goToSetpoint(Constants.kDropOffEncoderCount);
		}
    }
    public void goToNeutralScaleSetpoint(){
		updatePositionControl();
		if(isReadyForSetpoint()){
			goToSetpoint(Constants.kNeutralScaleEncoderCount);
		}
	}
	public boolean doWeHaveCube(){
    	//return true;
    	return mHaveCube;
	}
	private void updateCubeDetector(){
    	mHaveCube = cubeDetector.get();
	}
	private boolean isReadyForSetpoint(){
    	return !mIsManualMode && isCurrentModesReady() && doWeHaveCube();
	}
	private boolean isCurrentModesReady(){
    	return mCubeLiftStates == CubeLiftStates.POSITION_CONTROL ||
				mCubeLiftStates == CubeLiftStates.IDLE ||
				mCubeLiftStates == CubeLiftStates.HOLD_POSITION;
	}
	public int returnLiftPosition(){
    	return cubeMaster.getSelectedSensorPosition(0);
	}
	public boolean needsBothIntakes(){
    	return returnLiftPosition() < Constants.kCubeSpitOutNeedsOuterIntake || mCubePosition == CubePosition.BOTTOM_LIMIT;
	}
	public void manualMode(double power){
    	cubeMaster.set(ControlMode.PercentOutput, power);
	}
	public void setupForManualMode(){

	}

	/**
	 * Used to hold the cube when it is ready to be spat out
	 */
	public void holdPosition(){
    	cubeMaster.set(ControlMode.PercentOutput, Constants.kCubeHoldPercentOutput);
	}
	public int returnWantedPosition(){
		return mLiftWantedPosition;
	}
}
