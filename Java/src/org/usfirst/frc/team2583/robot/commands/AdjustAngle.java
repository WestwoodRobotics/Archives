package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AdjustAngle extends Command {

	public static final double P = .3,
							   I = 0,
							   D = 0,
							   tolerance = 0.2; // tolerance in percentage
	
	Arm ar = Arm.getInstance();
	private PIDController pid;
	
    public AdjustAngle(double angle) {
    	requires(ar);
    	
    	pid = new PIDController(P, I, D, new PIDSource() {
			PIDSourceType m_sourceType = PIDSourceType.kDisplacement;

			@Override
			public double pidGet() {
				return ar.getPot();
			}

			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				m_sourceType = pidSource;
			}

			@Override
			public PIDSourceType getPIDSourceType() {
				return m_sourceType;
			}
		}, d -> ar.setSpeed(d));
    	
    	pid.setInputRange(RobotMap.armMin, RobotMap.armMax);
    	pid.setOutputRange(-RobotMap.armMaxSpeed, RobotMap.armMaxSpeed);
    	pid.setPercentTolerance(tolerance);
    	pid.setSetpoint(angle);
    	
    	setInterruptible(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
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
    	ar.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	pid.disable();
    	ar.setSpeed(0);
    }
}
