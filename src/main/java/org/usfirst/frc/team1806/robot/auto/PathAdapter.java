package org.usfirst.frc.team1806.robot.auto;

import org.usfirst.frc.team1806.robot.path.Path;

public interface PathAdapter {
    public Path getElimLeftSide();

    public Path threeCubePart1();

    public Path threeCubePart2();

    public Path threeCubePart3();

    public Path getElimCrossRightSide();

    public Path leftSidePart1();

    public Path leftSidePart2();

    public Path leftSidePart3();

    public Path leftSidePart4();

    public Path rightSidePart1();

    public Path rightSidePart2();

    public Path leftSideScale();

    public Path leftSideSwitch();

    public Path rightSideSwitch();

    public Path leftRightDriveToSwitch();

    public Path leftRightSwitchToCube();

    public Path leftRightCubeToSwitch();

    public Path rightLeftScaleToSwitch();

    public void initPaths();
}
