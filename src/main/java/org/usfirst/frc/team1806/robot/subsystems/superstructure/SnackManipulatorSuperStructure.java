package org.usfirst.frc.team1806.robot.subsystems.superstructure;

import org.usfirst.frc.team1806.robot.Constants;
import org.usfirst.frc.team1806.robot.loop.Loop;
import org.usfirst.frc.team1806.robot.loop.Looper;
import org.usfirst.frc.team1806.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team1806.robot.subsystems.LiftSubsystem;
import org.usfirst.frc.team1806.robot.subsystems.Subsystem;

/**
 * The Snack Manipulator Super Structure deals with all of the different subsystems
 * that deal with the powercube. These subsystems include Intake and Lift
 * 
 * Please enjoy the name of the super structure
 * @author SWAT
 *
 */
public class SnackManipulatorSuperStructure implements Subsystem{
	
	//We need to grab instances of these to manipulate each one here

	private LiftSubsystem mLiftSubsystem;
	private CubeEaterSuperStructure mIntakeSubsystem;
	private static SnackManipulatorSuperStructure mSnack ;
	public SnackManipulatorSuperStructure(){
		mLiftSubsystem = new LiftSubsystem();
		mIntakeSubsystem = new CubeEaterSuperStructure();
	}
	public static SnackManipulatorSuperStructure getInstance() {
		if(mSnack == null) {
			mSnack = new SnackManipulatorSuperStructure();
		}
		return mSnack;
	}



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
			
		}
	};
	@Override
	public void writeToLog() {
		// TODO Auto-generated method stub
		mLiftSubsystem.writeToLog();
		mIntakeSubsystem.writeToLog();
	}

	@Override
	public void outputToSmartDashboard() {
		mIntakeSubsystem.outputToSmartDashboard();
		mLiftSubsystem.outputToSmartDashboard();
	}

	@Override
	public void stop() {
		mIntakeSubsystem.stop();
		mLiftSubsystem.stop();
	}

	@Override
	public void zeroSensors() {
		mIntakeSubsystem.zeroSensors();
		mLiftSubsystem.zeroSensors();
	}

	@Override
	public void registerEnabledLoops(Looper enabledLooper) {
		// TODO Auto-generated method stub
		mIntakeSubsystem.registerEnabledLoops(enabledLooper);
		mLiftSubsystem.registerEnabledLoops(enabledLooper);
		
	}
	public void goToHighScaleSetpoint(){
		mLiftSubsystem.goToHighScaleSetpoint();
//		mIntakeSubsystem.stopAllIntake();
	}
	public void goToSwitchSetpoint(){
		mLiftSubsystem.goToSwitchSetpoint();
//		mIntakeSubsystem.stopAllIntake();
	}
	public void goToDropOffSetpoint(){
		mLiftSubsystem.goToDropOffSetpoint();
//		mIntakeSubsystem.stopAllIntake();
	}
	public void goToNeutralScaleSetpoint(){
		mLiftSubsystem.goToNeutralScaleSetpoint();
//		mIntakeSubsystem.stopAllIntake();
	}
	public void spitOutCube(){
		mIntakeSubsystem.spitOutCube(mLiftSubsystem.needsBothIntakes());
	}
	public void intakeCube(){
		mIntakeSubsystem.intaking();
	}
	public void goToManualMode(double power){
		mLiftSubsystem.manualMode(power);
	}
	public void goToBottom(){
		mLiftSubsystem.resetToBottom();
	}
	public boolean isAtPosition(){
			return mLiftSubsystem.isAtPosition();
	}
	public void stopIntakeMotors(){
		mIntakeSubsystem.stopAllIntake();
	}
	public boolean doWeGotACube(){
		return mLiftSubsystem.doWeHaveCube();
	}
}
