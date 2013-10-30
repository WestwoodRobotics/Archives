/**
 *
 * BEST 2013 Logic Gate Puzzle Display
 *
 *
 * Austin Reuland
 * Westwood Robotics
 */

//Button Input Pins
int btnPins[10] = {5,6,7,2,3,4,8,9,10,11};
//LED Output Pins
int ledPins[8] = {A2,A3,A4,A5,A1,A0,13,12};
//Button States (True/1, False/0)
int btnStates[10];
//Button Value Groups
int btnSets[3];
//Path State Values {A,B,C,D,E,F,G,H}
int pathStates[8];
//Variables that are reused Constantly
int i, baseI, serialBytes;
//Variables for programming mode
int programMode, currentPath, pgmBtns[3];

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

  /*Possible Starting Paths:
  A B C D|A B C D|A B C D|A B C D
  0 0 0 0|0 0 0 1|0 0 1 0|0 0 1 1
  0 1 0 0|0 1 0 1|0 1 1 0|0 1 1 1
  1 0 0 0|1 0 0 1|1 0 1 0|1 0 1 1
  1 1 0 0|1 1 0 1|1 1 1 0|1 1 1 1
  */
  randomSeed(analogRead(A6));
  for(i = 0; i < 4; i++){
    pathStates[i] = random(0,2);
  }

  //Begin Serial Communication
  Serial.begin(9600);
  //Send 'Setup Complete' Message
  Serial.print("Setup Complete\n");

}

//Start the Main Loop
void loop(){

  //Grab all the Button Values
  for(i = 0; i < 10; i++){
    btnStates[i] = !digitalRead(btnPins[i]);
  }

  //Calculate Each Button Group Value
  for(i = 0; i < 3; i++){
    baseI = i * 3;
    btnSets[i] = btnStates[baseI]*1 + btnStates[baseI+1]*2 + btnStates[baseI+2]*4;
  }

  if(btnSets[2] == 7 && btnSets[1] == 7)
    programMode = 1;


  if(programMode){

    if(btnStates[8] == 1 && pgmBtns[2] == 0)
    pathStates[currentPath] = !pathStates[currentPath];

    if(btnStates[7] == 1 && pgmBtns[1] == 0)
    currentPath++;

    if(currentPath == 4)
    currentPath = 0;

    if(btnStates[6] == 1 && pgmBtns[0] == 0)
    programMode = 0;

    pgmBtns[0] = btnStates[6];
    pgmBtns[1] = btnStates[7];
    pgmBtns[2] = btnStates[8];

    }else{
    //Check if we have Valid Paths
    pathStates[4] = checkPath(btnSets[0], pathStates[0], pathStates[1]);
    pathStates[5] = checkPath(btnSets[1], pathStates[2], pathStates[3]);
    pathStates[6] = checkPath(8, pathStates[4], btnStates[9]);
    pathStates[7] = checkPath(btnSets[2], pathStates[5], pathStates[6]) && (btnSets[0] > 0) && (btnSets[1] > 0) && (btnSets[2] > 0) ? 1 : 0;
  }
    //Write out the LED Values
    for(i = 0; i < 8; i++){
      digitalWrite(ledPins[i], pathStates[i]);
    }

  // Serial.print(String(btnStates[0]) + "|" +
  //              String(btnStates[1]) + "|" +
  //              String(btnStates[2]) + "|" +
  //              String(btnStates[3]) + "|" +
  //              String(btnStates[4]) + "|" +
  //              String(btnStates[5]) + "|" +
  //              String(btnStates[6]) + "|" +
  //              String(btnStates[7]) + "|" +
  //              String(btnStates[8]) + "|" +
  //              String(btnStates[9]) + "\n");
}

//Method to check for a Valid Path
int checkPath(int n, int a, int b){
  switch (n) {
      case 1: //If We are using the AND Gate
      return ((a == 1) && (b == 1));
      break;
      case 2: //If we are using the OR Gate
      return ((a == 1) || (b == 1));
      break;
      case 3: //If we are using NAND Gate
      return !((a == 1) && (b == 1));
      break;
      case 4: //If we are using NOR Gate
      return !((a == 1) || (b == 1));
      break;
      case 5: //If we are using XOR Gate
      return ((a == 1) ^ (b == 1));
      break;
      case 6: //If we are using XNOR Gate
      return !((a == 1) ^ (b == 1));
      break;
      case 8: //For the NOT Gate
      return ((b == 1 ? !a : 0) || (b == 0 ? a : 0));
      break;

      default:
      return 0;
    }
  }