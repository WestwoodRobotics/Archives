/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2583.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static final double encoderPulses = 4096;
	public static final double wheelDiameter = 0; // Diameter in inches
	public static final double pulsesPerFoot = encoderPulses / ((wheelDiameter / 12.0) * 2 * Math.PI);
	
	//Talon motors
	public static final int leftTalon1  = 0;
	public static final int leftTalon2	= 1;
	public static final int leftTalon3 	= 2;
	public static final int rightTalon1 = 3;
	public static final int rightTalon2 = 4;
	public static final int rightTalon3 = 5;
	
	public static final int forwardChannel = 0;
	public static final int backwardChannel = 7;
	public static final boolean closedLoopControl = true;
}
