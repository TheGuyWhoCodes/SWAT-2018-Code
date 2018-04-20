package org.usfirst.frc.team1806.robot.auto.paths.centerautos.switchtocube;

import java.util.ArrayList;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.RobotState;
import org.usfirst.frc.team1806.robot.auto.BluePathAdapter;
import org.usfirst.frc.team1806.robot.auto.RedPathAdapter;
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
        if(Robot.isBlue){
            return BluePathAdapter.centerAutoLeftSwitchToBackwards();
        } else {
            return RedPathAdapter.centerAutoLeftSwitchToBackwards();
        }
    }

    @Override
    public RigidTransform2d getStartPose() {
        if(Robot.isBlue){
            /// ---- BLUE ---- ///
            return new RigidTransform2d(new Translation2d(123, 215), Rotation2d.fromDegrees(RobotState.getInstance().getLatestFieldToVehicle().getValue().getRotation().getDegrees()));
        } else {
            /// ---- RED ---- ///
            return new RigidTransform2d(new Translation2d(123, 215), Rotation2d.fromDegrees(RobotState.getInstance().getLatestFieldToVehicle().getValue().getRotation().getDegrees()));
        }
    }

    @Override
    public boolean isReversed() {
        return true;
    }
    // WAYPOINT_DATA: [{"position":{"x":116,"y":200},"speed":0,"radius":0,"comment":""},{"position":{"x":90,"y":200},"speed":60,"radius":5,"comment":""},{"position":{"x":70,"y":170},"speed":60,"radius":5,"comment":""},{"position":{"x":60,"y":170},"speed":60,"radius":0,"comment":""}]
    // IS_REVERSED: false
    // FILE_NAME: LeftSwitchToBackwards
}