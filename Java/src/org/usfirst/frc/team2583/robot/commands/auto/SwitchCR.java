package org.usfirst.frc.team2583.robot.commands.auto;

import org.usfirst.frc.team2583.robot.FieldMap;
import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.RobotMap.Dir;
import org.usfirst.frc.team2583.robot.commands.AdjustClaw;
import org.usfirst.frc.team2583.robot.commands.DriveDistance;
import org.usfirst.frc.team2583.robot.commands.OperateArm;
import org.usfirst.frc.team2583.robot.commands.OperateIntake;
import org.usfirst.frc.team2583.robot.commands.ShiftGears;
import org.usfirst.frc.team2583.robot.commands.TurnTo;
import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous mode switch placer: center -> right
 * 
 * This command drives the robot forward partially,
 * turns to the right,
 * drives forward until lined up with the switch,
 * turns back to face the switch,
 * drives forward again to get to the switch and break the baseline,
 * and releases the cube into the switch
 */
public class SwitchCR extends CommandGroup {

    public SwitchCR() {
        
    	if(DriveTrain.getInstance().getGear() == true){
    		addSequential(new ShiftGears());
    	}
    	
    	double halfDistance = (FieldMap.switchDistanceClose - RobotMap.totalLength) / 2;
    	
    	addSequential(new DriveDistance(halfDistance)); // Drive half the distance towards the switch
    	addSequential(new TurnTo(90)); // Turn Right 90 degrees
    	addSequential(new DriveDistance((FieldMap.switchLength / 2) - (FieldMap.plateLength / 2) + 12 - (RobotMap.totalWidth / 2)));	// Drive up to align with the center of the plate
    	addSequential(new TurnTo(-90)); // Turn Left 90 degrees
    	addSequential(new DriveDistance(halfDistance)); // Drive the remaining distance to switch
    	addSequential(new OperateArm(Dir.DOWN, RobotMap.armDownTime));
    	addSequential(new OperateIntake(RobotMap.Take.OUT)); // Push PowerCube out of the robot and onto the switch
    	addSequential(new AdjustClaw());
    }
}
