package org.usfirst.frc.team1806.robot.auto.modes;

import org.usfirst.frc.team1806.robot.auto.AutoModeBase;
import org.usfirst.frc.team1806.robot.auto.AutoModeEndedException;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToBottom;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToHighScale;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToNeutralScale;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToSwitch;

public class LiftTester extends AutoModeBase {
    @Override
    protected void routine() throws AutoModeEndedException {
        runAction(new LiftToSwitch());
        runAction(new LiftToNeutralScale());
        runAction(new LiftToHighScale());
        runAction(new LiftToBottom());
    }
}
