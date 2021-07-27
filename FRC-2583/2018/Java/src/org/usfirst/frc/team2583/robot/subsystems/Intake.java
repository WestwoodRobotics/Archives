package org.usfirst.frc.team2583.robot.subsystems;

import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.commands.OperateIntake;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	
    private Spark leftWheel = new Spark(RobotMap.leftIntakeSpark);
    private Spark rightWheel = new Spark(RobotMap.rightIntakeSpark);
    
    private Solenoid controller = new Solenoid(RobotMap.clawChannelA);
    
    public Intake() {
    	rightWheel.setInverted(true);
//    	setState(close);
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new OperateIntake(RobotMap.Take.NOWHERE));
    }
    
    public void setSpeed(double speedL, double speedR) {
    	
    	leftWheel.set(speedL * RobotMap.maxIntakeSpeedL);
    	rightWheel.set(speedR * RobotMap.maxIntakeSpeedR);
    	
    }
    
    public double getSpeed() {
    	return leftWheel.get();
    }
    
//    public void setState(DoubleSolenoid.Value state) {
//    	controller.set(state);
//    }
    
    public void toggleState() {
    	controller.set(!controller.get());
    }
    
//    public DoubleSolenoid.Value getState() {
//    	return controller.get();
//    }
    
    private static Intake instance;
    public static Intake getInstance() {
    	if(instance == null) {
    		instance = new Intake();
    	}
    	
    	return instance;
    }
}

