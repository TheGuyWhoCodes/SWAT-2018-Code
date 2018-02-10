package org.usfirst.frc.team1806.robot.auto.modes;

import org.usfirst.frc.team1806.robot.auto.AutoModeBase;
import org.usfirst.frc.team1806.robot.auto.AutoModeEndedException;
import org.usfirst.frc.team1806.robot.auto.actions.*;
import org.usfirst.frc.team1806.robot.auto.actions.intakeaction.IntakeCube;
import org.usfirst.frc.team1806.robot.auto.actions.intakeaction.SpitOutCube;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToBottom;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToHighScale;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToNeutralScale;
import org.usfirst.frc.team1806.robot.auto.paths.DumbMode;
import org.usfirst.frc.team1806.robot.auto.paths.LeftSideCrossScale;
import org.usfirst.frc.team1806.robot.auto.paths.LeftSideSafe;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.leftside.LeftSideScalePart1;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.leftside.LeftSideScalePart2;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.leftside.LeftSideScalePart3;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.leftside.LeftSideScalePart4;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.rightside.RightSideScaleToBlockPart1;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.rightside.RightSideScaleToBlockPart2;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.util.Translation2d;

import edu.wpi.first.wpilibj.DriverStation;

import java.util.Arrays;


/**
 * This is our "World Famous" auto used to deposit two / three cubes to the scale
 *
 * This will be ran probably only at elims!
 */
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
							new RunActionAtX(135, new LiftToHighScale()),
							new RunActionAtX(268, new RunActionAtLiftHeight(15000, (new SpitOutCube(.1))))
					}
			)));
			runAction(new OutputTime("Finished Left Side"));

			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new TurnTowardsPoint(new Translation2d(190,210)),
							new RunActionAtAngleRange(-180,-15,new LiftToBottom())
					}
			)));
			runAction(new OutputTime("Finished Turn"));
			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new IntakeCube(),
							new DrivePathAction(new LeftSideScalePart1())
					})));
			runAction(new OutputTime("Grabbed Cube"));
			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new DrivePathAction(new LeftSideScalePart2())
					})));

			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new TurnTowardsPoint(new Translation2d(600, 155)),
							new RunActionAtAngleRange(-75,75, new LiftToNeutralScale()),
							//new RunActionAtX(268, new RunActionAtLiftHeight(15000, (new SpitOutCube(.1))))
							new RunActionAtLiftHeight(15000, (new SpitOutCube(.1)))
					}
			)));

			runAction(new LiftToBottom());
//			runAction(new TurnTowardsPoint(new Translation2d(190,190)));
//			runAction(new ParallelAction(Arrays.asList(
//					new Action[]{
//							new IntakeCube(),
//							new DrivePathAction(new LeftSideScalePart3())
//					})));
//			runAction(new DrivePathAction(new LeftSideScalePart4()));


		} else if(gameData.charAt(1) == 'R') {
			PathContainer rightScalePath = new LeftSideCrossScale();
			runAction(new ResetPoseFromPathAction(rightScalePath));
			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new DrivePathAction(rightScalePath),
							new RunActionAtY(70, new LiftToHighScale()),
							new RunActionAtX(260,new RunActionAtLiftHeight(15000, (new SpitOutCube(.1))))
					}
			)));
			runAction(new OutputTime("Done driving towards scale!"));
			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new RunActionAtAngleRange(-45,45, new LiftToBottom()),
							new TurnTowardsPoint(new Translation2d(245, 65))
					}
			)));



			runAction(new OutputTime("Spun towards cube"));
			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new IntakeCube(),
							new DrivePathAction(new RightSideScaleToBlockPart1())
					})));

			runAction(new OutputTime("Grabbed Cube!"));
			runAction(new DrivePathAction(new RightSideScaleToBlockPart2()));
			runAction(new OutputTime("Drove to Scale!"));
			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new TurnTowardsPoint(new Translation2d(600, 80)),
							new RunActionAtAngleRange(-45,45, new LiftToNeutralScale()),
							new RunActionAtLiftHeight(15000, (new SpitOutCube(.1)))
					}
			)));
			runAction(new LiftToBottom());
			runAction(new OutputTime("Done with Auto!"));
			runAction(new WaitAction(15));
		} else {
			PathContainer dumbMode = new DumbMode();
			runAction(new ResetPoseFromPathAction(dumbMode));
			runAction(new DrivePathAction(dumbMode));
		}
	}

}
