<?php

	$GpsId = $_POST['GpsId'];
	$Latitude = $_POST['Latitude'];
	$Longitude = $_POST['Longitude'];

	require_once (dirname(__FILE__)."/getConnection.php");

	 $object = new Connection();
	 $conn = $object->connect();

	require_once(dirname(__FILE__) . '/sqlCommand.php');

	$ob = new SqlCommand();
	$ob->createTable($conn);
	$ob->insert($conn,$GpsId,$Latitude,$Longitude);

	require_once(dirname(__FILE__) . '/update.php');
	$updateObj = new UpdateLocation();
	$updateObj->updateCurrentLocation($conn,$GpsId,$Latitude,$Longitude);


	$conn->close();
?>