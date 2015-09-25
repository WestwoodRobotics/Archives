//Created by Qrules for Westwood Robotics under GNU GPL v3
//Based on adafruit libraries for neopixel led modules

#include <Wire.h>
#include <Adafruit_NeoPixel.h>
#ifdef __AVR__
  #include <avr/power.h>
#endif

//Define the number of pixels on each strip (strips 1-5), useful if missing pixels
//Set to zero to turn off strip
#define NUMPIXELS1 58
#define NUMPIXELS2 0
//#define NUMPIXELS2 59
#define NUMPIXELS3 0
//#define NUMPIXELS3 60
#define NUMPIXELS4 0
//#define NUMPIXELS4 60
#define NUMPIXELS5 0
//#define NUMPIXELS5 60

#define NUMPIXELS_TOTAL (NUMPIXELS1 + NUMPIXELS2 + NUMPIXELS3 + NUMPIXELS4 + NUMPIXELS5)

//digital pin to connect the LED Strip input pin to
#define PIN 8 
//initialize LED
#define LED_PIN 13

//For temporary storage
String inString = "";
float tempVal = 0.0;
float val = 0.0;
int count1 = 0;
int count2 = 0;
int activate = 0;

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
  // initialize the serial communication:
  Serial.begin(9600);
  while (!Serial) {
    ; // wait for serial port to connect. Needed for Leonardo only
  }
  Serial.println("Please send a percent between 0 and 200!");
  // initialize the LED_PIN as an output:
  pinMode(LED_PIN, OUTPUT);
  digitalWrite(LED_PIN, HIGH);
  // This is for Trinket 5V 16MHz, you can remove these three lines if you are not using a Trinket
 /* #if defined (__AVR_ATtiny85__)
    if (F_CPU == 16000000) clock_prescale_set(clock_div_1);
  #endif
  */
  // End of trinket special code

  //begin strip
  strip.begin();
  show();
  delay(1500);
  digitalWrite(LED_PIN, LOW);
  
}

void loop() {
  
  while (Serial.available() > 0) {
    int inChar = Serial.read();

    if (inChar != '\n') { 

      // As long as the incoming byte
      // is not a newline,
      // convert the incoming byte to a char
      // and add it to the string
      inString += (char)inChar;
      //Activate thing to parse the string
      activate = 1;
    }
  }
  
  if(activate){
    activate = 0;
    count1 = 0;
    //Send to float
    val = inString.toFloat();
    //Clear string
    inString = "";
    if(val != tempVal){
      tempVal = val;
      //Turn on led
      digitalWrite(LED_PIN, HIGH);
      Serial.println();
      Serial.println("Got value: " + String(val) + "%");
      Serial.println();
      count2 = 2;
    }
    
   }
    
    //make sure nobody sends fake values
    if(val < 0){
      val = 0.0;
      tempVal = 0.0;
    } 
    if(val > 200){
      val = 0.0;
      tempVal = 0.0;
    }
   
    //Turn off recieve led
    if(count1 > 2)
      digitalWrite(LED_PIN, LOW);

    //calculate percent
    int percent = (int)val;
    //Find pixel numbers based on percent (for a 60 pixel strip)
    int pixelInt = (30 * percent/100);
    //remove any -1 errors
    if(pixelInt < 0) {
      pixelInt = 0;
    }

    //Output current market percent on serial
    if(count2 > 2) {
    Serial.println("Market value percent: " + String(percent) + "%");
    Serial.println("Displaying " + String(pixelInt) + " pixels, send a value between 0 and 200 to change it!");
    count2 = 0; 
    }
    //display the partial percents on the neopixels in a pretty rainbow, passes in a delay in ms and the pixelInt
    
    rainbow(5, pixelInt);

    count1++;
    count2++;
}

//class to organize rainbow display on pixel part defined by percent integer above
void rainbow(uint8_t wait, int setpix) {
  uint16_t i, j;

  //Clear all pixels (fresh slate), also eliminates blink of changing pixels versus clearing them individually
  for(int k = 0; k < NUMPIXELS_TOTAL + 5; k++) {
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

