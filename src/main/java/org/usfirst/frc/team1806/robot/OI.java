/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1806.robot;

import edu.wpi.first.wpilibj.CircularBuffer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team1806.robot.auto.actions.controller.VibrateControllerForTime;
import org.usfirst.frc.team1806.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team1806.robot.subsystems.DriveTrainSubsystem;
import org.usfirst.frc.team1806.robot.subsystems.LiftSubsystem;
import org.usfirst.frc.team1806.robot.subsystems.SubsystemManager;
import org.usfirst.frc.team1806.robot.subsystems.superstructure.SnackManipulatorSuperStructure;
import org.usfirst.frc.team1806.robot.util.CheesyDriveHelper;
import org.usfirst.frc.team1806.robot.util.Latch;
import org.usfirst.frc.team1806.robot.util.XboxController;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private DriveTrainSubsystem mDriveTrainSubsystem = DriveTrainSubsystem.getInstance();
	private ClimberSubsystem mClimberSubsystem = ClimberSubsystem.getInstance();
    private CheesyDriveHelper mCheesyDriveHelper = new CheesyDriveHelper();
	private XboxController dc = new XboxController(0);
	private XboxController oc = new XboxController(1);
	private XboxController autoController = new XboxController(2);
	private SnackManipulatorSuperStructure mSnackManipulator = SnackManipulatorSuperStructure.getInstance();
	private Latch shiftingLatch = new Latch();
	private Latch cubeManualMode = new Latch();
	private Latch teleOpHold = new Latch();
	private Latch cubeOverride = new Latch();
	private Latch autoInTeleOp = new Latch();
	private boolean didWeHaveACube = false;
	private boolean wereWeManual = false;
	private boolean didWeBumpCubeUp = false;
	private boolean didWeBumpCubeDown = false;
	public void runCommands(){
		synchronized (mDriveTrainSubsystem) {
			if(dc.getRightTrigger() > .2 || SnackManipulatorSuperStructure.getInstance().returnLiftHeight() > Constants.kCreepModeLiftHeight) {
				mDriveTrainSubsystem.setCreepMode(mCheesyDriveHelper.cheesyDrive(
						dc.getLeftJoyY(), dc.getRightJoyX(),dc.getButtonRB() , mDriveTrainSubsystem.isHighGear()));
			}else {
				mDriveTrainSubsystem.setOpenLoop(mCheesyDriveHelper.cheesyDrive(
						dc.getLeftJoyY(), dc.getRightJoyX(),dc.getButtonRB() , mDriveTrainSubsystem.isHighGear()));
			}
		}

		synchronized (mSnackManipulator) {
			if ((mSnackManipulator.doWeGotACube()) && !didWeHaveACube) {
				dc.rumble(1, 1, 1);
				oc.rumble(1, 1, 1);
			}
			if (Math.abs(dc.getLeftTrigger()) > .2) {
				SnackManipulatorSuperStructure.getInstance().intakeCube(Constants.kInnerIntakeSpeed, Constants.kInnerIntakeSpeed);
			} else if (oc.getButtonLB()) {
				SnackManipulatorSuperStructure.getInstance().operaterIntake(1, 1);
			} else if (dc.getButtonRB()) {
				SnackManipulatorSuperStructure.getInstance().spitOutCube(.67);
			} else if (dc.getPOVUp()) {
				mSnackManipulator.spitOutCube(.9);
			} else if (dc.getPOVDown()) {
				mSnackManipulator.spitOutCube(.36);
			} else if (dc.getPOVRight()) {
				mSnackManipulator.spitOutCube(.5);
			} else if (dc.getPOVLeft()) {
				mSnackManipulator.spitOutCube(.4);
			}  else {
				mSnackManipulator.stopIntakeMotors();
			}

			if (cubeManualMode.update(oc.getButtonA())) {
				if(oc.getButtonB()){
					mSnackManipulator.goToManualMode(Constants.kCubeHoldPercentOutput);
				} else {
					mSnackManipulator.goToManualMode(CheesyDriveHelper.handleDeadband(oc.getLeftJoyY(), .2));
				}
			} else if (!cubeManualMode.returnStatus() && wereWeManual) {
				mSnackManipulator.setIdleMode();
			} else if (dc.getButtonA()) {
				mSnackManipulator.goToBottom();
			} else if (dc.getButtonB()) {
				mSnackManipulator.goToSwitchSetpoint();
			} else if (dc.getButtonY()) {
				mSnackManipulator.goToHighScaleSetpoint();
			} else if (dc.getButtonX()) {
				mSnackManipulator.goToNeutralScaleSetpoint();
			} else if (dc.getButtonLB()) {
				mSnackManipulator.goToWinningScaleSetpoint();
			} else if (oc.getButtonStart()) {
				mSnackManipulator.resetLiftSensors();
			} else if((oc.getPOVUp() || dc.getButtonStart()) && !didWeBumpCubeUp){
				SnackManipulatorSuperStructure.getInstance().bumpSetpointUp();
			} else if((oc.getPOVDown() || dc.getButtonBack()) && !didWeBumpCubeDown){
				SnackManipulatorSuperStructure.getInstance().bumpSetpointDown();
			}
			didWeBumpCubeUp = dc.getButtonStart() || oc.getPOVUp();
			didWeBumpCubeDown = dc.getButtonBack() || oc.getPOVDown();
			wereWeManual = cubeManualMode.returnStatus();
			didWeHaveACube = mSnackManipulator.doWeGotACube();
		}

		if(oc.getButtonRB()){
			System.out.println("holding climber");
			mClimberSubsystem.liftClimberAtPower(.1, oc.getButtonY());
			mClimberSubsystem.stopClimbing();
		} else if(oc.getButtonX()){
			mClimberSubsystem.liftClimberAtPower(-.5, oc.getButtonY());
			mClimberSubsystem.stopClimbing();
		}else if(oc.getRightTrigger() > .2){
			mClimberSubsystem.liftClimberAtPower(.8, oc.getButtonY());
			mClimberSubsystem.stopClimbing();
		} else{
			mClimberSubsystem.stopLifting();
		}

		if(oc.getLeftTrigger() > .2){
			mClimberSubsystem.climbAtPower(oc.getLeftTrigger(), oc.getButtonY());
			mClimberSubsystem.stopLifting();
		} else {
			mClimberSubsystem.stopClimbing();
		}
		if(Constants.enableAutoInTeleOp){
			autoInTeleOp.update(autoController.getButtonStart());
		}

	}
	public void resetAutoLatch(){
	    autoInTeleOp.resetLatch();
    }
	public void autoRunCommands(){
		autoInTeleOp.update(autoController.getButtonBack());
		if(autoController.getButtonA()){
			SnackManipulatorSuperStructure.getInstance().overrideCubeDetector();
		}
	}
	public boolean autoInTeleOpOn(){
		return autoInTeleOp.returnStatus();
	}
}
