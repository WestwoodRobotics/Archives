package org.usfirst.frc.team2583.robot.subsystems;

import org.usfirst.frc.team2583.robot.commands.OperateLift;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ScissorLift extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	
	//takes scissor lift to top
	public void toTop() {
		
		
		
	}
	
	//take scissor lift to bottom
	public void toBot() {
	
		
	
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new OperateLift(""));
    }
}

