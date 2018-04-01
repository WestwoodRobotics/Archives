package org.usfirst.frc.team2583.robot.commands.auto;

import org.usfirst.frc.team2583.robot.FieldMap;
import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.RobotMap.Take;
import org.usfirst.frc.team2583.robot.commands.DriveDistance;
import org.usfirst.frc.team2583.robot.commands.Extend;
import org.usfirst.frc.team2583.robot.commands.OperateIntake;
import org.usfirst.frc.team2583.robot.commands.ShiftGears;
import org.usfirst.frc.team2583.robot.commands.TurnTo;
import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous mode switch placer: right -> right
 * 
 * Start this program with the bumpers against the corner of the field
 */
public class SwitchRR extends CommandGroup {

    public SwitchRR() {
    	
    	if(DriveTrain.getInstance().getGear()){
    		addSequential(new ShiftGears());
    	}
    	
    	addSequential(new DriveDistance(FieldMap.switchDistanceClose, 0.45));	// Drive up to be parallel to the target
    	addSequential(new TurnTo(-90));		// Turn to the left to face the target
    	
    	// Distance from the target in inches (placed as a separate variable for readability purposes
    	// Should be ~36.56 inches
    	double distanceFromTarget = FieldMap.switchSpace - (FieldMap.cornerToSide + (RobotMap.robotLength + RobotMap.bumperMod) / 2);
    	
    	addSequential(new DriveDistance(distanceFromTarget));	// Drive up to the wall
    	addSequential(new Extend(Take.OUT));
    	addSequential(new OperateIntake(Take.OUT));	// Shoot out to release the cube
    }
}
