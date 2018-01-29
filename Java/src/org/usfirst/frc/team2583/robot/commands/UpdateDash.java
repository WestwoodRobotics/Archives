package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class UpdateDash extends Command {

	private DriveTrain dt = DriveTrain.getInstance();
	
    public UpdateDash() {}

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Gear Mode", dt.getGear() == false ? "High" : "Low");
    	SmartDashboard.putNumber("Right Encoder", dt.getEncoders()[0]);
    	SmartDashboard.putNumber("Left Encoder", dt.getEncoders()[1]);
    	
    	// IMU
    	double[] gyroHeadings = {
    		dt.getXHeading(),
    		dt.getYHeading(),
    		dt.getZHeading()
    	};
    	double[] accelVals = {
    		dt.getXAccel(),
    		dt.getYAccel(),
    		dt.getZAccel()
    	};
    	double[] magVals = {
    		dt.getXMag(),
    		dt.getYMag(),
    		dt.getZMag()
    	};
    	SmartDashboard.putNumberArray("Gyro", gyroHeadings);
    	SmartDashboard.putNumberArray("Accel", accelVals);
    	SmartDashboard.putNumberArray("Mag", magVals);
    	SmartDashboard.putNumber("IMU Temp", dt.getTemp());
    	SmartDashboard.putNumber("Pressure", dt.getPressure());
    	
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
