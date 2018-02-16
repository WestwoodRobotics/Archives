package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnTo extends Command {

	public static final double P = 0.9,
							   I = 2.0,
							   D = 1.0,
							   absoluteTolerance = 0.6;
	
	private PIDController pid;
	
    public TurnTo(double degrees) {
        requires(DriveTrain.getInstance());
    	
    	pid = new PIDController(P, I, D, new PIDSource() {
    		PIDSourceType m_sourceType = PIDSourceType.kDisplacement;
    		
			@Override
			public double pidGet() {
				return DriveTrain.getInstance().getZHeading();
			}

			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				m_sourceType = pidSource;
			}

			@Override
			public PIDSourceType getPIDSourceType() {
				return m_sourceType;
			}
    	}, d -> DriveTrain.getInstance().turnRate(d));
        
    	pid.setOutputRange(-1, 1);
    	pid.setAbsoluteTolerance(absoluteTolerance);
    	pid.setSetpoint(degrees);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.getInstance().resetIMU();
    	pid.reset();
    	pid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pid.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	pid.disable();
    	DriveTrain.getInstance().driveWheels(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	pid.disable();
    	DriveTrain.getInstance().driveWheels(0, 0);
    }
}
