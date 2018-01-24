/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2583.robot;

import org.usfirst.frc.team2583.robot.subsystems.Arm;
import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2583.robot.subsystems.Output;
import org.usfirst.frc.team2583.robot.subsystems.ScissorLift;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static OI m_oi;
	public static DriveTrain dt_s;
	public static Arm ar_s;
	public static ScissorLift sl_s;
	public static Output o_s;
	public static Compressor comp = new Compressor();
	
	
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		// The order of these initializations are very important: any subsystems referenced in one initializer must be initialized already
		ar_s = new Arm();
		sl_s = new ScissorLift();
		m_oi = new OI();
		dt_s = new DriveTrain();
		o_s = new Output();
		
		CameraServer.getInstance().startAutomaticCapture();
		// chooser.addObject("My Auto", new MyAutoCommand());
		comp.setClosedLoopControl(RobotMap.closedLoopControl);
		SmartDashboard.putData("Auto mode", m_chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		dt_s.calibrate();	// Calibrate the imu
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		// Get and store the results of the randomizations of the switches and scales
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		FieldMap.homeSwitch = gameData.charAt(0);
		FieldMap.scale = gameData.charAt(1);
		FieldMap.farSwitch = gameData.charAt(2);
		
		if (FieldMap.homeSwitch == 'L' || FieldMap.homeSwitch == 'l'){
			SmartDashboard.putString("Home Switch", "Left");
		}
		else{
			SmartDashboard.putString("Home Switch", "Right");
		}
		
		if (FieldMap.scale == 'L' || FieldMap.scale == 'l'){
			SmartDashboard.putString("Scale", "Left");
		}
		else{
			SmartDashboard.putString("Scale", "Right");
		}
		
		if (FieldMap.farSwitch == 'L' || FieldMap.farSwitch == 'l'){
			SmartDashboard.putString("Far Switch", "Left");
		}
		else{
			SmartDashboard.putString("Far Switch", "Right");
		}
		
		m_autonomousCommand = m_chooser.getSelected(); // needs to be replaced

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
