package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.Robot;
import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This is the first choice for autonomous. It drives straight forward to the gear.
 * The robot needs to be aligned to the middle prong already.
 */
public class AutoForwardGear extends Command {

	public final static double driveDistance = 114.3 - 30;	// Drive distance in inches
	
	private final DriveTrain dt = Robot.drivetrain;
	
    public AutoForwardGear() {
    	requires(dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	dt.resetEncoders();
    	dt.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	dt.tankDriveAuto(RobotMap.autoSpeed, RobotMap.autoSpeed * 0.97);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(dt.getLeftDistance() + dt.getRightDistance()) / 2 >= driveDistance;
    }

    // Called once after isFinished returns true
    protected void end() {
    	dt.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
