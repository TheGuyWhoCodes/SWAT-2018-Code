package org.usfirst.frc.team1806.robot.auto.actions;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class SpitOutTime implements Action {
	String splitName;
	public SpitOutTime(String splitName) {
		// TODO Auto-generated constructor stub
		this.splitName = splitName;
	}
	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		//Nothing!
	}

	@Override
	public void done() {
		// TODO Auto-generated method stub
		System.out.println(splitName + " Time: " + Timer.getMatchTime());
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		//Nothing
	}

}
