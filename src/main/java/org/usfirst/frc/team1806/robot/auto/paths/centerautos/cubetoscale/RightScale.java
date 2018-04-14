package org.usfirst.frc.team1806.robot.auto.paths.centerautos.cubetoscale;


import java.util.ArrayList;

import org.usfirst.frc.team1806.robot.RobotState;
import org.usfirst.frc.team1806.robot.path.PathBuilder.Waypoint;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.path.PathBuilder;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

public class RightScale implements PathContainer {

    @Override
    public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(81,165,0,0));
        sWaypoints.add(new Waypoint(91,130,5,100));
        sWaypoints.add(new Waypoint(103,100,5,100));
        sWaypoints.add(new Waypoint(123,60,5,100));
        sWaypoints.add(new Waypoint(143,45,0,100));
        sWaypoints.add(new Waypoint(160,35,0,100));
        sWaypoints.add(new Waypoint(310,25,0,100));
        sWaypoints.add(new Waypoint(320,35,0,100));
        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }

    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(81, 165), Rotation2d.fromDegrees(RobotState.getInstance().getLatestFieldToVehicle().getValue().getRotation().getDegrees()));
    }

    @Override
    public boolean isReversed() {
        return false;
    }
    // WAYPOINT_DATA: [{"position":{"x":85,"y":165},"speed":0,"radius":0,"comment":""},{"position":{"x":50,"y":100},"speed":60,"radius":0,"comment":""},{"position":{"x":60,"y":60},"speed":60,"radius":0,"comment":""},{"position":{"x":80,"y":40},"speed":60,"radius":0,"comment":""},{"position":{"x":100,"y":30},"speed":60,"radius":0,"comment":""},{"position":{"x":310,"y":30},"speed":60,"radius":0,"comment":""}]
    // IS_REVERSED: false
    // FILE_NAME: UntitledPath
}