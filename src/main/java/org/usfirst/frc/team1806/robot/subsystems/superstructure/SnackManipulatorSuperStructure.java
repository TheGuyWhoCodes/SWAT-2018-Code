package org.usfirst.frc.team1806.robot.subsystems.superstructure;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1806.robot.Constants;
import org.usfirst.frc.team1806.robot.Robot;
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
public class SnackManipulatorSuperStructure implements Subsystem {

    //We need to grab instances of these to manipulate each one here
    PowerDistributionPanel powerDistributionPanel;
    private LiftSubsystem mLiftSubsystem;
    private CubeEaterSuperStructure mIntakeSubsystem;
    private static SnackManipulatorSuperStructure mSnack;

    public SnackManipulatorSuperStructure() {
        mLiftSubsystem = new LiftSubsystem();
        mIntakeSubsystem = new CubeEaterSuperStructure();
        powerDistributionPanel = new PowerDistributionPanel();
    }

    public static SnackManipulatorSuperStructure getInstance() {
        if (mSnack == null) {
            mSnack = new SnackManipulatorSuperStructure();
        }
        return mSnack;
    }


    private Loop mLooper = new Loop() {

        @Override
        public void onStop(double timestamp) {
            // TODO Auto-generated method stub
            mIntakeSubsystem.stopAllIntake();
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
        SmartDashboard.putNumber("Intake PDP Total: ", powerDistributionPanel.getCurrent(5) + powerDistributionPanel.getCurrent(6));

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

    public void goToHighScaleSetpoint() {
        mLiftSubsystem.goToHighScaleSetpoint();
//		mIntakeSubsystem.stopAllIntake();
    }

    public void goToSwitchSetpoint() {
        mLiftSubsystem.goToSwitchSetpoint();
//		mIntakeSubsystem.stopAllIntake();
    }
    public void goToWinningScaleSetpoint(){
        mLiftSubsystem.goToWinningScale();
    }
    public void goToTeleopHold(){
        mLiftSubsystem.goToTeleOpHold();
    }
    public void goToExchangeSetpoint() {
        mLiftSubsystem.goToExchangeHeight();
    }

    public void goToDropOffSetpoint() {
        mLiftSubsystem.goToDropOffSetpoint();
//		mIntakeSubsystem.stopAllIntake();
    }

    public void goToNeutralScaleSetpoint() {
        mLiftSubsystem.goToNeutralScaleSetpoint();
//		mIntakeSubsystem.stopAllIntake();
    }

    public synchronized void spitOutCube(double power) {
        mIntakeSubsystem.spitOutCube(mLiftSubsystem.needsBothIntakes(), (power * 12) / Robot.powerDistributionPanel.getVoltage());
    }

    public void intakeCube(double leftPower, double rightPower) {
        double mRightPower = 1; //Math.abs(Math.sin(7 * Timer.getFPGATimestamp()));
        double mLeftPower = 1;//Math.abs(Math.sin(7 * Timer.getFPGATimestamp()));
        mIntakeSubsystem.intaking(leftPower * mLeftPower, rightPower * mRightPower);
    }
    public void operaterIntake(double leftPower, double rightPower){
        mIntakeSubsystem.operaterIntake();
    }
    public void goToManualMode(double power) {
        mLiftSubsystem.manualMode(power);
    }

    public void goToBottom() {
        mLiftSubsystem.resetToBottom();
    }

    public boolean isAtPosition() {
        return mLiftSubsystem.isAtPosition();
    }

    public void stopIntakeMotors() {
        mIntakeSubsystem.stopAllIntake();
    }

    public boolean doWeGotACube() {
        return mLiftSubsystem.doWeHaveCube();
    }

    public boolean areWeAtBottom() {
        return mLiftSubsystem.areWeAtBottomLimit();
    }

    public int returnLiftHeight() {
        return mLiftSubsystem.returnLiftHeight();
    }

    public synchronized void resetLiftSensors() {
        mLiftSubsystem.zeroSensorsAtBottom();
    }

    /**
     * Increases the setpoint of the lift based on a constant
     * @return true if setting the setpoint was successful
     *          false if setting the setpoint wasn't successful
     * @see LiftSubsystem
     */
    public boolean bumpSetpointUp() {
        return mLiftSubsystem.bumpSetpointUp();
    }
    /**
     * Decreases the setpoint of the lift based on a constant
     * @return true if setting the setpoint was successful
     *          false if setting the setpoint wasn't successful
     * @see LiftSubsystem
     */
    public boolean bumpSetpointDown() {
        return mLiftSubsystem.bumpSetpointDown();
    }

    public void intakeRightSide(double power){
        mIntakeSubsystem.intakeRightSide(power);
    }
    public void intakeLeftSide(double power){
        mIntakeSubsystem.intakeLeftSide(power);
    }
    public void setIdleMode(){
        mLiftSubsystem.setLiftIdle();
    }

}

