/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2583.robot;

import org.usfirst.frc.team2583.robot.commands.AdjustClaw;
import org.usfirst.frc.team2583.robot.commands.EnterHighGear;
import org.usfirst.frc.team2583.robot.commands.OperateArm;
import org.usfirst.frc.team2583.robot.commands.OperateIntake;
import org.usfirst.frc.team2583.robot.commands.ShiftGears;
import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static final int xBoxPort = 0;
	public static final int jRightPort = 1;
	public static final int jLeftPort = 2;
	public static final int throttlePort = 3;
	
	// Joysticks
	Joystick jRight = new Joystick(jRightPort);
	Joystick jLeft	= new Joystick(jLeftPort);
	
	// Xbox Controller and buttons and stuff
	XboxController x1 			= new XboxController(xBoxPort);
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
	
	Joystick throttle = new Joystick(throttlePort);
	// Three buttons required to release the ramp
	public final Button rampRelease1 = new JoystickButton(throttle, 1);
	public final Button rampRelease2 = new JoystickButton(throttle, 2);
	public final Button rampRelease3 = new JoystickButton(throttle, 3);
	// Buttons to adjust the height of the ramp
	public final Button rampUp		 = new JoystickButton(throttle, 4);
	public final Button rampdown	 = new JoystickButton(throttle, 5);
	
	
	// Joystick Buttons
	Button jRightTrigg			= new JoystickButton(jRight, 1);
	
	public OI() {
		buttonY.whileHeld(new OperateArm(RobotMap.Dir.UP));
		buttonA.whileHeld(new OperateArm(RobotMap.Dir.DOWN));
		buttonB.whenPressed(new AdjustClaw());
		
		jRightTrigg.whenPressed(new EnterHighGear());
		jRightTrigg.whenReleased(new ShiftGears());
		
		leftBumper.whileHeld(new OperateIntake(RobotMap.Take.OUT));
		rightBumper.whileHeld(new OperateIntake(RobotMap.Take.IN));
		
		if(rightBumper.get() == true){
			DriveTrain.getInstance().shiftGear();
		}
	}
	
	public double getJLY() {
		return -jLeft.getY();
	}
	
	public double getJRY() {
		return -jRight.getY();
	}
	
	private static OI instance;
	public static OI getInstance() {
		if(instance == null) {
			instance = new OI();
		}
		
		return instance;
	}
}
