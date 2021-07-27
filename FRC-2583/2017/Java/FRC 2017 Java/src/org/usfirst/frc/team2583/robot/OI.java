package org.usfirst.frc.team2583.robot;

import org.usfirst.frc.team2583.robot.commands.AddGear;
import org.usfirst.frc.team2583.robot.commands.PressGear;
import org.usfirst.frc.team2583.robot.commands.RemoveGear;
import org.usfirst.frc.team2583.robot.commands.ReverseToggle;
import org.usfirst.frc.team2583.robot.commands.StartFast;
import org.usfirst.frc.team2583.robot.commands.StartSlow;
import org.usfirst.frc.team2583.robot.commands.StopFast;
import org.usfirst.frc.team2583.robot.commands.StopSlow;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public XboxController x1 = new XboxController(0);;
	public Joystick joy = new Joystick(1);
	
	public OI(){
		
		// Flight joystick buttons/axis
		// Adds one to the gear count on the dashboard
		Button addGear = new JoystickButton(joy, 5);		// TODO: change this button to a different one
		addGear.whenPressed(new AddGear());
		// Subtracts one from the gera count on the dashboard
		Button removeGear = new JoystickButton(joy, 4);		// TODO: change this button to a different one
		removeGear.whenPressed(new RemoveGear());
		// Activates the gear mechanism
		Button gearPressJ = new JoystickButton(joy, 6);
		gearPressJ.whenPressed(new PressGear());
		
		// Xbox controller buttons/axis
		// Toggles reverse mode
		Button toggleReverse = new JoystickButton(x1, RobotMap.YButton);
		toggleReverse.whenPressed(new ReverseToggle());
		// Runs the robot in slow mode while held down
		Button runSlow = new JoystickButton(x1, RobotMap.rightBumper);
		runSlow.whenPressed(new StartSlow());
		runSlow.whenReleased(new StopSlow());
		// Runs the robot in fast mode while held down
		Button runFast = new JoystickButton(x1, RobotMap.leftBumper);
		runFast.whenPressed(new StartFast());
		runFast.whenReleased(new StopFast());
		// Activates the gear mechanism
		Button gearPressX = new JoystickButton(x1, RobotMap.AButton);
		gearPressX.whenPressed(new PressGear());
	}
}

