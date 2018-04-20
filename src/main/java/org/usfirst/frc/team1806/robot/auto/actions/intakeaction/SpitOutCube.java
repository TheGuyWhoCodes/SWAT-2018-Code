package org.usfirst.frc.team1806.robot.auto.actions.intakeaction;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.auto.actions.Action;
import org.usfirst.frc.team1806.robot.subsystems.superstructure.SnackManipulatorSuperStructure;

public class SpitOutCube implements Action{
    Timer timer = new Timer();
    double startTime;
    double wantedTime;
    double power;
    PowerDistributionPanel powerDistributionPanel;
    public SpitOutCube(double time){
        wantedTime = time;
        power = .75;
    }
    public SpitOutCube(double time, double power){
        wantedTime = time;
        this.power = power;
    }
    @Override
    public boolean isFinished() {
        return timer.hasPeriodPassed(wantedTime);
    }

    @Override
    public void update() {
        SnackManipulatorSuperStructure.getInstance().spitOutCube(power);
    }

    @Override
    public void done() {
        SnackManipulatorSuperStructure.getInstance().stopIntakeMotors();
    }

    @Override
    public void start() {
        timer.reset();
        timer.start();
        System.out.println("We are spitting!");
        startTime = timer.getFPGATimestamp();
        SnackManipulatorSuperStructure.getInstance().spitOutCube(power);
        }
        }
