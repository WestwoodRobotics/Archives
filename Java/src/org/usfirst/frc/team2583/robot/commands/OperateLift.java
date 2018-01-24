package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OperateLift extends Command {
	
	boolean isUp = false;
	
    public OperateLift(String pos) {
    	if(pos == "u")
    		isUp = true;
    	else if (pos == "d")
    		isUp = false;
    	
    	requires(Robot.sl_s);
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
    	if(isUp){
    		Robot.sl_s.toTop();
    	}
    	else if(!isUp){
    		Robot.sl_s.toBot();
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
