/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1806.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.Arrays;

import org.usfirst.frc.team1806.robot.auto.AutoModeExecuter;
import org.usfirst.frc.team1806.robot.auto.AutoModeSelector;
import org.usfirst.frc.team1806.robot.auto.BluePathAdapter;
import org.usfirst.frc.team1806.robot.auto.modes.QualMode;
import org.usfirst.frc.team1806.robot.loop.Looper;
import org.usfirst.frc.team1806.robot.path.motion.RobotStateEstimator;
import org.usfirst.frc.team1806.robot.subsystems.*;
import org.usfirst.frc.team1806.robot.subsystems.superstructure.SnackManipulatorSuperStructure;
import org.usfirst.frc.team1806.robot.util.CrashTracker;
import org.usfirst.frc.team1806.robot.util.DriveSignal;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	private DriveTrainSubsystem mDrive = DriveTrainSubsystem.getInstance();
	public static Talon meme1, meme2;
    private RobotState mRobotState = RobotState.getInstance();
    private AutoModeExecuter mAutoModeExecuter = null;
	public static OI m_oi;
	public static PowerDistributionPanel powerDistributionPanel;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	private final SubsystemManager mSubsystemManager = new SubsystemManager(
			Arrays.asList(DriveTrainSubsystem.getInstance(), SnackManipulatorSuperStructure.getInstance(), ClimberSubsystem.getInstance()));
    private Looper mEnabledLooper = new Looper();
    public static boolean isCompBot = true;
    /*
     * LLL
     * RRR
     * LRL
     * RLR
     */
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		zeroAllSensors();
        mEnabledLooper.register(RobotStateEstimator.getInstance());
        mSubsystemManager.registerEnabledLoops(mEnabledLooper);
		powerDistributionPanel = new PowerDistributionPanel();
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
        mAutoModeExecuter = null;
        mAutoModeExecuter = new AutoModeExecuter();
        mAutoModeExecuter.setAutoMode(new QualMode());
        mDrive.setCoastMode();
        AutoModeSelector.initAutoModeSelector();
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e){
        	System.out.println(e);
		}
		BluePathAdapter.initPaths();
	}


	@Override
	public void disabledInit() {
		mEnabledLooper.stop();
        if(mAutoModeExecuter != null) {
            mAutoModeExecuter.stop();
        }
	}

	@Override
	public void disabledPeriodic() {
		allPeriodic(); AutoModeSelector.initAutoModeSelector();
	}


	@Override
	public void autonomousInit() {
		try {
			zeroAllSensors();
			CrashTracker.logAutoInit();
            System.out.println("Auto start timestamp: " + Timer.getFPGATimestamp());
            if (mAutoModeExecuter != null) {
                mAutoModeExecuter.stop(); 
            }
//            mDrive.setHighGear(true);
            mDrive.setBrakeMode();
            
            mEnabledLooper.start();
			mAutoModeExecuter.setAutoMode(AutoModeSelector.getSelectedAutoMode());
            mAutoModeExecuter.start();
		} catch (Throwable t) {
            CrashTracker.logThrowableCrash(t);
            throw t;
		}

	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		allPeriodic();
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
        mEnabledLooper.start();
        if(mAutoModeExecuter != null) {
            mAutoModeExecuter.stop();
        }
        mDrive.setOpenLoop(DriveSignal.NEUTRAL);
        mDrive.setNeutralMode(false);
	}


	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		m_oi.runCommands();
		allPeriodic();
	}

	@Override
	public void testPeriodic() {
	}
	public void zeroAllSensors() {
//		System.out.println("Zeroing all Sensors..");
        mSubsystemManager.zeroSensors();
        mRobotState.reset(Timer.getFPGATimestamp(), new RigidTransform2d());
//        System.out.print("All Sensors zeroed!");

	}
	public synchronized void allPeriodic() {
		mSubsystemManager.outputToSmartDashboard();
		mRobotState.outputToSmartDashboard();
		mEnabledLooper.outputToSmartDashboard();
		SmartDashboard.putString("Auto We Are Running", AutoModeSelector.returnNameOfSelectedAuto());
		SmartDashboard.putNumber("PDP Total", powerDistributionPanel.getTotalCurrent());
	}
}
