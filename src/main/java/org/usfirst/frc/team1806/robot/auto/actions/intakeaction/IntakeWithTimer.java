package org.usfirst.frc.team1806.robot.auto.actions.intakeaction;

import edu.wpi.first.wpilibj.CircularBuffer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.auto.actions.Action;
import org.usfirst.frc.team1806.robot.subsystems.superstructure.SnackManipulatorSuperStructure;

public class IntakeWithTimer implements Action {
    private double mIntakingSpeed = 0;
    Timer timer, stopperTimer;
    double stopTime = .1; // Seconds to stop the intake for
    boolean hasStopped = false;
    PowerDistributionPanel powerDistributionPanel;
    CircularBuffer intakeCircularBuffer;
    double circularBufferTotal = 0;
    int wantedSize = 35;
    double currentThreshold = 80;
    double baseLine = 3;
    double totalCurrent;
    double overrideTime = 0;
    Timer timerToOverride;
    public IntakeWithTimer(double stopTime){
        this.overrideTime = stopTime;
        powerDistributionPanel = new PowerDistributionPanel();
        intakeCircularBuffer = new CircularBuffer(wantedSize);
        timer = new Timer();
        stopperTimer = new Timer();
        timerToOverride = new Timer();

    }
    @Override
    public boolean isFinished() {
        return timer.get() > overrideTime;
    }

    @Override
    public void update() {
            SnackManipulatorSuperStructure.getInstance().intakeCube(1, 1);
    }

    @Override
    public void done() {
        SnackManipulatorSuperStructure.getInstance().stopIntakeMotors();
        System.out.println("stopping intake");
    }

    @Override
    public void start() {
        System.out.println("starting intake");
        stopperTimer.reset();
        stopperTimer.start();
    }
}