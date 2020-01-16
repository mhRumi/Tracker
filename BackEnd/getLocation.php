<?php
	
	$GpsId = $_POST['GpsId'];
	require_once (dirname(_FILE_)."/getConnection.php");

	$object = new Connection();
	$conn = $object->connect();
	$sqlgetLocation = "Select *FROM Bus_info where GpsId = $GpsId;";

	$result = $conn->query($sqlgetLocation);

	if($row = $result->num_rows > 0){
		while ($row = $result->fetch_assoc()) {
			echo $row['Latitude'].' '.$row['Longitude']."\n";
		}
	}else{
		echo "No data in server";
	}
?>
