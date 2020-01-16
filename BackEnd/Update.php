<?php
	
	class UpdateLocation{


		function updateCurrentLocation($conn,$GpsId,$latitude,$longitude){

			$sql = "Update Bus_info set Latitude = '$latitude',
			                            Longitude = '$longitude'
			                            where GpsId ='$GpsId' ;";

			if($conn->query($sql) == true){
				echo "updated successfully";
			}else{
				echo $conn->error;
			}                            


		}
	}
?>
