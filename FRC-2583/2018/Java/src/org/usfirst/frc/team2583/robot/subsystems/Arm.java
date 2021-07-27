package org.usfirst.frc.team2583.robot.subsystems;

import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.commands.OperateArm;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {

	private WPI_TalonSRX armMotor = new WPI_TalonSRX(RobotMap.armTalon);
		
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
    	armMotor.set(speed);
    }
    
    public void stopArm() {
    	armMotor.set(0.0);
    }
    
    private static Arm instance;
    public static Arm getInstance() {
    	if(instance == null) {
    		instance = new Arm();
    	}
    	
    	return instance;
    }
}

