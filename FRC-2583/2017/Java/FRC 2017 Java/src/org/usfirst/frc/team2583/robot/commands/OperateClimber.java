package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.Robot;
import org.usfirst.frc.team2583.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OperateClimber extends Command {

    public OperateClimber() {
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double speed;
    	//if(Robot.oi.joy.getRawButton(4)) speed = RobotMap.climbSpeed * -1;
    	//else
    	speed = (Robot.oi.joy.getRawButton(5) 
    			? RobotMap.climbSpeed 
    			: Robot.oi.x1.getRawAxis(RobotMap.rightTrigger) * RobotMap.climbSpeed);
    	
    	Robot.climber.run(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
