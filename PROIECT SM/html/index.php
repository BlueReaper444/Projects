<!DOCTYPE html> <html> <head> <style> .button{ height:40px; width:70%;
}
</style> <?php 
		if (isset($_POST['script'])) 
		{
			exec('sudo python /var/www/html/ora.py'); 
			exec('sudo python /var/www/html/date.py'); 
			exec('sudo python /var/www/html/cam.py');
			$myfile = fopen("info/date.txt", "r") or die("Unable to open date file!");
			$data=fread($myfile,filesize("info/date.txt")); 
			fclose($myfile); 
			$myfile = fopen("info/ora.txt", "r") or die("Unable to open hour file!"); 
			$ora=fread($myfile,filesize("info/ora.txt")); 
			fclose($myfile); 
			$myfile = fopen("/home/pi/Proiect/GardenData.txt", "r") or die("Unable to open data file!"); 
			$dates=fread($myfile,filesize("/home/pi/Proiect/GardenData.txt")); 
			$line=explode("\n",$dates);
			$mode=$line[0];
			$temp=$line[1];
			$light=$line[2];
			fclose($myfile); 
			$myfile = fopen("info/soil.txt", "r") or die("Unable to open soil data file!"); 
			$soil=fread($myfile,filesize("info/soil.txt")); 
			fclose($myfile);
}

if (isset($_POST['lighton']))   // Light on
{
	exec('sudo python /var/www/html/lighton.py');
	                        exec('sudo python /var/www/html/ora.py');
                        exec('sudo python /var/www/html/date.py');
                        exec('sudo python /var/www/html/cam.py');
                        $myfile = fopen("info/date.txt", "r") or die("Unable to open date file!");
                        $data=fread($myfile,filesize("info/date.txt"));
                        fclose($myfile);
                        $myfile = fopen("info/ora.txt", "r") or die("Unable to open hour file!");
                        $ora=fread($myfile,filesize("info/ora.txt"));
                        fclose($myfile);
                        $myfile = fopen("/home/pi/Proiect/GardenData.txt", "r") or die("Unable to open data file!");
                        $dates=fread($myfile,filesize("/home/pi/Proiect/GardenData.txt"));
                        $line=explode("\n",$dates);
			$mode=$line[0];
                        $temp=$line[1];
                        $light=$line[2];
                        fclose($myfile);
                        $myfile = fopen("info/soil.txt", "r") or die("Unable to open soil data file!");
                        $soil=fread($myfile,filesize("info/soil.txt"));
                        fclose($myfile);

}

if (isset($_POST['lightoff']))   // Light off
{
        exec('sudo python /var/www/html/lightoff.py');
                        exec('sudo python /var/www/html/ora.py');
                        exec('sudo python /var/www/html/date.py');
                        exec('sudo python /var/www/html/cam.py');
                        $myfile = fopen("info/date.txt", "r") or die("Unable to open date file!");
                        $data=fread($myfile,filesize("info/date.txt"));
                        fclose($myfile);
                        $myfile = fopen("info/ora.txt", "r") or die("Unable to open hour file!");
                        $ora=fread($myfile,filesize("info/ora.txt"));
                        fclose($myfile);
                        $myfile = fopen("/home/pi/Proiect/GardenData.txt", "r") or die("Unable to open data file!");
                        $dates=fread($myfile,filesize("/home/pi/Proiect/GardenData.txt"));
                        $line=explode("\n",$dates);
			$mode=$line[0];
                        $temp=$line[1];
                        $light=$line[2];
                        fclose($myfile);
                        $myfile = fopen("info/soil.txt", "r") or die("Unable to open soil data file!");
                        $soil=fread($myfile,filesize("info/soil.txt"));
                        fclose($myfile);

}

if (isset($_POST['wateron']))   // Water on
{
        exec('sudo python /var/www/html/wateron.py');

 exec('sudo python /var/www/html/ora.py');
                        exec('sudo python /var/www/html/date.py');
                        exec('sudo python /var/www/html/cam.py');
                        $myfile = fopen("info/date.txt", "r") or die("Unable to open date file!");
                        $data=fread($myfile,filesize("info/date.txt"));
                        fclose($myfile);
                        $myfile = fopen("info/ora.txt", "r") or die("Unable to open hour file!");
                        $ora=fread($myfile,filesize("info/ora.txt"));
                        fclose($myfile);
                        $myfile = fopen("/home/pi/Proiect/GardenData.txt", "r") or die("Unable to open data file!");
                        $dates=fread($myfile,filesize("/home/pi/Proiect/GardenData.txt"));
                        $line=explode("\n",$dates);
                        $mode=$line[0];
			$temp=$line[1];
                        $light=$line[2];
                        fclose($myfile);
                        $myfile = fopen("info/soil.txt", "r") or die("Unable to open soil data file!");
                        $soil=fread($myfile,filesize("info/soil.txt"));
                        fclose($myfile);

}

