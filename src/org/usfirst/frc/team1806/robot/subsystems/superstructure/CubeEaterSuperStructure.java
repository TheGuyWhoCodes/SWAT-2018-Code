package org.usfirst.frc.team1806.robot.subsystems.superstructure;

import org.usfirst.frc.team1806.robot.RobotMap;
import org.usfirst.frc.team1806.robot.loop.Loop;
import org.usfirst.frc.team1806.robot.loop.Looper;
import org.usfirst.frc.team1806.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team1806.robot.subsystems.Subsystem;

public class CubeEaterSuperStructure implements Subsystem{
	
    static CubeEaterSuperStructure mInstance = null;
    private final IntakeSubsystem mOuterIntake = new IntakeSubsystem(.5, RobotMap.rightOuterIntake, RobotMap.leftOuterIntake);
    private final IntakeSubsystem mInnerIntake = new IntakeSubsystem(.5, RobotMap.rightInnerIntake, RobotMap.leftInnerIntake);
    public static CubeEaterSuperStructure getInstance() {
        if (mInstance == null) {
            mInstance = new CubeEaterSuperStructure();
        }
        return mInstance;
    }
    public enum IntakeStates {
    	IDLE, //  On startup
    	INTAKE, // Intaking
    	OUTTAKE // Outtaking
    }
    public enum WantedStates {
    	IDLE, 
    	INTAKE,		//ya know b
    	OUTTAKE
    }
    private IntakeStates mIntakeStates = IntakeStates.IDLE;
    private WantedStates mWantedStates = WantedStates.IDLE;
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
	private Loop mLoop = new Loop() {
		@Override
		public void onStart(double timestamp) {
			synchronized (CubeEaterSuperStructure.this) {
				
			}			
		}
        private double mWantStateChangeStartTime;
		@Override
		public void onStop(double timestamp) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onLoop(double timestamp) {
			synchronized (CubeEaterSuperStructure.this) {
				switch(mIntakeStates) {
				case IDLE:
					return;
				case INTAKE:
					return;
				case OUTTAKE:
					return;
				default:
					break;
				}
			}
		}
	};
	public void stopAllIntake() {
		mIntakeStates = IntakeStates.IDLE;
		mInnerIntake.stopAllMotors();
		mOuterIntake.stopAllMotors();
	}
	public void intaking() {
		mIntakeStates = IntakeStates.INTAKE;
		mInnerIntake.intaking();
		mOuterIntake.intaking();
	}
	public void outTaking() {
		mIntakeStates = IntakeStates.OUTTAKE;
		mInnerIntake.outtaking();
		mOuterIntake.outtaking();
	}
}
