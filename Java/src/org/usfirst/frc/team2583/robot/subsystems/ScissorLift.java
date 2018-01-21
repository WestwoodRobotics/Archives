package org.usfirst.frc.team2583.robot.subsystems;

import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.commands.OperateLift;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ScissorLift extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Compressor comp = new Compressor();
	DoubleSolenoid sol = new DoubleSolenoid(RobotMap.forwardChannel, RobotMap.backwardChannel);
	
	public ScissorLift(){
		comp.setClosedLoopControl(RobotMap.closedLoopControl);
	}
	
	//takes scissor lift to top
	public void toTop() {
		sol.set(DoubleSolenoid.Value.kForward);
	}
	
	//take scissor lift to bottom
	public void toBot() {
		sol.set(DoubleSolenoid.Value.kReverse);
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new OperateLift(""));
    }
}

