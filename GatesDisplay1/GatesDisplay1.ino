/*
  BEST 2013 Display Code MC1 
  Input Pins:
    or: 24, 26
    and: 30, 32
    not: 36
    
  Output Pins:
    or: 3
    and: 6
    not: 9
*/

int btnPins[13] = {1,2,3,4,5,6,7,8,9,10,11,12,13};
int ledPins[7] = {A0,A1,A2,A3,A4,A5,A6};
short ledStates[7] = {LOW,LOW,LOW,LOW,LOW,LOW,LOW};

void setup() {
  int i = 0;
  for(i = 0; i < 5; i = i + 1){
    pinMode(btnPins[i], INPUT_PULLUP);
  }
  
  for(i = 0; i < 3; i = i + 1){
    pinMode(ledPins[i], OUTPUT);
  }
}

void loop(){
  
  //OR
  ledStates[0] = (!digitalRead(btnPins[0]) | !digitalRead(btnPins[1]) ? HIGH : LOW); //OR
  ledStates[1] = !(!digitalRead(btnPins[2]) | !digitalRead(btnPins[3]) ? HIGH : LOW); //NOR
  ledStates[2] = (!digitalRead(btnPins[4]) ^ !digitalRead(btnPins[5]) ? HIGH : LOW); //XOR
  ledStates[3] = !(!digitalRead(btnPins[6]) ^ !digitalRead(btnPins[7]) ? HIGH : LOW); //XNOR

  //AND
  ledStates[4] = (!digitalRead(btnPins[8]) & !digitalRead(btnPins[9]) ? HIGH : LOW); //AND
  ledStates[5] = !(!digitalRead(btnPins[10]) & !digitalRead(btnPins[11]) ? HIGH : LOW); //NAND

  //NOT
  ledStates[6] = digitalRead(btnPins[12]); //NOT
  
  for(int i = 0; i < 3; i++){
    digitalWrite(ledPins[i], ledStates[i]);
  }

  delay(100);
}
