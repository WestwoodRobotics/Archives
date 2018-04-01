package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Extend extends Command {

	private Arm ar = Arm.getInstance();
	
	final RobotMap.Take t;
	
    public Extend(RobotMap.Take t) {
        this.t = t;
        
        setTimeout(2);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	ar.unlockArm();
    	
    	switch(t) {
    	case IN:
    		ar.retract();
    		break;
    	case OUT:
    		ar.extend();
    		break;
    	default:
    		
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
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
