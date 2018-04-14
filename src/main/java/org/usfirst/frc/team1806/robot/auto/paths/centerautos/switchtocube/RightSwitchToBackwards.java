package org.usfirst.frc.team1806.robot.auto.paths.centerautos.switchtocube;

import org.usfirst.frc.team1806.robot.RobotState;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.path.PathBuilder;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

import java.util.ArrayList;

public class RightSwitchToBackwards implements PathContainer {
    @Override
    public Path buildPath() {
        ArrayList<PathBuilder.Waypoint> sWaypoints = new ArrayList<PathBuilder.Waypoint>();
        sWaypoints.add(new PathBuilder.Waypoint(116.75,115,0,0));
        sWaypoints.add(new PathBuilder.Waypoint(90,115,5,60));
        sWaypoints.add(new PathBuilder.Waypoint(70,165,5,60));
        sWaypoints.add(new PathBuilder.Waypoint(55,165,0,60));
        return PathBuilder.buildPathFromWaypoints(sWaypoints);    }

    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(116.75, 115), Rotation2d.fromDegrees(RobotState.getInstance().getLatestFieldToVehicle().getValue().getRotation().getDegrees()));
    }

    @Override
    public boolean isReversed() {
        return true;
    }
}
