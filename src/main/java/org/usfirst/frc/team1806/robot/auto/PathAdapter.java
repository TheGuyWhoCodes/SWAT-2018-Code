package org.usfirst.frc.team1806.robot.auto;

import org.usfirst.frc.team1806.robot.path.Path;
import org.usfirst.frc.team1806.robot.path.PathBuilder;
import org.usfirst.frc.team1806.robot.path.PathBuilder.Waypoint;
import java.util.ArrayList;

/**
 * This class allows us to build our path before hand, hopefully soon we can do measurements on the field to
 * have more precise movements
 */

public class PathAdapter {
    // Path Variables
    static final double kLargeRadius = 45;
    static final double kModerateRadius = 30;
    static final double kNominalRadius = 20;
    static final double kSmallRadius = 10;
    static final double kSpeed = 90;

    private static Path getElimLeftSide = null;
    public static Path getElimLeftSide(){
        if(getElimLeftSide != null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(16,264,0,0));
            sWaypoints.add(new Waypoint(30,264,0,120));
            sWaypoints.add(new Waypoint(190,264,10,90));
            sWaypoints.add(new Waypoint(227,255,10,90));
            sWaypoints.add(new Waypoint(270,240,0,80));

            getElimLeftSide = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return getElimLeftSide;
    }

    private static Path getElimRightSide = null;
    public static Path getElimCrossRightSide(){
        if(getElimRightSide != null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(16,264,0,0));
            sWaypoints.add(new Waypoint(30,264,0,70));
            sWaypoints.add(new Waypoint(190,264,0,120));
            sWaypoints.add(new Waypoint(235,264,30,120));
            sWaypoints.add(new Waypoint(235,215,0,60));
            sWaypoints.add(new Waypoint(235,100,0,120));
            sWaypoints.add(new Waypoint(235,50,20,60));
            sWaypoints.add(new Waypoint(270,50,0,45));
            getElimRightSide = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return getElimRightSide;
    }
    ////////// ------ SCALE TO BLOCK ------ //////////////

    private static Path leftSidePart1 = null;
    public static Path leftSidePart1(){
        if(leftSidePart1 != null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(275,245,0,69));
            sWaypoints.add(new Waypoint(230,225,0,69));

            leftSidePart1 = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return leftSidePart1;
    }

    private static Path leftSidePart2 = null;
    public static Path leftSidePart2(){
        if(leftSidePart2 != null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(240,227,0,0));
            sWaypoints.add(new Waypoint(262,230,0,69));

            leftSidePart2 = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return leftSidePart2;
    }
    private static Path leftSidePart3 = null;
    public static Path leftSidePart3(){
        if(leftSidePart3 != null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new PathBuilder.Waypoint(275,235,0,69));
            sWaypoints.add(new PathBuilder.Waypoint(240,190,0,69));

            leftSidePart3 = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return leftSidePart3;
    }
    private static Path leftSidePart4 = null;
    public static Path leftSidePart4(){
        if(leftSidePart4 != null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new PathBuilder.Waypoint(240,190,0,69));
            sWaypoints.add(new PathBuilder.Waypoint(275,235,0,69));
            leftSidePart4 = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return leftSidePart4;
    }
    private static Path rightSidePart1 = null;
    public static Path rightSidePart1(){
        if(rightSidePart1 != null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(270,50,0,0));
            sWaypoints.add(new Waypoint(245,75,0,120));
            rightSidePart1 = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return rightSidePart1;
    }
    private static Path rightSidePart2 = null;
    public static Path rightSidePart2(){
        if(rightSidePart2 != null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(245,65,0,0));
            sWaypoints.add(new Waypoint(280,55,0,120));
            rightSidePart2 = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return rightSidePart2;
    }

///////////////////////
    // CENTER STUFF
    private static Path leftSideScale = null;
    public static Path leftSideScale(){
        if(leftSideScale != null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(16,160,0,0));
            sWaypoints.add(new Waypoint(25,160,5,30));
            sWaypoints.add(new Waypoint(100,250,20,60));
            sWaypoints.add(new Waypoint(150,270,30,60));
            sWaypoints.add(new Waypoint(270,250,0,60));
            sWaypoints.add(new Waypoint(280,250,0,60));
            leftSideScale = PathBuilder.buildPathFromWaypoints(sWaypoints);

        }
        return leftSideScale;
    }

    private static Path leftSideSwitch = null;
    public static Path leftSideSwitch(){
        if(leftSideSwitch != null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(16,165,0,0));
            sWaypoints.add(new Waypoint(40,165,20,60));
            sWaypoints.add(new Waypoint(70,200,10,60));
            sWaypoints.add(new Waypoint(90,220,20,60));
            sWaypoints.add(new Waypoint(125,220,0,60));
            leftSideSwitch = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return leftSideSwitch;
    }
    private static Path rightSideSwitch = null;
    public static Path rightSideSwitch(){
        if(rightSideSwitch != null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(16,165,0,0));
            sWaypoints.add(new Waypoint(25,165,5,70));
            sWaypoints.add(new Waypoint(75,115,30,70));
            sWaypoints.add(new Waypoint(120,115,0,70));
            sWaypoints.add(new Waypoint(127,115,0,70));
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
        if(leftRightDriveToSwitch != null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(16,264,0,0));
            sWaypoints.add(new Waypoint(30,264,0,100));
            sWaypoints.add(new Waypoint(180,264,0,100));
            leftRightDriveToSwitch = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return leftRightDriveToSwitch;
    }

    private static Path leftRightSwitchToCube = null;
    public static Path leftRightSwitchToCube(){
        if(leftRightSwitchToCube != null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(180,264,0,0));
            sWaypoints.add(new Waypoint(245,264,30,60));
            sWaypoints.add(new Waypoint(245,215,0,60));
            sWaypoints.add(new Waypoint(245,90,0,120));
            leftRightSwitchToCube = PathBuilder.buildPathFromWaypoints(sWaypoints);
        }
        return leftRightSwitchToCube;
    }

    private static Path rightLeftScaleToSwitch = null;

    public static Path rightLeftScaleToSwitch(){
        if(rightLeftScaleToSwitch != null){
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(275,242,0,0));
            sWaypoints.add(new Waypoint(245,232,30,60));
            sWaypoints.add(new Waypoint(245,175,0,80));
            sWaypoints.add(new Waypoint(245,75,0,60));
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
    }
}




