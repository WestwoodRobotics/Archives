
package org.usfirst.frc.team2583.robot;

import org.usfirst.frc.team2583.robot.commands.AutoForwardGear;
import org.usfirst.frc.team2583.robot.commands.AutoLeftGear;
import org.usfirst.frc.team2583.robot.commands.AutoRightGear;
import org.usfirst.frc.team2583.robot.commands.BaselineAuto;
import org.usfirst.frc.team2583.robot.commands.UpdateDash;
import org.usfirst.frc.team2583.robot.subsystems.Climber;
import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2583.robot.subsystems.GearMech;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public final static GearMech gearMech = new GearMech();			// The GearMech is the active gear mechanism
	public final static DriveTrain drivetrain = new DriveTrain();	// The DriveTrain controls the wheels of the robot
	public final static Climber climber = new Climber();			// The climber is used to climb the rope at the end of the match
	public final static OI oi = new OI();							// The Operator Interface class handles button presses
	
    Command autonomousCommand;	// Hold the command to execute during Autonomous Mode
    UpdateDash dashboardUpdate;	// The command that constantly populates the LabView Dashboard with information

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    	//CameraServer.getInstance().startAutomaticCapture();
    	
    	// Starts the dashboardUpdate command
    	dashboardUpdate = new UpdateDash();
    	dashboardUpdate.start();
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function takes input from the LabView Dashboard, chooses, and starts the Autonomous program
	 */
    public void autonomousInit() {
    	// Reset the encoders (most Autonomous modes rely on this)
    	drivetrain.resetEncoders();
    	
    	// If dashboardUpdate has been initialized, start it
    	if(dashboardUpdate != null && !dashboardUpdate.isRunning()){
    		dashboardUpdate.start();
    	}
    	
    	// Get the input from the Dashboard in integer form
    	int autochoice = (int)SmartDashboard.getNumber("Auto Select", 1);

    	// Assign the command for autonomous
    	autonomousCommand =   autochoice == 4 ? new BaselineAuto()	// Choice #4: break the baseline
    						: autochoice == 3 ? new AutoRightGear() // Choice #3: place the gear on the right side of the airship
    						: autochoice == 2 ? new AutoLeftGear()	// Choice #2: place the gear on the left side of the airship
    						: new AutoForwardGear();				// Default: place the gear on the middle peg of the airship

    	// If autonomousCommand has been properly initialized, start it
    	if (autonomousCommand != null){
    		autonomousCommand.start();
    	}
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	// Stop the autonomous command if it is still running
        if (autonomousCommand != null) autonomousCommand.cancel();
        // Make sure the dashboard is being updated
        if (dashboardUpdate != null && !dashboardUpdate.isRunning()) dashboardUpdate.start();
        // Reset the encoders
        drivetrain.resetEncoders();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
