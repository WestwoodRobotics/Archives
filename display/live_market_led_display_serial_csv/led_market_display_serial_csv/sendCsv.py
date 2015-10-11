import serial

#Where the arduino is mounted
ser = serial.Serial('/dev/ttyACM0', 9600)

#Put your 5 values in ; format: Coal;Magnetite;Bauxite;Chalcopyrite;Spodumene 

ser.write('100.0;100.0;100.0;100.0;100.0')
