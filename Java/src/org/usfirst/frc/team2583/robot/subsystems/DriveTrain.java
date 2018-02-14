package org.usfirst.frc.team2583.robot.subsystems;

import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.commands.TankDrive;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveTrain extends PIDSubsystem {

	public static final double P = 0.3;
	public static final double I = 0;
	public static final double D = 0;
	
	public static final double ramp = 0.1;  // acceleration of drive motors in sec
	
	// Talons for the drivetrain
	private WPI_TalonSRX left1  = new WPI_TalonSRX(RobotMap.leftTalon1);
	private WPI_TalonSRX left2 	= new WPI_TalonSRX(RobotMap.leftTalon2);
	private WPI_TalonSRX left3	= new WPI_TalonSRX(RobotMap.leftTalon3);
	private WPI_TalonSRX right1	= new WPI_TalonSRX(RobotMap.rightTalon1);
	private WPI_TalonSRX right2 = new WPI_TalonSRX(RobotMap.rightTalon2);
	private WPI_TalonSRX right3 = new WPI_TalonSRX(RobotMap.rightTalon3);
	
	// Speed controllers group the Talons into the individual gear boxes (sides of the robot)
	private SpeedControllerGroup leftGroup  = new SpeedControllerGroup(left1, left2, left3);
	private SpeedControllerGroup rightGroup = new SpeedControllerGroup(right1, right2, right3);
	
	// DifferentialDrive is a drive class for Tank
	private DifferentialDrive drive = new DifferentialDrive(leftGroup, rightGroup);
	
	// Gyro, Accelerometer, Magnometer, and Pressure Sensor
	private ADIS16448_IMU imu = new ADIS16448_IMU();
	
	private Solenoid sol = new Solenoid(RobotMap.gearShiftChannel);
	
	private Encoder rightEnc = new Encoder(RobotMap.rightEncA, RobotMap.rightEncB, false, Encoder.EncodingType.k1X);
	private Encoder leftEnc  = new Encoder(RobotMap.leftEncA,  RobotMap.leftEncB,  false, Encoder.EncodingType.k1X);
	
	//TODO remove these
	//private PIDController pidLeft = new PIDController(0.3, 0, 0, leftEnc, leftGroup);
	//private PIDController pidRight = new PIDController(0.3, 0, 0, rightEnc, rightGroup);
	
	public DriveTrain(){
		
		super("DriveTrain", P, I, D);
		
		//SsetOutputRange(-0.7, 0.7);
    	//getPIDController().setContinuous(true);
/*		
		left1.setInverted(true);
		left2.setInverted(true);
		left3.setInverted(true);
		right1.setInverted(true);
		right2.setInverted(true);
		right3.setInverted(true);*/
		
		left1.configOpenloopRamp(ramp, 25);
		left2.configOpenloopRamp(ramp, 25);
		left3.configOpenloopRamp(ramp, 25);
		right1.configOpenloopRamp(ramp, 25);
		right2.configOpenloopRamp(ramp, 25);
		right3.configOpenloopRamp(ramp, 25);
		
		rightEnc.setDistancePerPulse(RobotMap.distancePerPulse);
		leftEnc.setDistancePerPulse(RobotMap.distancePerPulse);
		leftEnc.setReverseDirection(true);
		
		rightEnc.reset();
		leftEnc.reset();
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new TankDrive());
    }
    
    public void driveWheels(double leftSpd, double rightSpd) {
    	drive.tankDrive(leftSpd, rightSpd);
    }
    
    public void calibrate() {
    	imu.calibrate();
    }
    
     /**
     * Toggle the gear of the drivetrain (on both sides) using pneumatic controls
     */
    public void shiftGear() {
    	sol.set(!sol.get());
    }
    
    public void setGear(RobotMap.Gear g) {
    	
    	sol.set(g == RobotMap.Gear.HIGH);
    }
    
    public boolean getGear() {
    	return !sol.get();
    }
    
    /**
     * 
     * @return a list enc[], where enc[0] is the right encoder value and enc[1] is the left
     */
    public double[] getEncoders() {
    	double[] enc = {rightEnc.getDistance(), leftEnc.getDistance()};
    	return enc;
    }
    
    public double[] getEncoderDist() {
    	double[] enc = {rightEnc.getDistance(), leftEnc.getDistance()};
    	return enc;
    }
    
    public void resetEncoders() {
    	rightEnc.reset();
    	leftEnc.reset();
    }
    
    
    /*
    public void pidEnable(){
		pidLeft.enable();
		//pidRight.enable();
    }
    
    public void pidDisable(){
		pidLeft.disable();
		//pidRight.disable();
    }
    
    public void pidSetpoint(double setpoint){
    	pidLeft.setSetpoint(setpoint * RobotMap.pulsesPerInch);
    	//pidRight.setSetpoint(setpoint * RobotMap.pulsesPerInch);
    	
    	pidLeft.setOutputRange(-0.7, 0.7);
    	//pidRight.setOutputRange(-0.7, 0.7);
    }
    
    public boolean pidOnTarget(){
    	return pidLeft.onTarget();
    }
    */
    public double getXHeading() {
    	return imu.getAngleX();
    }
    
    public double getYHeading() {
    	return imu.getAngleY();
    }
    
    public double getZHeading() {
    	return imu.getAngleZ();
    }
    
    public double getXAccel() {
    	return imu.getAccelX();
    }
    
    public double getYAccel() {
    	return imu.getAccelY();
    }
    
    public double getZAccel() {
    	return imu.getAccelZ();
    }
    
    public double getTemp() {
    	return imu.getTemperature();
    }
    
    public double getXMag() {
    	return imu.getMagX();
    }
    
    public double getYMag() {
    	return imu.getMagY();
    }
    
    public double getZMag() {
    	return imu.getMagZ();
    }
    
    public double getPressure() {
    	return imu.getBarometricPressure();
    }
    
    private static DriveTrain instance;
    public static DriveTrain getInstance() {
    	if(instance == null) {
    		instance = new DriveTrain();
    	}
    	
    	return instance;
    }

	@Override
	protected double returnPIDInput() {
		double encoderAverage = (rightEnc.getDistance() + leftEnc.getDistance()) / 2;
		return encoderAverage;
	}

	@Override
	protected void usePIDOutput(double output) {
		driveWheels(output, output);
	}
}

