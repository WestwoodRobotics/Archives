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

// Defines constants and variables for the program
#ifndef RCONFIG
#define RCONFIG

// Deadband makes sure that the robot doesn't move when it shouldn't
#define DEADBAND_LIMIT 10.0
#define DEADBAND(x) ((abs(x) >= DEADBAND_LIMIT) ? x : 0.0)

// Button and joysick names
#define JOY_AXIS_LEFT Ch3
#define JOY_AXIS_RIGHT Ch2

#define SLOW_BTN Btn6D
#define FORWARD_BTN Btn6U
#define DOOR_BTN Btn5U

// Constants
#define SLOW_MOD 0.25

#define doorClosePos 127
#define doorOpenPos (doorClosePos + -100)

// Variables
bool doorOpen = false;

#endif
