package org.usfirst.frc.team1806.robot.auto;

import edu.wpi.first.wpilibj.DriverStation;
import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.path.PathBuilder;
import org.usfirst.frc.team1806.robot.path.PathBuilder.Waypoint;
import java.util.ArrayList;

/**
 * This class allows us to build our path before hand, hopefully soon we can do measurements on the field to
 * have more precise movements
 */

public class BluePathAdapter {
    // Path Variables
    static final double kLargeRadius = 45;
    static final double kModerateRadius = 30;
    static final double kNominalRadius = 20;
    static final double kSmallRadius = 10;
    static final double kSpeed = 90;

    private static Path getElimLeftSide = null;
    public static Path getElimLeftSide(){
        if(getElimLeftSide == null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(16,270,0,0));
            sWaypoints.add(new Waypoint(30,270,0,100));
            sWaypoints.add(new Waypoint(190,260,10,100));
            sWaypoints.add(new Waypoint(227,253,10,100));
            sWaypoints.add(new Waypoint(260,250,0,80));
            getElimLeftSide = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return getElimLeftSide;
    }
    public static Path threeCubePart1 = null;

    public static Path threeCubePart1(){
        //Drive back to get to cube
        if(threeCubePart1 == null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(221,229,0,0));
            sWaypoints.add(new Waypoint(240,240,0,120));
            threeCubePart1 = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return threeCubePart1;
    }

    public static Path threeCubePart2 = null;

    public static Path threeCubePart2(){
        //Go Forward to grab cube
        if(threeCubePart2 == null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(240,240,0,0)); //0
            sWaypoints.add(new Waypoint(240,212,0,80));
            sWaypoints.add(new Waypoint(215,195,0,50));
            threeCubePart2 = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return threeCubePart2;
    }
    public static Path threeCubePart3 = null;

    public static Path threeCubePart3(){
        //Back to place it on the scale
        if(threeCubePart3 == null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(215,195,0,0));
            sWaypoints.add(new Waypoint(240,217,0,60));
            sWaypoints.add(new Waypoint(250,225,0,60));
            sWaypoints.add(new Waypoint(260,230,0,60));

            return PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return threeCubePart3;
    }
    private static Path getElimRightSide = null;
    public static Path getElimCrossRightSide(){
        if(getElimRightSide == null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(16,270,0,0));
            sWaypoints.add(new Waypoint(30,270,0,80));
            sWaypoints.add(new Waypoint(140,282,10,80));
            sWaypoints.add(new Waypoint(190,272,10,80));
            sWaypoints.add(new Waypoint(237,250,30,80));
            sWaypoints.add(new Waypoint(237,180,0,80));
            sWaypoints.add(new Waypoint(237,100,0,80));
            sWaypoints.add(new Waypoint(247,55,20,60));
            sWaypoints.add(new Waypoint(274,55,0,60));
            getElimRightSide = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return getElimRightSide;
    }
    ////////// ------ SCALE TO BLOCK ------ //////////////

    private static Path leftSidePart1 = null;
    public static Path leftSidePart1(){
        if(leftSidePart1 == null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(260,250,0,0));
            sWaypoints.add(new Waypoint(244,229,0,70));
            sWaypoints.add(new Waypoint(221,229,0,60));

            leftSidePart1 = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return leftSidePart1;
    }

    private static Path leftSidePart2 = null;
    public static Path leftSidePart2(){
        if(leftSidePart2 == null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(221,229,0,0));
            sWaypoints.add(new Waypoint(270,235,0,69));

            leftSidePart2 = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return leftSidePart2;
    }
    private static Path leftSidePart3 = null;
    public static Path leftSidePart3(){
        if(leftSidePart3 == null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(270,235,0,0));
            sWaypoints.add(new Waypoint(240,230,5,60));
            sWaypoints.add(new Waypoint(240,210,5,60));
            sWaypoints.add(new Waypoint(225,204,0,60));

            leftSidePart3 = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return leftSidePart3;
    }
    private static Path leftSidePart4 = null;
    public static Path leftSidePart4(){
        if(leftSidePart4 == null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new PathBuilder.Waypoint(225,204,0,69));
            sWaypoints.add(new PathBuilder.Waypoint(272,230,0,69));
            leftSidePart4 = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return leftSidePart4;
    }
    private static Path rightSidePart1 = null;
    public static Path rightSidePart1(){
        if(rightSidePart1 == null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(274,55,0,0));
            sWaypoints.add(new Waypoint(240,66,0,120));
            sWaypoints.add(new Waypoint(220,70,0,120));
            rightSidePart1 = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return rightSidePart1;
    }
    private static Path rightSidePart2 = null;
    public static Path rightSidePart2(){
        if(rightSidePart2 == null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(220,70,0,0));
            sWaypoints.add(new Waypoint(282,45,20,120));
            rightSidePart2 = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return rightSidePart2;
    }
    private static Path broncorightSidePart1 = null;
    public static Path broncorightSidePart1(){
        if(broncorightSidePart1 == null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(270,55,0,0));
//            sWaypoints.add(new Waypoint(237,90,5,80));
            sWaypoints.add(new Waypoint(217,90,0,40));


            broncorightSidePart1 = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return broncorightSidePart1;
    }
    private static Path broncorightSidePart2 = null;
    public static Path broncorightSidePart2(){
        if(broncorightSidePart2 == null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(217,90,0,0));
            sWaypoints.add(new Waypoint(250,55,0,80));
            sWaypoints.add(new Waypoint(265,55,0,80));
            broncorightSidePart2 = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return broncorightSidePart2;
    }

