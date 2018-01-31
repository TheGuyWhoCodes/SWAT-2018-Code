/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1806.robot;

import org.usfirst.frc.team1806.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team1806.robot.subsystems.DriveTrainSubsystem;
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
	private SnackManipulatorSuperStructure mSnackManipulator = SnackManipulatorSuperStructure.getInstance();
	private Latch shiftingLatch = new Latch();
	private Latch cubeManualMode = new Latch();
	public void runCommands(){
		synchronized (mDriveTrainSubsystem) {
			if(dc.getRightTrigger() > .2) {
				mDriveTrainSubsystem.setCreepMode(mCheesyDriveHelper.cheesyDrive(
						dc.getLeftJoyY(), dc.getRightJoyX(),dc.getButtonRB() , mDriveTrainSubsystem.isHighGear()));		
			}else {
				mDriveTrainSubsystem.setOpenLoop(mCheesyDriveHelper.cheesyDrive(
						dc.getLeftJoyY(), dc.getRightJoyX(),dc.getButtonRB() , mDriveTrainSubsystem.isHighGear()));	
			}
			mDriveTrainSubsystem.setHighGear(shiftingLatch.update(dc.getButtonLB()));
			if(dc.getButtonA()) {
				mSnackManipulator.goToBottom();
			} else if(dc.getButtonB()){
				mSnackManipulator.goToSwitchSetpoint();
			} else if(dc.getButtonY()){
				mSnackManipulator.goToHighScaleSetpoint();
			} else if(dc.getButtonX()){
				mSnackManipulator.goToNeutralScaleSetpoint();
			} else if(cubeManualMode.update(oc.getButtonA())){
				mSnackManipulator.goToManualMode(CheesyDriveHelper.handleDeadband(oc.getLeftJoyY(), .2));
			}
			if(Math.abs(dc.getLeftTrigger()) > .2){
				mSnackManipulator.intakeCube();
			} else if(Math.abs(oc.getLeftTrigger()) > .2){
				mSnackManipulator.spitOutCube();
			} else {
				mSnackManipulator.stopIntakeMotors();
			}
			if(oc.getRightTrigger() > .2){
				if(oc.getButtonY()){
					mClimberSubsystem.liftClimberAtPower(oc.getRightTrigger(), oc.getButtonY());
				}
			} else {

			}
			if(oc.getLeftTrigger() > .2){
				if(oc.getButtonY()){
					mClimberSubsystem.climbAtPower(oc.getLeftTrigger(), oc.getButtonY());
				}
			} else {

			}
		}
	}
	
	public void setDriverRumble(double value){
		dc.setRumble(RumbleType.kLeftRumble, value);
		dc.setRumble(RumbleType.kRightRumble, value);
		//joelyoloyilyiyi
	}
	public void setOperatorRumble(){
		dc.setRumble(RumbleType.kLeftRumble, 1);
		dc.setRumble(RumbleType.kRightRumble, 1);
	}
	public void stopRumble(){
		dc.setRumble(RumbleType.kLeftRumble, 0);
		dc.setRumble(RumbleType.kRightRumble, 0);
		
		oc.setRumble(RumbleType.kLeftRumble, 0);
		oc.setRumble(RumbleType.kRightRumble, 0);
	}
}
