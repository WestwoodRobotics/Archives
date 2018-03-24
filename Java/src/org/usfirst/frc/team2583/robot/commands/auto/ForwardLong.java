package org.usfirst.frc.team2583.robot.commands.auto;

import org.usfirst.frc.team2583.robot.FieldMap;
import org.usfirst.frc.team2583.robot.commands.DriveDistance;
import org.usfirst.frc.team2583.robot.commands.ShiftGears;
import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Head forward to cross the baseline in auto
 * 
 * This command ends with the robot lined up next to the switch,
 * assuming it began flat against the wall on either the left or right side.
 */
public class ForwardLong extends CommandGroup {

    public ForwardLong() {
        
    	//Check if gear shift is in fast mode
    	if(DriveTrain.getInstance().getGear() == true){
    		addSequential(new ShiftGears());	// Ensure slow-mode
    	}
    	
    	addSequential(new DriveDistance(FieldMap.switchDistanceMid));
    }
}
