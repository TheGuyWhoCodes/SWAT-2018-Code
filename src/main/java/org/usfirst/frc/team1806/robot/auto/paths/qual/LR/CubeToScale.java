package org.usfirst.frc.team1806.robot.auto.paths.qual.LR;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.RobotState;
import org.usfirst.frc.team1806.robot.auto.BluePathAdapter;
import org.usfirst.frc.team1806.robot.auto.RedPathAdapter;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

public class CubeToScale implements PathContainer {
    @Override
    public Path buildPath() {

        if(Robot.isBlue){
            return BluePathAdapter.leftRightCubeToSwitch();
        } else {
            return RedPathAdapter.leftRightCubeToSwitch();
        }
    }

    @Override
    public RigidTransform2d getStartPose() {
        if(Robot.isBlue){
            /// ---- BLUE ---- ///
            return new RigidTransform2d(new Translation2d(242, 100), Rotation2d.fromDegrees(RobotState.getInstance().getLatestFieldToVehicle().getValue().getRotation().getDegrees()));
        } else {
            /// ---- RED ---- ///
            return new RigidTransform2d(new Translation2d(242, 100), Rotation2d.fromDegrees(RobotState.getInstance().getLatestFieldToVehicle().getValue().getRotation().getDegrees()));
        }
    }

    @Override
    public boolean isReversed() {
        return false;
    }
    // WAYPOINT_DATA: [{"position":{"x":260,"y":60},"speed":60,"radius":0,"comment":""},{"position":{"x":240,"y":100},"speed":60,"radius":20,"comment":""},{"position":{"x":220,"y":100},"speed":60,"radius":0,"comment":""}]
    // IS_REVERSED: false
    // FILE_NAME: RightSideScaleToBlockPart1
}