#used lybraries
import RPi.GPIO as GPIO
import time
from w1thermsensor import W1ThermSensor

sensor=W1ThermSensor()

#Variables
moisture_sensor = 2 #GPIO 2
water_pump = 3 #GPIO 3
light=14 #GPIO 14
light_sensor=17 #GPIO 17
delay_light=0.1 #delay used for light_sensor logic
mode=0 #manual = 1, auto = 0

#GPIO setup
GPIO.setmode(GPIO.BCM)

GPIO.setup(moisture_sensor, GPIO.IN)

GPIO.setup(water_pump,GPIO.OUT)
GPIO.output(water_pump,0)

GPIO.setup(light,GPIO.OUT)
GPIO.output(light,0) # sting LED

GPIO.setup(light_sensor,GPIO.OUT)
GPIO.output(light_sensor, 0)

#moisture sensor function
def callback(moisture_sensor):
        mode = 0
        if GPIO.input(moisture_sensor):
                with open("GardenData.txt") as file:
                        data = file.readlines()
			data[0]=data[0][:len(data[0])-1]
                        if(data[0]=="mode=0"):
				print("DA")
                                mode=0
                        else:
				print("NU")

                                mode=1

                print("no water detected")
                with open("/var/www/html/info/soil.txt","w") as file:
                        file.write("No water detected")
                if(mode==0):
                        GPIO.output(water_pump,1)
        else:
                print("water detected")
                with open("/var/www/html/info/soil.txt","w") as file:
                        file.write("Water detected")
                if(mode==0):
                        GPIO.output(water_pump,0)

#moisture sensor event
GPIO.add_event_detect(moisture_sensor,GPIO.BOTH,bouncetime=300)
GPIO.add_event_callback(moisture_sensor,callback)

#light logic
while True:
#       print('Temperatura este %2.2f '%(t)+"grade Celsius")

	mode=0
	with open("GardenData.txt") as file:
        	data = file.readlines()
                data[0]=data[0][:len(data[0])-1]
                if(data[0]=="mode=0"):
                	print("DA")
                        mode=0
                else:
                        print("NU")
                        mode=1

        GPIO.setup(light_sensor,GPIO.OUT)
        GPIO.output(light_sensor, 0)
        time.sleep(0.1)

        GPIO.setup(light_sensor, GPIO.IN)
        count=0
        while(GPIO.input(light_sensor)==0):
                count+=1

        t=sensor.get_temperature()
        print(t)
	if mode==0:
        	if count > 80000 or t<25:
                	GPIO.output(light,1)
                	with open("GardenData.txt","r+w") as file:
                        	data = file.readlines()
                        	data[0]=data[0][:len(data[0])-1]
                        	print(data[0])
                        	if data[0]=='mode=0':
                                	text="mode=0"+'\n'+str(t)+'\n'+"on"
					file.seek(0)
                                	file.write(text)
					file.truncate()
                        	else:
                                	text="mode=1"+'\n'+str(t)+'\n'+"on"
					file.seek(0)
                                	file.write(text)
					file.truncate()
        	else:
                	GPIO.output(light,0)
                	with open("GardenData.txt","r+w") as file:
                	        data = file.readlines()
                	        data[0]=data[0][:len(data[0])-1]
                	        print(data[0])
                	        if data[0]=='mode=0':
                	                text="mode=0"+'\n'+str(t)+'\n'+"off"
                	                file.seek(0)
					file.write(text)
					file.truncate()
                	        else:
                	                text="mode=1"+'\n'+str(t)+'\n'+"off"
                	                file.seek(0)
					file.write(text)
					file.truncate()
