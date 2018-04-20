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

public class LeftScale implements PathContainer {

    @Override
    public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(86.5,165,0,0));
        sWaypoints.add(new Waypoint(91,195,0,120));
        sWaypoints.add(new Waypoint(103,230,0,120));
        sWaypoints.add(new Waypoint(123,270,0,120));
        sWaypoints.add(new Waypoint(143,290,0,120));
        sWaypoints.add(new Waypoint(250,290,0,120));
        sWaypoints.add(new Waypoint(341,288,0,120));
        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }

    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(85, 165),  Rotation2d.fromDegrees(RobotState.getInstance().getLatestFieldToVehicle().getValue().getRotation().getDegrees()));
    }

    @Override
    public boolean isReversed() {
        return false;
    }
    // WAYPOINT_DATA: [{"position":{"x":81,"y":165},"speed":0,"radius":0,"comment":""},{"position":{"x":91,"y":195},"speed":100,"radius":0,"comment":""},{"position":{"x":103,"y":230},"speed":100,"radius":0,"comment":""},{"position":{"x":123,"y":270},"speed":100,"radius":0,"comment":""},{"position":{"x":143,"y":285},"speed":100,"radius":0,"comment":""},{"position":{"x":310,"y":305},"speed":100,"radius":0,"comment":""},{"position":{"x":320,"y":290},"speed":100,"radius":0,"comment":""}]
    // IS_REVERSED: false
    // FILE_NAME: LeftScale
}