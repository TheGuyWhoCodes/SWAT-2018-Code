package org.usfirst.frc.team1806.robot.subsystems.superstructure;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1806.robot.Constants;
import org.usfirst.frc.team1806.robot.RobotMap;
import org.usfirst.frc.team1806.robot.loop.Loop;
import org.usfirst.frc.team1806.robot.loop.Looper;
import org.usfirst.frc.team1806.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team1806.robot.subsystems.Subsystem;

/**
 * The Cube Eater superstructure is the subsystem that allows us to manipulate both
 * our inner intake and outter intake, states are tracked in here. There will be multiple instances of 
 * the intakes in other classes bc we need to access them and manipulate it elsewhere
 * 
 * @see SnackManipulatorSuperStructure
 * @author SWAT
 */
public class CubeEaterSuperStructure implements Subsystem{
    static CubeEaterSuperStructure mInstance = null;
//    private final IntakeSubsystem mOuterIntake;
    private final IntakeSubsystem mInnerIntake;
    public static CubeEaterSuperStructure getInstance() {
        if (mInstance == null) {
            mInstance = new CubeEaterSuperStructure();
        }
        return mInstance;
    }
    public CubeEaterSuperStructure() {
//		mOuterIntake = new IntakeSubsystem(Constants.kOuterIntakeSpeed, RobotMap.rightOuterIntake, RobotMap.leftOuterIntake);
		mInnerIntake = new IntakeSubsystem(Constants.kInnerIntakeSpeed, RobotMap.rightInnerIntake, RobotMap.leftInnerIntake);
	}
    public enum IntakeStates {
    	IDLE, //  On startup
    	INTAKE, // Intaking
    	OUTTAKE, // Outtaking
		SPIT_OUT // Spitting out cube
    }
    private IntakeStates mIntakeStates = IntakeStates.IDLE;
    @Override
	public void writeToLog() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void outputToSmartDashboard() {
		// TODO Auto-generated method stub
		SmartDashboard.putString("Intake States", mIntakeStates.toString());
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

	public void stopAllIntake() {
		mIntakeStates = IntakeStates.IDLE;
		mInnerIntake.stopAllMotors();
//		mOuterIntake.stopAllMotors();
	}
	public void intaking(double power) {
		mIntakeStates = IntakeStates.INTAKE;
		mInnerIntake.intakeAtPower(power);
//		mOuterIntake.intaking();
	}
	public void outTaking(double power) {
		mIntakeStates = IntakeStates.OUTTAKE;
		mInnerIntake.outtaking(power);
//		mOuterIntake.outtaking(power);
	}
	public void innerOuttake(double power){
		mIntakeStates = IntakeStates.OUTTAKE;
		mInnerIntake.outtaking(power);
	}
	public void spitOutCube(boolean needsOuterIntake, double power){
		if(needsOuterIntake) {
			outTaking(power);
		} else {
			innerOuttake(power);
		}
	}

}
