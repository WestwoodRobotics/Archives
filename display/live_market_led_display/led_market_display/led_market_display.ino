//Created by Qrules for Westwood Robotics under GNU GPL v3
//Based on adafruit libraries for neoixel led modules

#include <Wire.h>
#include <Adafruit_NeoPixel.h>
#ifdef __AVR__
  #include <avr/power.h>
#endif
#include <LiquidCrystal.h>
#define NUMSTRIPS 2
#define NUMPERSTRIP 60
#define NUMPIXELS (NUMPERSTRIP * NUMSTRIPS)
#define PIN1 8
#define PIN2 9
#define PIN3 10
#define PIN4 11
#define PIN5 12
#define PIN6 13
LiquidCrystal lcd(7,6,5,4,3,2);
#define POTENTIOMETER 3



// Connect D in of neopixel to digital 6

// EOC is not used, it signifies an end of conversion
// XCLR is a reset pin, also not used here
// Connect LCD to digital pins 12,11,5,4,3,2 (follow image in images folder)
// Connect relay to 5v, pin digital 13, and ground
// Connect refrigeration unit positive line to normally open on the relay and its power to common
// Connect potentiometer to 5v, gnd, and analog pin 3
// Parameter 1 = number of pixels in strip
// Parameter 2 = Arduino pin number (most are valid)
// Parameter 3 = pixel type flags, add together as needed:
//   NEO_KHZ800  800 KHz bitstream (most NeoPixel products w/WS2812 LEDs)
//   NEO_KHZ400  400 KHz (classic 'v1' (not v2) FLORA pixels, WS2811 drivers)
//   NEO_GRB     Pixels are wired for GRB bitstream (most NeoPixel products)
//   NEO_RGB     Pixels are wired for RGB bitstream (v1 FLORA pixels, not v2)
Adafruit_NeoPixel strip1 = Adafruit_NeoPixel(NUMPIXELS, PIN1, NEO_GRB + NEO_KHZ800);
Adafruit_NeoPixel strip2 = Adafruit_NeoPixel(NUMPIXELS, PIN2, NEO_GRB + NEO_KHZ800);
Adafruit_NeoPixel strip3 = Adafruit_NeoPixel(NUMPIXELS, PIN3, NEO_GRB + NEO_KHZ800);
Adafruit_NeoPixel strip4 = Adafruit_NeoPixel(NUMPIXELS, PIN4, NEO_GRB + NEO_KHZ800);
Adafruit_NeoPixel strip5 = Adafruit_NeoPixel(NUMPIXELS, PIN5, NEO_GRB + NEO_KHZ800);

// IMPORTANT: To reduce NeoPixel burnout risk, add 1000 uF capacitor across
// pixel power leads, add 300 - 500 Ohm resistor on first pixel's data input
// and minimize distance between Arduino and first pixel.  Avoid connecting
// on a live circuit...if you must, connect GND first.

void setup() {

  lcd.begin(16, 2);
  // This is for Trinket 5V 16MHz, you can remove these three lines if you are not using a Trinket
 /* #if defined (__AVR_ATtiny85__)
    if (F_CPU == 16000000) clock_prescale_set(clock_div_1);
  #endif
  */
  // End of trinket special code

  strip1.begin();
  strip2.begin();
  strip3.begin();
  strip4.begin();
  strip5.begin();
  show();
  
}

void loop() {
    float val = analogRead(POTENTIOMETER);
    float percent = (int)(200 * (val)/1020);
    float pixels = (30 * (percent/100));
    int pixelInt = (int)pixels;
    if (pixelInt < 0) {
      pixelInt = 0;
    }
    
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("Market value percent: ");
    lcd.setCursor(0, 1);
    lcd.print("Current: ");
    lcd.print ((int)percent);
    lcd.print ("%");

rainbow(100, pixelInt);


}

