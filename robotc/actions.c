// Uses tank driving, both controls forwards
void tank_drive(){
	// Asigns the motor speed based on the placement of the joy stick, and whether the mode is reversed or not
	motor[rightWheel] = DEADBAND(vexRT[AXIS_RY]) * (reversed ? -1 : 1);
	motor[leftWheel] = DEADBAND(vexRT[AXIS_LY]) * (reversed ? -1 : 1);
}

// Uses driving where one joystick controls forwards/backwatds, and one controls left/right
void arcade_drive(){
	// Asigns the motor speed based on the placement of the joy stick, and whether the mode is reversed or not
	motor[rightWheel] = DEADBAND(vexRT[AXIS_RY]) - DEADBAND(vexRT[AXIS_LX]) * (reversed ? -1 : 1);
	motor[leftWheel] = DEADBAND(vexRT[AXIS_RY]) + DEADBAND(vexRT[AXIS_LX]) * (reversed ? -1 : 1);
}

// Multithreading thingy
task drive(){
	// runs these if statements forever
	while(true){
		// if Button '6u' is pressed, it will toggle tank drive off / on
		if (vexRT[BTN_DTOGGLE]){
			tankDrive = !tankDrive;
			while(vexRT[BTN_DTOGGLE]);
		}
		// if button '5u' is pressed, it will toggle reverse off / on
		if(vexRT[BTN_REVERSE]) {
			reversed = !reversed;
			while (vexRT[BTN_REVERSE]);
		}

		// tank drive if the bool is true, arcade drive if the bool is false
		if (tankDrive)
			tank_drive();
		else
			arcade_drive();
	}
}

task net(){
	while(true){
		motor[netMotor] = vexRT[BTN_NET_UP] - vexRT[BTN_NET_DOWN];
	}
}

task slider(){
	while(true){
		motor[sliderMotor] = vexRT[BTN_SLIDER_UP] - vexRT[BTN_SLIDER_DOWN];
	}
}