    ///////////////////////
    // CENTER STUFF
    private static Path leftSideScale = null;
    public static Path leftSideScale(){
        if(leftSideScale == null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(16,160,0,0));
            sWaypoints.add(new Waypoint(25,160,5,30));
            sWaypoints.add(new Waypoint(100,250,20,60));
            sWaypoints.add(new Waypoint(150,270,30,60));
            sWaypoints.add(new Waypoint(270,260,0,60));
            sWaypoints.add(new Waypoint(280,250,0,60));
            leftSideScale = PathBuilder.buildPathFromWaypoints(sWaypoints);

        }
        return leftSideScale;
    }

    private static Path leftSideSwitch = null;
    public static Path leftSideSwitch(){
        if(leftSideSwitch == null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(16,165,0,0));
            sWaypoints.add(new Waypoint(40,165,0,60));
            sWaypoints.add(new Waypoint(70,200,0,60));
            sWaypoints.add(new Waypoint(90,220,0,60));
            sWaypoints.add(new Waypoint(120,220,0,60));
            leftSideSwitch = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return leftSideSwitch;
    }
    private static Path rightSideSwitch = null;
    public static Path rightSideSwitch(){
        if(rightSideSwitch == null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(16,165,0,0));
            sWaypoints.add(new Waypoint(25,165,5,60));
            sWaypoints.add(new Waypoint(75,115,0,60));
            sWaypoints.add(new Waypoint(120,115,0,60));
            rightSideSwitch = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return rightSideSwitch;
    }
    ////////// ------ VARIOUS QUAL PATHS ------ //////////////

    /*
     * LLL
     * RRL
     * LRL
     * RLL
     */
    private static Path leftRightDriveToSwitch = null;
    public static Path leftRightDriveToSwitch(){
        if(leftRightDriveToSwitch == null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(16,270,0,0));
            sWaypoints.add(new Waypoint(30,270,5,90));
            sWaypoints.add(new Waypoint(50,270,0,90));
            sWaypoints.add(new Waypoint(102,248,0,90));
            sWaypoints.add(new Waypoint(111,242,0,60));

            leftRightDriveToSwitch = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return leftRightDriveToSwitch;
    }

    private static Path leftRightSwitchToCube = null;
    public static Path leftRightSwitchToCube(){
        if(leftRightSwitchToCube == null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(111,242,0,0));
            sWaypoints.add(new Waypoint(135,265,0,100));
            sWaypoints.add(new Waypoint(190,272,0,100));
            sWaypoints.add(new Waypoint(230,250,0,100));
            sWaypoints.add(new Waypoint(240,180,0,60));
            sWaypoints.add(new Waypoint(238,130,0,60));
            sWaypoints.add(new Waypoint(219,90,0,60));
            sWaypoints.add(new Waypoint(213,80,0,60));
            sWaypoints.add(new Waypoint(207,35,0,60));
            sWaypoints.add(new Waypoint(265,40,0,75));
            leftRightSwitchToCube = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return leftRightSwitchToCube;
    }
    private static Path leftRightCubeToSwitch = null;
    public static Path leftRightCubeToSwitch() {
        if(leftRightCubeToSwitch == null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(242,100,0,0));
            sWaypoints.add(new Waypoint(205,80,10,60));
            sWaypoints.add(new Waypoint(205,35,20,60));
            sWaypoints.add(new Waypoint(270,70,0,60));

            leftRightCubeToSwitch = PathBuilder.buildPathFromWaypoints(sWaypoints);

        }
        return leftRightCubeToSwitch;
    }
    private static Path rightLeftScaleToSwitch = null;

    public static Path rightLeftScaleToSwitch(){
        if(rightLeftScaleToSwitch == null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(275,235,0,0));
            sWaypoints.add(new Waypoint(237,232,30,60));
            sWaypoints.add(new Waypoint(237,175,0,80));
            sWaypoints.add(new Waypoint(237,75,0,60));
            rightLeftScaleToSwitch = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return rightLeftScaleToSwitch;
    }


    public static void initPaths(){
        getElimLeftSide();
        rightLeftScaleToSwitch();
        leftRightSwitchToCube();
        leftRightDriveToSwitch();
        rightSidePart2();
        rightSidePart1();
        leftSidePart4();
        leftSidePart2();
        leftSidePart3();
        leftSidePart1();
        getElimCrossRightSide();
        threeCubePart1();
        threeCubePart2();
        threeCubePart3();
        leftRightCubeToSwitch();
    }
}



