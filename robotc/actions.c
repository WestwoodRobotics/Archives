// Uses tank driving, utilizing both joysticks for the forward/back motion of their respective wheels
void tank_drive(){
	// Asigns the motor speed based on the position of the joy stick, and whether the driver is going in reverse or not
	//	if driving in reverse, the joysticks that input is taken from is reversed
	motor[rightWheel] = DEADBAND(vexRT[AXIS_RY]) * (reversed ? -1 : 1);
	motor[leftWheel] = DEADBAND(vexRT[AXIS_LY]) * (reversed ? -1 : 1);
}

// Uses driving where one joystick controls forwards/backwatds, and one controls left/right
void arcade_drive(){
	// Asigns the motor speed based on the position of the joy stick, and whether the mode is reversed or not
	motor[rightWheel] = DEADBAND(vexRT[AXIS_RY]) - DEADBAND(vexRT[AXIS_LX]) * (reversed ? -1 : 1);
	motor[leftWheel] = DEADBAND(vexRT[AXIS_RY]) + DEADBAND(vexRT[AXIS_LX]) * (reversed ? -1 : 1);
}

// Task that drives the robot
task drive(){
	// The robot will drive until turned off
	while(true){
		// if the toggle button is pressed, it will toggle tank drive off / on
		if (vexRT[BTN_DTOGGLE]){
			tankDrive = !tankDrive;
			while(vexRT[BTN_DTOGGLE]);	// Wait until the button is no longer pressed
		}
		// if the reverse button is pressed, it will toggle reverse off / on
		if(vexRT[BTN_REVERSE]) {
			reversed = !reversed;
			while (vexRT[BTN_REVERSE]);	// Wait until the button is no longer pressed
		}

		// calls either the tank or arcade drive methods
		if (tankDrive)
			tank_drive();
		else
			arcade_drive();
	}
}

// Runs the roller on the side of the robot
task roller(){
	while(true){
		motor[rollerMotor] = (vexRT[BTN_ROLLER_UP] - vexRT[BTN_ROLLER_DOWN]) * 127;	// Runs the roller motor when either controlling button is pressed
	}
}

// Runs the sweeper on the front of the robot
task sweeper(){
	while(true){
		motor[sweeperMotor] = (vexRT[BTN_SWEEPER] - vexRT[BTN_SWEEPER_REVERSE]) * 127;	// Runs the sweeper when either controlling button is pressed
	}
}

// Runs the interval servo for the ramp
task ramp(){
	while(true){
		if(vexRT[BTN_RAMP_UP]){
			motor[rampServo] = RAMP_MAX;
		}else if(vexRT[BTN_RAMP_DOWN]){
			motor[rampServo] = RAMP_MIN;
		}
	}
}
