from datetime import date

def data():
	today = date.today()
	d1 = today.strftime("%d/%m/%Y")
	file = open("info/date.txt","w")
	file.write(d1)
	file.close()

if __name__ == "__main__":
	data();
