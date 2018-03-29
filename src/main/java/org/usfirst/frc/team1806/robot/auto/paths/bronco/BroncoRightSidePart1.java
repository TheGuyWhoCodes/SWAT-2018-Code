package org.usfirst.frc.team1806.robot.auto.paths.bronco;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.RobotState;
import org.usfirst.frc.team1806.robot.auto.BluePathAdapter;
import org.usfirst.frc.team1806.robot.auto.RedPathAdapter;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

public class BroncoRightSidePart1 implements PathContainer {
    @Override
    public Path buildPath() {
        if(Robot.isBlue){
            return BluePathAdapter.broncorightSidePart1();
        } else {
            return RedPathAdapter.broncorightSidePart1();
        }
    }

    @Override
    public RigidTransform2d getStartPose() {
        if(Robot.isBlue){
            /// ---- BLUE ---- ///
            return new RigidTransform2d(new Translation2d(270, 55), Rotation2d.fromDegrees(RobotState.getInstance().getLatestFieldToVehicle().getValue().getRotation().getDegrees()));
        } else {
            /// ---- RED ---- ///
            return new RigidTransform2d(new Translation2d(270, 55), Rotation2d.fromDegrees(RobotState.getInstance().getLatestFieldToVehicle().getValue().getRotation().getDegrees()));
        }
    }

    @Override
    public boolean isReversed() {
        return false;
    }
}