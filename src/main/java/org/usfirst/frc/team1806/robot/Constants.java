package org.usfirst.frc.team1806.robot;

import org.usfirst.frc.team1806.robot.util.Translation2d;

public class Constants {
    public static double kLooperDt = 0.005;
    public static double kDriveWheelDiameterInches = 4;
    public static double kTrackWidthInches = 27.5;
    public static double kTrackScrubFactor = .978;
    
    public static double kDriveInchesPerCount = 4.119233974e-4;
    public static int kDriveTrainPIDSetTimeout = 30; 
    public static double kCountsPerInch = 2427.635;


    // Block Locations
    
    public static Translation2d kRightBlockLocation = new Translation2d(200, 100);
    
    ///Motion
    
    public static double kMinLookAhead = 12.0; // inches
    public static double kMinLookAheadSpeed = 9.0; // inches per second
    public static double kMaxLookAhead = 24.0; // inches
    public static double kMaxLookAheadSpeed = 120.0; // inches per second
    public static double kDeltaLookAhead = kMaxLookAhead - kMinLookAhead;
    public static double kDeltaLookAheadSpeed = kMaxLookAheadSpeed - kMinLookAheadSpeed;

    public static double kInertiaSteeringGain = 0.0; // angular velocity command is multiplied by this gain *
                                                     // our speed
                                                     // in inches per sec
    public static double kSegmentCompletionTolerance = 0.25; // inches
    public static double kPathFollowingMaxAccel = 120.0; // inches per second^2
    public static double kPathFollowingMaxVel = 120.0; // inches per second
    public static double kPathFollowingProfileKp = 1.15;
    public static double kPathFollowingProfileKi = 0.06;
    public static double kPathFollowingProfileKv = 0.00002;
    public static double kPathFollowingProfileKffv = 1.25;
    public static double kPathFollowingProfileKffa = 0.05;
    public static double kPathFollowingGoalPosTolerance = 0.75;
    public static double kPathFollowingGoalVelTolerance = 12.0;
    public static double kPathStopSteeringDistance = 9.0;

//
    // PID gains for drive velocity loop (HIGH GEAR)
    // Units: setpoint, error, and output are in counts per tenth of a second
    public static double kDriveHighGearVelocityKp = 0.016 ; // 1.2/1500;
    public static double kDriveHighGearVelocityKi = 0.0000; //0.0;
    public static double kDriveHighGearVelocityKd = 0.2; //0.0001; //6.0/1500;
    public static double kDriveHighGearVelocityKf = .025; //.0025;
    public static int kDriveHighGearVelocityIZone = 0;
    public static double kDriveHighGearVelocityRampRate = .1;
    public static double kDriveHighGearNominalOutput = 0.25;

    public static double kDriveHighGearMaxSetpoint = 10 * 12; //FPS
    // PID gains for drive velocity loop (LOW GEAR)
    // Units: setpoint, error, and output are in counts
    public static double kDriveLowGearPositionKp = .17;
    public static double kDriveLowGearPositionKi = 0.006;
    public static double kDriveLowGearPositionKd = 13.0;
    public static double kDriveLowGearPositionKf = 1/2000;
    public static int kDriveLowGearPositionIZone = 700;
    public static double kDriveLowGearPositionRampRate = 240.0; // V/s
    public static double kDriveLowGearNominalOutput = 0.5; // V
    public static int kDriveLowGearMaxVelocity = 20146; // Counts
    public static int kDriveLowGearMaxAccel = 20146; // Counts
    
    //PID Gains for the Cube Position closed loop
    // Units: setpoints, error, and output are in counts
    public static int kCubePositionPIDTimeout = 10;
    public static double kCubePositionkP = 0.55;
    public static double kCubePositionkI = 0;
    public static double kCubePositionkD = 0;
    public static double kCubePositionkF = 1/20000;
    public static int kCubePositionIZone = 0;
    public static double kCubePositionRampRate = 0;
    
    // Encoder constants used by Cube Lift system
    public static int kHighScaleEncoderCount = 18000;
    public static int kNeutralScaleEncoderCount = 14000;
    public static int kDropOffEncoderCount = 0;
    public static int kSwitchEncoderCount = 7500;
    public static int kPositionControlPIDSlot = 0;
    public static int kEncoderCountsPerInch = 0;
    public static double kCubeMoveToLimitSwitchSpeed = .2;
    public static double kCubeHoldPercentOutput = .08;
    public static int kCubePositionTolerance = 500;
    public static int kCubeVelocityTolerance = 50;
    public static int kCubeTopLimitSwitchPosition = 500;
    public static int kCubeSpitOutNeedsOuterIntake = 0;
    public static int kBottomLimitTolerance = 50;
    
    // Intaking Constants
    public static double kOuterIntakeSpeed = .5;
    public static double kInnerIntakeSpeed = .5;
}
