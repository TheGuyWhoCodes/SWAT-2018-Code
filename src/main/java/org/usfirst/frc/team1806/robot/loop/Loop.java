package org.usfirst.frc.team1806.robot.loop;

public interface Loop {
    public void onStart(double timestamp);

    public void onLoop(double timestamp);

    public void onStop(double timestamp);
}
