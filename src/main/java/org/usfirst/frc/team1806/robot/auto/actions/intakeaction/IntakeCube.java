package org.usfirst.frc.team1806.robot.auto.actions.intakeaction;

import edu.wpi.first.wpilibj.CircularBuffer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team1806.robot.auto.actions.Action;
import org.usfirst.frc.team1806.robot.subsystems.superstructure.SnackManipulatorSuperStructure;

public class IntakeCube implements Action {
    Timer timer;
    double stopTime = 1; // Seconds to stop the intake for
    boolean hasStopped = false;
    PowerDistributionPanel powerDistributionPanel;
    CircularBuffer intakeCircularBuffer;
    double circularBufferTotal = 0;
    int wantedSize = 70;
    double currentThreshold = 70;
    public IntakeCube(){
        powerDistributionPanel = new PowerDistributionPanel();
        intakeCircularBuffer = new CircularBuffer(wantedSize);
        timer = new Timer();
    }
    @Override
    public boolean isFinished() {
        //return true;
         return SnackManipulatorSuperStructure.getInstance().doWeGotACube();
    }

    @Override
    public void update() {
        circularBufferTotal = 0;
        double totalCurrent = powerDistributionPanel.getCurrent(5) + powerDistributionPanel.getCurrent(6);
        // COMP BOT PLEASE USE CHRIS double currentThreshold = powerDistributionPanel.getCurrent(6) + powerDistributionPanel.getCurrent(7);
        intakeCircularBuffer.addFirst(totalCurrent);
        for(int i=0; i < wantedSize ; i++){
            circularBufferTotal += intakeCircularBuffer.get(i);
        }
        if(circularBufferTotal / wantedSize >= totalCurrent){
            boolean hasStopped = true;
            timer.start();
        }
        if(hasStopped && !timer.hasPeriodPassed(stopTime)){
            SnackManipulatorSuperStructure.getInstance().intakeCube(0, 0);
        } else if(hasStopped && timer.hasPeriodPassed(stopTime)) {
            timer.reset();
            hasStopped = false;
        } else {
            SnackManipulatorSuperStructure.getInstance().intakeCube(.7, .7);
        }
    }

    @Override
    public void done() {
        SnackManipulatorSuperStructure.getInstance().stopIntakeMotors();
        System.out.println("stopping intake");
    }

    @Override
    public void start() {
        System.out.println("starting intake");
    }
}
