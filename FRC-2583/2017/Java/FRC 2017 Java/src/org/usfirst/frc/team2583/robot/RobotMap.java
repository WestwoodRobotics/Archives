package org.usfirst.frc.team2583.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	// CAN Talon Motor IDs
	public static final int fLeft = 1;
	public static final int bLeft = 3;
	public static final int fRight = 4;
	public static final int bRight = 2;
	
    public static final int gearTalon = 6;		// TODO: make sure that this port is available
    public static final int windowTalon = 7;
    public static final int climbTalon = 8;
    
    // Encoder Ports
    public static final int driveRightA = 4;
    public static final int driveRightB = 5;
    public static final int driveLeftA = 6;
    public static final int driveLeftB = 7;
    
    // XBox Controller Axes
    public static final int leftYAxis = 1;
    public static final int rightYAxis = 5;
    public static final int rightTrigger = 3;
    public static final int leftTrigger = 2;
    
    // Flight Controller Axes
    public static final int joyYAxis = 1;
    
    // XBox Controller Buttons
    public static final int rightBumper = 6;
    public static final int leftBumper = 5;
    public static final int YButton = 4;
    public static final int XButton = 3;
    public static final int BButton = 2;
    public static final int AButton = 1;
    
    // Flight Controller Buttons
    public static final int trigger = 1;
    
    // Speed Constants
    public static final double maxFastSpeed = 0.85;	// Maximum drive speed while in fast mode
    public static final double maxNormSpeed = 0.75;	// Maximum drive speed while in normal mode
    public static final double maxSlowSpeed = 0.5;	// Maximum drive speed while in slow mode
    public static final double climbSpeed = 1;		// Speed for the climber
    public static final double autoSpeed = 0.6;		// Speed for the driveTrain while in autonomous mode
    public static final double gearSpeed = 1;		// Speed for the gearMech
    
    // Other Constants
    public static final double wheelCircumference = 6 * Math.PI; // Circumference of the driveTrain's wheels in inches
    public static final double distancePerEncPulse = 1.0 / 2048.0 * wheelCircumference;	// Distance traveled per pulse in inches
    public static final double Kp = 0.03;	// Scaling constant for turning with help of gyro
    public static final double g = 32.174;	// Acceleration due to gravity in ft/sec^2
    public static final double accelDeadband = 0.03;	// Attempt to make the accelerometer work properly
    public static final double clock = 0.02; // Commands are called every 0.02 seconds to update
    public static final double leftScale = 0.955;	// Attempt to make the robot drive straight
    
    // Toggle-State Variables
    public static boolean isReversed = false;
    public static boolean isFast = false;
    public static boolean isSlow = false;
    
    // Count of the Gears place
    public static int gearsPlaced = 0;
}
