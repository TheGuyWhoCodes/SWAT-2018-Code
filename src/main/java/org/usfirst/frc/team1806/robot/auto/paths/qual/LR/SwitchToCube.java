package org.usfirst.frc.team1806.robot.auto.paths.qual.LR;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.auto.BluePathAdapter;
import org.usfirst.frc.team1806.robot.RobotState;
import org.usfirst.frc.team1806.robot.auto.RedPathAdapter;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

public class SwitchToCube implements PathContainer {
    
    @Override
    public Path buildPath() {
        if(Robot.isBlue){
            return BluePathAdapter.leftRightSwitchToCube();
        } else {
            return RedPathAdapter.leftRightSwitchToCube();
        }
    }
    
    @Override
    public RigidTransform2d getStartPose() {
        if(Robot.isBlue){
            /// ---- BLUE ---- ///
            return new RigidTransform2d(new Translation2d(120, 243), Rotation2d.fromDegrees(RobotState.getInstance().getLatestFieldToVehicle().getValue().getRotation().getDegrees()));
        } else {
            /// ---- RED ---- ///
            return new RigidTransform2d(new Translation2d(120, 243), Rotation2d.fromDegrees(RobotState.getInstance().getLatestFieldToVehicle().getValue().getRotation().getDegrees()));
        }
    }
    
    @Override
    public boolean isReversed() {
        return false; 
    }
	// WAYPOINT_DATA: [{"position":{"x":180,"y":264},"speed":0,"radius":0,"comment":""},{"position":{"x":235,"y":264},"speed":60,"radius":30,"comment":""},{"position":{"x":235,"y":215},"speed":60,"radius":0,"comment":""},{"position":{"x":235,"y":110},"speed":120,"radius":0,"comment":""}]
	// IS_REVERSED: false
	// FILE_NAME: ScaleToSwitch
}