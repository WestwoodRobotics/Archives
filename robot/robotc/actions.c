// Moves the lift based on input from two buttons
task dustpan(){
	while(true){
		// The lift is controlled by the up or down buttons on both sides of the controller
<<<<<<< HEAD
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
=======
		int up = vexRT[DUSTPAN_UP];												// Input for up
		int down = vexRT[DUSTPAN_DOWN];										// Input for down
		motor[dustpanMotor] = (up - down) * BTN_SPEED;					// Sets the left motor's speed
>>>>>>> 30761cac855f0f53cdd4b99819748e904a24f9f9
	}
}

// Runs the start-up stuffs
void init(){
	arcade = false;				// Set the default drive mode to tank_drive
}
