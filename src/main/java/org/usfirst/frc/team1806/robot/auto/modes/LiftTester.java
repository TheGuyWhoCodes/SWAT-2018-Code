package org.usfirst.frc.team1806.robot.auto.modes;

import org.usfirst.frc.team1806.robot.auto.AutoModeBase;
import org.usfirst.frc.team1806.robot.auto.AutoModeEndedException;
import org.usfirst.frc.team1806.robot.auto.actions.intakeaction.SpitOutCube;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToBottom;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToHighScale;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToNeutralScale;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToSwitch;

/**
 * Lift Tester auto will be used to test our lift very quickly whenever we get to an event
 *
 * Self-Tester
 */
public class LiftTester extends AutoModeBase {
    @Override
    protected void routine() throws AutoModeEndedException {
        runAction(new LiftToSwitch());
        runAction(new LiftToNeutralScale());
        runAction(new LiftToHighScale(false));
        runAction(new LiftToBottom(false));
        runAction(new SpitOutCube(.5));
    }
}
