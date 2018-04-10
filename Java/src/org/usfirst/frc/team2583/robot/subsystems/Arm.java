package org.usfirst.frc.team2583.robot.subsystems;

import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.commands.OperateArm;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 *
 */
public class Arm extends Subsystem {

	private WPI_TalonSRX armMotor = new WPI_TalonSRX(RobotMap.armTalon);
	
	final Value lockVal = DoubleSolenoid.Value.kForward;
	final Value unlockVal = DoubleSolenoid.Value.kReverse;
	
	Potentiometer pot = new AnalogPotentiometer(0, RobotMap.armPotMax, RobotMap.armMin);
	
	private Solenoid extender = new Solenoid(RobotMap.extenderChannel);
	final boolean extendVal = true;
	
	/**
	 * Initialize StopArm as the default command
	 */
    public void initDefaultCommand() {
        setDefaultCommand(new OperateArm(RobotMap.Dir.NOWHERE));
    }
    
    /**
     * Sets the speed of the fulcrum motor to turn
     * 
     * @param speed the speed for the motor to turn
     */
    public void setSpeed(double speed) {
    	
    	if(pot.get() >= RobotMap.armMax) {
    		speed = Math.min(speed, 0);
    	}else if(pot.get() >= RobotMap.armMin) {
    		speed = Math.max(speed, 0);
    	}
    	armMotor.set(speed);
    }
    
    public double getPot() {
    	return pot.get();
    }
    
    public void stopArm() {
    	armMotor.set(0.0);
    }
    
    public void extend() {
    	extender.set(extendVal);
    }
    
    public void retract() {
    	extender.set(!extendVal);
    }
    
    private static Arm instance;
    public static Arm getInstance() {
    	if(instance == null) {
    		instance = new Arm();
    	}
    	
    	return instance;
    }
}

