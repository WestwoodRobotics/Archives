package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.FieldMap;
import org.usfirst.frc.team2583.robot.subsystems.Arm;
import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2583.robot.subsystems.Intake;

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
    	SmartDashboard.putBoolean("High Gear", dt.getGear());
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
    	
    	char h = FieldMap.homeSwitch;
    	char s = FieldMap.scale;
    	char f = FieldMap.farSwitch;
    	SmartDashboard.putString("Home Switch", h == 'L' ? "Left" : "Right");
    	SmartDashboard.putString("Scale", s == 'L' ? "Left" : "Right");
    	SmartDashboard.putString("Far Switch", f == 'L' ? "Left" : "Right");
    	
    	SmartDashboard.putNumberArray("Gyro", gyroHeadings);
    	SmartDashboard.putNumberArray("Accel", accelVals);
    	SmartDashboard.putNumberArray("Mag", magVals);
    	SmartDashboard.putNumber("IMU Temp", dt.getTemp());
    	SmartDashboard.putNumber("Pressure", dt.getPressure());
    	
    	SmartDashboard.putData(DriveTrain.getInstance());
    	SmartDashboard.putData(Arm.getInstance());
    	SmartDashboard.putData(Intake.getInstance());
    	
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