void rainbow(uint8_t wait, int setpix) {
  uint16_t i, j;
  for(int k = 0; k < NUMPIXELS; k++) {
     strip1.setPixelColor(k, strip1.Color(0,0,0)); //Black or off
     strip2.setPixelColor(k, strip2.Color(0,0,0)); //Black or off
     strip3.setPixelColor(k, strip3.Color(0,0,0)); //Black or off
     strip4.setPixelColor(k, strip4.Color(0,0,0)); //Black or off
     strip5.setPixelColor(k, strip5.Color(0,0,0)); //Black or off
  }
  
  for(j=0; j<256; j++) {
      for(i=0; i<setpix; i++) {
         strip1.setPixelColor(i, Wheel1((i+j) & 255));
         strip2.setPixelColor(i, Wheel2((i+j) & 255));
         strip3.setPixelColor(i, Wheel3((i+j) & 255));
         strip4.setPixelColor(i, Wheel4((i+j) & 255));
         strip5.setPixelColor(i, Wheel5((i+j) & 255));
      }
            
  show();
  delay(wait);
  } 
}

// Input a value 0 to 255 to get a color value.
// The colours are a transition r - g - b - back to r.
uint32_t Wheel1(byte WheelPos) {
  WheelPos = 255 - WheelPos;
  if(WheelPos < 85) {
    return strip1.Color(255 - WheelPos * 3, 0, WheelPos * 3);
  }
  if(WheelPos < 170) {
    WheelPos -= 85;
    return strip1.Color(0, WheelPos * 3, 255 - WheelPos * 3);
  }
  WheelPos -= 170;
  return strip1.Color(WheelPos * 3, 255 - WheelPos * 3, 0);
}
uint32_t Wheel2(byte WheelPos) {
  WheelPos = 255 - WheelPos;
  if(WheelPos < 85) {
    return strip2.Color(255 - WheelPos * 3, 0, WheelPos * 3);
  }
  if(WheelPos < 170) {
    WheelPos -= 85;
    return strip2.Color(0, WheelPos * 3, 255 - WheelPos * 3);
  }
  WheelPos -= 170;
  return strip2.Color(WheelPos * 3, 255 - WheelPos * 3, 0);
}
uint32_t Wheel3(byte WheelPos) {
  WheelPos = 255 - WheelPos;
  if(WheelPos < 85) {
    return strip3.Color(255 - WheelPos * 3, 0, WheelPos * 3);
  }
  if(WheelPos < 170) {
    WheelPos -= 85;
    return strip3.Color(0, WheelPos * 3, 255 - WheelPos * 3);
  }
  WheelPos -= 170;
  return strip3.Color(WheelPos * 3, 255 - WheelPos * 3, 0);
}
uint32_t Wheel4(byte WheelPos) {
  WheelPos = 255 - WheelPos;
  if(WheelPos < 85) {
    return strip4.Color(255 - WheelPos * 3, 0, WheelPos * 3);
  }
  if(WheelPos < 170) {
    WheelPos -= 85;
    return strip4.Color(0, WheelPos * 3, 255 - WheelPos * 3);
  }
  WheelPos -= 170;
  return strip4.Color(WheelPos * 3, 255 - WheelPos * 3, 0);
}
uint32_t Wheel5(byte WheelPos) {
  WheelPos = 255 - WheelPos;
  if(WheelPos < 85) {
    return strip5.Color(255 - WheelPos * 3, 0, WheelPos * 3);
  }
  if(WheelPos < 170) {
    WheelPos -= 85;
    return strip5.Color(0, WheelPos * 3, 255 - WheelPos * 3);
  }
  WheelPos -= 170;
  return strip5.Color(WheelPos * 3, 255 - WheelPos * 3, 0);
}
void show() {
  strip1.show(); // Initialize all pixels to 'off'
  strip2.show(); // Initialize all pixels to 'off'
  strip3.show(); // Initialize all pixels to 'off'
  strip4.show(); // Initialize all pixels to 'off'
  strip5.show(); // Initialize all pixels to 'off'
}

