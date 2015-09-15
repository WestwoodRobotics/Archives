task tank_drive(){
	motor[leftMotor] = DEADBAND(vexRT[JOY_AXIS_LEFT]) * (SLOW_MOD / (vexRT[SLOW_BTN] > 0 ? 1 : SLOW_MOD);
	motor[rightMotor] = DEADBAND(vexRT[JOY_AXIS_RIGHT]) * (SLOW_MOD / vexRT[SLOW_BTN] > 0 ? 1 : SLOW_MOD);
}
