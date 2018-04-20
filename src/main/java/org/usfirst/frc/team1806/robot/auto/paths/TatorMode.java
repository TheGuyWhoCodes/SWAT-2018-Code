package org.usfirst.frc.team1806.robot.auto.paths;

import java.util.ArrayList;

import org.usfirst.frc.team1806.robot.path.PathBuilder.Waypoint;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.path.PathBuilder;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

public class TatorMode implements PathContainer {

    @Override
    public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(16,270,0,0));
        sWaypoints.add(new Waypoint(30,270,0,60));
        sWaypoints.add(new Waypoint(140,270,0,60));
        sWaypoints.add(new Waypoint(193,270,0,60));
        sWaypoints.add(new Waypoint(240,250,0,60));
        sWaypoints.add(new Waypoint(240,180,0,60));
        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }

    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(16, 270), Rotation2d.fromDegrees(0));
    }

    @Override
    public boolean isReversed() {
        return false;
    }
    // WAYPOINT_DATA: [{"position":{"x":16,"y":270},"speed":0,"radius":0,"comment":""},{"position":{"x":30,"y":270},"speed":60,"radius":0,"comment":""},{"position":{"x":140,"y":270},"speed":60,"radius":0,"comment":""},{"position":{"x":193,"y":270},"speed":60,"radius":0,"comment":""},{"position":{"x":240,"y":250},"speed":60,"radius":0,"comment":""},{"position":{"x":240,"y":180},"speed":60,"radius":0,"comment":""}]
    // IS_REVERSED: false
    // FILE_NAME: TatorMode
}