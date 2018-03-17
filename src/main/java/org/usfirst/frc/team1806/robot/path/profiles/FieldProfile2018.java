package org.usfirst.frc.team1806.robot.path.profiles;

/**
 * Interface that holds all the 2018 field measurements required by the BluePathAdapter
 * An Implementing class should hold values that correspond to the delta between the measured distance and the official field distance.
 * 
 * @see PathAdapter
 */
public interface FieldProfile2018 {

	public double getRedWallToLeftSwitch();
	
	public double getRedCenterToLeftSwitchOuterEdge();
	
	public double getRedWallToRightSwitch();
	
	public double getRedCenterToRightSwitchOuterEdge();
	
	public double getRedWallToLeftScale();
	
	public double getRedCenterToLeftScaleOuterEdge();
	
	public double getRedWallToRightScale();
	
	public double getRedCenterToRightScaleOuterEdge();
	
	public double getBlueWallToLeftSwitch();
	
	public double getBlueCenterToLeftSwitchOuterEdge();
	
	public double getBlueWallToRightSwitch();
	
	public double getBlueCenterToRightSwitchOuterEdge();
	
	public double getBlueWallToLeftScale();
	
	public double getBlueCenterToLeftScaleOuterEdge();
	
	public double getBlueWallToRightScale();
	
	public double getBlueCenterToRightScaleOuterEdge();

	public double getRedWallToRightScaleOuterEdge();
}
