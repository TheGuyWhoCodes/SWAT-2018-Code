package org.usfirst.frc.team1806.robot.subsystems;

public interface LiftInterface extends Subsystem {
	/**
	 * This method will make the master talon go to the setpoint you give it
	 * @param setpoint the height you want the liftactions at
	 */
	public void goToSetpoint(int setpoint);
	
	/**
	 * This will make the Lift go down till it knows there is a signal from the limit switch
	 */
	public void zeroOnBottom();
	
	/**
	 * This will make the motor go to the max will it reaches a signal from the limit switch
	 */
	public void goToTop();
	
	/**
	 * @return a {@link double} representing the current height of the liftactions in inches.
	 */
	public double getHeightInInches();
	/**
	 * 
	 * @return a {@link double} representing the current height of the liftactions in counts from encoder
	 */
	public int getHeightInCounts();
	
	/**
	 * 
	 * @return a {@link boolean} that says if the elevator is heading towards the target value
	 */
	public boolean isOnTarget();
}
