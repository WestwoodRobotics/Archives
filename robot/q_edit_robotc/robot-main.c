#pragma config(Motor,  port2,           leftMotor,     tmotorServoContinuousRotation, openLoop, reversed, driveLeft)
#pragma config(Motor,  port3,           liftLeft,      tmotorServoContinuousRotation, openLoop, driveLeft)
#pragma config(Motor,  port4,           doorServo,     tmotorServoStandard, openLoop)
#pragma config(Motor,  port8,           liftRight,     tmotorServoContinuousRotation, openLoop, driveRight)
#pragma config(Motor,  port9,           rightMotor,    tmotorServoContinuousRotation, openLoop, driveRight)
//*!!Code automatically generated by 'ROBOTC' configuration wizard               !!*//

// Include the other files necessary for the program
#include "config.h"
#include "drive.c"
#include "actions.c"

// Run the main task: starts the other tasks and controls the back door
task main()
{
	// Runs the start-up actions (see actions.c)
	init();

	// Starts taking input from the driver for moving (see drive.c)
	startTask(drive);

	startTask(dump_dozer);

	// Runs the moveable door
	while(true){
		if(vexRT[DOOR_UP_BTN]){
			// Call the toggle_door method (in actions.c) to open the door
			toggle_door(true);
		}else if(vexRT[DOOR_DOWN_BTN]){
			// Call the toggle_door method (in actions.c) to close the door
			toggle_door(false);
		}
	}
}
