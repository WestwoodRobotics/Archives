package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.OI;
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
	
	private boolean reversed;
		
    public TankDrive() {
        requires(dt_s);
              
        setInterruptible(true);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	dt_s.setSquaredInputs(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	leftSpd = OI.getInstance().getJLY();
    	rightSpd = OI.getInstance().getJRY();
    	
    	reversed = OI.getInstance().getLeftTrigger();
    	
    	dt_s.driveWheels((reversed ? -rightSpd : leftSpd), (reversed ? -leftSpd : rightSpd));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	dt_s.driveWheels(0, 0);
    	dt_s.setSquaredInputs(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
