package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopArm extends Command {
	
	Arm ar = Arm.getInstance();

    public StopArm() {
    	requires(ar);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(ar.isLocked()) {
    		ar.unlockArm();
    	}
    	
    	ar.stopArm();
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
    	ar.lockArm();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
