package org.usfirst.frc.team1806.robot.auto.modes;

import org.usfirst.frc.team1806.robot.Constants;
import org.usfirst.frc.team1806.robot.RobotState;
import org.usfirst.frc.team1806.robot.auto.AutoModeBase;
import org.usfirst.frc.team1806.robot.auto.AutoModeEndedException;
import org.usfirst.frc.team1806.robot.auto.actions.DrivePathAction;
import org.usfirst.frc.team1806.robot.auto.actions.ResetPoseFromPathAction;
import org.usfirst.frc.team1806.robot.auto.actions.SpitOutTime;
import org.usfirst.frc.team1806.robot.auto.actions.TurnToHeading;
import org.usfirst.frc.team1806.robot.auto.actions.TurnTowardsPoint;
import org.usfirst.frc.team1806.robot.auto.actions.WaitAction;
import org.usfirst.frc.team1806.robot.auto.paths.DumbMode;
import org.usfirst.frc.team1806.robot.auto.paths.LeftSideCrossScale;
import org.usfirst.frc.team1806.robot.auto.paths.LeftSideSafe;
import org.usfirst.frc.team1806.robot.auto.paths.LeftSideScale;
import org.usfirst.frc.team1806.robot.auto.paths.RightSideScale;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.rightside.RightSideScaleToBlockPart1;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.rightside.RightSideScaleToBlockPart2;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

import edu.wpi.first.wpilibj.DriverStation;

public class RightSideScaleAuto  extends AutoModeBase{

		@Override
		protected void routine() throws AutoModeEndedException {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage().toUpperCase();
		if(gameData.charAt(1) == 'L') {
			PathContainer safeSide = new LeftSideSafe();
			runAction(new ResetPoseFromPathAction(safeSide));
			runAction(new DrivePathAction(safeSide));
		} else if(gameData.charAt(1) == 'R') {
			PathContainer rightScalePath = new LeftSideCrossScale();
			runAction(new ResetPoseFromPathAction(rightScalePath));
			runAction(new DrivePathAction(rightScalePath));
			runAction(new SpitOutTime("Done driving towards scale!"));
			runAction(new TurnTowardsPoint(new Translation2d(245, 65)));
			runAction(new SpitOutTime("Spun towards cube"));
			runAction(new DrivePathAction(new RightSideScaleToBlockPart1()));
			runAction(new SpitOutTime("Grabbed Cube!"));
			runAction(new DrivePathAction(new RightSideScaleToBlockPart2()));
			runAction(new SpitOutTime("Drove to Scale!"));
			runAction(new TurnTowardsPoint(new Translation2d(600, 80)));
			runAction(new SpitOutTime("Done with Auto!"));
			runAction(new WaitAction(15));
		} else {
			PathContainer dumbMode = new DumbMode();
			runAction(new ResetPoseFromPathAction(dumbMode));
			runAction(new DrivePathAction(dumbMode));
		}
	}

}
