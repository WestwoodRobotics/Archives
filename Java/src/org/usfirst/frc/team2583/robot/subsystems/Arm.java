package org.usfirst.frc.team2583.robot.subsystems;

import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.commands.OperateArm;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {

	private WPI_TalonSRX armMotor = new WPI_TalonSRX(6);
	
	private DigitalInput limitSwitchUpper;
	private DigitalInput limitSwitchLower;
	
	/**
	 * Initialize StopArm as the default command
	 */
    public void initDefaultCommand() {
        setDefaultCommand(new OperateArm(RobotMap.Dir.NOWHERE)); // Simply turns the motors off if nothing is supposed to be happening
        limitSwitchUpper = new DigitalInput(RobotMap.armUpperLimit);
    }
    
    /**
     * Sets the speed of the fulcrum motor to turn
     * 
     * @param speed the speed for the motor to turn
     */
    public void setSpeed(double speed) {
    	armMotor.set(speed);
    }
    
    public boolean switchClosedUpper(){
    	if(!limitSwitchUpper.get()){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    public boolean switchClosedLower(){
    	if(!limitSwitchLower.get()){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    private static Arm instance;
    public static Arm getInstance() {
    	if(instance == null) {
    		instance = new Arm();
    	}
    	
    	return instance;
    }
}

