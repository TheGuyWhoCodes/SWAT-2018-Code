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

public class SwitchToCubePart3 implements PathContainer {

    @Override
    public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(95,180,0,0));
        sWaypoints.add(new Waypoint(85,200,0,100));
        sWaypoints.add(new Waypoint(70,215,0,100));

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }

    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(95, 180),  Rotation2d.fromDegrees(RobotState.getInstance().getLatestFieldToVehicle().getValue().getRotation().getDegrees()));
    }

    @Override
    public boolean isReversed() {
        return true;
    }
    // WAYPOINT_DATA: [{"position":{"x":95,"y":180},"speed":0,"radius":0,"comment":""},{"position":{"x":85,"y":200},"speed":100,"radius":0,"comment":""},{"position":{"x":70,"y":215},"speed":100,"radius":0,"comment":""}]
    // IS_REVERSED: false
    // FILE_NAME: SwitchToCubePart3
}