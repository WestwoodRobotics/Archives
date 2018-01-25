package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.subsystems.ScissorLift;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OperateLift extends Command {
	
	private RobotMap.Dir dir;
	
    public OperateLift(RobotMap.Dir d) {
    	dir = d;
    	
    	requires(ScissorLift.getInstance());
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
    	switch(dir) {
    	case UP:
    		ScissorLift.getInstance().toTop();
    		break;
    	case DOWN:
    		ScissorLift.getInstance().toBot();
    		break;
		default:
			break;
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
