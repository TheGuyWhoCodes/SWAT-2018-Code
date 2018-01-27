package org.usfirst.frc.team1806.robot.subsystems.superstructure;

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
	
	private LiftSubsystem mLiftSubsystem = new LiftSubsystem().getInstance();
	private CubeEaterSuperStructure mIntakeSubsystem = new CubeEaterSuperStructure().getInstance();
	
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
			// TODO make these actually do something
			mLiftSubsystem.stop();
			mIntakeSubsystem.stopAllIntake();
			
		}
	};
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
	/**
	 * When we reach the point switch / scale / drop off we will spit out the cube right??
	 */
	public void spitOutCubes() {
//		if(mLiftSubsystem.returnCubePosition() == mLiftSubsystem.)
	}
}
