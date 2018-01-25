package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.Robot;
import org.usfirst.frc.team2583.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OperateOutput extends Command {
	
	private RobotMap.Dir dir;
    public OperateOutput(RobotMap.Dir d) {
        dir = d;
    	
        requires(Robot.o_s);
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
    	switch(dir){
    	case UP:
    		Robot.o_s.pushOut();
    		break;
    	case DOWN:
    		Robot.o_s.pullIn();
    		break;
    	default:
    		System.out.println("Invalid argument to OperateOutput!");
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
