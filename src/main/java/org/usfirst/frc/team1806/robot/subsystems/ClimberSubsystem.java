package org.usfirst.frc.team1806.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1806.robot.Constants;
import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.RobotMap;
import org.usfirst.frc.team1806.robot.loop.Loop;
import org.usfirst.frc.team1806.robot.loop.Looper;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ClimberSubsystem implements Subsystem {
	private static int maxCurrentDraw = 90;
	private TalonSRX upMotor, downA, downB, downC;
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
		downA = new TalonSRX(RobotMap.downA);
		downB = new TalonSRX(RobotMap.downB);
		downC = new TalonSRX(RobotMap.downC);
		downB.configContinuousCurrentLimit(maxCurrentDraw,10);
		downA.configContinuousCurrentLimit(maxCurrentDraw,10);
		downC.configContinuousCurrentLimit(maxCurrentDraw,10);
		downB.follow(downA); // set to follow mode
		downC.follow(downA); // set to follow mode
		setBrakeMode();
		downB.setInverted(true);
		downC.setInverted(true);
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
		SmartDashboard.putNumber("Climb CIM 1: ", downA.getOutputCurrent());
		SmartDashboard.putNumber("Climb CIM 2: ", downB.getOutputCurrent());
		SmartDashboard.putNumber("Climb CIM 3: ", downC.getOutputCurrent());
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
		downA.setNeutralMode(NeutralMode.Brake);
		downB.setNeutralMode(NeutralMode.Brake);
		downC.setNeutralMode(NeutralMode.Brake);
	}
	public void setCoastMode() {
		upMotor.setNeutralMode(NeutralMode.Coast);
		downA.setNeutralMode(NeutralMode.Coast);
		downB.setNeutralMode(NeutralMode.Coast);
		downC.setNeutralMode(NeutralMode.Coast);
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
		SmartDashboard.putNumber("Climber Power: ", power);
		if(mDriverStation.isOperatorControl() && mDriverStation.getMatchTime() < 45){
			downA.set(ControlMode.PercentOutput, power);

		} else if(overrideTime) {
			downA.set(ControlMode.PercentOutput, power);
		} else {
			downA.set(ControlMode.PercentOutput, 0);
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
		if(Robot.powerDistributionPanel.getCurrent(Constants.climberPDPPort) >= Constants.climberPDPCutOff){
			upMotor.set(ControlMode.PercentOutput, 0);
		} else if(mDriverStation.isOperatorControl() && mDriverStation.getMatchTime() < 45) {
			upMotor.set(ControlMode.PercentOutput, power);
		} else if(overrideTime){
			upMotor.set(ControlMode.PercentOutput, power);
		} else {
			upMotor.set(ControlMode.PercentOutput, 0);
		}
	}
	public void stopClimbing(){
		downA.set(ControlMode.PercentOutput, 0);

	}
	public void stopLifting(){
		upMotor.set(ControlMode.PercentOutput, 0);
	}
	public boolean canClimb(){
		return DriverStation.getInstance().isOperatorControl() && DriverStation.getInstance().getMatchNumber() > 10;
	}
}
