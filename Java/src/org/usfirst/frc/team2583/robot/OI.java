/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2583.robot;

import org.usfirst.frc.team2583.robot.commands.OperateArm;
import org.usfirst.frc.team2583.robot.commands.OperateLift;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	// Joysticks
	Joystick jRight = new Joystick(1);
	Joystick jLeft	= new Joystick(2);
	
	// Xbox Controller and buttons and stuff
	XboxController x1 			= new XboxController(0);
	Button buttonA 				= new JoystickButton(x1, 1);
	Button buttonB 				= new JoystickButton(x1, 2);
	Button buttonX 				= new JoystickButton(x1, 3);
	Button buttonY 				= new JoystickButton(x1, 4);
	Button leftBumper 			= new JoystickButton(x1, 5);
	Button rightBumper 			= new JoystickButton(x1, 6);
	Button buttonSelect 		= new JoystickButton(x1, 7);
	Button buttonStart 			= new JoystickButton(x1, 8);
	Button leftJoystickPress 	= new JoystickButton(x1, 9);
	Button rightJoystickPress 	= new JoystickButton(x1, 10);
	
	public OI() {	
		// Operate the scissor lift
		buttonA.whenPressed(new OperateLift("d"));
		buttonY.whenPressed(new OperateLift("u"));
		
		buttonX.whileHeld(new OperateArm(RobotMap.Dir.UP));
		buttonB.whileHeld(new OperateArm(RobotMap.Dir.DOWN));
	}
	
	public double getJLY() {
		return jLeft.getY();
	}
	
	public double getJRY() {
		return jRight.getY();
	}
}
