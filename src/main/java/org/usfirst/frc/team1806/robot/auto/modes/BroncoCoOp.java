package org.usfirst.frc.team1806.robot.auto.modes;

import edu.wpi.first.wpilibj.DriverStation;
import org.usfirst.frc.team1806.robot.Constants;
import org.usfirst.frc.team1806.robot.auto.AutoModeBase;
import org.usfirst.frc.team1806.robot.auto.AutoModeEndedException;
import org.usfirst.frc.team1806.robot.auto.actions.*;
import org.usfirst.frc.team1806.robot.auto.actions.intakeaction.IntakeCube;
import org.usfirst.frc.team1806.robot.auto.actions.intakeaction.IntakeNoCheck;
import org.usfirst.frc.team1806.robot.auto.actions.intakeaction.SpitOutCube;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.*;
import org.usfirst.frc.team1806.robot.auto.paths.DumbMode;
import org.usfirst.frc.team1806.robot.auto.paths.LeftSideCrossScale;
import org.usfirst.frc.team1806.robot.auto.paths.LeftSideSafe;
import org.usfirst.frc.team1806.robot.auto.paths.bronco.BroncoRightSidePart1;
import org.usfirst.frc.team1806.robot.auto.paths.bronco.BroncoRightSidePart2;
import org.usfirst.frc.team1806.robot.auto.paths.qual.LR.DriveToSwitch;
import org.usfirst.frc.team1806.robot.auto.paths.qual.LR.SwitchToCube;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.leftside.LeftSideScalePart1;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.leftside.LeftSideScalePart2;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.leftside.LeftSideScalePart3;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.leftside.LeftSideScalePart4;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.rightside.RightSideScaleToBlockPart1;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.rightside.RightSideScaleToBlockPart2;
import org.usfirst.frc.team1806.robot.auto.paths.threecube.ThreeCubePart1;
import org.usfirst.frc.team1806.robot.auto.paths.threecube.ThreeCubePart2;
import org.usfirst.frc.team1806.robot.auto.paths.threecube.ThreeCubePart3;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.util.Translation2d;

import java.util.Arrays;

public class BroncoCoOp extends AutoModeBase {
    @Override
    protected void routine() throws AutoModeEndedException {
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage().toUpperCase().substring(0, 2);
        if(gameData.equals("LL")) {
            PathContainer safeSide = new LeftSideSafe();
            runAction(new ResetPoseFromPathAction(safeSide));
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new SeriesAction(Arrays.asList(
                                    new LiftToTeleopHold(),
                                    new RunActionAtX(260, new RunActionAtLiftHeight(Constants.kHighScaleSpitOutCount, (new SpitOutCube(.1))))
                            )),
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
                            new LiftToSwitch(),
                            new RunActionAtLiftHeight(Constants.kSwitchEncoderSpit, new SpitOutCube(.1))
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
                            new TurnTowardsPoint(new Translation2d(300, 210)),
                            new LiftToHighScale(false),
                            new RunActionAtLiftHeight(Constants.kHighScaleSpitOutCount, new SpitOutCube(.1))
                    })));

        } else if(gameData.equals("LR")) {
            PathContainer safeSide = new DriveToSwitch();
            runAction(new ResetPoseFromPathAction(safeSide));
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new DrivePathAction(safeSide),
                            new LiftToSwitch(),
                            new RunActionAtX(110, new SpitOutCube(.1))
                    })));
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new TurnTowardsPoint(new Translation2d(170,300)),
                            new LiftToBottom(true)
                    })));
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new SeriesAction(Arrays.asList(
                                    new Action[]{
                                            new RunActionAtX(130, new LiftToTeleopHold()),
                                            new RunActionAtY(160, new LiftToBottom(true))
                                    }
                            )),
                            new DrivePathAction(new SwitchToCube()),
                            new RunActionAtY(150, new IntakeCube())

                    })));

            runAction(new LiftToHighScale(false));
            runAction(new RunActionAtLiftHeight(Constants.kHighScaleSpitOutCount, new SpitOutCube(.1)));
        } else if(gameData.charAt(1) == 'R') {
            runAction(new WaitAction(.6));
            PathContainer rightScalePath = new LeftSideCrossScale();
            runAction(new ResetPoseFromPathAction(rightScalePath));
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new SeriesAction(Arrays.asList(
                                    new Action[]{
                                            new LiftToTeleopHold(),
                                            new RunActionAtX(260,new RunActionAtLiftHeight(Constants.kNeutralScaleSpitOutCount, (new SpitOutCube(.1))))
                                    }
                            )),
                            new DrivePathAction(rightScalePath),
                            new RunActionAtY(120, new LiftToNeutralScale()),
                    }
            )));
            runAction(new OutputTime("Done driving towards scale!"));
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new RunActionAtAngleRange(-45,45, new LiftToBottom(false)),
                            new TurnTowardsPoint(new Translation2d(245, 75))
                    }
            )));

            runAction(new OutputTime("Spun towards cube"));
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new IntakeNoCheck(),
                            new DrivePathAction(new BroncoRightSidePart1())
                    })));


            runAction(new LiftToSwitch());
            runAction(new SpitOutCube(.1));


//            runAction(new OutputTime("Grabbed Cube!"));
//            runAction(new DrivePathAction(new BroncoRightSidePart2()));
//            runAction(new OutputTime("Drove to Scale!"));
//            runAction(new ParallelAction(Arrays.asList(
//                    new Action[]{
//                            new TurnTowardsPoint(new Translation2d(300, 75)),
//                            new RunActionAtAngleRange(-120,120, new LiftToNeutralScale()),
//                            new RunActionAtLiftHeight(Constants.kNeutralScaleSpitOutCount, (new SpitOutCube(.1)))
//                    }
//            )));
            runAction(new LiftToBottom(true));
            runAction(new OutputTime("Done with Auto!"));
            runAction(new WaitAction(15));
        } else if(gameData.charAt(1) == 'L'){
            PathContainer safeSide = new LeftSideSafe();
            runAction(new ResetPoseFromPathAction(safeSide));
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new SeriesAction(Arrays.asList(
                                    new LiftToTeleopHold(),
                                    new RunActionAtX(260, new RunActionAtLiftHeight(Constants.kHighScaleSpitOutCount, (new SpitOutCube(.1))))
                            )),
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
            runAction(new DrivePathAction(new LeftSideScalePart2()));
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new TurnTowardsPoint(new Translation2d(280, 220)),
                            new LiftToHighScale(false),
                            new RunActionAtLiftHeight(Constants.kHighScaleSpitOutCount, new SpitOutCube(.1))
                    }
            )));
            runAction(new LiftToBottom(true));
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new IntakeCube(),
                            new DrivePathAction(new LeftSideScalePart3()),
                    })));
            runAction(new DrivePathAction(new LeftSideScalePart4()));
        }else {
            PathContainer dumbMode = new DumbMode();
            //runAction(new ResetPoseFromPathAction(dumbMode));
            runAction(new DrivePathAction(dumbMode));
            runAction(new TurnTowardsPoint(new Translation2d(0, 0)));
        }
    }
}
