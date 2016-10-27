#define pinF 3  // The pin that will move the slides forward
#define pinB 2  // The pin that will move the slides backward

// The pins for the animal sounds
#define pinS1 4
#define pinS2 5
#define pinS3 6
#define pinS4 7
#define pinS5 8
#define pinS6 9

// Returns a value equal to pinF or pinB if either is pressed. Returns -1 otherwise.
int getInput(){
  if(digitalRead(pinF) == LOW) return pinF;
  if(digitalRead(pinB) == LOW) return pinB;
  if(digitalRead(pinS1) == LOW) return pinS1;
  if(digitalRead(pinS2) == LOW) return pinS2;
  if(digitalRead(pinS3) == LOW) return pinS3;
  if(digitalRead(pinS4) == LOW) return pinS4;
  if(digitalRead(pinS5) == LOW) return pinS5;
  if(digitalRead(pinS6) == LOW) return pinS6;
  return -1;
}

void waitForRelease(int ms){
  while(getInput() != -1);
  delay(ms);
}

void waitForRelease(){
  waitForRelease(400);
}

void setup(){
  pinMode(pinF, INPUT);
  pinMode(pinB, INPUT);
  
  Serial.begin(9600);
  while(!Serial);
  Serial.println(":");
}

void loop(){
  switch(getInput()){
    case pinF:
      Serial.print("F");
      waitForRelease();
      break;
    case pinB:
      Serial.print("B");
      waitForRelease();
      break;
    case pinS1:
      Serial.print("1");
      waitForRelease(2000);
      break;
    case pinS2:
      Serial.print("2");
      waitForRelease(2000);
      break;
    case pinS3:
      Serial.print("3");
      waitForRelease(2000);
      break;
    case pinS4:
      Serial.print("4");
      waitForRelease(2000);
      break;
    case pinS5:
      Serial.print("5");
      waitForRelease(2000);
      break;
    case pinS6:
      Serial.print("6");
      waitForRelease(2000);
      break;
    default:
      break;
  }
}
