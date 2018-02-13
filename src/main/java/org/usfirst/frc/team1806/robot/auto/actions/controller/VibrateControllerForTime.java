package org.usfirst.frc.team1806.robot.auto.actions.controller;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team1806.robot.auto.actions.Action;
import org.usfirst.frc.team1806.robot.util.XboxController;


public class VibrateControllerForTime implements Action {
    Timer timer;
    double wantedTime;
    XboxController controller;
    public VibrateControllerForTime(double time, XboxController contoller){
        timer = new Timer();
        wantedTime = time;
        this.controller = contoller;
    }
    @Override
    public boolean isFinished() {
        return timer.hasPeriodPassed(wantedTime);
    }

    @Override
    public void update() {

    }

    @Override
    public void done() {
        controller.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
        controller.setRumble(GenericHID.RumbleType.kRightRumble, 0);
    }

    @Override
    public void start() {
        timer.reset();
        timer.start();
        controller.setRumble(GenericHID.RumbleType.kLeftRumble, 1);
        controller.setRumble(GenericHID.RumbleType.kRightRumble, 1);
    }
}
