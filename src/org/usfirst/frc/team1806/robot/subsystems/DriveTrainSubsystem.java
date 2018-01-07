package org.usfirst.frc.team1806.robot.subsystems;

import java.awt.Robot;
import java.util.HashSet;

import org.usfirst.frc.team1806.robot.RobotMap;
import org.usfirst.frc.team1806.robot.loop.Looper;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.SPI;

/**
 * The DrivetrainSubsystem deals with all of the drivetrain
 * code used on the robot.
 */
public class DriveTrainSubsystem extends Subsystem{

	//Initialize all of the drive motors
	private TalonSRX leftB;
	private TalonSRX rightB;
	private TalonSRX leftA;
	private TalonSRX leftC;
	private TalonSRX rightA;
	private TalonSRX rightC;
	
	private DoubleSolenoid shifter;
	private AHRS navx;
	// This HashSet is used for PDP usage in our modified Talon code
	private HashSet<Integer> rightSidePDP = new HashSet<Integer>() {{
		int[] PDPValues = {13,14,15};
		add(PDPValues[0]);
		add(PDPValues[1]);
		add(PDPValues[2]);
	}};
	private HashSet<Integer> leftSidePDP = new HashSet<Integer>() {{
		int[] PDPValues = {0,1,2};
		add(PDPValues[0]);
		add(PDPValues[1]);
		add(PDPValues[2]);
	}};
	
	public DriveTrainSubsystem() {
		//init the all of the motor controllers
		leftB = new TalonSRX(RobotMap.leftB);
		rightB = new TalonSRX(RobotMap.rightB);
		leftA = new TalonSRX(RobotMap.leftA);
		leftC = new TalonSRX(RobotMap.leftC);
		rightA = new TalonSRX(RobotMap.rightA);
		rightC = new TalonSRX(RobotMap.rightC);
		
		//Follow for right side 
		rightA.set(com.ctre.phoenix.motorcontrol.ControlMode.Follower, RobotMap.rightB);
		rightC.set(com.ctre.phoenix.motorcontrol.ControlMode.Follower, RobotMap.rightB);
		// Follow for left side
		leftA.set(com.ctre.phoenix.motorcontrol.ControlMode.Follower, RobotMap.leftB);
		leftC.set(com.ctre.phoenix.motorcontrol.ControlMode.Follower, RobotMap.leftB);
		//Invert the left side
		leftA.setInverted(true);
		leftB.setInverted(true);
		leftC.setInverted(true);
		
		// init solenoids
		shifter = new DoubleSolenoid(RobotMap.shiftLow, RobotMap.shiftHigh);
		//init navx
		navx = new AHRS(SPI.Port.kMXP);
	}

	@Override
	public void outputToSmartDashboard() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void zeroSensors() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void registerEnabledLoops(Looper enabledLooper) {
		// TODO Auto-generated method stub
		
	}
	
	//////
	public void leftDrive(double output) {
		leftB.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, output);
	}
	public void rightDrive(double output) {
		rightB.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, output);
	}
	public void arcadeDrive(double power, double turn){
		leftDrive(power + turn); //verify signs
		rightDrive(power - turn);
	}
    public void resetYaw(){
    	navx.zeroYaw();
     }
    
     public void resetNavx(){
    	navx.reset();
     }
    
     public boolean isNavxConnected(){
    	return navx.isConnected();
     }

    
     public double getTrueAngle(){
    	return navx.getAngle();
     }
    
    
     public double getYaw(){
    	return navx.getYaw();
     }
    
     public double getPitch(){
    	return navx.getPitch();
     }
    
     public double getRoll(){
    	return navx.getRoll();
     }
    
     public double getRotationalSpeed(){
    	return navx.getRate();
     }
    
     public double getQuaternion(){
    	return navx.getQuaternionZ() * 180;
     }
    
     public double getTilt(){
    	return Math.sqrt(Math.pow(navx.getPitch(), 2) + Math.pow(navx.getRoll(), 2));
     }

}

