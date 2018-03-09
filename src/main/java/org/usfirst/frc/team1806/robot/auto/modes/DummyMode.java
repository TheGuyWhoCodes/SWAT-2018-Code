package org.usfirst.frc.team1806.robot.auto.modes;

import org.usfirst.frc.team1806.robot.auto.AutoModeBase;
import org.usfirst.frc.team1806.robot.auto.AutoModeEndedException;
import org.usfirst.frc.team1806.robot.auto.actions.DrivePathAction;
import org.usfirst.frc.team1806.robot.auto.actions.ResetPoseFromPathAction;
import org.usfirst.frc.team1806.robot.auto.actions.TurnTowardsPoint;
import org.usfirst.frc.team1806.robot.auto.paths.DumbMode;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.util.Translation2d;

public class DummyMode extends AutoModeBase {

	/**
	 * Dummy Mode is used just in case all is wrong
	 *
	 * The Lift Doesn't work
	 * Booooochard jacked up the gear boxes
	 * the robot is in fire
	 *
	 * Dummy mode just sits still
	 * @throws AutoModeEndedException
	 */
	@Override
	protected void routine() throws AutoModeEndedException {
		PathContainer dumbMode = new DumbMode();
		runAction(new ResetPoseFromPathAction(dumbMode));
		runAction(new DrivePathAction(dumbMode));
		runAction(new TurnTowardsPoint(new Translation2d(0, 0)));
	}

}
