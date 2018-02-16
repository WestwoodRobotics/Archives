package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves the arm based on the input provided through the constructor method
 */
public class OperateArm extends Command {
	
	RobotMap.Dir dir;
	Arm ar = Arm.getInstance();
	
    public OperateArm(RobotMap.Dir d) {
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
    	switch(dir){
    	case UP:
    		return ar.switchClosedUpper();
    	case DOWN:
    		return ar.switchClosedLower();
    	default:
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	ar.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
