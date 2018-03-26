package org.usfirst.frc.team1806.robot.auto.actions.intakeaction;

import edu.wpi.first.wpilibj.CircularBuffer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team1806.robot.auto.actions.Action;
import org.usfirst.frc.team1806.robot.subsystems.superstructure.SnackManipulatorSuperStructure;

public class IntakeNoCheck  implements Action {
    Timer timer, stopperTimer;
    double stopTime = .1; // Seconds to stop the intake for
    boolean hasStopped = false;
    PowerDistributionPanel powerDistributionPanel;
    CircularBuffer intakeCircularBuffer;
    double circularBufferTotal = 0;
    int wantedSize = 50;
    double currentThreshold = 25;
    public IntakeNoCheck(){
        powerDistributionPanel = new PowerDistributionPanel();
        intakeCircularBuffer = new CircularBuffer(wantedSize);
        timer = new Timer();
        stopperTimer = new Timer();
    }
    @Override
    public boolean isFinished() {
        return SnackManipulatorSuperStructure.getInstance().doWeGotACube();
    }

    @Override
    public void update() {
        circularBufferTotal = 0;
        //  double totalCurrent = Robot.powerDistributionPanel.getCurrent(5) + Robot.powerDistributionPanel.getCurrent(6);
        double totalCurrent = powerDistributionPanel.getCurrent(6) + powerDistributionPanel.getCurrent(7);
        intakeCircularBuffer.addFirst(totalCurrent);
        for(int i=0; i < wantedSize ; i++){
            circularBufferTotal += intakeCircularBuffer.get(i);
        }
        if(circularBufferTotal / wantedSize >= currentThreshold && !hasStopped){
            hasStopped = true;
            timer.reset();
            timer.start();
        }
        if(stopperTimer.get() % 1.3 > 1.0){
            SnackManipulatorSuperStructure.getInstance().intakeCube(0, 0);
        } else {
            SnackManipulatorSuperStructure.getInstance().intakeCube(1, 1);
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
        stopperTimer.reset();
        stopperTimer.start();
    }
}
