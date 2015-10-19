// Moves the lift based on input from two buttons
task dustpan(){
	while(true){
		// The lift is controlled by the up or down buttons on both sides of the controller
		int up = vexRT[DUST_UP];													// Input for up
		int down = vexRT[DUST_UP];												// Input for down
		motor[dustPan] = (up - down) * BTN_SPEED;					// Sets the dust pan motor's speed
	}
}

task fork_lift(){
	while(true){
		int up = vexRT[FORK_UP];													// Input for up
		int down = vexRT[FORK_DOWN];											// Input for down
		motor[forkLift] = (up - down) * BTN_SPEED;			// Sets the fork lift's motor's speed
	}
}

// Runs the start-up stuffs
void init(){
	arcade = false;				// Set the default drive mode to tank_drive
}
