//Created by Qrules for Westwood Robotics under GNU GPL v3
//Based on adafruit libraries for neopixel led modules

#include <Wire.h>
#include <Adafruit_NeoPixel.h>
#ifdef __AVR__
  #include <avr/power.h>
#endif
#include <LiquidCrystal.h>

//Define the number of pixels on each strip (strips 1-5), useful if missing pixels
//Set to zero to turn off strip
#define NUMPIXELS1 60
#define NUMPIXELS2 0
//#define NUMPIXELS2 60
#define NUMPIXELS3 0
//#define NUMPIXELS3 60
#define NUMPIXELS4 0
//#define NUMPIXELS4 60
#define NUMPIXELS5 0
//#define NUMPIXELS5 60

#define NUMPIXELS_TOTAL (NUMPIXELS1 + NUMPIXELS2 + NUMPIXELS3 + NUMPIXELS4 + NUMPIXELS5)

//digital pin to connect the LED Strip input pin to
#define PIN 8 
//initialize LCD on digital pins 2,3,4,5,6,7 leaving TX and RX open (d0 && d1) for use in other projects
LiquidCrystal lcd(7,6,5,4,3,2);
//Analog pin to connect control potentiometer to
#define POTENTIOMETER 3


// Parameter 1 = number of pixels in strip
// Parameter 2 = Arduino pin number (most are valid)
// Parameter 3 = pixel type flags, add together as needed:
//   NEO_KHZ800  800 KHz bitstream (most NeoPixel products w/WS2812 LEDs)
//   NEO_KHZ400  400 KHz (classic 'v1' (not v2) FLORA pixels, WS2811 drivers)
//   NEO_GRB     Pixels are wired for GRB bitstream (most NeoPixel products)
//   NEO_RGB     Pixels are wired for RGB bitstream (v1 FLORA pixels, not v2)

//Start strip, length of all 5 strips combined on the PIN defined above
Adafruit_NeoPixel strip = Adafruit_NeoPixel(NUMPIXELS_TOTAL, PIN, NEO_GRB + NEO_KHZ800);

// IMPORTANT: To reduce NeoPixel burnout risk, add 1000 uF capacitor across
// pixel power leads, add 300 - 500 Ohm resistor on first pixel's data input
// and minimize distance between Arduino and first pixel.  Avoid connecting
// on a live circuit...if you must, connect GND first.

void setup() {
  //Start lcd display
  lcd.begin(16, 2);
  
  // This is for Trinket 5V 16MHz, you can remove these three lines if you are not using a Trinket
 /* #if defined (__AVR_ATtiny85__)
    if (F_CPU == 16000000) clock_prescale_set(clock_div_1);
  #endif
  */
  // End of trinket special code

  //begin strip
  strip.begin();
  show();
  
}

void loop() {
  
    //raw analog value 0 - 1020 ish from potentiometer
    float val = analogRead(POTENTIOMETER);
    //calculate percent from potentiometer reading
    float percent = (int)(200 * (val)/1020);
    //Find pixel numbers based on percent (for a 60 pixel strip)
    int pixelInt = (int)(30 * (percent/100));
    //remove any -1 errors
    if (pixelInt < 0) {
      pixelInt = 0;
    }

    //show current market percent on lcd
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("Market value percent: ");
    lcd.setCursor(0, 1);
    lcd.print("Current: ");
    lcd.print ((int)percent);
    lcd.print ("%");

    //display the partial percents on the neopixels in a pretty rainbow, passes in a delay in ms and the pixelInt
    rainbow(15, pixelInt);


}

//class to organize rainbow display on pixel part defined by percent integer above
void rainbow(uint8_t wait, int setpix) {
  uint16_t i, j;

  //Clear all pixels (fresh slate), also eliminates blink of changing pixels versus clearing them individually
  for(int k = 0; k < NUMPIXELS_TOTAL; k++) {
     strip.setPixelColor(k, strip.Color(0,0,0)); //Black or off
  }
  
  for(j=0; j<256; j++) {
      for(i=0; i<setpix; i++) {
         strip.setPixelColor(i, Wheel((i+j) & 255));
         
        if(NUMPIXELS2 != 0)
         strip.setPixelColor(i + NUMPIXELS1, Wheel((i+j) & 255));

        if(NUMPIXELS3 != 0)
         strip.setPixelColor(i + NUMPIXELS1 + NUMPIXELS2, Wheel((i+j) & 255));

        if(NUMPIXELS4 != 0)
         strip.setPixelColor(i + NUMPIXELS1 + NUMPIXELS2 + NUMPIXELS3, Wheel((i+j) & 255));

        if(NUMPIXELS5 != 0 )
         strip.setPixelColor(i + NUMPIXELS1 + NUMPIXELS2 + NUMPIXELS3 + NUMPIXELS4, Wheel((i+j) & 255));
      }
            
  show();
  delay(wait);
  } 
}

// Input a value 0 to 255 to get a color value.
// The colours are a transition r - g - b - back to r.
uint32_t Wheel(byte WheelPos) {
  WheelPos = 255 - WheelPos;
  if(WheelPos < 85) {
    return strip.Color(255 - WheelPos * 3, 0, WheelPos * 3);
  }
  if(WheelPos < 170) {
    WheelPos -= 85;
    return strip.Color(0, WheelPos * 3, 255 - WheelPos * 3);
  }
  WheelPos -= 170;
  return strip.Color(WheelPos * 3, 255 - WheelPos * 3, 0);
}

//Shows pixels
void show() {
  strip.show(); // Initialize all pixels
}

