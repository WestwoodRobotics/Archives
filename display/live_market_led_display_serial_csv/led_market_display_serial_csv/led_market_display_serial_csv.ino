nclude <Wire.h>
#include <Adafruit_NeoPixel.h>
#ifdef __AVR__
  #include <avr/power.h>
#endif

//Define the number of pixels on each strip (strips 1-5), useful if missing pixels
//Set to zero to turn off strip
#define NUMPIXELS1 58
#define NUMPIXELS2 59
#define NUMPIXELS3 60
#define NUMPIXELS4 58
#define NUMPIXELS5 58

#define NUMPIXELS_TOTAL (NUMPIXELS1 + NUMPIXELS2 + NUMPIXELS3 + NUMPIXELS4 + NUMPIXELS5)

//digital pin to connect the LED Strip input pin to
#define PIN 6 
//initialize LED
#define LED_PIN 13

//For storage of individual percentages
int per1 = 0;
int per2 = 0;
int per3 = 0;
int per4 = 0;
int per5 = 0;
//Used for the serial read method
int percent = 0;
int newPercent = 0;
const int MaxChars = 4;
char strValue[MaxChars+1];
int index = 0;
int perCount = 1;

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
  // wait for serial port to connect. Needed for Leonardo only
  }
  Serial.println("Please send percents!");
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

//Automatically runs whenever serial input is detected
void serialEvent()
{
   while(Serial.available()) 
   {
      char ch = Serial.read();
      Serial.write(ch);
      if(index < MaxChars && isDigit(ch))       
      {                    
        strValue[index++] = ch;              
      }
      else
      {                    
        strValue[index] = 0;                    
        newPercent = atoi(strValue);                    
        if(newPercent >= 0 && newPercent <= 200)
        {
          if(perCount == 1)
            per1 = newPercent;
          if(perCount == 2)
            per2 = newPercent;
          if(perCount == 3)
            per3 = newPercent;
          if(perCount == 4)
            per4 = newPercent;
          if(perCount == 5) {
            per5 = newPercent;
            perCount = 0;
          }
          perCount++;
          
         }
         else 
         {
          if(perCount == 5) 
            perCount = 0;
          perCount++;
         }
         
         index = 0;
         percent = newPercent;
      }  
   }
}

void loop() 
{
  //prints out current percents
  Serial.println("Coal: " + String(per1) + "%, " + "Magnetite: " + String(per2) + "%, " + "Bauxite: " + String(per3) + "%, " + "Chalcopyrite: " + String(per4) + "%, " + "Spodumene: " + String(per5) + "%");
  showEachVal(15000);
  showRainbowVal(200, 15);
  showRainbowVal(200, 15);
  showRainbowVal(200, 15);
  showRainbowVal(200, 15);
  showRainbowVal(200, 15);
  colorWipe(strip.Color(203, 85, 0), 10); // Orange
  colorWipe(strip.Color(150, 150, 150), 10); // White
  colorWipe(strip.Color(203, 85, 0), 10); // Orange
  colorWipe(strip.Color(150, 150, 150), 10); // White
}

// Fill the dots one after the other with a color
void colorWipe(uint32_t c, uint8_t wait) {
  for(uint16_t i=0; i<strip.numPixels(); i++) {
    strip.setPixelColor(i, c);
    strip.show();
    delay(wait);
  }
}    

//Calculates and shows the market values
void showRainbowVal(int percent, int wait)
{
    //Find pixel numbers based on percent (for a 60 pixel strip)
    int pixelInt = (30 * percent/100);
    //remove any -1 errors
    if(pixelInt < 0) {
      pixelInt = 0;
    }
       
    rainbow(wait, pixelInt);
}

void showEachVal(int wait)
{
    //Find pixel numbers based on percent (for a 60 pixel strip)
    int pixelInt1 = (int)(30 * per1/100);
    int pixelInt2 = (int)(30 * per2/100);
    int pixelInt3 = (int)(30 * per3/100);
    int pixelInt4 = (int)(30 * per4/100);
    int pixelInt5 = (int)(30 * per5/100);
    //remove any -1 errors
    if(pixelInt1 < 0) {
      pixelInt1 = 0;
    }
    if(pixelInt2 < 0) {
      pixelInt2 = 0;
    }
    if(pixelInt3 < 0) {
      pixelInt3 = 0;
    }
    if(pixelInt4 < 0) {
      pixelInt4 = 0;
    }
    if(pixelInt5 < 0) {
      pixelInt5 = 0;
    }
    //Output current market percent    
    //Clear all pixels (fresh slate), also eliminates blink of changing pixels versus clearing them individually
  for(int k = 0; k < NUMPIXELS_TOTAL; k++) {
     strip.setPixelColor(k, strip.Color(0,0,0)); //Black or off
  }
  for (int temp = 0; temp < pixelInt1; temp++){
    if (temp > NUMPIXELS1)
      temp = NUMPIXELS1;
    strip.setPixelColor(temp, strip.Color(80,0,80));
  }
  
  for (int temp = 0; temp < pixelInt2; temp++){
    if (temp > NUMPIXELS2)
      temp = NUMPIXELS2;
    strip.setPixelColor(temp + NUMPIXELS1, strip.Color(80,80,80));
  }
  
  for (int temp = 0; temp < pixelInt3; temp++){
    if (temp > NUMPIXELS3)
      temp = NUMPIXELS3;
    strip.setPixelColor(temp + NUMPIXELS1 + NUMPIXELS2, strip.Color(0,255,0));       
  }

  for (int temp = 0; temp < pixelInt4; temp++){
    if (temp > NUMPIXELS4)
      temp = NUMPIXELS4;
    strip.setPixelColor(temp + NUMPIXELS1 + NUMPIXELS2 + NUMPIXELS3, strip.Color(255,0,0));
  }

  for (int temp = 0; temp < pixelInt5; temp++){
    if (temp > NUMPIXELS5)
      temp = NUMPIXELS5;
    strip.setPixelColor(temp + NUMPIXELS1 + NUMPIXELS2 + NUMPIXELS3 + NUMPIXELS4, strip.Color(0,0,255));
  }
            
  show();
  delay(wait);
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
         int temp = i;
         if (temp > NUMPIXELS1)
          temp = NUMPIXELS1;
         strip.setPixelColor(temp, Wheel((temp+j) & 255));
         temp = i;

         if (temp > NUMPIXELS2)
          temp = NUMPIXELS2;
         strip.setPixelColor(temp + NUMPIXELS1, Wheel((temp+j) & 255));
         temp = i;
         
         if (temp > NUMPIXELS3)
          temp = NUMPIXELS3;
         strip.setPixelColor(temp + NUMPIXELS1 + NUMPIXELS2, Wheel((temp+j) & 255));
         temp = i;
         
         if (temp > NUMPIXELS4)
          temp = NUMPIXELS4;
         strip.setPixelColor(temp + NUMPIXELS1 + NUMPIXELS2 + NUMPIXELS3, Wheel((temp+j) & 255));
         temp = i;
         
         if (temp > NUMPIXELS5)
          temp = NUMPIXELS5;
         strip.setPixelColor(temp + NUMPIXELS1 + NUMPIXELS2 + NUMPIXELS3 + NUMPIXELS4, Wheel((temp+j) & 255));
         
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

