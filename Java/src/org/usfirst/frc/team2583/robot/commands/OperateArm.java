package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.Robot;
import org.usfirst.frc.team2583.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves the arm based on the input provided through the constructor method
 */
public class OperateArm extends Command {
	
	RobotMap.Dir dir;
	
    public OperateArm(RobotMap.Dir d) {
        requires(Robot.ar_s);
        dir = d;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	switch(dir) {
    	case UP:
    		Robot.ar_s.setSpeed(1);
    		break;
    	case DOWN:
    		Robot.ar_s.setSpeed(-1);
    		break;
    	default:
    		Robot.ar_s.setSpeed(0);	
    	}
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
