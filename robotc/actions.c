// Uses tank driving, utilizing both joysticks for the forward/back motion of their respective wheels
void tank_drive(){
	// Asigns the motor speed based on the position of the joy stick, and whether the driver is going in reverse or not
	motor[rightWheel] = !reversed ? DEADBAND(vexRT[AXIS_RY]) : (-1 * DEADBAND(vexRT[AXIS_LY]));
	motor[leftWheel] = !reversed ? DEADBAND(vexRT[AXIS_LY]) : (-1 * DEADBAND(vexRT[AXIS_RY]));
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

// Runs the net on the side of the robot
task net(){
	while(true){
		motor[netMotor] = (vexRT[BTN_NET_UP] - vexRT[BTN_NET_DOWN]) * 127;	// Runs te net motor when either button is pressed
	}
}

// Runs the sweeper on the front of the robot
task sweeper(){
	while(true){
		motor[sweeperMotor] = (vexRT[BTN_SWEEPER] - vexRT[BTN_SWEEPER_REVERSE]) * 127;	// Runs the sweeper when the button is pressed
	}
}

// Runs the continuous servo for the water
task water_servo(){
	while(true){
		motor[waterServoCont] = motor[waterServoCont] + (vexRT[BTN_WATER] - vexRT[BTN_WATER_REVERSE]) * (WATER_SERVO_SPEED / 20);	// Run the sweeper if the button is pressed
		wait1Msec(50);	// Wait for one twentieth of a second before continuing or 5o miliseconds
	}
}
