package org.usfirst.frc.team1806.robot.auto.actions.liftactions;

import org.usfirst.frc.team1806.robot.auto.actions.Action;
import org.usfirst.frc.team1806.robot.subsystems.superstructure.SnackManipulatorSuperStructure;

public class LiftToHighScale implements Action {
    boolean instant;
    public LiftToHighScale(boolean instantFinish){
        instant = instantFinish;
    }
    @Override
    public boolean isFinished() {
        return SnackManipulatorSuperStructure.getInstance().isAtPosition() || instant;
    }

    @Override
    public void update() {

    }

    @Override
    public void done() {

    }

    @Override
    public void start() {
        SnackManipulatorSuperStructure.getInstance().goToHighScaleSetpoint();
    }
}
