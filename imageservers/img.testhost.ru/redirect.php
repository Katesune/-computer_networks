<?php

	$dbusername = 'katesune';
	$dbpassword = 'tapok';

	$db = new PDO("mysql:dbname=test_database;host=localhost",
    $dbusername, $dbpassword, [PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION]);

    $data = [];

    if ($_POST['image']!='') {
    	$imagename  = strval($_POST['image']); 

    	$imagename = trim ($imagename ,"\n\r\t\v\0" );

    	$sql = "SELECT * FROM `servers` WHERE `filename`='".$imagename."';";

		$host=$db->query($sql)->fetchAll();
		
        
		$imgserver = $host[0]['servername'];
    		$servername = 'Location: http://'.$imgserver.'/';
			
		header($servername); 
    echo $imgserver;
}
