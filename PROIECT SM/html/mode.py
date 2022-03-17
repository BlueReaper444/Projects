import RPi.GPIO as GPIO
import time
from w1thermsensor import W1ThermSensor
sensor=W1ThermSensor()
t=sensor.get_temperature()

water_pump = 3 #GPIO 3
light=14 #GPIO 14

with open("/home/pi/Proiect/GardenData.txt","r+w") as file:
	data = file.readlines()
        data[0]=data[0][:len(data[0])-1]
        print(data[0])
        if data[0]=='mode=0':
        	text="mode=1"+'\n'+str(t)+'\n'+"off"
		file.seek(0)
                file.write(text)
		file.truncate()
	else:
               	text="mode=0"+'\n'+str(t)+'\n'+"off"
		file.seek(0)
		file.write(text)
		file.truncate()
	GPIO.setmode(GPIO.BCM)
	GPIO.setup(light,GPIO.OUT)
	GPIO.output(light,0) # sting LED
	GPIO.setup(water_pump,GPIO.OUT)
	GPIO.output(water_pump,0) # opresc apa

