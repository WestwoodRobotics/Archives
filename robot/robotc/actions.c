// Moves the lift based on input from two buttons
task dustpan(){
	while(true){
		// The lift is controlled by the up or down buttons on both sides of the controller
		int up = vexRT[DUSTPAN_UP];												// Input for up
		int down = vexRT[DUSTPAN_DOWN];										// Input for down
		motor[dustpanMotor] = (up - down) * BTN_SPEED;					// Sets the left motor's speed
	}
}

// Runs the start-up stuffs
void init(){
	arcade = false;				// Set the default drive mode to tank_drive
}
