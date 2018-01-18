package org.usfirst.frc.team1806.robot;

public class Constants {
    public static double kLooperDt = 0.005;
    public static double kDriveWheelDiameterInches = 3.5;
    public static double kTrackWidthInches = 32;
    public static double kTrackScrubFactor = .978;
    
    public static double kDriveInchesPerCount = 3.573839636812121e-4; 
    public static int kDriveTrainPIDSetTimeout = 15; 
    public static double kCountsPerInch = 2798.111;

    
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
    public static double kPathFollowingMaxAccel = 60.0; // inches per second^2
    public static double kPathFollowingMaxVel = 120.0; // inches per second
    public static double kPathFollowingProfileKp = 1.00;
    public static double kPathFollowingProfileKi = 0.03;
    public static double kPathFollowingProfileKv = 0.02;
    public static double kPathFollowingProfileKffv = 1.0;
    public static double kPathFollowingProfileKffa = 0.05;
    public static double kPathFollowingGoalPosTolerance = 0.75;
    public static double kPathFollowingGoalVelTolerance = 12.0;
    public static double kPathStopSteeringDistance = 9.0;


    // PID gains for drive velocity loop (HIGH GEAR)
    // Units: setpoint, error, and output are in inches per second.
    public static double kDriveHighGearVelocityKp = .06 ; // 1.2/1500;
    public static double kDriveHighGearVelocityKi = 0.00002; //0.0;
    public static double kDriveHighGearVelocityKd = .3; //0.0001; //6.0/1500;
    public static double kDriveHighGearVelocityKf = 1/17000; //.0025;
    public static int kDriveHighGearVelocityIZone = 0;
    public static double kDriveHighGearVelocityRampRate = .5;
    public static double kDriveHighGearNominalOutput = 0.5;
    public static double kDriveHighGearMaxSetpoint = 14 * 12;

    // PID gains for drive velocity loop (LOW GEAR)
    // Units: setpoint, error, and output are in inches per second.
    public static double kDriveLowGearPositionKp = 1.0;
    public static double kDriveLowGearPositionKi = 0.002;
    public static double kDriveLowGearPositionKd = 100.0;
    public static double kDriveLowGearPositionKf = .45;
    public static int kDriveLowGearPositionIZone = 700;
    public static double kDriveLowGearPositionRampRate = 240.0; // V/s
    public static double kDriveLowGearNominalOutput = 0.5; // V
    public static int kDriveLowGearMaxVelocity = (int) (6/ kDriveInchesPerCount); // TODO: make reflect actual robot observed values
    public static int kDriveLowGearMaxAccel = 123456; // TODO: make reflect actual robot observed values

}
