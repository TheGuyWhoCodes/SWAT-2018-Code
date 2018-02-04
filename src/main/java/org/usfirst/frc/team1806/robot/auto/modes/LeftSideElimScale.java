package org.usfirst.frc.team1806.robot.auto.modes;

import org.usfirst.frc.team1806.robot.Constants;
import org.usfirst.frc.team1806.robot.RobotState;
import org.usfirst.frc.team1806.robot.auto.AutoModeBase;
import org.usfirst.frc.team1806.robot.auto.AutoModeEndedException;
import org.usfirst.frc.team1806.robot.auto.actions.*;
import org.usfirst.frc.team1806.robot.auto.actions.intakeaction.SpitOutCube;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToBottom;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToHighScale;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToSwitch;
import org.usfirst.frc.team1806.robot.auto.paths.DumbMode;
import org.usfirst.frc.team1806.robot.auto.paths.LeftSideCrossScale;
import org.usfirst.frc.team1806.robot.auto.paths.LeftSideSafe;
import org.usfirst.frc.team1806.robot.auto.paths.LeftSideScale;
import org.usfirst.frc.team1806.robot.auto.paths.RightSideScale;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.leftside.LeftSideScalePart1;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.leftside.LeftSideScalePart2;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.leftside.LeftSideScalePart3;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.leftside.LeftSideScalePart4;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.rightside.RightSideScaleToBlockPart1;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.rightside.RightSideScaleToBlockPart2;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

import edu.wpi.first.wpilibj.DriverStation;

import java.util.Arrays;

public class LeftSideElimScale  extends AutoModeBase{

		@Override
		protected void routine() throws AutoModeEndedException {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage().toUpperCase();
		if(gameData.charAt(1) == 'L') {
			System.out.println("Running left Side!");
			PathContainer safeSide = new LeftSideSafe();
			runAction(new ResetPoseFromPathAction(safeSide));
			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new DrivePathAction(safeSide),
							new RunActionAtX(175, new LiftToHighScale()),
							new RunActionAtX(250, new SpitOutCube(.5))
					}
			)));
			runAction(new SpitOutTime("Finished Left Side"));
			runAction(new LiftToBottom());
			runAction(new TurnTowardsPoint(new Translation2d(190,210)));
			runAction(new SpitOutTime("Finished Turn"));
			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							//TODO put in the intaking control
							new DrivePathAction(new LeftSideScalePart1())
					})));
			runAction(new SpitOutTime("Grabbed Cube"));
			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new DrivePathAction(new LeftSideScalePart2())
					})));
			runAction(new TurnTowardsPoint(new Translation2d(600, 155)));
			runAction(new RunActionAtX(210, new LiftToHighScale()));
			runAction(new SpitOutCube(.5));
			runAction(new LiftToBottom());
//			runAction(new ParallelAction(Arrays.asList(
//					new Action[]{
//							new RunActionAtAngle(-90 ,new LiftToBottom()),
//							new TurnTowardsPoint(new Translation2d(190,190))
//					})));
//			runAction(new DrivePathAction(new LeftSideScalePart3()));
//			runAction(new DrivePathAction(new LeftSideScalePart4()));
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
