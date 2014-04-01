const int numButtons = 12;
const int analogButtons = 6;
//byte allButtons[numButtons];
//byte prevButtons[numButtons];

void setup() {
    Joystick.useManualSend(true);
    for (int i=0; i<numButtons; i++) {
        pinMode(i, INPUT_PULLUP);
    }   
    for (int i=0; i<analogButtons; i++) {
        pinMode(i + 14, INPUT_PULLUP);
    }
}

void loop(){
    Joystick.X((!digitalRead(14)) - (!digitalRead(15)));
    Joystick.Y((!digitalRead(16)) - (!digitalRead(17)));
    Joystick.Z((!digitalRead(18)) - (!digitalRead(19)));
    Joystick.Zrotate(0);
    Joystick.sliderLeft(0);
    Joystick.sliderRight(0);

    for (int i=0; i<numButtons; i++) {
        Joystick.button(i + 1, !digitalRead(i));
    }

    Joystick.send_now();
    
    delay(5);
}