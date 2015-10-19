/*
  By Qrules under GNU GPL v3 for Westwood Robotics 2015 BEST Season, Pay Dirt
  Used to run a pump that supplies water to a sluice box, controlled by a PIR motion detector to turn on water.  
*/

#define pirPin 2 //Input pin of pir sensor. Connect yellow to this pin and rest to vcc & gnd
#define ledPin 13 //Change the number to change the pin set for a led indicator
#define relayPin 3 //Change to change pin for relay, attach stuff to common and normally open
int count = 0;

// the setup function runs once when you press reset or power the board
void setup() {
  // initialize led and relay pin as an output.
  pinMode(ledPin, OUTPUT);
  pinMode(relayPin, OUTPUT);
  pinMode(pirPin, INPUT);
}

// the loop function runs over and over again forever
void loop() {
  if(digitalRead(pirPin) == HIGH && count < 150)
   {
     digitalWrite(relayPin, LOW);   // turn the Relay on (Low is the voltage level to turn on normally open)
     digitalWrite(ledPin, HIGH);   // turn the LED on (HIGH is the voltage level)   
     delay(50);
     count++;  
   }
  else 
  {
   digitalWrite(relayPin, HIGH);    // turn the Relay off by making the voltage High
   digitalWrite(ledPin, LOW);   // turn the LED on (HIGH is the v oltage level)
   count = 0;
   delay(2000);
  }
 }
