/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1806.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.Arrays;

import org.usfirst.frc.team1806.robot.auto.AutoModeExecuter;
import org.usfirst.frc.team1806.robot.auto.AutoModeSelector;
import org.usfirst.frc.team1806.robot.auto.modes.LeftSideScaleAuto;
import org.usfirst.frc.team1806.robot.auto.modes.RightSideScaleAuto;
import org.usfirst.frc.team1806.robot.loop.Looper;
import org.usfirst.frc.team1806.robot.path.motion.RobotStateEstimator;
import org.usfirst.frc.team1806.robot.subsystems.DriveTrainSubsystem;
import org.usfirst.frc.team1806.robot.subsystems.LiftSubsystem;
import org.usfirst.frc.team1806.robot.subsystems.SubsystemManager;
import org.usfirst.frc.team1806.robot.util.CrashTracker;
import org.usfirst.frc.team1806.robot.util.DriveSignal;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

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
	private LiftSubsystem mCubeLift = LiftSubsystem.getInstance();
    private RobotState mRobotState = RobotState.getInstance();
    private AutoModeExecuter mAutoModeExecuter = null;
	public static OI m_oi;
	public static PowerDistributionPanel powerDistributionPanel;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	private final SubsystemManager mSubsystemManager = new SubsystemManager(
			Arrays.asList(DriveTrainSubsystem.getInstance(), LiftSubsystem.getInstance()));
    private Looper mEnabledLooper = new Looper();
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		meme1 = new Talon(0);
		meme2 = new Talon(1);
		zeroAllSensors();
        mSubsystemManager.registerEnabledLoops(mEnabledLooper);
		powerDistributionPanel = new PowerDistributionPanel();
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
//		AutoModeSelector.initAutoModeSelector();
        mAutoModeExecuter = null;
        mAutoModeExecuter = new AutoModeExecuter();
        mAutoModeExecuter.setAutoMode(new RightSideScaleAuto());
        mEnabledLooper.register(RobotStateEstimator.getInstance());
        mDrive.setCoastMode();
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
		allPeriodic();
		meme1.set(0);
		meme2.set(0);
	}


	@Override
	public void autonomousInit() {
		try {
	        CrashTracker.logAutoInit();
            System.out.println("Auto start timestamp: " + Timer.getFPGATimestamp());
            if (mAutoModeExecuter != null) {
                mAutoModeExecuter.stop(); 
            }
            mDrive.setHighGear(true);
            mDrive.setBrakeMode();
            
            zeroAllSensors();
            mEnabledLooper.start();

            mAutoModeExecuter.start();
            mCubeLift.zeroOnBottom();
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
		System.out.println("Zeroing all Sensors..");
        mRobotState.reset(Timer.getFPGATimestamp(), new RigidTransform2d());
        mSubsystemManager.zeroSensors();
        System.out.print("All Sensors zeroed!");
	}
	public synchronized void allPeriodic() {
		mSubsystemManager.outputToSmartDashboard();
		mRobotState.outputToSmartDashboard();
		mEnabledLooper.outputToSmartDashboard();
		
	}
}
