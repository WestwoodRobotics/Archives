package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.RobotMap.Dir;
import org.usfirst.frc.team2583.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves the arm based on the input provided through the constructor method
 */
public class OperateArm extends Command {
	
	RobotMap.Dir dir;
	Arm ar = Arm.getInstance();
	
	private boolean willTimeout = false;
	
	public OperateArm(Dir d, double seconds) {
		requires(ar);
		willTimeout = true;
		setTimeout(seconds);
	}
	
    public OperateArm(Dir d) {
        requires(ar);
        
        dir = d;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	switch(dir) {
    	case UP:
    		ar.setSpeed(RobotMap.armSpeedUp);
    		break;
    	case DOWN:
    		ar.setSpeed(RobotMap.armSpeedDown);
    		break;
    	default:
    		ar.setSpeed(0);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return !willTimeout || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
