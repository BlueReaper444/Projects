import picamera
camera=picamera.PiCamera()

camera.resolution =(320,240)  # 1024,768
camera.capture("/var/www/html/imag/imag1.jpg")

