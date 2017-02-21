package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StartFast extends Command {

    public StartFast() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.fastToggle = true;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
