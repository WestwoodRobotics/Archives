package org.usfirst.frc.team2583.robot.subsystems;

import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.commands.OperateIntake;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	public static final double ramp = 0.25;	// ramp in seconds
	private static final double callFreq = 1000 / 20;	// Calls to commands per second
	private static final double maxChange = ramp / callFreq;
	
	private double targetSpeedL = 0;
	private double targetSpeedR = 0;
	
    private Spark leftWheel = new Spark(RobotMap.leftIntakeSpark);
    private Spark rightWheel = new Spark(RobotMap.rightIntakeSpark);

    private void rampUp() {
    	leftUpdate:
    	if(leftWheel.get() != targetSpeedL) {
    		double diff = targetSpeedL - leftWheel.get();
    		
    		if (Math.abs(diff) <= maxChange) {
    			leftWheel.set(targetSpeedL);
    			break leftUpdate;
    		}
    		
    		leftWheel.set(leftWheel.get() + maxChange * Math.signum(diff));
    	}
    	
    	rightUpdate:
    	if(rightWheel.get() != targetSpeedR) {
    		double diff = targetSpeedR - rightWheel.get();
    		
    		if (Math.abs(diff) <= maxChange) {
    			rightWheel.set(targetSpeedR);
    			break rightUpdate;
    		}
    		
    		rightWheel.set(rightWheel.get() + maxChange * Math.signum(diff));
    	}
    }
    
    public Intake() {
    	leftWheel.setInverted(true);
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new OperateIntake(RobotMap.Take.NOWHERE));
    }
    
    public void setSpeed(double speedL, double speedR) {
    	targetSpeedL = speedL;
    	targetSpeedR = speedR;
    	
    	rampUp();
    }
    
    public double getSpeed() {
    	return leftWheel.get();
    }
    
    private static Intake instance;
    public static Intake getInstance() {
    	if(instance == null) {
    		instance = new Intake();
    	}
    	
    	return instance;
    }
}

