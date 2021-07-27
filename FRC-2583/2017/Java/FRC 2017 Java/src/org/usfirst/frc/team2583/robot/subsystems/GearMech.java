package org.usfirst.frc.team2583.robot.subsystems;

import org.usfirst.frc.team2583.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 *
 */
public class GearMech extends Subsystem {

	private CANTalon mech = new CANTalon(RobotMap.gearTalon);

	// TODO: make sure these parameters match up (port, scaling (max turn), offset)
	Potentiometer potentiometer = new AnalogPotentiometer(0, 360, 0);
	
	// There is no default command for this subsystem
    public void initDefaultCommand() {}
    
    /**
     * Sets the gear mechanism to extend. It will continue extending until halt() is called
     */
    public void extend(){
    	mech.set(RobotMap.gearSpeed);
    }
    
    /**
     * Sets the gear mechanism to retract. It will continue retracting until halt() is called
     */
    public void retract(){
    	mech.set(-RobotMap.gearSpeed);
    }
    
    /**
     * Halts the gear mechanism's movements.
     */
    public void halt(){
    	mech.set(0);
    }
    
    public double getPot(){
    	return potentiometer.get();
    }
}

