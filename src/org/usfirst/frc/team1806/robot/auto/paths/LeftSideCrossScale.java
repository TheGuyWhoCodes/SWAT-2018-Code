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
        sWaypoints.add(new Waypoint(30,264,0,70));
        sWaypoints.add(new Waypoint(190,264,0,120));
        sWaypoints.add(new Waypoint(235,264,30,120));
        sWaypoints.add(new Waypoint(235,215,0,60));
        sWaypoints.add(new Waypoint(235,100,0,120));
        sWaypoints.add(new Waypoint(235,45,20,60));
        sWaypoints.add(new Waypoint(280,40,0,45));
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
	// WAYPOINT_DATA: [{"position":{"x":16,"y":264},"speed":0,"radius":0,"comment":""},{"position":{"x":30,"y":264},"speed":70,"radius":0,"comment":""},{"position":{"x":190,"y":264},"speed":120,"radius":0,"comment":""},{"position":{"x":237,"y":264},"speed":120,"radius":30,"comment":""},{"position":{"x":237,"y":215},"speed":60,"radius":0,"comment":""},{"position":{"x":245,"y":55},"speed":120,"radius":20,"comment":""},{"position":{"x":280,"y":65},"speed":60,"radius":0,"comment":""}]
	// IS_REVERSED: false
	// FILE_NAME: LeftSideCrossScale
}