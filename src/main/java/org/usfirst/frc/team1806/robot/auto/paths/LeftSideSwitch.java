package org.usfirst.frc.team1806.robot.auto.paths;
import java.util.ArrayList;

import org.usfirst.frc.team1806.robot.path.PathBuilder.Waypoint;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.path.PathBuilder;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

public class LeftSideSwitch implements PathContainer {

    @Override
    public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(16,165,0,0));
        sWaypoints.add(new Waypoint(40,165,20,60));
        sWaypoints.add(new Waypoint(70,200,10,60));
        sWaypoints.add(new Waypoint(90,220,20,60));
        sWaypoints.add(new Waypoint(125,220,0,60));

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }

    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(16, 165), Rotation2d.fromDegrees(180.0));
    }

    @Override
    public boolean isReversed() {
        return false;
    }
    // WAYPOINT_DATA: [{"position":{"x":16,"y":165},"speed":0,"radius":0,"comment":""},{"position":{"x":40,"y":165},"speed":60,"radius":20,"comment":""},{"position":{"x":70,"y":200},"speed":60,"radius":10,"comment":""},{"position":{"x":90,"y":220},"speed":60,"radius":20,"comment":""},{"position":{"x":125,"y":220},"speed":60,"radius":0,"comment":""}]
    // IS_REVERSED: false
    // FILE_NAME: LeftSideSwitch
}