package org.usfirst.frc.team1806.robot.auto.modes;

import org.usfirst.frc.team1806.robot.auto.AutoModeBase;
import org.usfirst.frc.team1806.robot.auto.AutoModeEndedException;
import org.usfirst.frc.team1806.robot.auto.actions.ResetPoseFromPathAction;
import org.usfirst.frc.team1806.robot.auto.paths.LeftSideScale;
import org.usfirst.frc.team1806.robot.path.PathContainer;

public class LeftSideScaleAuto extends AutoModeBase{

	@Override
	protected void routine() throws AutoModeEndedException {
		PathContainer leftScalePath = new LeftSideScale();
		runAction(new ResetPoseFromPathAction(leftScalePath));
	}

}
