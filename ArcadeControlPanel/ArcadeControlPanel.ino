const int numButtons = 12;
const int analogButtons = 2;
//byte allButtons[numButtons];
//byte prevButtons[numButtons];

void setup() {
    Joystick.useManualSend(true);
    for (int i=0; i<numButtons; i++) {
        pinMode(i, INPUT_PULLUP);
    }   
    pinMode(12, INPUT_PULLUP);
    pinMode(14, INPUT_PULLUP);
}

void loop(){
    Joystick.X((!digitalRead(12) - !digitalRead(14)));
    Joystick.Y((!digitalRead(14) - !digitalRead(12)));

    for (int i=0; i<numButtons; i++) {
        Joystick.button(i + 1, !digitalRead(i));
    }
    Joystick.button(13, !digitalRead(12));
    Joystick.button(14, !digitalRead(14));

    Joystick.send_now();
    
    delay(5);
}