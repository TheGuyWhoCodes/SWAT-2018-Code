package org.usfirst.frc.team1806.robot.auto.paths;

import java.util.ArrayList;

import org.usfirst.frc.team1806.robot.path.PathBuilder.Waypoint;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.path.PathBuilder;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

public class LeftSideSafe implements PathContainer {
    
    @Override
    public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(16,264,0,0));
        sWaypoints.add(new Waypoint(30,264,0,70));
        sWaypoints.add(new Waypoint(190,264,10,120));
        sWaypoints.add(new Waypoint(227,255,10,120));
        sWaypoints.add(new Waypoint(240,240,10,60));
        sWaypoints.add(new Waypoint(270,240,0,60));
        sWaypoints.add(new Waypoint(275,240,0,60));

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
	// WAYPOINT_DATA: [{"position":{"x":16,"y":264},"speed":0,"radius":0,"comment":""},{"position":{"x":30,"y":264},"speed":70,"radius":0,"comment":""},{"position":{"x":190,"y":264},"speed":120,"radius":10,"comment":""},{"position":{"x":227,"y":255},"speed":120,"radius":10,"comment":""},{"position":{"x":240,"y":240},"speed":60,"radius":10,"comment":""},{"position":{"x":270,"y":240},"speed":60,"radius":0,"comment":""},{"position":{"x":275,"y":240},"speed":60,"radius":0,"comment":""}]
	// IS_REVERSED: false
	// FILE_NAME: LeftSideSafe
}