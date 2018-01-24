package org.usfirst.frc.team1806.robot.subsystems;

import org.usfirst.frc.team1806.robot.RobotMap;
import org.usfirst.frc.team1806.robot.loop.Looper;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class IntakeSubsystem implements Subsystem{
	private TalonSRX leftIntake, rightIntake;
	private DoubleSolenoid leftSolenoid, rightSolenoid;
	
	private static IntakeSubsystem mIntakeSubsystem = new IntakeSubsystem();
	public IntakeSubsystem() {
		leftSolenoid = new DoubleSolenoid(RobotMap.leftIntakeHigh, RobotMap.leftIntakeLow);
		rightSolenoid = new DoubleSolenoid(RobotMap.rightIntakeHigh, RobotMap.rightIntakeLow);
	}
	
	public static IntakeSubsystem getInstance() {
		return mIntakeSubsystem;
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

}
