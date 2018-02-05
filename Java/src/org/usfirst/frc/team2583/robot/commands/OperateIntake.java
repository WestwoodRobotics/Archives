package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OperateIntake extends Command {

	final RobotMap.Take t;
	
    public OperateIntake(RobotMap.Take t) {
        requires(Intake.getInstance());
        this.t = t;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	double speed = 
    			t == RobotMap.Take.IN ? 1
    		  : t == RobotMap.Take.OUT ? -1
    		  : 0;
    	
    	Intake.getInstance().setSpeed(RobotMap.maxIntakeSpeedL * speed, RobotMap.maxIntakeSpeedR * speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {}

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
