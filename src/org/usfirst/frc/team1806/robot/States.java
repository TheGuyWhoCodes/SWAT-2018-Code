package org.usfirst.frc.team1806.robot;

/*
 * The states class is used to track where each
 * subsystem is in the robot, changed with Command Requestors.
 */
//TODO Add more states once we know whats happening with the robot.
public class States {
	public enum CameraType{
		BLOCK, TARGET
	}
	public enum Shifter{
		HIGH, LOW
	}
	public enum Driving{
		DRIVING,VISION,CREEP
	}
	public CameraType cameraTracker;
	public Shifter shifterTracker;
	public Driving drivingTracker;
	public void resetStates(){
		cameraTracker = CameraType.BLOCK;
		drivingTracker = Driving.DRIVING;
		shifterTracker = Shifter.LOW;
	}
}
