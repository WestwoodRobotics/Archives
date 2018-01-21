/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2583.robot;

import org.usfirst.frc.team2583.robot.commands.OperateLift;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	//0 as start of index
	//a - 0; b - 1; x - 2; y - 3; 
	//lBump - 4; rBump - 5; select - 6; start - 7; LeftJoystickDown - 8; rightJoysticDown - 9
	
	XboxController x1 = new XboxController(0);
	Button aIndex = new JoystickButton(x1, 0);
	Button bIndex = new JoystickButton(x1, 1);
	Button xIndex = new JoystickButton(x1, 2);
	Button yIndex = new JoystickButton(x1, 3);
	Button leftBumper = new JoystickButton(x1, 4);
	Button rightBumper = new JoystickButton(x1, 5);
	Button selectIndex = new JoystickButton(x1, 6);
	Button startIndex = new JoystickButton(x1, 7);
	Button leftJoystickPress = new JoystickButton(x1, 8);
	Button rightJoystickPress = new JoystickButton(x1, 9);
	
	public OI() {
		
		aIndex.whenPressed(new OperateLift("down"));
		yIndex.whenPressed(new OperateLift("up"));
	}
	
	
}
