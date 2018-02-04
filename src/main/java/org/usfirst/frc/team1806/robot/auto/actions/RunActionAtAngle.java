package org.usfirst.frc.team1806.robot.auto.actions;

import org.usfirst.frc.team1806.robot.RobotState;
import org.usfirst.frc.team1806.robot.subsystems.superstructure.SnackManipulatorSuperStructure;

public class RunActionAtAngle implements Action {
    private SnackManipulatorSuperStructure mSnackManipulator = SnackManipulatorSuperStructure.getInstance();
    private double triggerAngle;
    private double currentAngle;
    private double lastAngle;
    private Action action = null;
    private boolean hasRunAction = false;
    public RunActionAtAngle(double angle, Action action){
        this.triggerAngle = angle;
        this.action = action;
    }
    @Override
    public boolean isFinished() {
        return action.isFinished();
    }

    @Override
    public void update() {
        currentAngle = RobotState.getInstance().getLatestFieldToVehicle().getValue().getRotation().getDegrees();
        if((lastAngle <= triggerAngle && currentAngle > triggerAngle) || (currentAngle <= triggerAngle && lastAngle > triggerAngle) && !hasRunAction){
            action.start();
            hasRunAction = true;
        }
    }

    @Override
    public void done() {

    }

    @Override
    public void start() {

    }
}
