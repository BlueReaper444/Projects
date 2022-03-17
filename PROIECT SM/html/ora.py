from datetime import datetime

def ora ():
	now = datetime.now()
	current_time = now.strftime("%H:%M:%S")
	file = open("info/ora.txt","w")
	file.write(current_time)
	file.close()

if __name__ == "__main__":
	ora();
