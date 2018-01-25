package org.usfirst.frc.team2583.robot.subsystems;

import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.commands.OperateLift;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ScissorLift extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
//	DoubleSolenoid sol = new DoubleSolenoid(RobotMap.scissorLiftChannel, RobotMap.scissorLiftChannel);
//	DoubleSolenoid sol2 = new DoubleSolenoid(RobotMap.scissorLiftChannel2, RobotMap.scissorLiftChannel2);
	
	//takes scissor lift to top
	public void toTop() {
//		sol.set(DoubleSolenoid.Value.kForward);
//		sol2.set(DoubleSolenoid.Value.kForward);
	}
	
	//take scissor lift to bottom
	public void toBot() {
//		sol.set(DoubleSolenoid.Value.kReverse);
//		sol2.set(DoubleSolenoid.Value.kReverse);
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new OperateLift(RobotMap.Dir.NOWHERE));
    }
    
    private static ScissorLift instance;
    public static ScissorLift getInstance() {
    	if(instance == null) {
    		instance = new ScissorLift();
    	}
    	
    	return instance;
    }
}

