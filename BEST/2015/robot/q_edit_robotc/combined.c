/*
Created by Qrules for Westwood Robotics during the 2015-2016 BEST competition, Pay Dirt. Licensed under GNU GPL v3
*/

#pragma config(Motor,  port2,           leftWheelMotor,     tmotorServoContinuousRotation, openLoop, reversed, driveLeft)
#pragma config(Motor,  port3,           liftMotorLeft,      tmotorServoContinuousRotation, openLoop, driveLeft)
#pragma config(Motor,  port4,           doorMotor,     tmotorServoContinuousRotation, openLoop, driveLeft)  //Might need to change driveLeft
#pragma config(Motor,  port8,           liftMotorRight,     tmotorServoContinuousRotation, openLoop, driveRight)
#pragma config(Motor,  port9,           rightWheelMotor,    tmotorServoContinuousRotation, openLoop, driveRight)
//*!!Code automatically generated by 'ROBOTC' configuration wizard               !!*//
//Declares the names and ports of the connected sensors and motors


//Below are declarations for variables and constants used in the code

//Smooth to reduce jumpy behavior and debounce input
#define SMOOTH_ENABLED false 	//Change to true/false to enable/disable the smoothing
#define SMOOTH_LIMIT 10.0 	//Constant time value for delay, decrease for less delay
#define SMOOTH(x) ((abs(x) >= SMOOTH_LIMIT) ? x : 0.0) //defining the function of x

//Button and Joysick Names
#define JOY_AXIS_LEFT Ch3   	//Forward-Backward movement on the left joystick
#define JOY_AXIS_RIGHT Ch2  	//Forward-Backward movement on the right joystick
#define JOY_AXIS_TURN Ch1   	//Left-Right movement on the right joystick for turning

//Drive debug variables
#define SLOW_BTN Btn7L      	//Define button to decrease the speed of the robot
#define ARCADE_BTN Btn6U    	//Toggles the robot's drive mode
#define REVERSE_BTN Btn6D	//Changes the direction of the robot's wheels

//Opens and closes the back door
#define DOOR_UP_BTN Btn5U	//Raises the back door
#define DOOR_DOWN_BTN Btn5D	//Lowers the back door

//Control the lift
#define LIFT_UP Btn8U		//Moves the lift up
#define LIFT_UP2 Btn7U		//Also moves the lift up
#define LIFT_DOWN Btn8D		//Moves the lift down
#define LIFT_DOWN2 Btn7D	//Also moves the lift down

//Constants
#define SLOW_MOD 0.5		//The relative slow speed of the robot
#define BTN_SPEED 127		//A modifier to multiply the button inputs by for motors; decrease to decrease speed

//Variables
bool arcade = false;		// Tracks the drive mode for the robot

//Checks for slow and reverse modes based on button input
#define SLOW (vexRT[SLOW_BTN] > 0 ? SLOW_MOD : 1)
#define REVERSE (vexRT[REVERSE_BTN] > 0 ? -1 : 1)

#endif 				//End declararions of variables and constants


//Run the main task: starts the other tasks and controls the back door threaded
task main()
{
	//Runs the start-up actions (see actions.c)
	init();

	//Starts taking input from the driver for moving and for the bed(see drive.c)
	startTask(drive);
	startTask(dump_dozer);

	//Runs the moveable door
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






// Moves the lift based on input from two buttons
task dump_dozer(){
	while(true){
		// The lift is controlled by the up or down buttons on both sides of the controller
		int up = vexRT[LIFT_UP] + vexRT[LIFT_UP2];				// Input for up
		int down = vexRT[LIFT_DOWN] + vexRT[LIFT_DOWN2];	// Input for down
		motor[liftLeft] = (up - down) * BTN_SPEED;					// Sets the left motor's speed
		motor[liftRight] = (up - down) * BTN_SPEED;					// Sets the right motor's speed
	}
}

// Toggles the state of the back door
// The parameter if true if the door should move up, false if down
void toggle_door(bool moveUp){
	motor[doorServo] = moveUp ? doorOpenPos : doorClosePos;	// Change the state of the door
}

// Runs the start-up stuffs
void init(){
	toggle_door(true);		// Close the back door
	arcade = false;				// Set the default drive mode to tank_drive
}




void sync_arcade(){
	motor[leftMotor] = (DEADBAND(vexRT[JOY_AXIS_LEFT]) + DEADBAND(vexRT[JOY_AXIS_TURN])) * SLOW * REVERSE;
	motor[rightMotor] = (DEADBAND(vexRT[JOY_AXIS_LEFT]) - DEADBAND(vexRT[JOY_AXIS_TURN])) * SLOW * REVERSE;
}

// This method moves each wheel independently, taking input from the respective joysticks
void tank_drive(){
	motor[leftMotor] = DEADBAND(vexRT[JOY_AXIS_LEFT]) * SLOW * REVERSE;
	motor[rightMotor] = DEADBAND(vexRT[JOY_AXIS_RIGHT]) * SLOW * REVERSE;
}

// Takes input from the driver to move the robot
// This task operates concurrently with the main method
task drive(){
	// The loop repeats forever (or until the robot is turned off)
	while(true){
		if(vexRT[ARCADE_BTN]) arcade = !arcade;
		if(arcade){
			// If the ARCADE_BTN is pressed, we want to move both wheel together
			sync_arcade();
		}else{
			// If the ARCADE_BTN is not pressed, we want to move each wheel independently
			tank_drive();
		}
	}
}
