/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2583.robot;

import org.usfirst.frc.team2583.robot.commands.TurnTo;
import org.usfirst.frc.team2583.robot.commands.UpdateDash;
import org.usfirst.frc.team2583.robot.commands.auto.BackDrive;
import org.usfirst.frc.team2583.robot.commands.auto.BreakCL;
import org.usfirst.frc.team2583.robot.commands.auto.BreakCR;
import org.usfirst.frc.team2583.robot.commands.auto.ForwardLong;
import org.usfirst.frc.team2583.robot.commands.auto.SwitchCL;
import org.usfirst.frc.team2583.robot.commands.auto.SwitchCR;
import org.usfirst.frc.team2583.robot.commands.auto.SwitchLL;
import org.usfirst.frc.team2583.robot.commands.auto.SwitchLR;
import org.usfirst.frc.team2583.robot.commands.auto.SwitchRL;
import org.usfirst.frc.team2583.robot.commands.auto.SwitchRR;
import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;

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

	private UpdateDash ud = new UpdateDash();
	public static Compressor comp = new Compressor(0);
	
	
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		CameraServer.getInstance().startAutomaticCapture();
		// chooser.addObject("My Auto", new MyAutoCommand());
		comp.setClosedLoopControl(RobotMap.closedLoopControl);
				
		DriveTrain.getInstance().setGear(RobotMap.Gear.LOW); // Start out in low gear
		DriveTrain.getInstance();
		
		OI.getInstance();
		
		ud.start();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
  	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		DriveTrain.getInstance().calibrate();	// Calibrate the imu
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
		if (gameData.length() == 0) {
			gameData = "LRL";  // in case we don't get it e.g. in testing
		}
		System.out.println(gameData);
		
		if(gameData.length() > 0) {
			FieldMap.homeSwitch = Character.toUpperCase(gameData.charAt(0));
			FieldMap.scale = Character.toUpperCase(gameData.charAt(1));
			FieldMap.farSwitch = Character.toUpperCase(gameData.charAt(2));
		}
		
		m_autonomousCommand = selectAuto();
		System.out.println("The auto command selected: " + m_autonomousCommand);
		
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
		
		if (!ud.isRunning()) ud.start();
	}
	
	/**
	 * Purely for testing purposes or last-resort type thing.
	 * Please don't use this at competition unless really required.
	 * 
	 * @return The auto command to run based on the override values
	 */
	private Command autoOverride() {
		String dashIn = SmartDashboard.getString("Override Select", RobotMap.overNothing);
		
		return 
		dashIn.equals(RobotMap.overFL) ?
				new ForwardLong()
	  :	dashIn.equals(RobotMap.overCL) ?
				new SwitchCL()
	  :	dashIn.equals(RobotMap.overCR) ?
				new SwitchCR()
	  :	dashIn.equals(RobotMap.overLL) ?
				new SwitchLL()
	  :	dashIn.equals(RobotMap.overLR) ?
				new SwitchLR()
	  :	dashIn.equals(RobotMap.overRL) ?
				new SwitchRL()
	  :	dashIn.equals(RobotMap.overRR) ?
				new SwitchRR()
	  :	dashIn.equals(RobotMap.overBreakCL) ?
				new BreakCL()
	  :	dashIn.equals(RobotMap.overBreakCR) ?
				new BreakCR()
	  :	dashIn.equals(RobotMap.overTurn) ?
				new TurnTo(SmartDashboard.getNumber("AutoArg", 0))
	  :	dashIn.equals(RobotMap.overNothing) ?
			  	null
	  : new ForwardLong();			
	}
	
	/**
	 * Select the autonomous command best fitting to the circumstances
	 * based on driver settings, position on the field, and the random
	 * state of the nearest switch.
	 * 
	 * Meant to be run at the beginning of autonomous period.
	 * 
	 * @return The autonomous command found
	 */
	private Command selectAuto() {
		
		System.out.println("Selecting autonomous data...");
		
		if(SmartDashboard.getBoolean("Override", false)) {
			System.out.println("Overriding...");
			return autoOverride();
		}
		
		String position = SmartDashboard.getString("StartPos", RobotMap.positionL);
		System.out.println("Our position: " + position);
		String strategy = SmartDashboard.getString("Strategy", RobotMap.strategyBreak);
		System.out.println("Our strategy: " + strategy);
		
		// TODO comment this massive block of horribleness below
		switch(strategy) {
		case RobotMap.strategyBackward:
			return new BackDrive();
		case RobotMap.strategyBreak:
			if(position.equals(RobotMap.positionC)) {
				return new BreakCR();
			}else {
				return new ForwardLong();
			}
		case RobotMap.strategyAny:	// Pay attention to fall-through
			     if(position.equals(RobotMap.positionL) && FieldMap.homeSwitch == 'R') return new SwitchLR();
			else if(position.equals(RobotMap.positionR) && FieldMap.homeSwitch == 'L') return new SwitchRL();
		case RobotMap.strategyOurs: // Including either side if we are center
			System.out.println("Check 0");
			if(position.equals(RobotMap.positionC)) {
				System.out.println("Check 1");
				return FieldMap.homeSwitch == 'L'
						? new SwitchCL()
						: new SwitchCR();
			}else if(position.equals(RobotMap.positionL)) {
				System.out.println("Check 2");
				return FieldMap.homeSwitch == 'L' ? new SwitchLL() : new SwitchLR();
			}else {
				System.out.println("Check 3");
				return FieldMap.homeSwitch == 'R' ? new SwitchRR() : new SwitchRL();
			}
		case RobotMap.strategyNothing:
			return null;
		default:
			return new ForwardLong();
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
		
		DriveTrain.getInstance().resetEncoders();
		DriveTrain.getInstance().setDeadband(RobotMap.deadbandLimit);
		
		if (!ud.isRunning()) ud.start();
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
