package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.OI;
import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives the robot based on input from the joysticks
 */
public class TankDrive extends Command {

	// Probably a good idea to change this to only be auto
	
	OI m_oi = OI.getInstance();
	DriveTrain dt_s = DriveTrain.getInstance();
	
	private double leftSpd;
	private double rightSpd;
		
    public TankDrive() {
        requires(dt_s);
        
        setInterruptible(true);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.dt_s.resetEncoders();
    	this.dt_s.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	leftSpd = deadband(OI.getInstance().getJLY());
    	rightSpd = deadband(OI.getInstance().getJRY());
    	
    	dt_s.driveWheels(leftSpd, rightSpd);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
    
    private double deadband(double x) {
    	return Math.abs(x) > RobotMap.deadbandLimit ? x : 0;
    }
}
