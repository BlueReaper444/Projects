
import RPi.GPIO as GPIO

light=8 #GPIO 14

GPIO.setmode(GPIO.BOARD)
GPIO.setup(light,GPIO.OUT)

GPIO.output(light,0)

