package org.usfirst.frc.team2583.robot.commands.auto;

import org.usfirst.frc.team2583.robot.FieldMap;
import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.commands.DriveDistance;
import org.usfirst.frc.team2583.robot.commands.DriveTime;
import org.usfirst.frc.team2583.robot.commands.OperateIntake;
import org.usfirst.frc.team2583.robot.commands.ShiftGears;
import org.usfirst.frc.team2583.robot.commands.TurnTo;
import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous mode switch placer: left -> left
 * 
 * Start this program with the bumpers against the corner of the field
 */
public class SwitchLL extends CommandGroup {

    public SwitchLL() {
    	
    	if(DriveTrain.getInstance().getGear() == true){
    		addSequential(new ShiftGears());
    	}
    	
    	addSequential(new ForwardLong());	// Drive up to be parallel to the target
    	addSequential(new TurnTo(90));		// Turn to the right to face the target
    	
    	// Distance from the target in inches (placed as a separate variable for readability purposes
    	double distanceFromTarget = FieldMap.scaleSpace - (FieldMap.cornerToSide + (RobotMap.robotLength + RobotMap.bumperMod) / 2);
    	
    	addSequential(new DriveDistance(distanceFromTarget));	// Drive up to the wall
    	addSequential(new DriveTime(0.25));	// Ensure that we are actually pressing up against the wall
    	addSequential(new OperateIntake(RobotMap.Take.OUT));	// Shoot out to release the cube
    }
}
