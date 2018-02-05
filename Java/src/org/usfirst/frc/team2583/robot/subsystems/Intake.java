package org.usfirst.frc.team2583.robot.subsystems;

import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.commands.OperateIntake;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    private Spark leftWheel = new Spark(RobotMap.leftIntakeSpark);
    private Spark rightWheel = new Spark(RobotMap.rightIntakeSpark);

    public Intake() {
    	leftWheel.setInverted(true);
    }
    
    public void initDefaultCommand() {
//        setDefaultCommand(new OperateIntake(RobotMap.Take.NOWHERE));
    }
    
    public void setSpeed(double speedL, double speedR) {
    	leftWheel.set(speedL);
    	rightWheel.set(speedR);
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

