package org.usfirst.frc.team1806.robot.auto.actions;


import org.usfirst.frc.team1806.robot.RobotState;
import org.usfirst.frc.team1806.robot.subsystems.superstructure.SnackManipulatorSuperStructure;

public class RunActionAtY implements Action{
    private SnackManipulatorSuperStructure mSnackManipulator = SnackManipulatorSuperStructure.getInstance();
    private double triggerY;
    private double currentY;
    private double lastY;
    private Action action = null;
    private boolean hasRunAction = false;
    public RunActionAtY(double y, Action action){
        this.triggerY = y;
        this.action = action;
    }
    @Override
    public boolean isFinished() {
        return action.isFinished();
    }

    @Override
    public void update() {
        currentY = RobotState.getInstance().getLatestFieldToVehicle().getValue().getTranslation().y();
        if((lastY <= triggerY && currentY > triggerY) || (currentY <= triggerY && lastY> triggerY) && !hasRunAction){
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
