import RPi.GPIO as GPIO

water_pump = 5 #GPIO 3

GPIO.setmode(GPIO.BOARD)
GPIO.setup(water_pump,GPIO.OUT)

GPIO.output(water_pump,0)

