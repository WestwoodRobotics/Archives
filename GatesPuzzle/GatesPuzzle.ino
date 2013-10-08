/**
 *
 * BEST 2013 Logic Gate Puzzle Display
 *
 *
 * Austin Reuland
 * Westwood Robotics
 */


//Button Input Pins
int btnPins[10] = {2,3,4,5,6,7,8,9,10,11};
//LED Output Pins
int ledPins[8] = {12,13,A0,A1,A2,A3,A4,A5};
//Button States (True/1, False/0)
int btnStates[10];
//Button Value Groups
int btnSets[3] = {0,0,0};
//Path State Values {A,B,C,D,E,F,G,H}
int pathStates[8] = {0,0,0,0,0,0,0,0};
//Variables that are reused Constantly
int i, baseI, serialBytes;
//Serial Input Array
char serialIn[64];

//Setup the Arduino
void setup(){

  //Set the Button Input Pins (Uses Internal Pullup Resistors)
  for(i = 0; i < 10; i++){
    pinMode(btnPins[i], INPUT_PULLUP);
  }

  //Set the LED Relay Pins
  for(i = 0; i < 8; i++){
    pinMode(ledPins[i], OUTPUT);
  }

  //TODO Implement Custom Path Setup
  /*Possible Starting Paths:
  A B C D|A B C D|A B C D|A B C D
  0 0 0 0|0 0 0 1|0 0 1 0|0 0 1 1
  0 1 0 0|0 1 0 1|0 1 1 0|0 1 1 1
  1 0 0 0|1 0 0 1|1 0 1 0|1 0 1 1
  1 1 0 0|1 1 0 1|1 1 1 0|1 1 1 1
  */
  for(i = 0; i < 4; i++){
    pathStates[i] = random(0,1);
  }

  //Begin Serial Communication
  Serial.begin(9600);
  //Send 'Setup Complete' Message
  Serial.print("Setup Complete");
}

//Start the Main Loop
void loop(){

  //Get data from the serial port (If any Available)
  if(Serial.available()){
    serialBytes = Serial.available();
    Serial.readBytes(serialIn, serialBytes);
    if((serialIn[1] == '|' && serialIn[3] == '|' && serialIn[5] == '|' && serialIn[7] == '|')){
      pathStates[0] = atoi(serialIn[0]);
      pathStates[1] = atoi(serialIn[2]);
      pathStates[2] = atoi(serialIn[4]);
      pathStates[3] = atoi(serialIn[6]);
      Serial.print("Path Setup: Complete (A - " + pathStates[0] +
                   " | B - " + pathStates[1] +
                   " | C - " + pathStates[2] +
                   " | D - " + pathStates[3] + ")\n");
    }else{
      Serial.print("Path Setup: Failure\n")
    }
    
  }

  //Grab all the Button Values
  for(i = 0; i < 10; i++){
    btnStates[i] = digitalRead(btnPins[i]);
  }

  //Calculate Each Button Group Value
  for(i = 0; i < 3; i++){
    baseI = i * 3;
    btnSets[i] = btnStates[baseI]*1 + btnStates[baseI+1]*2 + btnStates[baseI+2]*4;
  }

  //Check if we have Valid Paths
  pathStates[4] = checkPath(btnSets[0], pathStates[0], pathStates[1]);
  pathStates[5] = checkPath(btnSets[1], pathStates[2], pathStates[3]);
  pathStates[6] = (pathStates[4] ^ btnStates[9] ? 1 : 0);
  pathStates[7] = checkPath(btnSets[2], pathStates[5], pathStates[6]);
  
  //Write out the LED Values
  for(i = 0; i < 8; i++){
    digitalWrite(ledPins[i], pathStates[i]);
  }
}

//Method to check for a Valid Path
int checkPath(int n, int a, int b){
  return (
          //If We are using the AND Gate
          ((n == 1 && (a == 1) && (b == 1))) ? 1 : 0) ||
          //If we are using the OR Gate
          ((n == 2 && ((a == 1) || (b == 1))) ? 1 : 0) ||
          //If we are using NAND Gate
          ((n == 3 && !((a == 1) && (b == 1))) ? 1 : 0) ||
          //If we are using NOR Gate
          ((n == 4 && !((a == 1) || (b == 1))) ? 1 : 0) ||
          //If we are using XOR Gate
          ((n == 5 && ((a == 1) ^ (b == 1))) ? 1 : 0) ||
          //If we are using XNOR Gate
          ((n == 6 && !((a == 1) ^ (b == 1))) ? 1 : 0)
        );
}