package org.usfirst.frc.team1806.robot.auto.modes;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1806.robot.Constants;
import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.auto.AutoModeBase;
import org.usfirst.frc.team1806.robot.auto.AutoModeEndedException;
import org.usfirst.frc.team1806.robot.auto.actions.*;
import org.usfirst.frc.team1806.robot.auto.actions.intakeaction.IntakeCube;
import org.usfirst.frc.team1806.robot.auto.actions.intakeaction.IntakeWithTimer;
import org.usfirst.frc.team1806.robot.auto.actions.intakeaction.SpitOutCube;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.*;
import org.usfirst.frc.team1806.robot.auto.paths.DumbMode;
import org.usfirst.frc.team1806.robot.auto.paths.LeftSideCrossScale;
import org.usfirst.frc.team1806.robot.auto.paths.LeftSideSafe;
import org.usfirst.frc.team1806.robot.auto.paths.UpOneFootRR;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.leftside.LeftSideScalePart1;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.leftside.LeftSideScalePart2;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.leftside.LeftSideScalePart3;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.leftside.LeftSideScalePart4;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.rightside.RightSideScaleToBlockPart1;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.rightside.RightSideScaleToBlockPart2;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.rightside.RightSideScaleToBlockPart3;
import org.usfirst.frc.team1806.robot.auto.paths.threecube.ThreeCubePart1;
import org.usfirst.frc.team1806.robot.auto.paths.threecube.ThreeCubePart2;
import org.usfirst.frc.team1806.robot.auto.paths.threecube.ThreeCubePart3;
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
		if(Constants.enableAutoInTeleOp){
			gameData = SmartDashboard.getString("testingFieldValue", "").toUpperCase().substring(0,2);
		} else {
			gameData = DriverStation.getInstance().getGameSpecificMessage().toUpperCase().substring(0, 2);
		}
		 if(gameData.charAt(1) == 'R') {
		 	runAction(new SwitchToHighPID());
			PathContainer rightScalePath = new LeftSideCrossScale();
			runAction(new ResetPoseFromPathAction(rightScalePath));
			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new SeriesAction(Arrays.asList(
									new Action[]{
											new LiftToTeleopHold(),
//											new IntakeWithTimer(.1),
											new RunActionAtX(260,new RunActionAtLiftHeight(Constants.kHighScaleSpitOutCount, (new SpitOutCube(.1, 1))))
									}
							)),
							new DrivePathAction(rightScalePath),
							new RunActionAtY(110, new LiftToHighScale(false)),
					}
			)));
			runAction(new OutputTime("Done driving towards scale!"));
			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new LiftToBottom(false),
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
							new TurnTowardsPoint(new Translation2d(300, 65)),
							new LiftToNeutralScale(),
							new RunActionAtLiftHeight(Constants.kNeutralScaleSpitOutCount, (new SpitOutCube(.1)))
					}
			)));
			runAction(new LiftToBottom(true));
			runAction(new TurnTowardsPoint(new Translation2d(226, 90)));
			runAction(new ParallelAction(Arrays.asList(
					 new Action[]{
							 new IntakeCube(),
							 new DrivePathAction(new RightSideScaleToBlockPart3())
					 })));

			 runAction(new OutputTime("Done with Auto!"));
			runAction(new WaitAction(15));
		} else if(gameData.charAt(1) == 'L'){
			PathContainer safeSide = new LeftSideSafe();
			 runAction(new ResetPoseFromPathAction(safeSide));
			 runAction(new ParallelAction(Arrays.asList(
					 new Action[]{
							 new SeriesAction(Arrays.asList(
									 new LiftToTeleopHold(),
//									new IntakeWithTimer(.1),
									 new RunActionAtX(258, new RunActionAtLiftHeight(Constants.kHighScaleSpitOutCount, (new SpitOutCube(.1, .73))))
							 )),
							 new SwitchToLowPID(),
							 new DrivePathAction(safeSide),
							 new RunActionAtX(100, new LiftToHighScale(false)),
					 }
			 )));
			 runAction(new OutputTime("Finished Left Side"));
			 runAction(new LiftToBottom(true));
			 runAction(new TurnTowardsPoint(new Translation2d(223,220)));
			 runAction(new OutputTime("Finished Turn"));
			 runAction(new ParallelAction(Arrays.asList(
					 new Action[]{
							 new IntakeCube(),
							 new DrivePathAction(new LeftSideScalePart1()),
					 })));
			 runAction(new ParallelAction(Arrays.asList(
					 new Action[]{
							 new DrivePathAction(new LeftSideScalePart2()),
							 new RunActionAtX(230, new LiftToHighScale(true))
					 })));

			 runAction(new ParallelAction(Arrays.asList(
					 new Action[]{
							 new TurnTowardsPoint(new Translation2d(280, 225)),
							 new RunActionAtAngleRange(-50, 10, new SpitOutCube(.1))
//							new RunActionAtAngle(20, new SpitOutCube(.1))
//							new RunActionAtLiftHeight(Constants.kHighScaleSpitOutCount, new SpitOutCube(.1))
					 }
			 )));
			 runAction(new LiftToBottom(true));
			 runAction(new TurnTowardsPoint(new Translation2d(223,220)));
			 runAction(new ParallelAction(Arrays.asList(
					 new Action[]{
							 new IntakeCube(),
							 new DrivePathAction(new LeftSideScalePart3()),
					 })));
			 runAction(new ParallelAction(Arrays.asList(
					 new Action[]{
							 new DrivePathAction(new LeftSideScalePart4()),
							 new RunActionAtX(230, new LiftToHighScale(true))
					 })));
			 runAction(new ParallelAction(Arrays.asList(
					 new Action[]{
							 new TurnTowardsPoint(new Translation2d(310, 210)),
							 new RunActionAtAngleRange(-20, 20, new SpitOutCube(.1))
//							new RunActionAtLiftHeight(Constants.kHighScaleSpitOutCount, new SpitOutCube(.1))
					 })));

		}else {
			PathContainer dumbMode = new DumbMode();
			runAction(new ResetPoseFromPathAction(dumbMode));
			runAction(new DrivePathAction(dumbMode));
			runAction(new TurnTowardsPoint(new Translation2d(0, 0)));
		}
	}

}