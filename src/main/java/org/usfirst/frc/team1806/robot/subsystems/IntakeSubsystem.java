package org.usfirst.frc.team1806.robot.subsystems;

import edu.wpi.first.wpilibj.CircularBuffer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.RobotMap;
import org.usfirst.frc.team1806.robot.loop.Loop;
import org.usfirst.frc.team1806.robot.loop.Looper;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import org.usfirst.frc.team1806.robot.subsystems.superstructure.SnackManipulatorSuperStructure;

public class IntakeSubsystem implements Subsystem{
	private TalonSRX leftOuterIntake, rightOuterIntake;
	private double mIntakingSpeed = 0;
	Timer timer, stopperTimer;
	double stopTime = .3; // Seconds to stop the intake for
	boolean hasStopped = false;
	PowerDistributionPanel powerDistributionPanel;
	CircularBuffer intakeCircularBuffer;
	double circularBufferTotal = 0;
	int wantedSize = 30;
	double currentThreshold = 10;
	double baseLine = 3;
	double totalCurrent;
	private Loop mLooper = new Loop() {
		
		@Override
		public void onStop(double timestamp) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onStart(double timestamp) {
			stopperTimer.reset();
			stopperTimer.start();
		}
		
		@Override
		public void onLoop(double timestamp) {
			// TODO Auto-generated method stub
			
		}
	};
	public IntakeSubsystem(double intakingSpeed, int rightCAN, int leftCAN) {
		leftOuterIntake = new TalonSRX(rightCAN);
		rightOuterIntake = new TalonSRX(leftCAN);
		intakeCircularBuffer = new CircularBuffer(wantedSize);
		timer = new Timer();
		stopperTimer = new Timer();
		rightOuterIntake.setInverted(true);
		leftOuterIntake.setInverted(false);
		mIntakingSpeed = intakingSpeed;
	}
	@Override
	public void writeToLog() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void outputToSmartDashboard() {
		SmartDashboard.putNumber("Total Intake Current", circularBufferTotal);
		SmartDashboard.putNumber("Average Intake Current", circularBufferTotal / wantedSize);
		SmartDashboard.putBoolean("Are we over Intake Threshold", circularBufferTotal / wantedSize >= currentThreshold);
	}

	@Override
	public void stop() {
		hasStopped = false;
		timer.reset();
		timer.stop();
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
		circularBufferTotal = 0;
		//  double totalCurrent = Robot.powerDistributionPanel.getCurrent(5) + Robot.powerDistributionPanel.getCurrent(6);
		double totalCurrent = Robot.powerDistributionPanel.getCurrent(6) + Robot.powerDistributionPanel.getCurrent(7);
		intakeCircularBuffer.addFirst(totalCurrent);
		for(int i=0; i < wantedSize ; i++){
			circularBufferTotal += intakeCircularBuffer.get(i);
		}
		if(circularBufferTotal / wantedSize >= currentThreshold && !hasStopped){
			hasStopped = true;
			timer.reset();
			timer.start();
		}
		if(hasStopped && (timer.get() < stopTime)){
			System.out.println("stop time is doing it!");
			SnackManipulatorSuperStructure.getInstance().intakeCube(0, 0);
		} else if(stopperTimer.get() % 1.1 > 1.0){
			SnackManipulatorSuperStructure.getInstance().intakeCube(0, 0);
		} else if(hasStopped && timer.get() > stopTime) {
			timer.reset();
			timer.stop();
			hasStopped = false;
		} else{
			SnackManipulatorSuperStructure.getInstance().intakeCube(1, 1);
		}
		SmartDashboard.putNumber("Total Intake Current", totalCurrent);
		SmartDashboard.putNumber("Average Intake Current", circularBufferTotal / wantedSize);
		SmartDashboard.putBoolean("Are we over Intake Threshold", circularBufferTotal / wantedSize >= currentThreshold);
	}
	public void intakeAtPower(double leftPower, double rightPower){
		leftOuterIntake.set(ControlMode.PercentOutput, leftPower);
		rightOuterIntake.set(ControlMode.PercentOutput, rightPower);
	}
	public void outtaking(double power){
		leftOuterIntake.set(ControlMode.PercentOutput, -power);
		rightOuterIntake.set(ControlMode.PercentOutput, -power);
	}
	public void stopAllMotors() {
		leftOuterIntake.set(ControlMode.PercentOutput,0);
		hasStopped = false;
		timer.reset();
		timer.stop();
		rightOuterIntake.set(ControlMode.PercentOutput, 0);
	}
	public void intakeRightSide(double power){
		rightOuterIntake.set(ControlMode.PercentOutput, power);
	}
	public void intakeLeftSide(double power){
		leftOuterIntake.set(ControlMode.PercentOutput, power);
	}
}
