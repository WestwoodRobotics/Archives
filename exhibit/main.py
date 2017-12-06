import speech_recognition as sr
import pyaudio
import wave

### Sound files

afiles = [r"wav/Question1.wav",
          r"wav/Question2.wav",
          r"wav/Question3.wav",
          r"wav/Question4.wav",
          r"wav/Question5.wav",
          r"wav/Question6.wav",
          r"wav/Question7.wav"]

### Audio

chunk = 1024

def playAudio(sFile):
    f = wave.open(sFile,"rb")
    p = pyaudio.PyAudio()
    stream = p.open(format = p.get_format_from_width(f.getsampwidth()),  
                    channels = f.getnchannels(),  
                    rate = f.getframerate(),  
                    output = True)
    
    data = f.readframes(chunk)

    while data:
        stream.write(data)
        data = f.readframes(chunk)

    stream.stop_stream()
    stream.close()

    p.terminate()

### Speech recognition

r = sr.Recognizer()
m = sr.Microphone()

triggers = [["one"],
            ["two", "too", "to"],
            ["three", "tree"],
            ["four", "for"],
            ["five"],
            ["six", "sects"],
            ["seven"]]

def getPrompt():
    with m as source: audio = r.listen(source)
    phrase = r.recognize_sphinx(audio)
    words = phrase.split()

    print words
    
    for ts in triggers:
        for t in ts:
            if t in words:
                return triggers.index(ts)

    return getPrompt()
    
#   if("one" in words):
#       return 0
#   elif("two" in words):
#       return 1
#   elif("three" in words):
#       return 2
#   elif("four" in words):
#       return 3
#   elif("five" in words):
#       return 4
#   elif("six" in words):
#       return 5
#   elif("seven" in words):
#       return 6
#   else:
#       return getPrompt()

### Play the audio

def say(s=0):
    playAudio(afiles[s])

### Initialize the microphone

def init():
    with m as source:
        r.adjust_for_ambient_noise(source, duration=5)

### Do everything

def main():
    init()
    print "ok start"
    while True:
        say(getPrompt())

if __name__ == "__main__":
    try:
        main()
    except KeyboardInterrupt:
        pass
