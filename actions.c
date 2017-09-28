#include "config.h"

task drive(){
	while (true){
		if (driveTypeArcade == false){
			int joy1 = vexRT[Ch3];
			int joy2 = vexRT[Ch2];
			motor[leftMotor] = joy1;
			motor[rightMotor] = joy2;
		}
	}
}

task shoot(){
	while (true){
		int button1 = vexRT[Btn8U];
		if (button1){
			motor[shootServo] = maxShootAngle;
		}
		else{
			motor[shootServo] = minShootAngle;
		}
	}
}


task arcadeDrive(){
	while (true){
		int btnTogl = vexRT[Btn5U];
		if (btnTogl){
			driveTypeArcade = !driveTypeArcade;
		}
		if (driveTypeArcade){
			int y = vexRT[Ch3];
			int x = vexRT[Ch4];
			motor[leftMotor] = (x + y)/2;
			motor(rightMotor) = (y - x)/2;
		}
	}
}
