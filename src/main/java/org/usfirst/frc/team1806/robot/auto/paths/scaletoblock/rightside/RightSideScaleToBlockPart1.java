package org.usfirst.frc.team1806.robot.auto.paths.scaletoblock.rightside;

import java.util.ArrayList;

import org.usfirst.frc.team1806.robot.auto.PathAdapter;
import org.usfirst.frc.team1806.robot.path.PathBuilder.Waypoint;
import org.usfirst.frc.team1806.robot.RobotState;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.path.PathBuilder;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

public class RightSideScaleToBlockPart1 implements PathContainer {
    
    @Override
    public Path buildPath() {
        return PathAdapter.rightSidePart1();
    }
    
    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(275, 50), Rotation2d.fromDegrees(RobotState.getInstance().getLatestFieldToVehicle().getValue().getRotation().getDegrees()));
    }

    @Override
    public boolean isReversed() {
        return false; 
    }
	// WAYPOINT_DATA: [{"position":{"x":280,"y":40},"speed":0,"radius":0,"comment":""},{"position":{"x":240,"y":100},"speed":60,"radius":30,"comment":""},{"position":{"x":200,"y":100},"speed":60,"radius":0,"comment":""}]
	// IS_REVERSED: false
	// FILE_NAME: UntitledPath
}