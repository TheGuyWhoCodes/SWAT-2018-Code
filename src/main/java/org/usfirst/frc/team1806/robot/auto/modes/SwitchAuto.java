package org.usfirst.frc.team1806.robot.auto.modes;

import edu.wpi.first.wpilibj.DriverStation;
import org.usfirst.frc.team1806.robot.auto.AutoModeBase;
import org.usfirst.frc.team1806.robot.auto.AutoModeEndedException;
import org.usfirst.frc.team1806.robot.auto.actions.*;
import org.usfirst.frc.team1806.robot.auto.actions.intakeaction.SpitOutCube;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToBottom;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToHighScale;
import org.usfirst.frc.team1806.robot.auto.actions.liftactions.LiftToSwitch;
import org.usfirst.frc.team1806.robot.auto.paths.DumbMode;
import org.usfirst.frc.team1806.robot.auto.paths.LeftSideSwitch;
import org.usfirst.frc.team1806.robot.auto.paths.RightSideScale;
import org.usfirst.frc.team1806.robot.auto.paths.RightSideSwitch;
import org.usfirst.frc.team1806.robot.auto.paths.centerautos.switchtocube.LeftSwitchToBackwards;
import org.usfirst.frc.team1806.robot.path.PathContainer;

import java.util.Arrays;


public class SwitchAuto extends AutoModeBase {
	/**
	 * Rarely used, this will be our auto where we start from the center then deposit it at the switch.
	 * @throws AutoModeEndedException
	 */
	@Override
	protected void routine() throws AutoModeEndedException {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage().toUpperCase().substring(0, 2);
		System.out.println(gameData);
		if (gameData.charAt(0) == 'L') {
			PathContainer leftSwitch = new LeftSideSwitch();
			runAction(new ResetPoseFromPathAction(leftSwitch));
			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new RunActionAtX(105, new RunActionAtLiftHeight(7000, (new SpitOutCube(.1)))),
							new DrivePathAction(leftSwitch),
							new RunActionAtX(70, new LiftToSwitch())
					}
			)));
			runAction(new LiftToBottom(true));
			runAction(new DrivePathAction(new LeftSwitchToBackwards()));
			runAction(new WaitAction(15));
		} else if (gameData.charAt(1) == 'R') {
			PathContainer rightSwitch = new RightSideSwitch();
			runAction(new ResetPoseFromPathAction(rightSwitch));
			runAction(new ParallelAction(Arrays.asList(
					new Action[]{
							new RunActionAtX(105, new RunActionAtLiftHeight(7000, (new SpitOutCube(.1)))),
							new DrivePathAction(rightSwitch),
							new RunActionAtX(70, new LiftToSwitch())
					}
			)));
			runAction(new LiftToBottom(true));
		} else {
			runAction(new ResetPoseFromPathAction(new DumbMode()));
			runAction(new DrivePathAction(new DumbMode()));
		}
	}
}
