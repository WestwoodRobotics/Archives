package org.usfirst.frc.team2583.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleFlap extends Command {
	
	boolean state;
	
    public ToggleFlap() {
        //requires(Robot.flap);
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
    	//Robot.flap.togglePos();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
