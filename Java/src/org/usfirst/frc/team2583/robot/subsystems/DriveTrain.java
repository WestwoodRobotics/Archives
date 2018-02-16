package org.usfirst.frc.team2583.robot.subsystems;

import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.commands.TankDrive;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
	public static final double ramp = 0.1;  // acceleration of drive motors in sec
	
	// Talons for the drivetrain
	private WPI_TalonSRX leftMaster  	= new WPI_TalonSRX(RobotMap.leftTalon1);
	private WPI_TalonSRX left2 			= new WPI_TalonSRX(RobotMap.leftTalon2);
	private WPI_TalonSRX left3			= new WPI_TalonSRX(RobotMap.leftTalon3);
	private WPI_TalonSRX rightMaster	= new WPI_TalonSRX(RobotMap.rightTalon1);
	private WPI_TalonSRX right2 		= new WPI_TalonSRX(RobotMap.rightTalon2);
	private WPI_TalonSRX right3 		= new WPI_TalonSRX(RobotMap.rightTalon3);
	
	// DifferentialDrive is a drive class for Tank
	private DifferentialDrive drive = new DifferentialDrive(leftMaster, rightMaster);
	
	// Gyro, Accelerometer, Magnometer, and Pressure Sensor
	private ADIS16448_IMU imu = new ADIS16448_IMU();
	
	private Solenoid sol = new Solenoid(RobotMap.gearShiftChannel);
	
	private Encoder rightEnc = new Encoder(RobotMap.rightEncA, RobotMap.rightEncB, false, Encoder.EncodingType.k1X);
	private Encoder leftEnc  = new Encoder(RobotMap.leftEncA,  RobotMap.leftEncB,  false, Encoder.EncodingType.k1X);
	
	public DriveTrain(){
		
		left2.set(ControlMode.Follower, RobotMap.leftTalon1);
		left3.set(ControlMode.Follower, RobotMap.leftTalon1);
		right2.set(ControlMode.Follower, RobotMap.rightTalon1);
		right3.set(ControlMode.Follower, RobotMap.rightTalon1);
		
		leftMaster.configOpenloopRamp(ramp, 25);
		left2.configOpenloopRamp(ramp, 25);
		left3.configOpenloopRamp(ramp, 25);
		rightMaster.configOpenloopRamp(ramp, 25);
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
    
    public void setDeadband(double band) {
		drive.setDeadband(band);
	}
    
    public void driveWheels(double leftSpd, double rightSpd) {
    	drive.tankDrive(leftSpd, rightSpd);
    }
    
    public void turnRate(double rate) {
    	drive.curvatureDrive(0, rate, true);
    }
    
    public void calibrate() {
    	imu.calibrate();
    }
    
    public void resetIMU() {
    	imu.reset();
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
    
    public double getYaw() {
    	return imu.getYaw();
    }
    
    public double getPitch() {
    	return imu.getPitch();
    }
    
    public double getRoll() {
    	return imu.getRoll();
    }
    
    private static DriveTrain instance;
    public static DriveTrain getInstance() {
    	if(instance == null) {
    		instance = new DriveTrain();
    	}
    	
    	return instance;
    }
	
}

