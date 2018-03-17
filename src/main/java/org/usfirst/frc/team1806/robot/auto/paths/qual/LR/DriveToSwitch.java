package org.usfirst.frc.team1806.robot.auto.paths.qual.LR;

import org.usfirst.frc.team1806.robot.auto.BluePathAdapter;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

public class DriveToSwitch implements PathContainer {
    
    @Override
    public Path buildPath() {
        return BluePathAdapter.leftRightDriveToSwitch();
    }
    
    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(16, 270), Rotation2d.fromDegrees(0.0));
    }

    @Override
    public boolean isReversed() {
        return false; 
    }
	// WAYPOINT_DATA: [{"position":{"x":16,"y":264},"speed":0,"radius":0,"comment":""},{"position":{"x":30,"y":264},"speed":60,"radius":0,"comment":""},{"position":{"x":180,"y":264},"speed":60,"radius":0,"comment":""}]
	// IS_REVERSED: false
	// FILE_NAME: ScaleToSwitch
}