task tank_drive(){
	motor[leftMotor] = DEADBAND(vexRT[JOY_AXIS_LEFT]);
	motor[rightMotor] = DEADBAND(vexRT[JOY_AXIS_RIGHT]);
}
