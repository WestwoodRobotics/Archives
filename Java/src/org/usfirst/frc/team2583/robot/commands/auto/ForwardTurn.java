package org.usfirst.frc.team2583.robot.commands.auto;

import org.usfirst.frc.team2583.robot.commands.ShiftGears;
import org.usfirst.frc.team2583.robot.commands.TurnTo;
import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous mode switch placer: right -> left
 */
public class SpinCircle extends CommandGroup {

    public SpinCircle() {
    	if(DriveTrain.getInstance().getGear()){
    		addSequential(new ShiftGears());	// Ensure slow-mode
    	}
    	
    	addSequential(new TurnTo(45));
    	addSequential(new TurnTo(-90));
    	addSequential(new TurnTo(180));
    	addSequential(new TurnTo(-360));
    }
}
