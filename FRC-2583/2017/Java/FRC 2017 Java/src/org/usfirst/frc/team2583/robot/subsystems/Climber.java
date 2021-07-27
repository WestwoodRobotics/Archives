package org.usfirst.frc.team2583.robot.subsystems;

import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.commands.OperateClimber;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

	CANTalon climber = new CANTalon(RobotMap.climbTalon);
	
    public void initDefaultCommand() {
    	setDefaultCommand(new OperateClimber());
    }
    
    public void run(double speed){
    	climber.set(speed);
    }
}

