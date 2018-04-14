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
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToNeutralScale;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToSwitch;
import org.usfirst.frc.team1806.robot.auto.paths.LeftSideSwitch;
import org.usfirst.frc.team1806.robot.auto.paths.RightSideSwitch;
import org.usfirst.frc.team1806.robot.auto.paths.centerautos.LR.*;
import org.usfirst.frc.team1806.robot.auto.paths.centerautos.cubetoscale.LeftScale;
import org.usfirst.frc.team1806.robot.auto.paths.centerautos.cubetoscale.RightScale;
import org.usfirst.frc.team1806.robot.auto.paths.centerautos.switchtocube.LeftSwitchToBackwards;
import org.usfirst.frc.team1806.robot.auto.paths.centerautos.switchtocube.LeftSwitchToBackwardsToCube;
import org.usfirst.frc.team1806.robot.auto.paths.centerautos.switchtocube.RightSwitchToBackwards;
import org.usfirst.frc.team1806.robot.auto.paths.centerautos.switchtocube.RightSwitchToBackwardsToCube;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.util.Translation2d;

import java.util.ArrayList;
import java.util.Arrays;

public class CenterAuto extends AutoModeBase {
    @Override
    protected void routine() throws AutoModeEndedException {
        String gameData;
        if(Constants.enableAutoInTeleOp){
            gameData = SmartDashboard.getString("testingFieldValue", "").toUpperCase().substring(0,2);
        } else {
            gameData = DriverStation.getInstance().getGameSpecificMessage().toUpperCase().substring(0, 2);
        }
        if(gameData.equals("RR")) {
            System.out.println("RR bi");
            PathContainer rightSwitch = new RightSideSwitch();
            runAction(new ResetPoseFromPathAction(rightSwitch));
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new RunActionAtX(105, new RunActionAtLiftHeight(Constants.kSwitchEncoderSpit, (new SpitOutCube(.1)))),
                            new DrivePathAction(rightSwitch),
                            new RunActionAtX(40, new LiftToSwitch())
                    }
            )));
            runAction(new LiftToBottom(true));
            runAction(new DrivePathAction(new RightSwitchToBackwards()));
            System.out.println("done with backing up");
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new DrivePathAction(new RightSwitchToBackwardsToCube()),
                            new IntakeCube()
                    }
            )));
            runAction(new TurnTowardsPoint(new Translation2d(91,130)));
            runAction(new DrivePathAction(new RightScale()));
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new LiftToHighScale(true),
                            new RunActionAtLiftHeight(Constants.kHighScaleSpitOutCount, (new SpitOutCube(.1)))
                    }
            )));
        } else if(gameData.equals("LL")){
            PathContainer leftSwitch = new LeftSideSwitch();
            runAction(new ResetPoseFromPathAction(leftSwitch));
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new RunActionAtX(105, new RunActionAtLiftHeight(Constants.kSwitchEncoderSpit, (new SpitOutCube(.1)))),
                            new DrivePathAction(leftSwitch),
                            new RunActionAtX(40, new LiftToSwitch())
                    }
            )));
            runAction(new LiftToBottom(true));
            runAction(new DrivePathAction(new LeftSwitchToBackwards()));
            System.out.println("done with backing up");
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new DrivePathAction(new LeftSwitchToBackwardsToCube()),
                            new IntakeCube()
                    }
            )));
            runAction(new TurnTowardsPoint(new Translation2d(91,195)));
            runAction(new DrivePathAction(new LeftScale()));
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new LiftToHighScale(true),
                            new RunActionAtLiftHeight(Constants.kHighScaleSpitOutCount, (new SpitOutCube(.1)))
                    }
            )));
        } else if(gameData.equals("LR")){
            PathContainer leftSwitch = new LeftSideSwitch();
            runAction(new ResetPoseFromPathAction(leftSwitch));
                runAction(new ParallelAction(Arrays.asList(
                new Action[]{
                new RunActionAtX(105, new RunActionAtLiftHeight(Constants.kSwitchEncoderSpit, (new SpitOutCube(.1)))),
                new DrivePathAction(leftSwitch),
                new RunActionAtX(40, new LiftToSwitch())
                }
                )));
                runAction(new LiftToBottom(true));
                runAction(new DrivePathAction(new LeftSwitchToBackwards()));
                System.out.println("done with backing up");
                runAction(new ParallelAction(Arrays.asList(
                new Action[]{
                new DrivePathAction(new LeftSwitchToBackwardsToCube()),
                new IntakeCube()
                }
                )));
                runAction(new TurnTowardsPoint(new Translation2d(91,130)));
                runAction(new DrivePathAction(new RightScale()));
                runAction(new ParallelAction(Arrays.asList(
                new Action[]{
                new LiftToHighScale(true),
                new RunActionAtLiftHeight(Constants.kHighScaleSpitOutCount, (new SpitOutCube(.1)))
                }
                )));
        } else if(gameData.equals("RL")){
            System.out.println("RR bi");
            PathContainer rightSwitch = new RightSideSwitch();
            runAction(new ResetPoseFromPathAction(rightSwitch));
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new RunActionAtX(105, new RunActionAtLiftHeight(Constants.kSwitchEncoderSpit, (new SpitOutCube(.1)))),
                            new DrivePathAction(rightSwitch),
                            new RunActionAtX(40, new LiftToSwitch())
                    }
            )));
            runAction(new LiftToBottom(true));
            runAction(new DrivePathAction(new RightSwitchToBackwards()));
            System.out.println("done with backing up");
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new DrivePathAction(new RightSwitchToBackwardsToCube()),
                            new IntakeCube()
                    }
            )));
            runAction(new TurnTowardsPoint(new Translation2d(91,195)));
            runAction(new DrivePathAction(new LeftScale()));
            runAction(new ParallelAction(Arrays.asList(
                    new Action[]{
                            new LiftToHighScale(true),
                            new RunActionAtLiftHeight(Constants.kHighScaleSpitOutCount, (new SpitOutCube(.1)))
                    }
            )));
        } else {

        }

//        PathContainer leftSwitch = new LeftSideSwitch();
//        runAction(new ResetPoseFromPathAction(leftSwitch));
//        runAction(new ParallelAction(Arrays.asList(
//                new Action[]{
//                        new RunActionAtX(105, new RunActionAtLiftHeight(Constants.kSwitchEncoderSpit, (new SpitOutCube(.1)))),
//                        new DrivePathAction(leftSwitch),
//                        new RunActionAtX(40, new LiftToSwitch())
//                }
//        )));
//        runAction(new LiftToBottom(true));
//        runAction(new DrivePathAction(new SwitchToCubePart1()));
//        runAction(new ParallelAction(Arrays.asList(
//                new Action[]{
//                        new DrivePathAction(new SwitchToCubePart2()),
//                        new IntakeCube()
//        }
//        )));
//        runAction(new DrivePathAction(new SwitchToCubePart3()));
//        runAction(new ParallelAction(Arrays.asList(
//                new Action[]{
//                        new RunActionAtX(105, new RunActionAtLiftHeight(Constants.kSwitchEncoderSpit, (new SpitOutCube(.1)))),
//                        new DrivePathAction(new SwitchToCubePart4()),
//                        new RunActionAtX(40, new LiftToSwitch())
//                }
//        )));
//        runAction(new DrivePathAction(new SwitchToCubePart5()));
//        runAction(new ParallelAction(Arrays.asList(
//                new Action[]{
//                        new DrivePathAction(new SwitchToCubePart6()),
//                        new IntakeCube()
//                }
//        )));
//        runAction(new DrivePathAction(new ToRightScale()));
    }
}

//

