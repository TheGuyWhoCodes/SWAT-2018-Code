package org.usfirst.frc.team1806.robot.auto.paths;

import java.util.ArrayList;

import org.usfirst.frc.team1806.robot.Robot;
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
        sWaypoints.add(new Waypoint(55,165,25,60));
        sWaypoints.add(new Waypoint(100,70,30,70));
        sWaypoints.add(new Waypoint(200,60,20,80));
        sWaypoints.add(new Waypoint(235,70,10,90));
        sWaypoints.add(new Waypoint(290,75,0,90));
        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }
    
    @Override
    public RigidTransform2d getStartPose() {
        if(Robot.isBlue){
            /// ---- BLUE ---- ///
            return new RigidTransform2d(new Translation2d(16, 165), Rotation2d.fromDegrees(0.0));
        } else {
            /// ---- RED ---- ///
            return new RigidTransform2d(new Translation2d(16, 165), Rotation2d.fromDegrees(0.0));
        }
    }

    @Override
    public boolean isReversed() {
        return false; 
    }
	// WAYPOINT_DATA: [{"position":{"x":16,"y":165},"speed":0,"radius":0,"comment":""},{"position":{"x":55,"y":165},"speed":60,"radius":25,"comment":""},{"position":{"x":100,"y":70},"speed":70,"radius":30,"comment":""},{"position":{"x":200,"y":60},"speed":80,"radius":20,"comment":""},{"position":{"x":235,"y":70},"speed":90,"radius":30,"comment":""},{"position":{"x":280,"y":80},"speed":90,"radius":0,"comment":""},{"position":{"x":281,"y":80},"speed":90,"radius":0,"comment":""}]
	// IS_REVERSED: false
	// FILE_NAME: RightSideScale
}
