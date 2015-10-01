// Runs the start-up stuffs
void init(){

}

void toggle_door(){
	motor[doorServo] = doorOpen ? doorClosePos : doorOpenPos;
	doorOpen = !doorOpen;
}
