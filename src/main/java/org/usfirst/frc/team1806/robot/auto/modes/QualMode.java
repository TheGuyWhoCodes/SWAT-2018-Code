package org.usfirst.frc.team1806.robot.auto.modes;

import org.usfirst.frc.team1806.robot.auto.AutoModeBase;
import org.usfirst.frc.team1806.robot.auto.AutoModeEndedException;
import org.usfirst.frc.team1806.robot.auto.actions.DrivePathAction;
import org.usfirst.frc.team1806.robot.auto.actions.ResetPoseFromPathAction;
import org.usfirst.frc.team1806.robot.auto.actions.OutputTime;
import org.usfirst.frc.team1806.robot.auto.actions.TurnTowardsPoint;
import org.usfirst.frc.team1806.robot.auto.actions.WaitAction;
import org.usfirst.frc.team1806.robot.auto.actions.intakeaction.SpitOutCube;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToBottom;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToHighScale;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToNeutralScale;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToSwitch;
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
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.util.Translation2d;

import edu.wpi.first.wpilibj.DriverStation;

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
		System.out.println(gameData);
		if(gameData.equals("RR")) {
			PathContainer rightScalePath = new LeftSideCrossScale();
			runAction(new ResetPoseFromPathAction(rightScalePath));
			runAction(new DrivePathAction(rightScalePath));
			runAction(new LiftToHighScale());
			runAction(new SpitOutCube(.2));
			runAction(new LiftToBottom());
			runAction(new TurnTowardsPoint(new Translation2d(245, 65)));
			runAction(new DrivePathAction(new RightSideScaleToBlockPart1()));
			runAction(new DrivePathAction(new UpOneFootRR(245,65,-12,false)));
			runAction(new LiftToSwitch());
			runAction(new SpitOutCube(.2));
		} else if(gameData.equals("LL")) {
			PathContainer safeSide = new LeftSideSafe();
			runAction(new ResetPoseFromPathAction(safeSide));
			runAction(new DrivePathAction(safeSide));
			runAction(new LiftToNeutralScale());
			runAction(new SpitOutCube(.2));
			runAction(new OutputTime("Finished Left Side"));
			runAction(new LiftToBottom());
			runAction(new TurnTowardsPoint(new Translation2d(190,210)));
			runAction(new OutputTime("Finished Turn"));
			runAction(new DrivePathAction(new LeftSideScalePart1()));
			runAction(new DrivePathAction(new UpOneFootRR(240,227,-12,false)));
			runAction(new OutputTime("Grabbed Cube"));
			runAction(new LiftToSwitch());
			runAction(new SpitOutCube(.2));
		} else if(gameData.equals("LR")) {
			PathContainer safeSide = new DriveToSwitch();
			runAction(new ResetPoseFromPathAction(safeSide));
			runAction(new DrivePathAction(safeSide));
			runAction(new TurnTowardsPoint(new Translation2d(180,60)));
			runAction(new WaitAction(1));
			runAction(new TurnTowardsPoint(new Translation2d(280,264)));
			runAction(new DrivePathAction(new SwitchToCube()));
			runAction(new TurnTowardsPoint(new Translation2d(0,90)));
			runAction(new DrivePathAction(new UpOneFootRR(245,90,-12,false)));
			runAction(new DrivePathAction(new RightSideScaleToBlockPart2()));
			runAction(new OutputTime("Drove to Scale!"));
			runAction(new TurnTowardsPoint(new Translation2d(600, 80)));
			runAction(new OutputTime("Done with Auto!"));
			runAction(new WaitAction(15));
		} else if(gameData.equals("RL")) {
			PathContainer safeSide = new LeftSideSafe();
			runAction(new ResetPoseFromPathAction(safeSide));
			runAction(new DrivePathAction(safeSide));
			runAction(new OutputTime("Finished Left Side"));
			runAction(new TurnTowardsPoint(new Translation2d(190,210)));
			runAction(new OutputTime("Finished Turn"));
			runAction(new DrivePathAction(new ScaleToSwitch()));
			runAction(new TurnTowardsPoint(new Translation2d(200,70)));
			runAction(new DrivePathAction(new UpOneFootRR(245,75,-20,false)));
		} else {
			runAction(new DrivePathAction(new DumbMode()));
		}
	}

}
