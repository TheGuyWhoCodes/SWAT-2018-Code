package org.usfirst.frc.team1806.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import org.usfirst.frc.team1806.robot.RobotMap;
import org.usfirst.frc.team1806.robot.loop.Loop;
import org.usfirst.frc.team1806.robot.loop.Looper;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import java.sql.Driver;

public class ClimberSubsystem implements Subsystem {
	
	private TalonSRX upMotor, leftDown, rightDown;
	private static ClimberSubsystem mClimberSubsystem = new ClimberSubsystem();
	private DriverStation mDriverStation = DriverStation.getInstance();
	public enum ClimberStates {
		MOVING_UP,
		PULLING_DOWN,
		IDLE
	}
	private ClimberStates mClimberStates;
	public ClimberSubsystem() {
		upMotor = new TalonSRX(RobotMap.upMotor);
		leftDown = new TalonSRX(RobotMap.downLeft);
		rightDown = new TalonSRX(RobotMap.downRight);
		
		rightDown.follow(leftDown); // set to follow mode
		setBrakeMode();
		mClimberStates = ClimberStates.IDLE;
	}
	public static ClimberSubsystem getInstance() {
		return mClimberSubsystem;
	}
	private Loop mLooper = new Loop() {

		@Override
		public void onStart(double timestamp) {
			setBrakeMode();
		}

		@Override
		public void onLoop(double timestamp) {
			synchronized (ClimberSubsystem.this) {
				switch(mClimberStates) {
				case MOVING_UP:
					return;
				case PULLING_DOWN:
					return;
				default:
					return;
				
				}
			}
		}

		@Override
		public void onStop(double timestamp) {

		}
		
	};
	@Override
	public void outputToSmartDashboard() {
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		setCoastMode();
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
	public void setBrakeMode() {
		upMotor.setNeutralMode(NeutralMode.Brake);
		leftDown.setNeutralMode(NeutralMode.Brake);
		rightDown.setNeutralMode(NeutralMode.Brake);
	}
	public void setCoastMode() {
		upMotor.setNeutralMode(NeutralMode.Coast);
		leftDown.setNeutralMode(NeutralMode.Coast);
		rightDown.setNeutralMode(NeutralMode.Coast);
	}
	/**
	 * Used to pull down on the bar when properly lined up 
	 * @param power
	 * Power to send to the two CIMS
	 */
	public void climbAtPower(double power, boolean overrideTime) {
		if(mClimberStates != ClimberStates.PULLING_DOWN){
			mClimberStates = ClimberStates.PULLING_DOWN;
		}

		if(mDriverStation.isOperatorControl() && mDriverStation.getMatchTime() < 45){
			leftDown.set(ControlMode.PercentOutput, power);
		} else if(overrideTime) {
			leftDown.set(ControlMode.PercentOutput, power);
		}
		//right is in follower
	}
	/**
	 * Used to move the climber up to start climbing
	 * @param power
	 * Power to send to the 775 pro to drive it up
	 */
	public void liftClimberAtPower(double power, boolean overrideTime) {
		if(mClimberStates != ClimberStates.MOVING_UP){
			mClimberStates = ClimberStates.MOVING_UP;
		}
		if(mDriverStation.isOperatorControl() && mDriverStation.getMatchTime() < 45) {
			upMotor.set(ControlMode.PercentOutput, power);
		} else if(overrideTime){
			upMotor.set(ControlMode.PercentOutput, power);
		}
	}
	public boolean canClimb(){
		return DriverStation.getInstance().isOperatorControl() && DriverStation.getInstance().getMatchNumber() > 10;
	}
}
