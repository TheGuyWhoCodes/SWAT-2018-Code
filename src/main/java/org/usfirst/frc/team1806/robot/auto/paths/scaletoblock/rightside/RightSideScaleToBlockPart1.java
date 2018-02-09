package org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.rightside;

import java.util.ArrayList;

import org.usfirst.frc.team1806.robot.path.PathBuilder.Waypoint;
import org.usfirst.frc.team1806.robot.RobotState;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.path.PathBuilder;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

public class RightSideScaleToBlockPart1 implements PathContainer {
    
    @Override
    public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(270,50,0,0));
        sWaypoints.add(new Waypoint(245,75,0,120));

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }
    
    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(260, 40), Rotation2d.fromDegrees(RobotState.getInstance().getLatestFieldToVehicle().getValue().getRotation().getDegrees())); 
    }

    @Override
    public boolean isReversed() {
        return false; 
    }
	// WAYPOINT_DATA: [{"position":{"x":280,"y":40},"speed":0,"radius":0,"comment":""},{"position":{"x":240,"y":100},"speed":60,"radius":30,"comment":""},{"position":{"x":200,"y":100},"speed":60,"radius":0,"comment":""}]
	// IS_REVERSED: false
	// FILE_NAME: UntitledPath
}