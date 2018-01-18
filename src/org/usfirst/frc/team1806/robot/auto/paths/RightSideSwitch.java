package org.usfirst.frc.team1806.robot.auto.paths;
import java.util.ArrayList;

import org.usfirst.frc.team1806.robot.path.PathBuilder.Waypoint;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.path.PathBuilder;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

public class RightSideSwitch implements PathContainer {
    
    @Override
    public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(16,165,0,0));
        sWaypoints.add(new Waypoint(25,165,5,70));
        sWaypoints.add(new Waypoint(75,115,30,70));
        sWaypoints.add(new Waypoint(120,115,0,70));
        sWaypoints.add(new Waypoint(127,115,0,70));

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
	// WAYPOINT_DATA: [{"position":{"x":16,"y":165},"speed":0,"radius":0,"comment":""},{"position":{"x":25,"y":165},"speed":70,"radius":5,"comment":""},{"position":{"x":60,"y":115},"speed":70,"radius":30,"comment":""},{"position":{"x":120,"y":105},"speed":70,"radius":0,"comment":""},{"position":{"x":130,"y":105},"speed":70,"radius":0,"comment":""}]
	// IS_REVERSED: false
	// FILE_NAME: RightSideSwitch
}