package org.usfirst.frc.team1806.robot.auto.modes;

import org.usfirst.frc.team1806.robot.auto.AutoModeBase;
import org.usfirst.frc.team1806.robot.auto.AutoModeEndedException;
import org.usfirst.frc.team1806.robot.auto.actions.DrivePathAction;
import org.usfirst.frc.team1806.robot.auto.actions.ResetPoseFromPathAction;
import org.usfirst.frc.team1806.robot.auto.actions.SpitOutTime;
import org.usfirst.frc.team1806.robot.auto.actions.TurnTowardsPoint;
import org.usfirst.frc.team1806.robot.auto.actions.WaitAction;
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

	@Override
	protected void routine() throws AutoModeEndedException {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage().toUpperCase().substring(0, 2);
		System.out.println(gameData);
		if(gameData.equals("RR")) {
			PathContainer rightScalePath = new LeftSideCrossScale();
			runAction(new ResetPoseFromPathAction(rightScalePath));
			runAction(new DrivePathAction(rightScalePath));
			runAction(new TurnTowardsPoint(new Translation2d(245, 65)));
			runAction(new DrivePathAction(new RightSideScaleToBlockPart1()));
			runAction(new DrivePathAction(new UpOneFootRR(245,65,-12,false)));
		} else if(gameData.equals("LL")) {
			PathContainer safeSide = new LeftSideSafe();
			runAction(new ResetPoseFromPathAction(safeSide));
			runAction(new DrivePathAction(safeSide));
			runAction(new SpitOutTime("Finished Left Side"));
			runAction(new TurnTowardsPoint(new Translation2d(190,210)));
			runAction(new SpitOutTime("Finished Turn"));
			runAction(new DrivePathAction(new LeftSideScalePart1()));
			runAction(new DrivePathAction(new UpOneFootRR(240,227,-12,false)));
			runAction(new SpitOutTime("Grabbed Cube"));
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
			runAction(new SpitOutTime("Drove to Scale!"));
			runAction(new TurnTowardsPoint(new Translation2d(600, 80)));
			runAction(new SpitOutTime("Done with Auto!"));
			runAction(new WaitAction(15));
		} else if(gameData.equals("RL")) {
			PathContainer safeSide = new LeftSideSafe();
			runAction(new ResetPoseFromPathAction(safeSide));
			runAction(new DrivePathAction(safeSide));
			runAction(new SpitOutTime("Finished Left Side"));
			runAction(new TurnTowardsPoint(new Translation2d(190,210)));
			runAction(new SpitOutTime("Finished Turn"));
			runAction(new DrivePathAction(new ScaleToSwitch()));
			runAction(new TurnTowardsPoint(new Translation2d(200,70)));
			runAction(new DrivePathAction(new UpOneFootRR(245,75,-20,false)));
		} else {
			runAction(new DrivePathAction(new DumbMode()));
		}
	}

}
