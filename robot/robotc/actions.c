// Moves the lift based on input from two buttons
task forklift(){
	while(true){
		// The lift is controlled by the up or down buttons on both sides of the controller
		int up = vexRT[FORKLIFT_UP];												// Input for up
		int down = vexRT[FORKLIFT_DOWN];										// Input for down
		motor[liftLeft] = (up - down) * BTN_SPEED;					// Sets the left motor's speed
		motor[liftRight] = (up - down) * BTN_SPEED;					// Sets the right motor's speed
	}
}

// Runs the start-up stuffs
void init(){
	toggle_door(true);		// Close the back door
	arcade = false;				// Set the default drive mode to tank_drive
}
