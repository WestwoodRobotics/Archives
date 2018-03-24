package org.usfirst.frc.team2583.robot;

/**
 * The FieldMap is for storing information about the field that the
 * robot will utilize in autonomous mode. This information includes
 * the positions of the switches and scale, the dimensions of the
 * field, and any other relevant information.
 */
public class FieldMap {
	
	// The three variables below will be 'L' or 'R'
	public static char homeSwitch;	// The side of our color on the home-side switch
	public static char scale;		// The side of our color on the scale
	public static char farSwitch;	// The side of our color on the farther switch
	
	// The distance variables found below are measured in inches
	// May need to adjust for the length of the robot when using these
	public static final double autoLineDistance = 120;	// Measured from the back wall to the auto-break line
	public static final double switchDistanceClose = 140;	// Measured from the back wall to the closest side of the home switch
	public static final double switchDistanceFar = 196;	// Measured from the back wall to the farthest side of the home switch
	public static final double switchDistanceMid = (switchDistanceClose + switchDistanceFar) / 2;	// Measured from the back wall to the center of the home switch
	public static final double switchLength = 144;	// Length of the switch, measured parallel to the alliance wall
	public static final double plateLength = 36;	// Length of the switch's plates, measured parallel to the alliance wall
	public static final double plateWidth = 48;		// Width of the switch's plates, measured perpendicular to the alliance wall
	public static final double cornerToSide = 29.69; // The distance of the corner along the alliance wall from the side of the field
	public static final double scaleSpace = 85.25;	// The space between the scale and the side of the field
}
