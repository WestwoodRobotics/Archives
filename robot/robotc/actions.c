// Moves the lift based on input from two buttons
task dump_dozer(){
	while(true){
		// The lift is controlled by the up or down buttons on both sides of the controller
		int up = vexRT[DUST_UP];													// Input for up
		int down = vexRT[DUST_UP];												// Input for down
		motor[dustPan] = (up - down) * BTN_SPEED;					// Sets the dust pan motor's speed
	}
}

task fork_lift(){
	while(true){
		int up2 = vexRT[FORK_UP];													// Input for up
		int down2 = vexRT[FORK_DOWN];											// Input for down
		motor[forkLift] = (up2 - down2) * BTN_SPEED;			// Sets the fork lift's motor's speed
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
