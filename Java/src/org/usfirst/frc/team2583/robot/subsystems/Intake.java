package org.usfirst.frc.team2583.robot.subsystems;

import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.commands.OperateIntake;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	
    private Spark leftWheel = new Spark(RobotMap.leftIntakeSpark);
    private Spark rightWheel = new Spark(RobotMap.rightIntakeSpark);
    
    private DoubleSolenoid controller = new DoubleSolenoid(RobotMap.clawChannelA, RobotMap.clawChannelB);
    
    private static final DoubleSolenoid.Value open  = DoubleSolenoid.Value.kForward;
    private static final DoubleSolenoid.Value close = DoubleSolenoid.Value.kReverse;
    
    public Intake() {
    	setState(close);
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
    
    public void setState(DoubleSolenoid.Value state) {
    	controller.set(state);
    }
    
    public void toggleState() {
    	if(controller.get() == open) {
    		controller.set(close);
    	} else {
    		controller.set(open);
    	}
    }
    
    public DoubleSolenoid.Value getState() {
    	return controller.get();
    }
    
    private static Intake instance;
    public static Intake getInstance() {
    	if(instance == null) {
    		instance = new Intake();
    	}
    	
    	return instance;
    }
}

