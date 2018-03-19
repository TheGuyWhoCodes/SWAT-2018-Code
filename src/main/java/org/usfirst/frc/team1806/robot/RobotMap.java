
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
    ///////////// CAN ports 
	
	//Drive train CAN
	public static int rightA = 2; //
	public static int masterRight = 1; //
	public static int leftA = 5;  //
	public static int masterLeft = 4;  //

	
	//Cube Elevator CANs
	public static int cubeMaster = 8;
	public static int cubeSlave = 12;

	
	//Intake CAN Ports
	
	public static int leftInnerIntake = 9;
	public static int rightInnerIntake = 11;

	////////// These are all of the solenoids for the robot
	
	//Shifting
	public static int shiftLow = 0;
	public static int shiftHigh = 1;
	

}
