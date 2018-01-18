package org.usfirst.frc.team1806.robot.auto.modes;

import org.usfirst.frc.team1806.robot.auto.AutoModeBase;
import org.usfirst.frc.team1806.robot.auto.AutoModeEndedException;
import org.usfirst.frc.team1806.robot.auto.actions.DrivePathAction;
import org.usfirst.frc.team1806.robot.auto.actions.ResetPoseFromPathAction;
import org.usfirst.frc.team1806.robot.auto.actions.WaitAction;
import org.usfirst.frc.team1806.robot.auto.paths.LeftSideScale;
import org.usfirst.frc.team1806.robot.auto.paths.RightSideScale;
import org.usfirst.frc.team1806.robot.path.PathContainer;

public class RightSideScaleAuto  extends AutoModeBase{

		@Override
		protected void routine() throws AutoModeEndedException {
			PathContainer rightScalePath = new RightSideScale();
			runAction(new ResetPoseFromPathAction(rightScalePath));
			runAction(new DrivePathAction(rightScalePath));
			runAction(new WaitAction(15));
		}

}
