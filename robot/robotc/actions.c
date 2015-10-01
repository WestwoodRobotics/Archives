// Runs the start-up stuffs
void init(){
	// Assume that the door is open so the it can be closed
	doorOpen = true;
	// Close the back door
	toggle_door();
}

// Toggles the state of the back door
void toggle_door(){
	// If the door is open, close it, if it is closed, open it
	motor[doorServo] = doorOpen ? doorClosePos : doorOpenPos;
	// change the boolean tracker to reflect the new state
	doorOpen = !doorOpen;
}
