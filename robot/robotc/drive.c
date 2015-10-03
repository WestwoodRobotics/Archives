// This method moves both wheels while taking input from only the right joystick
void sync_drive(){
	motor[leftMotor] = DEADBAND(vexRT[JOY_AXIS_RIGHT]) * (vexRT[SLOW_BTN] > 0 ? SLOW_MOD : 1) * (vexRT[BACK_BTN] > 0 ? -1 : 1);
	motor[rightMotor] = DEADBAND(vexRT[JOY_AXIS_RIGHT]) * (vexRT[SLOW_BTN] > 0 ? SLOW_MOD : 1) * (vexRT[BACK_BTN] > 0 ? -1 : 1);
}

void sync_arcade(){
	motor[leftMotor] = (DEADBAND(vexRT[JOY_AXIS_RIGHT]) + DEADBAND(vexRT[JOY_AXIS_TURN])) * (vexRT[SLOW_BTN] > 0 ? SLOW_MOD : 1) * (vexRT[BACK_BTN] > 0 ? -1 : 1);
	motor[leftMotor] = (DEADBAND(vexRT[JOY_AXIS_RIGHT]) - DEADBAND(vexRT[JOY_AXIS_TURN])) * (vexRT[SLOW_BTN] > 0 ? SLOW_MOD : 1) * (vexRT[BACK_BTN] > 0 ? -1 : 1);
}

// This method moves each wheel independently, taking input from the respective joysticks
void tank_drive(){
	motor[leftMotor] = DEADBAND(vexRT[JOY_AXIS_LEFT]) * (vexRT[SLOW_BTN] > 0 ? SLOW_MOD : 1) * (vexRT[BACK_BTN] > 0 ? -1 : 1);
	motor[rightMotor] = DEADBAND(vexRT[JOY_AXIS_RIGHT]) * (vexRT[SLOW_BTN] > 0 ? SLOW_MOD : 1) * (vexRT[BACK_BTN] > 0 ? -1 : 1);
}

// Takes input from the driver to move the robot
// This task operates concurrently with the main method
task drive(){
	// The loop repeats forever (or until the robot is turned off)
	while(true){
		if(vexRT[SYNC_BTN]){
			// If the SYNC_BTN is pressed, we want to move both wheel together
			sync_drive();
		}else{
			// If the SYNC_BTN is not pressed, we want to move each wheel independently
			tank_drive();
		}
	}
}
