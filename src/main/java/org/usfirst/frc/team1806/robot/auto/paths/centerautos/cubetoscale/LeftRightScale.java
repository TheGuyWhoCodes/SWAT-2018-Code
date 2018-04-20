package org.usfirst.frc.team1806.robot.auto.paths.centerautos.cubetoscale;

import org.usfirst.frc.team1806.robot.RobotState;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.path.PathBuilder;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

import java.util.ArrayList;

public class LeftRightScale implements PathContainer {

    @Override
    public Path buildPath() {
        ArrayList<PathBuilder.Waypoint> sWaypoints = new ArrayList<PathBuilder.Waypoint>();
        sWaypoints.add(new PathBuilder.Waypoint(86.5,165,0,0));
        sWaypoints.add(new PathBuilder.Waypoint(91,130,0,120));
        sWaypoints.add(new PathBuilder.Waypoint(103,100,0,120));
        sWaypoints.add(new PathBuilder.Waypoint(123,60,0,120));
        sWaypoints.add(new PathBuilder.Waypoint(143,45,0,120));
        sWaypoints.add(new PathBuilder.Waypoint(160,40,0,120));
        sWaypoints.add(new PathBuilder.Waypoint(325,36,0,120));
        sWaypoints.add(new PathBuilder.Waypoint(330,36,0,120));
        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }

    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(85, 165), Rotation2d.fromDegrees(RobotState.getInstance().getLatestFieldToVehicle().getValue().getRotation().getDegrees()));
    }

    @Override
    public boolean isReversed() {
        return false;
    }
    // WAYPOINT_DATA: [{"position":{"x":85,"y":165},"speed":0,"radius":0,"comment":""},{"position":{"x":50,"y":100},"speed":60,"radius":0,"comment":""},{"position":{"x":60,"y":60},"speed":60,"radius":0,"comment":""},{"position":{"x":80,"y":40},"speed":60,"radius":0,"comment":""},{"position":{"x":100,"y":30},"speed":60,"radius":0,"comment":""},{"position":{"x":310,"y":30},"speed":60,"radius":0,"comment":""}]
    // IS_REVERSED: false
    // FILE_NAME: UntitledPath

}
