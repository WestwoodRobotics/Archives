#pragma config(Motor,  port2,           rightMotor,    tmotorServoContinuousRotation, openLoop, reversed, driveLeft)
#pragma config(Motor,  port3,           dustPan,       tmotorServoContinuousRotation, openLoop, reversed, driveLeft)
#pragma config(Motor,  port4,           servoFlagDrive,tmotorServoStandard,           openLoop)
#pragma config(Motor,  port8,           forkLift,      tmotorServoContinuousRotation, openLoop, driveRight)
#pragma config(Motor,  port9,           leftMotor,     tmotorServoContinuousRotation, openLoop, driveRight)
//*!!Code automatically generated by 'ROBOTC' configuration wizard               !!*//

// Include the other files necessary for the program
#include "config.h"
#include "actions.c"

// Run the main task: starts the other tasks and controls the back door
task main()
{
	// Runs the start-up actions (see actions.c)
	init();

	// Starts taking input from the driver for moving (see drive.c)
	startTask(drive);

	// Run the dust pan
	startTask(dust_pan);

	// Run the forklift
	while(true){
		int up = vexRT[FORK_UP];										// Input for up
		int down = vexRT[FORK_DOWN];										// Input for down
		motor[forkLift] = (up - down) * BTN_SPEED;								// Sets the fork lift's motor's speed
	}
}
