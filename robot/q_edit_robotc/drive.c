void sync_arcade(){
	motor[leftMotor] = (DEADBAND(vexRT[JOY_AXIS_LEFT]) + DEADBAND(vexRT[JOY_AXIS_TURN])) * SLOW * REVERSE;
	motor[rightMotor] = (DEADBAND(vexRT[JOY_AXIS_LEFT]) - DEADBAND(vexRT[JOY_AXIS_TURN])) * SLOW * REVERSE;
}

// This method moves each wheel independently, taking input from the respective joysticks
void tank_drive(){
	motor[leftMotor] = DEADBAND(vexRT[JOY_AXIS_LEFT]) * SLOW * REVERSE;
	motor[rightMotor] = DEADBAND(vexRT[JOY_AXIS_RIGHT]) * SLOW * REVERSE;
}

// Takes input from the driver to move the robot
// This task operates concurrently with the main method
task drive(){
	// The loop repeats forever (or until the robot is turned off)
	while(true){
		if(vexRT[ARCADE_BTN]) arcade = !arcade;
		if(arcade){
			// If the ARCADE_BTN is pressed, we want to move both wheel together
			sync_arcade();
		}else{
			// If the ARCADE_BTN is not pressed, we want to move each wheel independently
			tank_drive();
		}
	}
}
