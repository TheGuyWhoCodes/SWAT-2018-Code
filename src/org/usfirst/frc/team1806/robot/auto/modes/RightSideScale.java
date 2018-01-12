package org.usfirst.frc.team1806.robot.auto.modes;

import java.util.ArrayList;

import org.usfirst.frc.team1806.robot.path.PathBuilder.Waypoint;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.path.PathBuilder;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

public class RightSideScale implements PathContainer {
    
    @Override
    public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(16,160,0,0)); //start point
        sWaypoints.add(new Waypoint(100,50,30,60)); //first
        sWaypoints.add(new Waypoint(160,50,0,70)); //second
        sWaypoints.add(new Waypoint(270,70,0,60)); //third

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
	// WAYPOINT_DATA: [{"position":{"x":16,"y":160},"speed":0,"radius":0,"comment":"start point"},{"position":{"x":100,"y":50},"speed":60,"radius":30,"comment":"first"},{"position":{"x":160,"y":50},"speed":70,"radius":0,"comment":"second"},{"position":{"x":270,"y":70},"speed":60,"radius":0,"comment":"third"}]
	// IS_REVERSED: false
	// FILE_NAME: RightSideScale
}