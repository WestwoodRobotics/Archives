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
