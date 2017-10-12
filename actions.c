task drive(){
	while (true){
		//Toggle Button
		if (vexRT[btnToggle]){
			driveTypeArcade = !driveTypeArcade;
			while(vexRT[btnToggle]);
		}

		//Drive
		if(driveTypeArcade){
			int y = vexRT[Ch3];
			int x = vexRT[Ch4];
			motor[leftMotor] = (x + y)/2;
			motor[rightMotor] = (y - x)/2;
		}
		else{
			motor[leftMotor] = vexRT[leftYAxis];
			motor[rightMotor] = vexRT[rightYAxis];
		}
	}
}

task shoot(){
	while (true){
		if (vexRT[shootBtn]){
			motor[shootServo] = maxShootAngle;
		}
		else{
			motor[shootServo] = minShootAngle;
		}
	}
}

task grabber(){
	while(true){
		motor[grabMotor] = vexRT[grabButton] ? 127
										 : vexRT[releaseButton] ? -127
										 : 0;
	}
}

task claw(){
	while(true){
		motor[clawMotor] = vexRT[clawBtn] ? 127
										 : vexRT[clawRevBtn] ? -127
										 : 0;
	}
}