if (isset($_POST['wateroff']))   // Water off
{
        exec('sudo python /var/www/html/wateroff.py');
 exec('sudo python /var/www/html/ora.py');
                        exec('sudo python /var/www/html/date.py');
                        exec('sudo python /var/www/html/cam.py');
                        $myfile = fopen("info/date.txt", "r") or die("Unable to open date file!");
                        $data=fread($myfile,filesize("info/date.txt"));
                        fclose($myfile);
                        $myfile = fopen("info/ora.txt", "r") or die("Unable to open hour file!");
                        $ora=fread($myfile,filesize("info/ora.txt"));
                        fclose($myfile);
                        $myfile = fopen("/home/pi/Proiect/GardenData.txt", "r") or die("Unable to open data file!");
                        $dates=fread($myfile,filesize("/home/pi/Proiect/GardenData.txt"));
                        $line=explode("\n",$dates);
			$mode=$line[0];
                        $temp=$line[1];
                        $light=$line[2];
                        fclose($myfile);
                        $myfile = fopen("info/soil.txt", "r") or die("Unable to open soil data file!");
                        $soil=fread($myfile,filesize("info/soil.txt"));
                        fclose($myfile);

}

if (isset($_POST['mode'])) //Change mode
{
	exec('sudo python /var/www/html/mode.py');
 exec('sudo python /var/www/html/ora.py');
                        exec('sudo python /var/www/html/date.py');
                        exec('sudo python /var/www/html/cam.py');
                        $myfile = fopen("info/date.txt", "r") or die("Unable to open date file!");
                        $data=fread($myfile,filesize("info/date.txt"));
                        fclose($myfile);
                        $myfile = fopen("info/ora.txt", "r") or die("Unable to open hour file!");
                        $ora=fread($myfile,filesize("info/ora.txt"));
                        fclose($myfile);
                        $myfile = fopen("/home/pi/Proiect/GardenData.txt", "r") or die("Unable to open data file!");
                        $dates=fread($myfile,filesize("/home/pi/Proiect/GardenData.txt"));
                        $line=explode("\n",$dates);
			$mode=$line[0];
                        $temp=$line[1];
                        $light=$line[2];
                        fclose($myfile);
                        $myfile = fopen("info/soil.txt", "r") or die("Unable to open soil data file!");
                        $soil=fread($myfile,filesize("info/soil.txt"));
                        fclose($myfile);

}

?> 
		<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
		<title>SM Project </title> 
	</head> 
	<body bgcolor="#650000"> <center> 
	<br><br><br>
		<table witdh="600" border="4" bgcolor="#650000" bordercolor="white"> <tr> <td> 
		<font face="verdana" color="white"><center>"Gh.Asachi" Technical University of Iasi - Romania</center> </font>
				<center><font face="impact" color='white' font size="6">SM Project</font>
				<br> 
				<font color="white" face ="arial" size="3">Created by: Balauta / Chiper / Tizu</font><br>
					<div class="info"> 
						<table style="width: 70%; text-align: left; margin-left: auto; margin-right: auto;" border="0" cellpadding="0" cellspacing="0">
							<tr> <td style="text-align: center;"><p name="data" style="color:white">Date: <?php echo $data; ?></p></td> <td style="text-align: center;"><p name="ora" style="color:white">Time: <?php echo $ora;?></p></td> </tr> 
							<tr> <td style="text-align: center;"><p name="temp" style="color:white">Temp: <?php echo $temp; ?> &deg; C</p></td> <td style="text-align: center;"><p name="temp" style="color:white">Mode: <?php if($mode=="mode=0")echo "Automat";else echo "Manual"; ?></p></td>  </tr> 
							<tr> <td style="text-align: center;"><p name="soil" style="color:white">Soil: <?php echo $soil; ?> </p></td> <td style="text-align: center;"><p name="light" style="color:white">Light: <?php echo $light; ?> </p></td></tr> 
						</table> 
					</div> 
					<div class="split right"> <img src="/imag/imag1.jpg" width="250" height="150">
					</div> 
					</center> <br> <center> 
					<form method="post"> 
					<button class= "button" name="script"> <font face="impact" color="black" size="3" >LIVE</font></button> <br> 
					<button class= "button" <?php if($mode=="mode=1")echo "";else echo "disabled"; ?>  name="lighton"> <font face="impact" color="green" size="3" >LIGHT ON</font></button> 
					<button class= "button" <?php if($mode=="mode=1")echo "";else echo "disabled"; ?>  name="lightoff"> <font face="impact" color="red" size="3" >LIGHT OFF</font></button> <br> 
					<button class= "button" <?php if($mode=="mode=1")echo "";else echo "disabled"; ?>  name="wateron"> <font face="impact" color="green" size="3" >WATERPUMP ON</font></button> 
					<button class= "button"	<?php if($mode=="mode=1")echo "";else echo "disabled"; ?>  name="wateroff"> <font face="impact" color="red" size="3" >WATERPUMP OFF</font></button>
					<button class= "button"	name="mode"> <font face="impact" color="black" size="3" >MODE</font></button>
				</form> </center> 
				<center> <br> 
				<font face="arial" color="white">&copy; 2021 Automation and Computer Engineering Faculty</font>
				</center> <br> </td> </tr> 
		</table> </center> 
	</body>
</html>
