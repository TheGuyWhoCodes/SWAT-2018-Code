package org.usfirst.frc.team1806.robot.auto.actions;

import org.usfirst.frc.team1806.robot.RobotState;
import org.usfirst.frc.team1806.robot.subsystems.superstructure.SnackManipulatorSuperStructure;

public class RunActionAtLiftHeight implements Action {
    int height;
    Action action = null;
    boolean hasRunAction = false;
    public RunActionAtLiftHeight(int height, Action action){
        this.action = action;
        this.height = height;
    }
    @Override
    public boolean isFinished() {
        return action.isFinished();
    }

    @Override
    public void update() {
        int currentLift = SnackManipulatorSuperStructure.getInstance().returnLiftHeight();
        if(currentLift > height && !hasRunAction){
            action.start();
            hasRunAction = true;
        }
        if(hasRunAction){
            action.update();
        }
    }

    @Override
    public void done() {
        action.done();
    }

    @Override
    public void start() {

    }
}
