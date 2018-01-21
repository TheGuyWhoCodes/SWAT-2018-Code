package org.usfirst.frc.team1806.robot.auto.paths;
import java.util.ArrayList;

import org.usfirst.frc.team1806.robot.path.PathBuilder.Waypoint;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.path.PathBuilder;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

public class LeftSideCrossScale implements PathContainer {
    
    @Override
    public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(16,264,0,0));
        sWaypoints.add(new Waypoint(237,264,30,90));
        sWaypoints.add(new Waypoint(245,49,30,80));
        sWaypoints.add(new Waypoint(280,60,0,60));

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }
    
    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(16, 264), Rotation2d.fromDegrees(0.0)); 
    }

    @Override
    public boolean isReversed() {
        return false; 
    }
	// WAYPOINT_DATA: [{"position":{"x":16,"y":260},"speed":0,"radius":0,"comment":""},{"position":{"x":232,"y":260},"speed":60,"radius":30,"comment":""},{"position":{"x":232,"y":65},"speed":60,"radius":30,"comment":""},{"position":{"x":280,"y":65},"speed":60,"radius":0,"comment":""}]
	// IS_REVERSED: true
	// FILE_NAME: LeftSideCrossScale
}