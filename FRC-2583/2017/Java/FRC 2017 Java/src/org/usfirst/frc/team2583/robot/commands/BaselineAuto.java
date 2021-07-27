package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.Robot;
import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BaselineAuto extends Command {

	private DriveTrain dt = Robot.drivetrain;
	public static final double driveDistance = 125;	// inches
	
    public BaselineAuto() {
    	requires(dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	dt.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	dt.tankDriveAuto(RobotMap.autoSpeed, RobotMap.autoSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(dt.getRightDistance()) > driveDistance;
    }

    // Called once after isFinished returns true
    protected void end() {
    	dt.tankDriveAuto(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
