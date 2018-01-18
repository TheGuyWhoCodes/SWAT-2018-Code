package org.usfirst.frc.team1806.robot.auto.paths.scaletoblock;

import java.util.ArrayList;

import org.usfirst.frc.team1806.robot.path.PathBuilder.Waypoint;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.path.PathBuilder;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

public class RightSideScaleToBlockPart2 implements PathContainer {
    
    @Override
    public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(281,75,0,0));
        sWaypoints.add(new Waypoint(210,100,20,60));
//        sWaypoints.add(new Waypoint(220,100,0,30));

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }
    
    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(240, 30), Rotation2d.fromDegrees(0.0)); 
    }

    @Override
    public boolean isReversed() {
        return false; 
    }
	// WAYPOINT_DATA: [{"position":{"x":281,"y":75},"speed":0,"radius":0,"comment":""},{"position":{"x":281,"y":75},"speed":60,"radius":5,"comment":""},{"position":{"x":260,"y":55},"speed":60,"radius":20,"comment":""},{"position":{"x":240,"y":100},"speed":60,"radius":20,"comment":""},{"position":{"x":220,"y":100},"speed":60,"radius":0,"comment":""}]
	// IS_REVERSED: true
	// FILE_NAME: UntitledPath
}