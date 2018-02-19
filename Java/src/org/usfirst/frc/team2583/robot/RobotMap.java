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
	public static final double wheelDiameter = 6; // Diameter in inches
	public static final double pulsesPerInch = encoderPulses / ((wheelDiameter) * 2 * Math.PI);
	public static final int    rightEncA 	 = 0;
	public static final int	   rightEncB	 = 1;
	public static final int    leftEncA		 = 2;
	public static final int    leftEncB		 = 3;
	
	public static final int	   armUpperLimit = 4;
	
	//Talon motors
	public static final int leftTalon1  = 0;
	public static final int leftTalon2	= 1;
	public static final int leftTalon3 	= 2;
	public static final int rightTalon1 = 3;
	public static final int rightTalon2 = 4;
	public static final int rightTalon3 = 5;
	
	//Arm
	public static final int fulcrumTalon = 6;

	//Drivetrain encoders
	public static final int pulsesPerRev = 256 * 9;
	public static final double wheelCircumference = Math.PI * wheelDiameter;
	public static final double distancePerPulse = wheelCircumference / pulsesPerRev;
	
	//Spark motors
	//Intake
	public static final int leftIntakeSpark = 0;
	public static final int rightIntakeSpark = 1;
	
	public static final int PDPIndex = 0;
	public static final int PCMIndex = 1;
	
	//Pneumatics control variables
	public static final int scissorLiftChannel = 0;
	public static final int scissorLiftChannel2 = 1;
	public static final int outputChannel = 2;
	public static final int gearShiftChannel = 7;
	public static final boolean closedLoopControl = true;
	
	public static final double deadbandLimit = 0.05;
	
	public static final double maxIntakeSpeedL = 0.9;
	public static final double maxIntakeSpeedR = 0.9;
	
	public static final double armSpeedUp = 0.5;
	public static final double armSpeedDown = -0.5;
	
	public static final double autoLineDistance = 0;
	public static final double switchDistance = autoLineDistance;
	
	// These values appear in the DriverStation for human input
	// These are for comparison to the Networktables values
	public static final String
		positionL 		= "Left",
		positionC 		= "Center",
		positionR 		= "Right",
		strategyNothing	= "Do nothing",
		strategyBreak 	= "Break the baseline",
		strategyAny		= "Go for the switch on any side",
		strategyOurs	= "Go for the switch if it is on our side";
	
	/**
	 * Provides constants for directions that things might want to move
	 */
	public enum Dir{
		UP,		// To be used when we need things to move UP
		DOWN,	// To be used when we need things to move DOWN
		NOWHERE;// To be used when we need things to NOT move
	}
	
	public enum Gear{
		HIGH,
		LOW;
	}
	
	public enum Take{
		IN,
		OUT,
		NOWHERE;
	}
}
