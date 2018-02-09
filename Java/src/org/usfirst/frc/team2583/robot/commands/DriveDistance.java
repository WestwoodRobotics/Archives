package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistance extends Command {

	DriveTrain dt_s = DriveTrain.getInstance();
	double inches = 0;
	
    public DriveDistance(double inches) {
        requires(dt_s);
        
        this.inches = inches;
        
        setInterruptible(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.dt_s.resetEncoders();
    	this.dt_s.setSetpoint(this.inches);
    	this.dt_s.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.dt_s.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	this.dt_s.disable();
    	dt_s.driveWheels(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.dt_s.disable();
    	dt_s.driveWheels(0, 0);
    }
}
