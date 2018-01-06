package org.usfirst.frc.team1806.robot.subsystems;

import java.util.HashSet;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrainSubsystem extends Subsystem {

	
	// This HashSet is used for PDP usage in our modified Talon code
	private HashSet<Integer> rightSidePDP = new HashSet<Integer>() {{
		int[] PDPValues = {13,14,15};
		add(PDPValues[0]);
		add(PDPValues[1]);
		add(PDPValues[2]);
	}};
	private HashSet<Integer> leftSidePDP = new HashSet<Integer>() {{
		int[] PDPValues = {0,1,2};
		add(PDPValues[0]);
		add(PDPValues[1]);
		add(PDPValues[2]);
	}};
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

