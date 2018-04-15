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
        return SnackManipulatorSuperStructure.getInstance().doWeGotACube() || timer.get() > overrideTime;
    }

    @Override
    public void update() {
        circularBufferTotal = 0;
        //double totalCurrent = Robot.powerDistributionPanel.getCurrent(5) + Robot.powerDistributionPanel.getCurrent(6);
        double totalCurrent = Robot.powerDistributionPanel.getCurrent(6) + Robot.powerDistributionPanel.getCurrent(7);
        intakeCircularBuffer.addFirst(totalCurrent);
        for(int i=0; i < wantedSize ; i++){
            circularBufferTotal += intakeCircularBuffer.get(i);
        }
        if(circularBufferTotal / wantedSize >= currentThreshold && !hasStopped){
            hasStopped = true;
            timer.reset();
            timer.start();
        }
        if(hasStopped && (timer.get() < stopTime)){
            System.out.println("stop time is doing it!");
            SnackManipulatorSuperStructure.getInstance().intakeCube(0, 0);
        } else if(stopperTimer.get() % 1.1 > 1.0){
            SnackManipulatorSuperStructure.getInstance().intakeCube(0, 0);
        } else if(hasStopped && timer.get() > stopTime) {
            timer.reset();
            timer.stop();
            hasStopped = false;
        } else{
            SnackManipulatorSuperStructure.getInstance().intakeCube(1, 1);
        }
        SmartDashboard.putNumber("Total Intake Current", totalCurrent);
        SmartDashboard.putNumber("Average Intake Current", circularBufferTotal / wantedSize);
        SmartDashboard.putBoolean("Are we over Intake Threshold", circularBufferTotal / wantedSize >= currentThreshold);
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