package org.usfirst.frc.team1806.robot.auto.actions.intakeaction;

import org.usfirst.frc.team1806.robot.auto.actions.Action;
import org.usfirst.frc.team1806.robot.subsystems.superstructure.SnackManipulatorSuperStructure;

public class IntakeCube implements Action {
    @Override
    public boolean isFinished() {
        return true;
        // return SnackManipulatorSuperStructure.getInstance().doWeGotACube();
    }

    @Override
    public void update() {
        SnackManipulatorSuperStructure.getInstance().intakeCube();
    }

    @Override
    public void done() {
        SnackManipulatorSuperStructure.getInstance().stopIntakeMotors();
    }

    @Override
    public void start() {

    }
}
