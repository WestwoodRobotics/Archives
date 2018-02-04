package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.OI;
import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives the robot based on input from the joysticks
 */
public class TankDrive extends Command {

	OI m_oi = OI.getInstance();
	DriveTrain dt_s = DriveTrain.getInstance();
	
	private double leftSpd;
	private double rightSpd;
	
	private double setpoint;
	
    public TankDrive() {
        requires(dt_s);
        
        setInterruptible(true);
    }
    
    public TankDrive(double leftSpd, double rightSpd, double setpoint) {
    	requires(dt_s);
        
    	this.leftSpd = leftSpd;
    	this.rightSpd = rightSpd;
    	
        //Setpoint is in inches
        this.setpoint = setpoint;
        
        setInterruptible(true);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.dt_s.pidEnable();
    	this.dt_s.pidSetpoint(this.setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	dt_s.driveWheels(leftSpd, rightSpd);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	dt_s.driveWheels(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	dt_s.driveWheels(0, 0);
    }
    
    private double deadband(double x) {
    	return Math.abs(x) > RobotMap.deadbandLimit ? x : 0;
    }
    
}
