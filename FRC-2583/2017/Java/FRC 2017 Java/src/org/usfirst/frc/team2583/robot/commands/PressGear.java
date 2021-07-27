package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PressGear extends Command {

	// TODO: fix these values (take experimental results)
	double potExtendedVal = 1;	// The value of the potentiometer when fully extended
	double potRetractedVal = 0;	// The value of the potentiometer when fully retracted
	
	private boolean retracting = false;
	
    public PressGear() {
        requires(Robot.gearMech);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gearMech.extend();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.gearMech.getPot() >= potExtendedVal){
    		Robot.gearMech.retract();
    		retracting = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (retracting == true) && (Robot.gearMech.getPot() <= potRetractedVal);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gearMech.halt();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.gearMech.halt();
    }
}
