package org.usfirst.frc.team2583.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous mode switch placer: center -> right
 */
public class SwitchCR extends CommandGroup {

    public SwitchCR() {
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
    	
    		//break past the base line
    		new ForwardLong();//makes robot go forward(Change distance so it reaches auto line)
    	
    }
}
