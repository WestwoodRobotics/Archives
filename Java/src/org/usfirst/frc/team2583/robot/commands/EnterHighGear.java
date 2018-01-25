package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EnterHighGear extends Command {

    public EnterHighGear() {
        
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
    	DriveTrain.getInstance().setGear(RobotMap.Gear.HIGH);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
