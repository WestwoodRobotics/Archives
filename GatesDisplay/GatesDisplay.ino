/*
  BEST 2013 Display Code MC1 
  Input Pins:
    not: 0
    and: 2, 3
    or: 5, 6
    nand: 8, 9
    nor:11, 12
    xor: A0, A1
    xnor: A3, A4
    
  Output Pins:
    not: 1
    and: 4
    or: 7
    nand: 10
    nor: 13
    xor: A2
    xnor: A5
*/
int btnPins[1] = {2,3,5,6,8,9,11,12,A0,A1,A3,A4};
int btnStates[1];
int ledPins[6] = {1,4,7,10,13,A2,A5};
int ledStates[6];
int i = 0;

void setup() {
  for(i = 0; i < 12; i++){
    pinMode(btnPins[i], INPUT_PULLUP);
  }
  
  for(i = 0; i < 6; i++){
    pinMode(ledPins[i], OUTPUT);
  }
}

void loop(){

  for(i = 0; i < 12; i++){
    btnStates[i] = !digitalRead(btnPins[i]);
  }

  ledStates[0] = (btnStates[0] && btnStates[1] ? HIGH : LOW); //AND
  ledStates[1] = (btnStates[2] | btnStates[3] ? HIGH : LOW); //OR
  ledStates[2] = (btnStates[4] && btnStates[5] ? LOW : HIGH); //NAND
  ledStates[3] = (btnStates[6] | btnStates[7] ? LOW : HIGH); //NOR
  ledStates[4] = (btnStates[8] ^ btnStates[9] ? HIGH : LOW); //XOR
  ledStates[5] = (btnStates[10] ^ btnStates[11] ? LOW : HIGH); //XNOR
  
  for(i = 0; i < 6; i++){
    digitalWrite(ledPins[i], ledStates[i]);
  }
}
