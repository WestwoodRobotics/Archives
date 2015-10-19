/*
* Copyright (c) 2015 Westwood Robotics <code.westwoodrobotics@gmail.com>.
*
* Permission is hereby granted, free of charge, to any person obtaining
* a copy of this software and associated documentation files (the
* "Software"), to deal in the Software without restriction, including
* without limitation the rights to use, copy, modify, merge, publish,
* distribute, sublicense, and/or sell copies of the Software, and to
* permit persons to whom the Software is furnished to do so, subject to
* the following conditions:
*
* The above copyright notice and this permission notice shall be
* included in all copies or substantial portions of the Software.
*/

/****Defines constants and variables for the program****/
#ifndef RCONFIG
#define RCONFIG

// Deadband makes sure that the robot doesn't move when it shouldn't
#define DEADBAND_LIMIT 10.0
#define DEADBAND(x) ((abs(x) >= DEADBAND_LIMIT) ? x : 0.0)

/****Button and Joysick Names****/
#define JOY_AXIS_LEFT Ch3   	// Forward-Backward movement on the left joystick
#define JOY_AXIS_RIGHT Ch2  	// Forward-Backward movement on the right joystick
#define JOY_AXIS_TURN Ch1   	// Left-Right movement on the right joystick for turning

// Control the drive
#define SLOW_BTN Btn5U      	// Decreases the speed of the robot
#define ARCADE_BTN Btn5D    	// Toggles the robot's drive mode
#define REVERSE_BTN Btn6U			// Changes the direction of the robot's wheels

// Control the dustpan
#define DUST_UP Btn7U					// Moves the dust pan up
#define DUST_DOWN Btn7D				// Moves the dust pan down

// Control the forklift
#define FORK_UP Btn8U					// Moves the forklift up
#define FORK_DOWN Btn8D				// Moves the forklift down

/****Constants with numbers****/
#define SLOW_MOD 0.5					// The relative slow speed of the robot
#define BTN_SPEED 127					// A modifier to multiply the button inputs by for motors

// Defines the positions for the opened and closed positions for the back door
#define doorClosePos 127
#define doorOpenPos (-1 * doorClosePos)

/****Variables & Such: They Change****/
bool arcade = false;					// Tracks the drive mode for the robot

// Checks for slow and reverse modes based on button input
#define SLOW (vexRT[SLOW_BTN] > 0 ? SLOW_MOD : 1)
#define REVERSE (vexRT[REVERSE_BTN] > 0 ? -1 : 1)

#endif // RCONFIG
