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
