package org.usfirst.frc.team1806.robot.auto.actions.intakeaction;

import edu.wpi.first.wpilibj.CircularBuffer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.auto.actions.Action;
import org.usfirst.frc.team1806.robot.subsystems.DriveTrainSubsystem;
import org.usfirst.frc.team1806.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team1806.robot.subsystems.superstructure.SnackManipulatorSuperStructure;

public class IntakeCube implements Action {
    private double mIntakingSpeed = 0;
    Timer timer, stopperTimer;
    double stopTime = .1; // Seconds to stop the intake for
    boolean hasStopped = false;
    PowerDistributionPanel powerDistributionPanel;
    CircularBuffer intakeCircularBuffer;
    double circularBufferTotal = 0;
    int wantedSize = 35;
    double currentThreshold = 100;
    double baseLine = 3;
    double totalCurrent;
    public IntakeCube(){
        powerDistributionPanel = new PowerDistributionPanel();
        intakeCircularBuffer = new CircularBuffer(wantedSize);
        timer = new Timer();
        stopperTimer = new Timer();
    }
    @Override
    public boolean isFinished() {
        System.out.println(SnackManipulatorSuperStructure.getInstance().getOverrideCubeDetector());
        return SnackManipulatorSuperStructure.getInstance().doWeGotACube() || SnackManipulatorSuperStructure.getInstance().getOverrideCubeDetector();
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
        SnackManipulatorSuperStructure.getInstance().stopOverrideCubeDetector();
    }

    @Override
    public void start() {
        System.out.println("starting intake");
        stopperTimer.reset();
        stopperTimer.start();
    }
}