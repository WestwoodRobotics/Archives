package org.usfirst.frc.team2583.robot.commands.auto;

import org.usfirst.frc.team2583.robot.FieldMap;
import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.commands.AdjustAngle;
import org.usfirst.frc.team2583.robot.commands.DriveDistance;
import org.usfirst.frc.team2583.robot.commands.ShiftGears;
import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BackDrive extends CommandGroup {

    public BackDrive() {
    	//Check if gear shift is in fast mode
    	if(DriveTrain.getInstance().getGear()){
    		addSequential(new ShiftGears());	// Ensure slow-mode
    	}
    	
    	// Drive backwards up to the close side of the switch
    	addSequential(new DriveDistance(
    			-(FieldMap.switchDistanceClose - (RobotMap.robotLength + RobotMap.bumperMod))
    			));
    	
    	addSequential(new AdjustAngle(RobotMap.armMin));
    }
}
