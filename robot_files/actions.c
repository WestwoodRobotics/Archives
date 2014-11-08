
bool isDriveTogglePressed = false;
/*
 * run drive switch
 * should we switch the drivemode?
 */
void checkSwitchDrivemode(){
    if(vexRT[JOY_BTN_TOGGLE_DRIVEMODE]){
        if(!isDriveTogglePressed){
            isDriveTogglePressed = true;
            currentDrivemode = DRIVE_MAX - currentDrivemode;
            motor[servoFlagDrive] = flagDriveServo[currentDrivemode];
        }
    }else{
        isDriveTogglePressed = false;
    }
}

bool isFlagTogglePressed = false;
/*
 * run flag switch
 * should we switch the flag mode?
 */
void checkSwitchFlagMode(){
    if(vexRT[JOY_BTN_TOGGLE_FLAGMODE]){
        if(!isFlagTogglePressed){
            isFlagTogglePressed = true;
            currentFlagMode = FLAG_MAX - currentFlagMode;
            motor[servoFlagMode] = flagModeServo[currentFlagMode];
        }
    }else{
        isFlagTogglePressed = false;
    }
}

/*
 * runArm
 * control the arms
 */
void runArm(){
    float lr8 = vexRT[JOY_BTN_ARM_LEFT] - vexRT[JOY_BTN_ARM_RIGHT];
    if(SensorValue[leftArmSwitch] && lr8 > 0) lr8 = 0;
    if(SensorValue[rightArmSwitch] && lr8 < 0) lr8 = 0;
    motor[armLRMotor] = lr8 * BTN_MOTOR_SPEED;
    
    float ud8 = vexRT[JOY_BTN_ARM_UP] - vexRT[JOY_BTN_ARM_DOWN];
    if(SensorValue[bottomHeightSwitch] && ud8 < 0) ud8 = 0;
    //if(!SensorValue[topHeightSwitch && ud8 > 0) ud8 = 0;
    motor[armUDMotor] = ud8 * BTN_MOTOR_SPEED;
}

/*
 * runClaw
 * control the claw
 */
void runClamp(){
    float ud7;
    ud7 = vexRT[JOY_BTN_CLAMP_OPEN] - vexRT[JOY_BTN_CLAMP_CLOSE];
    if(ud7 != 0.0) motor[servoClamp] = ud7 * 110;
}

void runHitch(){
    float lr7;
    lr7 = vexRT[JOY_BTN_HITCH_UP] - vexRT[JOY_BTN_HITCH_DOWN];
    if(lr7 != 0.0) motor[servoHitch] = lr7 * 128;
}

void cancelFlagTasks(){
    stopTask(task_flag_mode_chicken);
    stopTask(task_flag_mode_blade);
}

/*
 * runModeTask
 * run the currently selected mode task.
 */
void runModeTask(){
    cancelFlagTasks();
    switch (currentDrivemode) {
    case FLAG_CHICKEN:
        startTask(task_flag_mode_chicken);
        break;
    case FLAG_BLADE:
        startTask(task_flag_mode_blade);
        break;
    default:
        
        break;
    }
}

bool isModeRunPressed = false;
void checkRunModeTask(){
    if(vexRT[JOY_BTN_MODE_RUN]){
        if(!isModeRunPressed){
            isModeRunPressed = true;
            runModeTask();
        }
    }else{
        isModeRunPressed = false;
    }
}

/*
 * initFlags
 * set the flags to their default position on startup
 */
void initFlags(){
    motor[servoFlagDrive] = flagDriveServo[currentDrivemode];
    motor[servoFlagMode] = flagModeServo[currentFlagMode];
}

void initClamp(){
    motor[servoClamp] = 110;
}

void initHitch(){
    motor[servoHitch] = -128;
}