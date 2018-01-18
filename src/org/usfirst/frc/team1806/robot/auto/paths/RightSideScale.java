package org.usfirst.frc.team1806.robot.auto.paths;

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
        sWaypoints.add(new Waypoint(16,165,0,0));
        sWaypoints.add(new Waypoint(55,165,25,80));
        sWaypoints.add(new Waypoint(105,60,25,80));
        sWaypoints.add(new Waypoint(280,70,0,80));

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }
    
    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(16, 165), Rotation2d.fromDegrees(0.0)); 
    }

    @Override
    public boolean isReversed() {
        return false; 
    }
	// WAYPOINT_DATA: [{"position":{"x":16,"y":165},"speed":0,"radius":0,"comment":""},{"position":{"x":55,"y":165},"speed":30,"radius":25,"comment":""},{"position":{"x":105,"y":60},"speed":40,"radius":25,"comment":""},{"position":{"x":280,"y":70},"speed":60,"radius":0,"comment":""}]
	// IS_REVERSED: false
	// FILE_NAME: RightSideScale
}