package org.usfirst.frc.team2583.robot.commands.auto;

import org.usfirst.frc.team2583.robot.FieldMap;
import org.usfirst.frc.team2583.robot.commands.DriveDistance;
import org.usfirst.frc.team2583.robot.commands.DriveTime;
import org.usfirst.frc.team2583.robot.commands.ShiftGears;
import org.usfirst.frc.team2583.robot.commands.TurnTo;
import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BreakCL extends CommandGroup {

    public BreakCL() {
        
    	if(DriveTrain.getInstance().getGear() == true){
    		addSequential(new ShiftGears());
    	}
    	
    	addSequential(new DriveDistance(FieldMap.switchDistanceClose / 2)); // Drive half the distance towards the switch
    	addSequential(new TurnTo(-90)); // Turn Left 90 degrees
    	addSequential(new DriveDistance((FieldMap.switchLength / 2) - (FieldMap.plateLength / 2)));	// Drive up to align with the center of the plate
    	addSequential(new TurnTo(90)); // Turn Right 90 degrees
    	addSequential(new DriveDistance(FieldMap.switchDistanceClose / 2)); // Drive the remaining distance to switch
    	addSequential(new DriveTime(0.25));	// Drive for a bit longer, just to ensure we're touching the target
    }
}
