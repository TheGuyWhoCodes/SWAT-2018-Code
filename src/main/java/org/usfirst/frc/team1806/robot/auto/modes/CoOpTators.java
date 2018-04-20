package org.usfirst.frc.team1806.robot.auto.modes;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1806.robot.Constants;
import org.usfirst.frc.team1806.robot.auto.AutoModeBase;
import org.usfirst.frc.team1806.robot.auto.AutoModeEndedException;
import org.usfirst.frc.team1806.robot.auto.actions.*;
import org.usfirst.frc.team1806.robot.auto.actions.intakeaction.IntakeCube;
import org.usfirst.frc.team1806.robot.auto.actions.intakeaction.SpitOutCube;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToBottom;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToHighScale;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToSwitch;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToTeleopHold;
import org.usfirst.frc.team1806.robot.auto.paths.DumbMode;
import org.usfirst.frc.team1806.robot.auto.paths.LeftSideSafe;
import org.usfirst.frc.team1806.robot.auto.paths.TatorMode;
import org.usfirst.frc.team1806.robot.auto.paths.qual.LR.DriveToSwitch;
import org.usfirst.frc.team1806.robot.auto.paths.qual.LR.SwitchToCube;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.leftside.LeftSideScalePart1;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.leftside.LeftSideScalePart2;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.leftside.LeftSideScalePart3;
import org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.leftside.LeftSideScalePart4;
import org.usfirst.frc.team1806.robot.auto.paths.threecube.ThreeCubePart1;
import org.usfirst.frc.team1806.robot.auto.paths.threecube.ThreeCubePart2;
import org.usfirst.frc.team1806.robot.auto.paths.threecube.ThreeCubePart3;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.util.Translation2d;

import java.util.Arrays;

public class CoOpTators extends AutoModeBase {
    @Override
    protected void routine() throws AutoModeEndedException {
        String gameData;
        if(Constants.enableAutoInTeleOp){
            gameData = SmartDashboard.getString("testingFieldValue", "").toUpperCase().substring(0,2);
        } else {
            gameData = DriverStation.getInstance().getGameSpecificMessage().toUpperCase().substring(0, 2);
        }

        if(gameData.equals("LL")) {
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

        } else if(gameData.equals("RR")){
            PathContainer dumbMode = new TatorMode();
            runAction(new ResetPoseFromPathAction(dumbMode));
            runAction(new DrivePathAction(dumbMode));
        } else if(gameData.equals("LR")){
            PathContainer safeSide = new DriveToSwitch();
            runAction(new ResetPoseFromPathAction(safeSide));
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new DrivePathAction(safeSide),
                            new LiftToSwitch(),
                            new RunActionAtX(115, new SpitOutCube(.1, .85))
                    })));
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new TurnTowardsPoint(new Translation2d(170,300)),
                            new LiftToBottom(true)
                    })));
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new DrivePathAction(new SwitchToCube()),
                            new IntakeCube()
                    })));
        } else if(gameData.equals("RL")){
            PathContainer safeSide = new LeftSideSafe();
            runAction(new ResetPoseFromPathAction(safeSide));
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new SeriesAction(Arrays.asList(
                                    new LiftToTeleopHold(),
//									new IntakeWithTimer(.1),
                                    new RunActionAtX(258, new RunActionAtLiftHeight(Constants.kHighScaleSpitOutCount, (new SpitOutCube(.1, .85))))
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
        } else {
            PathContainer dumbMode = new TatorMode();
            runAction(new ResetPoseFromPathAction(dumbMode));
            runAction(new DrivePathAction(dumbMode));
        }
    }
}
