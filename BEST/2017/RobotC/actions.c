/*
 * drive the robot either in arcade or tank mode
 */
task drive(){
	while (true){
		wait1Msec(5); // Keep the processor from being overwhelmed (just in case)

		//Drive Process
		if(driveTypeArcade){
			//Arcade Drive - One vertical axis, one horizontal axis
			int y = vexRT[leftYAxis];	//Y Axis (vertical) - Forward and Backward
			int x = vexRT[leftXAxis];	//X Axis (horizontal) - Turn Left and Right
			motor[leftMotor] = (x + y)/2 * (isSlow ? slowSpeed : 1) * (isRev ? -1 : 1);
			motor[rightMotor] = (y - x)/2 * (isSlow ? slowSpeed : 1) * (isRev ? -1 : 1);
		}
		else{
			//Tank Drive - Each joystick corresponds to a wheel
			int left = vexRT[leftYAxis] * (isSlow ? slowSpeed : 1);
			int right = vexRT[rightYAxis] * (isSlow ? slowSpeed : 1);

			motor[leftMotor] = isRev ? -right : left;	  //Left Wheel
			motor[rightMotor] = isRev ? -left : right;	//Right Wheel
		}
	}
}

/*
 * Release the catapult if the button is pressed
 */
task shoot(){
	while (true){
		wait1Msec(5); // Keep the processor from being overwhelmed (just in case)

		if (vexRT[shootBtn] && vexRT[shootBtn2]){
			motor[shootServo] = maxShootAngle;	//Shooter fires water
			wait1Msec(10000);										//Wait before resetting
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
										 : vexRT[releaseBtn] ? -100	//Grabber moves up to original position
										 : motor[grabMotor];			//Grabber does nothing
	}
}

/*
 * Move the claw motor based on button input
 */
task magnets(){
	while(true){
		wait1Msec(5); // Keep the processor from being overwhelmed (just in case)

		motor[rightMagnet] = vexRT[magRBtn] ? 127			//magnet up
										 	 : vexRT[magRDnBtn] ? -127	//magnet down
										 	 : 0;															//nothing

		motor[leftMagnet]  = vexRT[magLBtn] ? 127
											 : vexRT[magLDnBtn] ? -127
											 : 0;
	}
}

/*
 * Update the slow-mode status
 */
task checkStates(){
	while(true){
		wait1Msec(5);

		if(vexRT[btnToggle]){
			driveTypeArcade = !driveTypeArcade; // Switches the drive variable
			while(vexRT[btnToggle]);						// Wait for the button to be released
		}

		if(vexRT[slowToggle]){
			isSlow = !isSlow;
			while(vexRT[slowToggle]);
		}

		if(vexRT[revToggle]){
			isRev = !isRev;
			while(vexRT[revToggle]);
		}
	}
}
