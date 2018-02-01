package org.usfirst.frc.team2583.robot.commands.auto;

import org.usfirst.frc.team2583.robot.commands.ShiftGears;
import org.usfirst.frc.team2583.robot.commands.TankDrive;
import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Head forward to cross the baseline in auto
 */
public class ForwardLong extends CommandGroup {

    public ForwardLong() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	//Check if gear shift is in fast mode
    	if(DriveTrain.getInstance().getGear() == false){
    		addSequential(new ShiftGears());
    	}
    	
    	addSequential(new TankDrive(1, 1, 10));
    	
    	
    }
}
