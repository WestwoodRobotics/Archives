task drive(){
	while (true){
		//Toggle Button
		if (vexRT[btnToggle]){
			driveTypeArcade = !driveTypeArcade;	//Switches button
			while(vexRT[btnToggle]);	//Loop holds true until button is released
		}

		//Drive Process
		if(driveTypeArcade){
			//Arcade Drive
			int y = vexRT[Ch3];	//Y Axis (vertical) - Forward and Backward
			int x = vexRT[Ch4];	//X Axis (horizontal) - Turn Left and Right
			motor[leftMotor] = (x + y)/2;
			motor[rightMotor] = (y - x)/2;
		}
		else{
			//Tank Drive - Each joystick corresponds to a wheel
			motor[leftMotor] = vexRT[leftYAxis];	//Left Wheel
			motor[rightMotor] = vexRT[rightYAxis];	//Right Wheel
		}
	}
}

task shoot(){
	while (true){
		if (vexRT[shootBtn]){
			motor[shootServo] = maxShootAngle;	//Shooter fires water
			delay(1000);
		}
		else{
			motor[shootServo] = minShootAngle;	//Shooter resets to original position
		}
	}
}

task grabber(){
	while(true){
		motor[grabMotor] = vexRT[grabButton] ? 127	//Grabber drops down around Manny
		: vexRT[releaseButton] ? -127	//Grabber moves up to original position
		: 0;	//Grabber does nothing
	}
}

task claw(){
	while(true){
		motor[clawMotor] = vexRT[clawBtn] ? 127	//Claw clutches chemical can
		: vexRT[clawRevBtn] ? -127	//Claw releases chemical can
		: 0;	//Claw does nothing
	}
}
