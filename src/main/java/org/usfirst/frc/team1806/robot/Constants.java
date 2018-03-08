package org.usfirst.frc.team1806.robot;

import org.usfirst.frc.team1806.robot.util.Translation2d;

public class Constants {
    public final static double kLooperDt = 0.005;
    public final static double kDriveWheelDiameterInches = 4;
    public final static double kTrackWidthInches = 27.5;
    public final static double kTrackScrubFactor = .978;

    public final static int kDriveTrainPIDSetTimeout = 30;
    public final static double kCountsPerInch = 331.90413;
    public final static double kDriveInchesPerCount = 0.0030129182182819;

    ///Motion

    public final static double kMinLookAhead = 9; // inches
    public final static double kMinLookAheadSpeed = 9.0; // inches per second
    public final static double kMaxLookAhead = 42; // inches
    public final static double kMaxLookAheadSpeed = 120.0; // inches per second
    public final static double kDeltaLookAhead = kMaxLookAhead - kMinLookAhead;
    public final static double kDeltaLookAheadSpeed = kMaxLookAheadSpeed - kMinLookAheadSpeed;

    public final static double kInertiaSteeringGain = 0.0; // angular velocity command is multiplied by this gain *
    // our speed
    // in inches per sec
    public final static double kSegmentCompletionTolerance = 0.5; // inches
    public final static double kPathFollowingMaxAccel = 120.0; // inches per second^2
    public final static double kPathFollowingMaxVel = 120.0; // inches per second
    public final static double kPathFollowingProfileKp = 1.15;
    public final static double kPathFollowingProfileKi = 0.05;
    public final static double kPathFollowingProfileKv = 0.00002;
    public final static double kPathFollowingProfileKffv = 1.2;
    public final static double kPathFollowingProfileKffa = 0.05;
    public final static double kPathFollowingGoalPosTolerance = 0.75;
    public final static double kPathFollowingGoalVelTolerance = 18.0;
    public final static double kPathStopSteeringDistance = 9.0;

    //
    // PID gains for drive velocity loop (HIGH GEAR)
    // Units: setpoint, error, and output are in counts per tenth of a second
    public final static double kDriveHighGearVelocityKp = 0.082; // 1.2/1500;
    public final static double kDriveHighGearVelocityKi = 0.00000; //0.0;
    public final static double kDriveHighGearVelocityKd = 0.4; //0.0001; //6.0/1500;
    public final static double kDriveHighGearVelocityKf = 0.225; //.025;
    public final static int kDriveHighGearVelocityIZone = 0;
    public final static double kDriveHighGearVelocityRampRate = .1;
    public final static double kDriveHighGearNominalOutput = 0.25;
    public final static double kDriveHighGearMaxSetpoint = 10.5 * 12; //FPS

    // PID gains for drive velocity loop (LOW GEAR)
    // Units: setpoint, error, and output are in counts
    public final static double kDriveLowGearPositionKp = .25;
    public final static double kDriveLowGearPositionKi = 0.00;
    public final static double kDriveLowGearPositionKd = 0;
    public final static double kDriveLowGearPositionKf = 0.0;
    public final static int kDriveLowGearPositionIZone = 250;
    public final static double kDriveLowGearPositionRampRate = 240.0; // V/s
    public final static double kDriveLowGearNominalOutput = 0.5; // V
    public final static int kDriveLowGearMaxVelocity = 20146; // Counts
    public final static int kDriveLowGearMaxAccel = 20146; // Counts
    public final static double kDriveTurnMaxPower = .6;
    //PID Gains for the Cube Position closed loop
    // Units: setpoints, error, and output are in counts
    public final static int kCubePositionPIDTimeout = 10;
    public final static double kCubePositionkP = 1;
    public final static double kCubePositionkI = 0.002;
    public final static double kCubePositionkD = 0.0015;
    public final static double kCubePositionkF = 1 / 20000;
    public final static int kCubePositionIZone = 800;
    public final static double kCubePositionRampRate = 0;

    // Encoder constants used by Cube Lift system
    public final static int kSpitOutConstant = 500;
    public final static int kCreepModeLiftHeight = 13000;
    public final static int kHighScaleEncoderCount = 18500;

    public final static int kHighScaleSpitOutCount = kHighScaleEncoderCount - kSpitOutConstant;

    public final static int kNeutralScaleEncoderCount = 15500;

    public final static int kNeutralScaleSpitOutCount = kNeutralScaleEncoderCount - kSpitOutConstant;

    public final static int kWinningScaleEncoderCount = 11700;

    public final static int kWinningScaleSpitOut = kWinningScaleEncoderCount - kSpitOutConstant;

    public final static int kTeleOpHoldHeight = 800;
    public final static int kDropOffEncoderCount = 0;
    public final static int kSwitchEncoderCount = 7000;

    public final static int kSwitchEncoderSpit = kSwitchEncoderCount - kSpitOutConstant;

    public final static int kPositionControlPIDSlot = 0;
    public final static int kEncoderCountsPerInch = 0;
    public final static double kCubeMoveToLimitSwitchSpeed = .2;
    public final static double kCubeHoldPercentOutput = .17;
    public final static double kCubeHoldkPGain = .00005;
    public final static int kCubePositionTolerance = 500;
    public final static int kCubeVelocityTolerance = 100;
    public final static int kCubeTopLimitSwitchPosition = 500;
    public final static int kCubeSpitOutNeedsOuterIntake = 0;
    public final static int kBottomLimitTolerance = 50;
    public final static int kBumpEncoderPosition = 50;

    // Intaking Constants
    public final static double kOuterIntakeSpeed = 1;
    public final static double kInnerIntakeSpeed = 1;
}
