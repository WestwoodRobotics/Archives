// Drives with independent input from left and right joysticks
task tank_drive(){
	while(true){
		motor[leftMotor] = DEADBAND(vexRT[JOY_AXIS_LEFT]) * (vexRT[SLOW_BTN] > 0 ? SLOW_MOD);
		motor[rightMotor] = DEADBAND(vexRT[JOY_AXIS_RIGHT]) * (vexRT[SLOW_BTN] > 0 ? SLOW_MOD);
	}
}
