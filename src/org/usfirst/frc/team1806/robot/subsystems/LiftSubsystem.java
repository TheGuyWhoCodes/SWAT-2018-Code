package org.usfirst.frc.team1806.robot.subsystems;

import org.usfirst.frc.team1806.robot.Constants;
import org.usfirst.frc.team1806.robot.RobotMap;
import org.usfirst.frc.team1806.robot.loop.Loop;
import org.usfirst.frc.team1806.robot.loop.Looper;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;

public class LiftSubsystem implements LiftInterface {
	
	
	public enum CubeLiftStates{
		POSITION_CONTROL,
		RESET_TO_BOTTOM,
		RESET_TO_TOP,
		MANUAL_CONTROL,
		IDLE
	}
	
	
	private TalonSRX cubeMaster, cubeSlave; //gotta have the power
	private DigitalInput bottomLimit, topLimit;
	private static LiftSubsystem mLiftSubsystem = new LiftSubsystem();
	private boolean isBrakeMode = false;
	private boolean mIsOnTarget = false;
	
	private CubeLiftStates mCubeLiftStates;
	public LiftSubsystem() {
		cubeMaster = new TalonSRX(RobotMap.cubeMaster);
		cubeSlave = new TalonSRX(RobotMap.cubeSlave);
		
		cubeSlave.follow(cubeMaster);
		
		bottomLimit = new DigitalInput(RobotMap.cubeBottomLimit);
		topLimit = new DigitalInput(RobotMap.cubeTopLimit);
	}
	public static LiftSubsystem getInstance() {
		return mLiftSubsystem;
	}
	private Loop mLoop = new Loop() {
		
		@Override
		public void onStop(double timestamp) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onStart(double timestamp) {
			setTalonsForPositionControl();
		}
		
		@Override
		public void onLoop(double timestamp) {
			switch(mCubeLiftStates) {
			case POSITION_CONTROL:
				updatePositionControl();
				return;
			case RESET_TO_BOTTOM:
				mIsOnTarget = false;
				updateResetToBottom();
				return;
			case RESET_TO_TOP:
				mIsOnTarget = false;
				updateResetToTop();
				return;
			default:
				return;
			
			}
			
		}
	};
		
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

	}

	@Override
	public void registerEnabledLoops(Looper enabledLooper) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeToLog() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToSetpoint(double setpoint) {
		// TODO Auto-generated method stub
		
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
	public void setTalonsForPositionControl() {
		setBrakeMode();
	}
	public void reloadGains() {
		cubeMaster.config_kP(Constants.kPositionControlPIDSlot, Constants.kCubePositionkP, Constants.kCubePositionPIDTimeout);
		cubeMaster.config_kI(Constants.kPositionControlPIDSlot, Constants.kCubePositionkI, Constants.kCubePositionPIDTimeout);
		cubeMaster.config_kD(Constants.kPositionControlPIDSlot, Constants.kCubePositionkD, Constants.kCubePositionPIDTimeout);
		cubeMaster.config_kF(Constants.kPositionControlPIDSlot, Constants.kCubePositionkF, Constants.kCubePositionPIDTimeout);
		cubeMaster.config_IntegralZone(Constants.kPositionControlPIDSlot, Constants.kCubePositionIZone, Constants.kCubePositionPIDTimeout);
		cubeMaster.configClosedloopRamp(Constants.kCubePositionRampRate, Constants.kCubePositionPIDTimeout);
	}
	public void updatePositionControl() {
		
	}
	
	public void updateResetToBottom() {
		if(!bottomLimit.get()) {
			cubeMaster.set(ControlMode.PercentOutput, -Constants.kCubeMoveToLimitSwitchSpeed);
		} else {
			zeroSensors();
		}
	}
	
	public void updateResetToTop() {
		
	}
}
