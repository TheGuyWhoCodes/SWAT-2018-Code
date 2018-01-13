package org.usfirst.frc.team1806.robot.auto.paths;

import org.usfirst.frc.team1806.robot.auto.AutoModeBase;
import org.usfirst.frc.team1806.robot.auto.AutoModeEndedException;

public class DumbMode extends AutoModeBase {

	@Override
	protected void routine() throws AutoModeEndedException {
		System.out.println("well you're sitting here");
	}

}
