#pragma config(Motor,  port2,           rightWheel,    tmotorServoContinuousRotation, openLoop)
#pragma config(Motor,  port3,           leftWheel,     tmotorServoContinuousRotation, openLoop, reversed)
//*!!Code automatically generated by 'ROBOTC' configuration wizard               !!*//

#include "config.h"
#include "actions.c"

task main()
{
	StartTask(drive);

	while(true);
}
