package org.usfirst.frc.team1806.robot.subsystems;

import org.usfirst.frc.team1806.robot.RobotMap;
import org.usfirst.frc.team1806.robot.loop.Loop;
import org.usfirst.frc.team1806.robot.loop.Looper;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class IntakeSubsystem implements Subsystem{
	private TalonSRX leftOuterIntake, rightOuterIntake;
	private double mIntakingSpeed = 0;

	private Loop mLooper = new Loop() {
		
		@Override
		public void onStop(double timestamp) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onStart(double timestamp) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onLoop(double timestamp) {
			// TODO Auto-generated method stub
			
		}
	};
	public IntakeSubsystem(double intakingSpeed, int rightCAN, int leftCAN) {
		leftOuterIntake = new TalonSRX(rightCAN);
		rightOuterIntake = new TalonSRX(leftCAN);

		rightOuterIntake.setInverted(false);
		leftOuterIntake.setInverted(true);
		mIntakingSpeed = intakingSpeed;
	}
	@Override
	public void writeToLog() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerEnabledLoops(Looper enabledLooper) {
		// TODO Auto-generated method stub
		
	}
	public void intaking(){
		leftOuterIntake.set(ControlMode.PercentOutput, mIntakingSpeed);
		rightOuterIntake.set(ControlMode.PercentOutput, mIntakingSpeed);
	}
	public void outtaking(double power){
		leftOuterIntake.set(ControlMode.PercentOutput, -power);
		rightOuterIntake.set(ControlMode.PercentOutput, -power);
	}
	public void stopAllMotors() {
		leftOuterIntake.set(ControlMode.PercentOutput,0);
		rightOuterIntake.set(ControlMode.PercentOutput, 0);
	}
}
