
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
	public static int leftB = 0;
	public static int rightB = 1;
	public static int leftA = 2;
	public static int leftC = 3;
	public static int rightA = 4;
	public static int rightC = 5;
	
	// These are all of the solenoids for the robot
	public static int shiftLow = 0;
	public static int shiftHigh = 1;
}
