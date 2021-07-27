package org.usfirst.frc.team2583.robot.commands;

import org.usfirst.frc.team2583.robot.Robot;
import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CenterToLine extends Command {

	DriveTrain dt = Robot.drivetrain;
	
	private enum Stage{
		BACK,
		LEFT,
		FORWARD;
	}
	
	private Stage stage = Stage.BACK;
	
	public static final double distanceBack = 24;	// Distance to travel back, in inches
	public static final double distanceLeft = 140;	// Distance to travel left, in inches
	public static final double distanceForward = 135;	// Distance to travel forward, in inches
	
    public CenterToLine() {
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	dt.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch(stage){
    	case BACK:
    		if(Math.abs(dt.getLeftDistance() + dt.getRightDistance()) / 2 > distanceBack){
    			stage = Stage.LEFT;   			
    			dt.resetEncoders();
    			while(Math.abs(dt.getLeftEncoder()) < 3 * 360){
    				dt.tankDrive(-.8, .8);
    			}
    			dt.drive(0, 0);
    			dt.resetEncoders();
    		}else{
    			dt.drive(-0.65, 0);
    			break;
    		}
    	case LEFT:
    		if(Math.abs(dt.getLeftDistance() + dt.getRightDistance()) / 2 > distanceLeft){
    			stage = Stage.FORWARD;
    			dt.resetEncoders();
    			while(Math.abs(dt.getLeftDistance()) < 3 * 360){
    				dt.tankDrive(.8, -.8);
    			}
    			dt.drive(0, 0);
    			dt.resetEncoders();
    		}else{
    			dt.drive(0.65, 0);
    			break;
    		}
    	case FORWARD:
    		dt.drive(.7, 0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return stage.equals(Stage.FORWARD) && Math.abs(dt.getLeftEncoder()) > distanceForward;
    }

    // Called once after isFinished returns true
    protected void end() {
    	dt.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
