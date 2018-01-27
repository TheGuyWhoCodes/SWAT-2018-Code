package org.usfirst.frc.team1806.robot.auto.modes;

import org.usfirst.frc.team1806.robot.auto.AutoModeBase;
import org.usfirst.frc.team1806.robot.auto.AutoModeEndedException;
import org.usfirst.frc.team1806.robot.auto.actions.DrivePathAction;
import org.usfirst.frc.team1806.robot.auto.actions.ResetPoseFromPathAction;
import org.usfirst.frc.team1806.robot.auto.actions.WaitAction;
import org.usfirst.frc.team1806.robot.auto.paths.RightSideScale;
import org.usfirst.frc.team1806.robot.auto.paths.RightSideSwitch;
import org.usfirst.frc.team1806.robot.path.PathContainer;

public class RightSideSwitchAuto extends AutoModeBase {
	@Override
	protected void routine() throws AutoModeEndedException {
		PathContainer rightSwitch = new RightSideSwitch();
		runAction(new ResetPoseFromPathAction(rightSwitch));
		runAction(new DrivePathAction(rightSwitch));
		runAction(new WaitAction(15));
	}
}
