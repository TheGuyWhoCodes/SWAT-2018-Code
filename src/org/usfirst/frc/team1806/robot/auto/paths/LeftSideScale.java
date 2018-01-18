package org.usfirst.frc.team1806.robot.auto.paths;

import java.util.ArrayList;

import org.usfirst.frc.team1806.robot.path.PathBuilder.Waypoint;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.path.PathBuilder;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

public class LeftSideScale implements PathContainer {
    
    @Override
    public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(16,160,0,0));
        sWaypoints.add(new Waypoint(25,160,5,30));
        sWaypoints.add(new Waypoint(100,250,20,60));
        sWaypoints.add(new Waypoint(150,270,30,60));
        sWaypoints.add(new Waypoint(270,250,0,60));
        sWaypoints.add(new Waypoint(280,250,0,60));

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
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