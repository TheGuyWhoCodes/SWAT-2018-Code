package org.usfirst.frc.team1806.robot.auto.paths.centerautos.switchtocube;

import java.util.ArrayList;

import org.usfirst.frc.team1806.robot.RobotState;
import org.usfirst.frc.team1806.robot.path.PathBuilder.Waypoint;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.path.PathBuilder;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

public class LeftSwitchToBackwards implements PathContainer {

    @Override
    public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(116.75,215,0,0));
        sWaypoints.add(new Waypoint(90,215,5,60));
        sWaypoints.add(new Waypoint(70,165,5,60));
        sWaypoints.add(new Waypoint(55,165,0,60));
        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }

    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(116.75, 215), Rotation2d.fromDegrees(RobotState.getInstance().getLatestFieldToVehicle().getValue().getRotation().getDegrees()));
    }

    @Override
    public boolean isReversed() {
        return true;
    }
    // WAYPOINT_DATA: [{"position":{"x":116,"y":200},"speed":0,"radius":0,"comment":""},{"position":{"x":90,"y":200},"speed":60,"radius":5,"comment":""},{"position":{"x":70,"y":170},"speed":60,"radius":5,"comment":""},{"position":{"x":60,"y":170},"speed":60,"radius":0,"comment":""}]
    // IS_REVERSED: false
    // FILE_NAME: LeftSwitchToBackwards
}