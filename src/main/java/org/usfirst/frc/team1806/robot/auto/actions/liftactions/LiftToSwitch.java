package org.usfirst.frc.team1806.robot.auto.actions.liftactions;

import org.usfirst.frc.team1806.robot.auto.actions.Action;
import org.usfirst.frc.team1806.robot.subsystems.superstructure.SnackManipulatorSuperStructure;

public class LiftToSwitch implements Action {
    @Override
    public boolean isFinished() {
        return SnackManipulatorSuperStructure.getInstance().isAtPosition();
    }

    @Override
    public void update() {
        SnackManipulatorSuperStructure.getInstance().goToSwitchSetpoint();
    }

    @Override
    public void done() {

    }

    @Override
    public void start() {
        SnackManipulatorSuperStructure.getInstance().goToSwitchSetpoint();
    }
}
