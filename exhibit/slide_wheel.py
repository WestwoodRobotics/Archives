# The port that the arduino is plugged into (probably needs to change each time)
arduino_port = "/dev/ttyS0"

# Function to simulate a keypress on windows machines
import win32api
def dokey(key1,key2=None):
	win32api.keybd_event(key1,0,0,0)
	if key2!=None:
		win32api.keybd_event(key2,0,0,0)
		win32api.keybd_event(key2,0,2,0)
	win32api.keybd_event(key1,0,2,0)
# Key for matching keys to their values
shift=16; ctrl=17; left=37; right=39; space=32

# Set up the serial connection to the arduino that should be plugged into the computer
import serial
ser = serial.Serial(arduino_port, 9600)
print "connecting to the Arduino"
serin = ser.read()
print "connected to the Arduino"

# Use the list of sounds to initialize the sounds to be played
sounds = ["sounds/Chicken.mp3", "sounds/Cow.mp3", "sounds/Goat.mp3", "sounds/Horse.mp3", 
			"sounds/Pig.mp3", "sounds/Rooster.mp3"]
import pygame
pygame.mixer.init()

# Main loop of the program
while True:
	serin = serial.read()
	if serin == "F":
		print "Pressing the right key"
		dokey(right)
	else if serin == "B":
		print "Pressing the left key"
		dokey(left)
	else if serin == "1":
		pygame.mixer.music.load(sounds[0])
		pygame.mixer.music.play()
	else if serin == "2":
		pygame.mixer.music.load(sounds[1])
		pygame.mixer.music.play()
	else if serin == "3":
		pygame.mixer.music.load(sounds[2])
		pygame.mixer.music.play()
	else if serin == "4":
		pygame.mixer.music.load(sounds[3])
		pygame.mixer.music.play()
	else if serin == "5":
		pygame.mixer.music.load(sounds[4])
		pygame.mixer.music.play()
	else if serin == "6":
		pygame.mixer.music.load(sounds[5])
		pygame.mixer.music.play()
	else
		print "Unknown input"

ser.close()