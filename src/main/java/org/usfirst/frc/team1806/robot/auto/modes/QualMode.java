package org.usfirst.frc.team1806.robot.auto.modes;

import org.usfirst.frc.team1806.robot.auto.AutoModeBase;
import org.usfirst.frc.team1806.robot.auto.AutoModeEndedException;
import org.usfirst.frc.team1806.robot.auto.actions.*;
import org.usfirst.frc.team1806.robot.auto.actions.intakeaction.IntakeCube;
import org.usfirst.frc.team1806.robot.auto.actions.intakeaction.SpitOutCube;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.*;
import org.usfirst.frc.team1806.robot.auto.paths.DumbMode;
import org.usfirst.frc.team1806.robot.auto.paths.LeftSideCrossScale;
import org.usfirst.frc.team1806.robot.auto.paths.LeftSideSafe;
import org.usfirst.frc.team1806.robot.auto.paths.UpOneFootRR;
import org.usfirst.frc.team1806.robot.auto.paths.qual.LR.DriveToSwitch;
import org.usfirst.frc.team1806.robot.auto.paths.qual.LR.SwitchToCube;
import org.usfirst.frc.team1806.robot.auto.paths.qual.RL.ScaleToSwitch;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.leftside.LeftSideScalePart1;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.rightside.RightSideScaleToBlockPart1;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.rightside.RightSideScaleToBlockPart2;
import org.usfirst.frc.team1806.robot.auto.paths.threecube.ThreeCubePart1;
import org.usfirst.frc.team1806.robot.auto.paths.threecube.ThreeCubePart2;
import org.usfirst.frc.team1806.robot.auto.paths.threecube.ThreeCubePart3;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.util.Translation2d;

import edu.wpi.first.wpilibj.DriverStation;

import java.util.Arrays;

public class QualMode extends AutoModeBase {
	/**
	 * Qual Mode will be what we use for almost every qual match
	 *
	 * This auto decides which side to run on then drops off a cube at both the scale and the switch
	 * @throws AutoModeEndedException
	 */
	@Override
	protected void routine() throws AutoModeEndedException {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage().toUpperCase().substring(0, 2);
		if(gameData.equals("RR")) {
			PathContainer rightScalePath = new LeftSideCrossScale();
			runAction(new ResetPoseFromPathAction(rightScalePath));
			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new DrivePathAction(rightScalePath),
//							new RunActionAtY(110, new LiftToHighScale(false)),
//							new RunActionAtX(260,new RunActionAtLiftHeight(17900, (new SpitOutCube(.1))))
					}
			)));
			runAction(new OutputTime("Done driving towards scale!"));
			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
//							new RunActionAtAngleRange(-80,80, new LiftToBottom(false)),
							new TurnTowardsPoint(new Translation2d(245, 65))
					}
			)));



//			runAction(new OutputTime("Spun towards cube"));
//			runAction(new ParallelAction(Arrays.asList(
//					new Action[]{
//							new IntakeCube(),
//							new DrivePathAction(new RightSideScaleToBlockPart1())
//					})));
//			runAction(new DrivePathAction(new UpOneFootRR(245,65,-12,false)));
//			runAction(new LiftToSwitch());
//			runAction(new SpitOutCube(.2));
		} else if(gameData.equals("LL")) {
			PathContainer safeSide = new LeftSideSafe();
			runAction(new ResetPoseFromPathAction(safeSide));
			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new DrivePathAction(safeSide),
							new RunActionAtX(70, new LiftToHighScale(false)),
							new RunActionAtX(268, new RunActionAtLiftHeight(17900, (new SpitOutCube(.1))))
					}
			)));
			runAction(new OutputTime("Finished Left Side"));

			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new TurnTowardsPoint(new Translation2d(190,210)),
							new RunActionAtAngleRange(-180,-15,new LiftToBottom(true))
					}
			)));
			runAction(new OutputTime("Finished Turn"));
			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new IntakeCube(),
							new DrivePathAction(new LeftSideScalePart1())
					})));
			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new LiftToSwitch(),
							new RunActionAtLiftHeight(5000, new SpitOutCube(.1))
					}
			)));
			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new DrivePathAction(new ThreeCubePart1()),
							new LiftToBottom(true)
					}
			)));

			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new IntakeCube(),
							new DrivePathAction(new ThreeCubePart2())
					})));
			runAction(new DrivePathAction(new ThreeCubePart3()));
			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new TurnTowardsPoint(new Translation2d(600, 210)),
							new LiftToHighScale(false),
							new RunActionAtLiftHeight(16000, new SpitOutCube(.1))
					})));
		} else if(gameData.equals("LR")) {
			PathContainer safeSide = new DriveToSwitch();
			runAction(new ResetPoseFromPathAction(safeSide));
			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new DrivePathAction(safeSide),
							new RunActionAtX(130, new LiftToSwitch())
                    })));
            runAction(new SpitOutCube(.1));
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new TurnTowardsPoint(new Translation2d(280,264)),
                            new LiftToBottom(true)
                    })));
            runAction(new DrivePathAction(new SwitchToCube()));
            runAction(new TurnTowardsPoint(new Translation2d(0,90)));
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new DrivePathAction(new UpOneFootRR(235,60,-12,false)),
                            new IntakeCube()
                    })));
            runAction(new DrivePathAction(new RightSideScaleToBlockPart2()));
            runAction(new OutputTime("Drove to Scale!"));
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new TurnTowardsPoint(new Translation2d(600, 80)),
                            new RunActionAtAngleRange(-45,45, new LiftToHighScale(true)),
                            new RunActionAtLiftHeight(17000, (new SpitOutCube(.1)))
                    })));
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
							new TurnTowardsPoint(new Translation2d(100,100)),
							new RunActionAtAngleRange(-180,-15,new LiftToBottom(true))
					}
			)));
			runAction(new OutputTime("Done with Auto!"));
			runAction(new WaitAction(15));
		} else if(gameData.equals("RL")) {
			PathContainer safeSide = new LeftSideSafe();
			runAction(new ResetPoseFromPathAction(safeSide));
			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new SeriesAction(Arrays.asList(
									new LiftToTeleopHold(),
									new RunActionAtX(263, new RunActionAtLiftHeight(18200, (new SpitOutCube(.1))))
							)),
							new DrivePathAction(safeSide),
							new RunActionAtX(100, new LiftToHighScale(false)),
					}
			)));
			runAction(new OutputTime("Finished Left Side"));
			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new TurnTowardsPoint(new Translation2d(190,210)),
							new RunActionAtAngleRange(-180,-15,new LiftToBottom(true))
					}
			)));
			runAction(new DrivePathAction(new ScaleToSwitch()));
			runAction(new TurnTowardsPoint(new Translation2d(200,70)));

			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new DrivePathAction(new UpOneFootRR(237,75,-15,false)),
							new IntakeCube()
					}
			)));
			runAction(new LiftToSwitch());
			runAction(new SpitOutCube(.1));
			runAction(new LiftToBottom(true));
		} else {
			runAction(new DrivePathAction(new DumbMode()));
		}
	}

}
