/****Drive Methods****/

void sync_arcade(){
	int leftSpeed = (DEADBAND(vexRT[JOY_AXIS_LEFT]) + DEADBAND(vexRT[JOY_AXIS_TURN])) * SLOW * REVERSE;
	int rightSpeed = (DEADBAND(vexRT[JOY_AXIS_LEFT]) - DEADBAND(vexRT[JOY_AXIS_TURN])) * SLOW * REVERSE;
	motor[leftMotor] = IS_REVERSED ? rightSpeed : leftSpeed;
	motor[rightMotor] = IS_REVERSED ? leftSpeed : rightSpeed;
}

// This method moves each wheel independently, taking input from the respective joysticks
void tank_drive(){
	int leftSpeed = DEADBAND(vexRT[JOY_AXIS_LEFT]) * SLOW * REVERSE;
	int rightSpeed = DEADBAND(vexRT[JOY_AXIS_RIGHT]) * SLOW * REVERSE;
	motor[leftMotor] = IS_REVERSED ? rightSpeed : leftSpeed;
	motor[rightMotor] = IS_REVERSED ? leftSpeed : rightSpeed;
}

// Takes input from the driver to move the robot
// This task operates concurrently with the main method
task drive(){
	// The loop repeats forever (or until the robot is turned off)
	while(true){
		if(vexRT[ARCADE_BTN]){
			arcade = !arcade; // Toggle arcade mode
			while(vexRT[ARCADE_BTN]){}
		}
		if(arcade){
			// If arcade mode is activated, we want to move both wheels together
			sync_arcade();
		}else{
			// If arcade mode is not activated, we want to move each wheel independently
			tank_drive();
		}
	}
}

/****Miscellaneous Methods****/

// Moves the lift based on input from two buttons
task dust_pan(){
	while(true){
		// The lift is controlled by the up or down buttons on both sides of the controller
		int up = vexRT[DUST_UP];													// Input for up
		int down = vexRT[DUST_DOWN];												// Input for down
		motor[dustPan] = (up - down) * BTN_SPEED;					// Sets the dust pan motor's speed
	}
}

// Runs the start-up stuffs
void init(){
	arcade = false;				// Set the default drive mode to tank_drive
}
