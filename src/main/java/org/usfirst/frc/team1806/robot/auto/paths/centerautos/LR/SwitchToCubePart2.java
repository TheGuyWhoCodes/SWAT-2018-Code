package org.usfirst.frc.team1806.robot.auto.paths.centerautos.LR;

import java.util.ArrayList;

import org.usfirst.frc.team1806.robot.RobotState;
import org.usfirst.frc.team1806.robot.path.PathBuilder.Waypoint;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.path.PathBuilder;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

public class SwitchToCubePart2 implements PathContainer {

    @Override
    public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(70,215,0,0));
        sWaypoints.add(new Waypoint(75,200,0,100));
        sWaypoints.add(new Waypoint(80,190,0,70));
        sWaypoints.add(new Waypoint(93,178,0,15));

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }

    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(70, 215),  Rotation2d.fromDegrees(RobotState.getInstance().getLatestFieldToVehicle().getValue().getRotation().getDegrees()));
    }

    @Override
    public boolean isReversed() {
        return false;
    }
    // WAYPOINT_DATA: [{"position":{"x":70,"y":215},"speed":0,"radius":0,"comment":""},{"position":{"x":85,"y":200},"speed":100,"radius":0,"comment":""},{"position":{"x":95,"y":180},"speed":100,"radius":0,"comment":""}]
    // IS_REVERSED: false
    // FILE_NAME: SwitchToCubePart2
}