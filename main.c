#pragma config(Motor,  port2,           rightWheel,    tmotorServoContinuousRotation, openLoop)
#pragma config(Motor,  port3,           leftWheel,     tmotorServoContinuousRotation, openLoop, reversed)
#pragma config(Motor,  port4,           netMotor,      tmotorServoContinuousRotation, openLoop)
#pragma config(Motor,  port5,           sweeperMotor,  tmotorServoContinuousRotation, openLoop)
//*!!Code automatically generated by 'ROBOTC' configuration wizard               !!*//

#include "config.h"
#include "actions.c"

task main()
{
	StartTask(drive);
	StartTask(net);
	StartTask(sweeper);
	StartTask(sweeperReverse);

	while(true);	// Don't want to let the program end before the robot is shut down
}
