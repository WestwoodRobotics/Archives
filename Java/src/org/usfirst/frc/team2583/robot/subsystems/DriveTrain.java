package org.usfirst.frc.team2583.robot.subsystems;

import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.commands.TankDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveTrain extends Subsystem {

	private WPI_TalonSRX frontLeft  = new WPI_TalonSRX(RobotMap.frontLeftTalon);
	private WPI_TalonSRX frontRight = new WPI_TalonSRX(RobotMap.frontRightTalon);
	private WPI_TalonSRX backLeft   = new WPI_TalonSRX(RobotMap.backLeftTalon);
	private WPI_TalonSRX backRight  = new WPI_TalonSRX(RobotMap.backLeftTalon);
	
	private SpeedControllerGroup leftGroup  = new SpeedControllerGroup(frontLeft,  backLeft);
	private SpeedControllerGroup rightGroup = new SpeedControllerGroup(frontRight, backRight);
	
	private DifferentialDrive drive = new DifferentialDrive(leftGroup, rightGroup);
	
    public void initDefaultCommand() {
    	setDefaultCommand(new TankDrive());
    }
    
    public void driveWheels(double left, double right){
    	drive.tankDrive(left, right);
    }
}

