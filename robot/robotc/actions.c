// Runs the start-up stuffs
void init(){

}

void run_door(){
	motor[doorServo] = doorOpen ? doorClosePos : doorOpenPos;
}
