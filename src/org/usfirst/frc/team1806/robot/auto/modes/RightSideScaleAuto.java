package org.usfirst.frc.team1806.robot.auto.modes;

import org.usfirst.frc.team1806.robot.auto.AutoModeBase;
import org.usfirst.frc.team1806.robot.auto.AutoModeEndedException;
import org.usfirst.frc.team1806.robot.auto.actions.DrivePathAction;
import org.usfirst.frc.team1806.robot.auto.actions.ResetPoseFromPathAction;
import org.usfirst.frc.team1806.robot.auto.actions.WaitAction;
import org.usfirst.frc.team1806.robot.auto.paths.LeftSideScale;
import org.usfirst.frc.team1806.robot.auto.paths.RightSideScale;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.RightSideScaleToBlockPart1;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.RightSideScaleToBlockPart2;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.path.PathContainer;

public class RightSideScaleAuto  extends AutoModeBase{

		@Override
		protected void routine() throws AutoModeEndedException {
			PathContainer rightScalePath = new RightSideScale();
			PathContainer rightScaleBlock1 = new RightSideScaleToBlockPart1();
			PathContainer rightScaleBlock2 = new RightSideScaleToBlockPart2();
			runAction(new ResetPoseFromPathAction(rightScalePath));
			runAction(new DrivePathAction(rightScalePath));
			runAction(new ResetPoseFromPathAction(rightScaleBlock1));
			runAction(new DrivePathAction(rightScaleBlock1));
			runAction(new ResetPoseFromPathAction(rightScaleBlock2));
			runAction(new DrivePathAction(rightScaleBlock2));
			runAction(new WaitAction(15));
		}

}
