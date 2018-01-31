package org.usfirst.frc.team1806.robot.auto.actions.liftactions;

import org.usfirst.frc.team1806.robot.auto.actions.Action;
import org.usfirst.frc.team1806.robot.subsystems.DriveTrainSubsystem;
import org.usfirst.frc.team1806.robot.subsystems.LiftSubsystem;
import org.usfirst.frc.team1806.robot.subsystems.superstructure.SnackManipulatorSuperStructure;

public class LiftToBottom implements Action {
    @Override
    public boolean isFinished() {
        return SnackManipulatorSuperStructure.getInstance().isAtPosition();
    }

    @Override
    public void update() {

    }

    @Override
    public void done() {

    }

    @Override
    public void start() {
        SnackManipulatorSuperStructure.getInstance().goToBottom();
    }
}
