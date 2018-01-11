package org.usfirst.frc.team2583.robot.subsystems;

import org.usfirst.frc.team2583.robot.commands.OperateIntake;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new OperateIntake());
    }
}

