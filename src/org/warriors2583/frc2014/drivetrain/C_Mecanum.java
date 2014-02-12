package org.warriors2583.frc2014.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import org.warriors2583.frc2014.OI;

/**
 *
 * @author Austin Reuland
 */
public class C_Mecanum extends Command {

    public C_Mecanum() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		super("C_Mecanum");
        requires(SS_Drivetrain.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        boolean scale = OI.getJDriveButton(5);
        SS_Drivetrain.mecanum(OI.getJDriveLeftX() * (scale ? 0.7 : 1),
                OI.getJDriveLeftY() * (scale ? 0.7 : 1),
                OI.getJDriveRightX() * (scale ? 0.75 : 1),
                0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
