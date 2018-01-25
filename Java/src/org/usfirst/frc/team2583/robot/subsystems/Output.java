package org.usfirst.frc.team2583.robot.subsystems;

import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.commands.OperateOutput;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Output extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Solenoid sol = new Solenoid(RobotMap.outputChannel, RobotMap.outputChannel);

    public void initDefaultCommand() {
        setDefaultCommand(new OperateOutput(RobotMap.Dir.NOWHERE));
    }
    
    public void pushOut(){
    	sol.set(true);
    }
    
    public void pullIn(){
    	sol.set(false);
    }
    
    private static Output instance;
    public static Output getInstance() {
    	if(instance == null) {
    		instance = new Output();
    	}
    	
    	return instance;
    }
}

