package org.usfirst.frc.team1806.robot.path.profiles;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

public class CurrentHalfFieldProfile {

	FieldProfile2018 fieldProfile;
	Alliance currentAlliance;
	/**
	 * Creates a profile for the current half field we're on based on the alliance reported by the
	 *  DriverStation and given field configuration.
	 * @param fp
	 */
	public CurrentHalfFieldProfile(FieldProfile2018 fp){
		fieldProfile = fp;
		currentAlliance = DriverStation.getInstance().getAlliance();
	}
	
	public double getWallToLeftSwitch(){
		return currentAlliance == Alliance.Blue? fieldProfile.getBlueWallToLeftSwitch():fieldProfile.getRedWallToLeftSwitch();
	}
	
	public double getCenterToLeftSwitchOuterEdge(){
		return currentAlliance == Alliance.Blue? fieldProfile.getBlueCenterToLeftSwitchOuterEdge():fieldProfile.getRedCenterToLeftSwitchOuterEdge();
	}
	
	public double getWallToRightSwitch(){
		return currentAlliance == Alliance.Blue? fieldProfile.getBlueWallToRightSwitch():fieldProfile.getRedWallToRightSwitch();
	}
	
	public double getCenterToRightSwitchOuterEdge(){
		return currentAlliance == Alliance.Blue? fieldProfile.getBlueCenterToRightSwitchOuterEdge():fieldProfile.getRedCenterToRightSwitchOuterEdge();
	}
	
	public double getWallToLeftScale(){
		return currentAlliance == Alliance.Blue? fieldProfile.getBlueWallToLeftScale():fieldProfile.getRedWallToLeftScale();
	}
	
	public double getCenterToLeftScaleOuterEdge(){
		return currentAlliance == Alliance.Blue? fieldProfile.getBlueCenterToLeftScaleOuterEdge():fieldProfile.getRedCenterToLeftScaleOuterEdge();
	}
	
	public double getWallToRightScale(){
		return currentAlliance == Alliance.Blue? fieldProfile.getBlueWallToRightScale():fieldProfile.getRedWallToRightScale();
	}
	
	public double getCenterToRightScaleOuterEdge(){
		return currentAlliance == Alliance.Blue? fieldProfile.getRedCenterToRightScaleOuterEdge():fieldProfile.getRedCenterToRightScaleOuterEdge();
	}
}
