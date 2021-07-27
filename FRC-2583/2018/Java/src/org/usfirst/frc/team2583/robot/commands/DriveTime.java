package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive for a specified number of seconds
 * 
 * Use this command when we need to ensure that we are pressed up against a wall
 * or that we fully complete a path that we may have undershot, without care of
 * overshooting the target.
 */
public class DriveTime extends Command {

	private DriveTrain dt_s = DriveTrain.getInstance();
	
    public DriveTime(double t) {
        requires(dt_s);
        
        setInterruptible(true);
        setTimeout(t);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	dt_s.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	dt_s.driveWheels(RobotMap.driveTimeSpeed, RobotMap.driveTimeSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
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
}
