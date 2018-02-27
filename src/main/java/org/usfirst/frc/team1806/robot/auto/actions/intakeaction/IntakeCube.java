package org.usfirst.frc.team1806.robot.auto.actions.intakeaction;

import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team1806.robot.auto.actions.Action;
import org.usfirst.frc.team1806.robot.subsystems.superstructure.SnackManipulatorSuperStructure;

public class IntakeCube implements Action {
    @Override
    public boolean isFinished() {
        //return true;
         return SnackManipulatorSuperStructure.getInstance().doWeGotACube();
    }

    @Override
    public void update() {
        SnackManipulatorSuperStructure.getInstance().intakeCube(.7 * Math.abs(Math.sin(6 * Timer.getFPGATimestamp())), .7 * Math.abs(Math.sin(6 * Timer.getFPGATimestamp())));
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
