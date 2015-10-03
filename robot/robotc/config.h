/*
* Copyright (c) 2014 Westwood Robotics <code.westwoodrobotics@gmail.com>.
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
#define JOY_AXIS_LEFT Ch3
#define JOY_AXIS_RIGHT Ch2
#define JOY_AXIS_TURN Ch1

// Press this button to decrease the speed of the robot
#define SLOW_BTN Btn7L
// Press this button to control both motor simultaneously with the right joysitck
// Note: Only works with forward-backward movement. Not turning.
#define SYNC_BTN Btn6U
// Press this button to raise position of the back door
#define DOOR_UP_BTN Btn5U
#define DOOR_DOWN_BTN Btn5D
#define BACK_BTN Btn6D
// Control the lift
#define LIFT_UP Btn8U
#define LIFT_UP2 Btn7U
#define LIFT_DOWN Btn8D
#define LIFT_DOWN2 Btn7D

/****Constants****/
// Defines how fast the slow speed of the robot should be relative to normal speed
#define SLOW_MOD 0.5
#define BTN_SPEED 127

// Defines the positions that servo controlling the door is in when the door is opened and closed
#define doorClosePos 127
#define doorOpenPos (-1 * doorClosePos)

/****Variables: They Change****/
// Tracks the current position of the door
//bool doorOpen;

bool arcade = false;

#endif
