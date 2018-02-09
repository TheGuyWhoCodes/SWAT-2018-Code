package org.usfirst.frc.team1806.robot.auto.actions;

import org.usfirst.frc.team1806.robot.RobotState;
import org.usfirst.frc.team1806.robot.subsystems.superstructure.SnackManipulatorSuperStructure;

public class RunActionAtX implements Action{
    private SnackManipulatorSuperStructure mSnackManipulator = SnackManipulatorSuperStructure.getInstance();
    private double triggerX;
    private double currentX;
    private double lastX;
    private Action action = null;
    private boolean hasRunAction = false;
    public RunActionAtX(double x, Action action){
        this.triggerX = x;
        this.action = action;
    }
    @Override
    public boolean isFinished() {
        return action.isFinished();
    }

    @Override
    public void update() {
        currentX = RobotState.getInstance().getLatestFieldToVehicle().getValue().getTranslation().x();
        if((lastX <= triggerX && currentX > triggerX) || (currentX <= triggerX && lastX > triggerX) && !hasRunAction){
            action.start();
            hasRunAction = true;
        }
        lastX = currentX;
    }

    @Override
    public void done() {

    }

    @Override
    public void start() {
        lastX = RobotState.getInstance().getLatestFieldToVehicle().getValue().getTranslation().x();
        currentX = RobotState.getInstance().getLatestFieldToVehicle().getValue().getTranslation().x();
    }
}
