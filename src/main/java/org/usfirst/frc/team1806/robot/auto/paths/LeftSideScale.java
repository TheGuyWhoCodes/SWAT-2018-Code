package org.usfirst.frc.team1806.robot.auto.paths;

import org.usfirst.frc.team1806.robot.auto.BluePathAdapter;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

public class LeftSideScale implements PathContainer {
    
    @Override
    public Path buildPath() {

        return BluePathAdapter.leftSideScale();
    }
    
    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(16, 160), Rotation2d.fromDegrees(180.0)); 
    }

    @Override
    public boolean isReversed() {
        return false; 
    }
	// WAYPOINT_DATA: [{"position":{"x":16,"y":160},"speed":0,"radius":0,"comment":""},{"position":{"x":25,"y":160},"speed":60,"radius":5,"comment":""},{"position":{"x":100,"y":250},"speed":60,"radius":20,"comment":""},{"position":{"x":150,"y":270},"speed":60,"radius":30,"comment":""},{"position":{"x":270,"y":250},"speed":60,"radius":0,"comment":""},{"position":{"x":280,"y":250},"speed":60,"radius":0,"comment":""}]
	// IS_REVERSED: true
	// FILE_NAME: LeftSideScale
}