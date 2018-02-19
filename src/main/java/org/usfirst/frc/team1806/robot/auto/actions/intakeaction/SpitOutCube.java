package org.usfirst.frc.team1806.robot.auto.actions.intakeaction;

import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team1806.robot.auto.actions.Action;
import org.usfirst.frc.team1806.robot.subsystems.superstructure.SnackManipulatorSuperStructure;

public class SpitOutCube implements Action{
    Timer timer = new Timer();
    double startTime;
    double wantedTime;
    public SpitOutCube(double time){
        wantedTime = time;
    }
    @Override
    public boolean isFinished() {
        return timer.hasPeriodPassed(wantedTime);
    }

    @Override
    public void update() {
        SnackManipulatorSuperStructure.getInstance().spitOutCube(.8);
    }

    @Override
    public void done() {
        SnackManipulatorSuperStructure.getInstance().stopIntakeMotors();
    }

    @Override
    public void start() {
        timer.reset();
        timer.start();
        System.out.println("We are spitting!");
        startTime = timer.getFPGATimestamp();
        SnackManipulatorSuperStructure.getInstance().spitOutCube(.8);
    }
}
