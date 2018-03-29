package org.usfirst.frc.team1806.robot.auto.paths;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.auto.BluePathAdapter;
import org.usfirst.frc.team1806.robot.auto.RedPathAdapter;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.util.RigidTransform2d;
import org.usfirst.frc.team1806.robot.path.PathContainer;
import org.usfirst.frc.team1806.robot.util.Rotation2d;
import org.usfirst.frc.team1806.robot.util.Translation2d;

public class LeftSideCrossScale implements PathContainer {
    
    @Override
    public Path buildPath() {
        if(Robot.isBlue){
            return BluePathAdapter.getElimCrossRightSide();
        } else {
            return RedPathAdapter.getElimCrossRightSide();
        }
    }
    
    @Override
    public RigidTransform2d getStartPose() {
        if(Robot.isBlue){
            /// ---- BLUE ---- ///
            return new RigidTransform2d(new Translation2d(16, 270), Rotation2d.fromDegrees(0.0));
        } else {
            /// ---- RED ---- ///
            return new RigidTransform2d(new Translation2d(16, 270), Rotation2d.fromDegrees(0.0));
        }
    }

    @Override
    public boolean isReversed() {
        return false; 
    }
	// WAYPOINT_DATA: [{"position":{"x":16,"y":264},"speed":0,"radius":0,"comment":""},{"position":{"x":30,"y":264},"speed":70,"radius":0,"comment":""},{"position":{"x":190,"y":264},"speed":120,"radius":0,"comment":""},{"position":{"x":237,"y":264},"speed":120,"radius":30,"comment":""},{"position":{"x":237,"y":215},"speed":60,"radius":0,"comment":""},{"position":{"x":245,"y":55},"speed":120,"radius":20,"comment":""},{"position":{"x":280,"y":65},"speed":60,"radius":0,"comment":""}]
	// IS_REVERSED: false
	// FILE_NAME: LeftSideCrossScale
}