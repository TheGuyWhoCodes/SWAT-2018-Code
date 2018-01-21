package org.usfirst.frc.team1806.robot.auto.modes;

import org.usfirst.frc.team1806.robot.Constants;
import org.usfirst.frc.team1806.robot.RobotState;
import org.usfirst.frc.team1806.robot.auto.AutoModeBase;
import org.usfirst.frc.team1806.robot.auto.AutoModeEndedException;
import org.usfirst.frc.team1806.robot.auto.actions.DrivePathAction;
import org.usfirst.frc.team1806.robot.auto.actions.ResetPoseFromPathAction;
import org.usfirst.frc.team1806.robot.auto.actions.TurnToHeading;
import org.usfirst.frc.team1806.robot.auto.actions.TurnTowardsPoint;
import org.usfirst.frc.team1806.robot.auto.actions.WaitAction;
import org.usfirst.frc.team1806.robot.auto.paths.DummyPath;
import org.usfirst.frc.team1806.robot.auto.paths.LeftSideCrossScale;
import org.usfirst.frc.team1806.robot.auto.paths.LeftSideScale;
import org.usfirst.frc.team1806.robot.auto.paths.RightSideScale;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.RightSideScaleToBlockPart1;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.RightSideScaleToBlockPart2;

import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

public class RightSideScaleAuto  extends AutoModeBase{

		@Override
		protected void routine() throws AutoModeEndedException {
//			PathContainer rightScalePath = new LeftSideCrossScale();
//			runAction(new ResetPoseFromPathAction(rightScalePath));
//			runAction(new DrivePathAction(rightScalePath));
//			runAction(new WaitAction(.7));
//			runAction(new DrivePathAction(new RightSideScaleToBlockPart1()));
//			runAction(new DrivePathAction(new RightSideScaleToBlockPart2()));
//			
//			runAction(new ResetPoseFromPathAction(new RightSideScaleToBlockPart3()));
//			
//			runAction(new DrivePathAction(new RightSideScaleToBlockPart3()));
//			runAction(new DrivePathAction(new RightSideScaleToBlockPart4()));
//			
//			runAction(new WaitAction(15));
			
		PathContainer rightScalePath = new LeftSideCrossScale();
		runAction(new ResetPoseFromPathAction(rightScalePath));
		runAction(new DrivePathAction(rightScalePath));
		runAction(new TurnTowardsPoint(new Translation2d(200, 100)));
		runAction(new DrivePathAction(new RightSideScaleToBlockPart1()));
		runAction(new DrivePathAction(new RightSideScaleToBlockPart2()));
		runAction(new TurnTowardsPoint(new Translation2d(600, 80)));
		runAction(new WaitAction(15));
		}

}
