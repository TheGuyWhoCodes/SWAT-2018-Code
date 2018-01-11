package org.usfirst.frc.team1806.robot.path.profiles;

/**
 * Interface that holds all the 2018 field measurements required by the PathAdapter
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
	
	public double getRedWallToRightScaleOuterEdge();
}
