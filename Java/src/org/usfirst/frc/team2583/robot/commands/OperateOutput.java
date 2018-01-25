package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.subsystems.Output;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OperateOutput extends Command {
	
	private Output o_s = Output.getInstance();
	
	private RobotMap.Dir dir;
    public OperateOutput(RobotMap.Dir d) {
        dir = d;
    	
        requires(o_s);
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
    		o_s.pushOut();
    		break;
    	case DOWN:
    		o_s.pullIn();
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
