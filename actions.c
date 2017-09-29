#include "config.h"

task drive(){
	while (1){
		int joy1 = vexRT[Ch2];
		int joy2 = vexRT[Ch3];
		motor[leftMotor] = joy1;
		motor[rightMotor] = joy2;
	}
}

task shoot(){
	while (1000000){
		int button1 = vexRT[Btn8U];
		motor[shootServo] = maxShootAngle;
	}
}
