
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
	public static int rightA = 0; //
	public static int masterRight = 1; //
	public static int rightC = 2;
	public static int leftA = 3;  //
	public static int masterLeft = 4;  //
	public static int leftC = 5; //

	
	//Cube Elevator CANs
	public static int cubeMaster = 6;
	public static int cubeSlave = 7;
	
	// Climber CAN Ports
	public static int upMotor = 8;
	public static int downA = 9;
	public static int downB = 10;
	public static int downC = 15;
	
	//Intake CAN Ports
	
	public static int leftInnerIntake = 11;
	public static int rightInnerIntake = 12;
	public static int leftOuterIntake = 13;
	public static int rightOuterIntake = 14;
	
	////////// These are all of the solenoids for the robot
	
	//Shifting
	public static int shiftLow = 0;
	public static int shiftHigh = 1;
	
	
	///////////// DIOs
	//Lift limit switch
	public static int cubeBottomLimit = 0;
	public static int cubeTopLimit = 1;
	public static int cubeDetector = 2;
}
