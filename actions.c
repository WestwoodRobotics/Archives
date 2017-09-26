task drive(){

	while (1){
		int joy1 = vexRT[Ch2];
		int joy2 = vexRT[Ch3];
		motor[leftMotor] = joy1;
		motor[rightMotor] = joy2;
	}
}
