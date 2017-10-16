/*
 * check the state of the drive toggle button
 * and assign the drive state variable appropriately
 */
void checkDrive(){
	if(vexRT[btnToggle]){
		driveTypeArcade = !driveTypeArcade; // Switches the drive variable
		while(vexRT[btnToggle]);						// Wait for the button to be released
	}
}

/*
 * drive the robot either in arcade or tank mode
 */
task drive(){
	while (true){
		wait1Msec(5); // Keep the processor from being overwhelmed (just in case)

		//Toggle Button
		checkDrive();

		//Drive Process
		if(driveTypeArcade){
			//Arcade Drive - One vertical axis, one horizontal axis
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

/*
 * Release the catapult if the button is pressed
 */
task shoot(){
	while (true){
		wait1Msec(5); // Keep the processor from being overwhelmed (just in case)

		if (vexRT[shootBtn]){
			motor[shootServo] = maxShootAngle;	//Shooter fires water
			wait1Msec(1000);										//Wait before resetting
		}
		else{
			motor[shootServo] = minShootAngle;	//Shooter resets to original position
		}
	}
}

/*
 * Move the large grabber motor based on button input
 */
task grabber(){
	while(true){
		wait1Msec(5); // Keep the processor from being overwhelmed (just in case)

		motor[grabMotor] = vexRT[grabBtn] ? 127			//Grabber drops down around Manny
										 : vexRT[releaseBtn] ? -127	//Grabber moves up to original position
										 : 0;														//Grabber does nothing
	}
}

/*
 * Move the claw motor based on button input
 */
task claw(){
	while(true){
		wait1Msec(5); // Keep the processor from being overwhelmed (just in case)

		motor[clawMotor] = vexRT[clawBtn] ? 127			//Claw clutches chemical can
										 : vexRT[clawRevBtn] ? -127	//Claw releases chemical can
										 : 0;												//Claw does nothing
	}
}
