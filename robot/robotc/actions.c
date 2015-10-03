// Moves the lift based on input from two buttons
task dump_dozer(){
	while(true){
		// The lift activates if the up or down buttons on either side of the controller are pressed
		motor[liftLeft] = ((vexRT[LIFT_UP] + vexRT[LIFT_UP2]) - (vexRT[LIFT_DOWN] + vexRT[LIFT_DOWN2])) * BTN_SPEED;
		motor[liftRight] = ((vexRT[LIFT_UP] + vexRT[LIFT_UP2]) - (vexRT[LIFT_DOWN] + vexRT[LIFT_DOWN2])) * BTN_SPEED;
	}
}

// Toggles the state of the back door
void toggle_door(){
	// If the door is open, close it, if it is closed, open it
	motor[doorServo] = doorOpen ? doorClosePos : doorOpenPos;
	// Change the boolean tracker to reflect the new state
	doorOpen = !doorOpen;
}

// Runs the start-up stuffs
void init(){
	// Assume that the door is open so the it can be closed
	doorOpen = true;
	// Close the back door
	toggle_door();
}
