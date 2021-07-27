package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.Robot;
import org.usfirst.frc.team2583.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class UpdateDash extends Command {

	// UpdateDash does not require any subsystems
    public UpdateDash() {}

    // Called just before this Command runs the first time
    // There is nothing to initialize
    protected void initialize() {}

    /**
     * Populates the driverstation's Dashboard with variable values
     */
    protected void execute() {
    	// The values of the encoders on the DriveTrain
    	SmartDashboard.putNumber("Left Encoder", Robot.drivetrain.getLeftEncoder());
		SmartDashboard.putNumber("Right Encoder", Robot.drivetrain.getRightEncoder());
		
		// The values of the speed variables [-1,1] as tracked by the DriveTrain
		SmartDashboard.putNumber("Left Speed", Math.abs(Robot.drivetrain.getLSpeed()));
		SmartDashboard.putNumber("Right Speed", Math.abs(Robot.drivetrain.getRSpeed()));
		
		// The driver's mode (fast, slow, reversed)
		SmartDashboard.putBoolean("Reversed", RobotMap.isReversed);
		SmartDashboard.putBoolean("Fast", RobotMap.isFast);
		SmartDashboard.putBoolean("Slow", RobotMap.isSlow);
		
		// The heading (horizontal & vertical) as given by the DriveTrain's gyro
		SmartDashboard.putNumber("Horizontal Heading", Math.sin(Math.toRadians(Robot.drivetrain.getGyroAngle())) * 50 + 50);
		SmartDashboard.putNumber("Vertical Heading", Math.cos(Math.toRadians(Robot.drivetrain.getGyroAngle())) * 50 + 50);
		
		// Updates the DriveTrain's vector readings
		Robot.drivetrain.updateVectors();	// TODO: Could this be the breakpoint?
		
		// The acceleration of the robot, as tracked by the DriveTrain's accelerometer
		SmartDashboard.putNumber("X Accel", Robot.drivetrain.getXAccel());
		SmartDashboard.putNumber("Y Accel", Robot.drivetrain.getYAccel());
		SmartDashboard.putNumber("Z Accel", Robot.drivetrain.getZAccel());
		
		// The velocity of the robot, as tracked by integrating the DriveTrain's accelerometer readings
		SmartDashboard.putNumber("X Velocity", Robot.drivetrain.getXVel());
		SmartDashboard.putNumber("Y Velocity", Robot.drivetrain.getYVel());
		SmartDashboard.putNumber("Z Velocity", Robot.drivetrain.getZVel());
		
		// The position of the robot, as tracked by integrating the velocities above
		SmartDashboard.putNumber("X Position", Robot.drivetrain.getXPos());
		SmartDashboard.putNumber("Y Position", Robot.drivetrain.getYPos());
		SmartDashboard.putNumber("Z Position", Robot.drivetrain.getZPos());
		
		// The number of gears on the tower
		SmartDashboard.putNumber("Gears", RobotMap.gearsPlaced);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;		// This command is never done: it will always continue to update the Dash
    }

    // Called once after isFinished returns true
    protected void end() {}	// This should never have to be called

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {}		// This should never have to be called
}
