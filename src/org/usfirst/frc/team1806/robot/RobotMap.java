
package org.usfirst.frc.team1806.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	/* This is all of the CAN ports for the DriveTrain*/
	public static int masterLeft = 4;
	public static int masterRight = 1;
	public static int leftA = 3;
	public static int leftC = 5;
	public static int rightA = 0;
	public static int rightC = 2;
	
	// These are all of the solenoids for the robot
	public static int shiftLow = 6;
	public static int shiftHigh = 7;
}
