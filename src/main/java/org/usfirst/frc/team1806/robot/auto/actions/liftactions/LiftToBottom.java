package org.usfirst.frc.team1806.robot.auto.actions.liftactions;

import org.usfirst.frc.team1806.robot.auto.actions.Action;
import org.usfirst.frc.team1806.robot.subsystems.DriveTrainSubsystem;
import org.usfirst.frc.team1806.robot.subsystems.LiftSubsystem;
import org.usfirst.frc.team1806.robot.subsystems.superstructure.SnackManipulatorSuperStructure;

public class LiftToBottom implements Action {
    boolean instant;
    public LiftToBottom(boolean finishInstantly){
        instant = finishInstantly;
    }
    @Override
    public boolean isFinished() {
        return (SnackManipulatorSuperStructure.getInstance().areWeAtBottom()) || instant;
    }

    @Override
    public void update() {
        SnackManipulatorSuperStructure.getInstance().goToBottom();
    }

    @Override
    public void done() {

    }

    @Override
    public void start() {
        SnackManipulatorSuperStructure.getInstance().goToBottom();
    }
}
