package org.usfirst.frc.team1806.robot.auto.paths;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.auto.BluePathAdapter;
import org.usfirst.frc.team1806.robot.auto.RedPathAdapter;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

public class LeftSideSwitch implements PathContainer {

    @Override
    public Path buildPath() {
        if(Robot.isBlue){
            return BluePathAdapter.leftSideSwitch();
        } else {
            return RedPathAdapter.leftSideSwitch();
        }
    }

    @Override
    public RigidTransform2d getStartPose() {
        if(Robot.isBlue){
            /// ---- BLUE ---- ///
            return new RigidTransform2d(new Translation2d(16, 165), Rotation2d.fromDegrees(0));
        } else {
            /// ---- RED ---- ///
            return new RigidTransform2d(new Translation2d(16, 165), Rotation2d.fromDegrees(0));
        }
    }

    @Override
    public boolean isReversed() {
        return false;
    }
    // WAYPOINT_DATA: [{"position":{"x":16,"y":165},"speed":0,"radius":0,"comment":""},{"position":{"x":40,"y":165},"speed":60,"radius":20,"comment":""},{"position":{"x":70,"y":200},"speed":60,"radius":10,"comment":""},{"position":{"x":90,"y":220},"speed":60,"radius":20,"comment":""},{"position":{"x":125,"y":220},"speed":60,"radius":0,"comment":""}]
    // IS_REVERSED: false
    // FILE_NAME: LeftSideSwitch
}