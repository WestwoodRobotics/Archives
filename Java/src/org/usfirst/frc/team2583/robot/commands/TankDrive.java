package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.OI;
import org.usfirst.frc.team2583.robot.Robot;
import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives the robot based on input from the joysticks
 */
public class TankDrive extends Command {

	OI m_oi = Robot.m_oi;
	DriveTrain dt_s = Robot.dt_s;
	
    public TankDrive() {
        requires(Robot.dt_s);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	dt_s.driveWheels(
    			deadband(m_oi.getJLY()), 
    			deadband(m_oi.getJRY()));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	dt_s.driveWheels(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	dt_s.driveWheels(0, 0);
    }
    
    private double deadband(double x) {
    	return x > RobotMap.deadbandLimit ? x : 0;
    }
}
