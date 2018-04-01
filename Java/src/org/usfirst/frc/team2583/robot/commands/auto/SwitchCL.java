package org.usfirst.frc.team2583.robot.commands.auto;

import org.usfirst.frc.team2583.robot.FieldMap;
import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.RobotMap.Take;
import org.usfirst.frc.team2583.robot.commands.AdjustClaw;
import org.usfirst.frc.team2583.robot.commands.DriveDistance;
import org.usfirst.frc.team2583.robot.commands.Extend;
import org.usfirst.frc.team2583.robot.commands.OperateIntake;
import org.usfirst.frc.team2583.robot.commands.ShiftGears;
import org.usfirst.frc.team2583.robot.commands.TurnTo;
import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous mode switch placer: center -> left
 * 
 * This command drives the robot forward partially,
 * turns to the left,
 * drives forward until lined up with the switch,
 * turns back to face the switch,
 * drives forward again to get to the switch and break the baseline,
 * and releases the cube into the switch
 */
public class SwitchCL extends CommandGroup {

    public SwitchCL() {

    	if(DriveTrain.getInstance().getGear() == true){
    		addSequential(new ShiftGears());
    	}
    	
    	addSequential(new DriveDistance(FieldMap.switchDistanceClose / 2)); // Drive half the distance towards the switch
    	addSequential(new TurnTo(-90)); // Turn Left 90 degrees
    	addSequential(new DriveDistance((FieldMap.switchLength / 2) - (FieldMap.plateLength / 2) - 12 + (RobotMap.robotWidth / 2)));	// Drive up to align with the center of the plate
    	addSequential(new TurnTo(90)); // Turn Right 90 degrees
    	addSequential(new DriveDistance(FieldMap.switchDistanceClose / 2)); // Drive the remaining distance to switch
    	addSequential(new Extend(Take.OUT));
    	addSequential(new OperateIntake(RobotMap.Take.OUT)); // Push PowerCube out of the robot and onto the switch
    	addSequential(new AdjustClaw());
    }
}
