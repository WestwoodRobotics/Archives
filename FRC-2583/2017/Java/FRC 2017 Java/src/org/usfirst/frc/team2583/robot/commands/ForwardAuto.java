package org.usfirst.frc.team2583.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ForwardAuto extends CommandGroup {

    public ForwardAuto() {
    	addSequential(new AutoForwardGear());
    	addSequential(new CenterToLine());
    }
}
