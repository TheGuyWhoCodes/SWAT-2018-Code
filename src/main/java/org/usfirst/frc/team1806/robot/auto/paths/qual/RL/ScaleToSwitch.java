package org.usfirst.frc.team1806.robot.auto.paths.qual.RL;

import org.usfirst.frc.team1806.robot.auto.BluePathAdapter;
import org.usfirst.frc.team1806.robot.RobotState;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

public class ScaleToSwitch implements PathContainer {
    
    @Override
    public Path buildPath() {
        return BluePathAdapter.rightLeftScaleToSwitch();
    }
    
    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(275, 242), Rotation2d.fromDegrees(RobotState.getInstance().getLatestFieldToVehicle().getValue().getRotation().getDegrees())); 
    }

    @Override
    public boolean isReversed() {
        return false; 
    }
	// WAYPOINT_DATA: [{"position":{"x":275,"y":242},"speed":0,"radius":0,"comment":""},{"position":{"x":225,"y":242},"speed":60,"radius":30,"comment":""},{"position":{"x":230,"y":65},"speed":60,"radius":0,"comment":""}]
	// IS_REVERSED: false
	// FILE_NAME: ScaleToSwitch
}