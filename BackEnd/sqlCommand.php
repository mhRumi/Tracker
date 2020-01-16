<?php


	class SqlCommand{


		function createDatabase(){

			    $sql = "Create database ". $DB_NAME;
			    if($conn->query($sql)== TRUE){
			 	    echo "Database has been crated successfully\n";
			    }else{
			 	    echo $conn->error;
			    }
		}

		function insert($conn,$GpsId,$Latitude,$Longitude){

		        $sqlInsert = "insert into Bus_info(GpsId,Latitude,Longitude)
		 		VALUES('$GpsId','$Latitude','$Longitude');";

		 		if($conn->query($sqlInsert) == true){
		 				echo "Data has been updated successfully\n";
		 		}else{
		 				echo "Error updating data   ".$conn->error."\n";
		 		}


		}

		function createTable($conn){
			
			    $sqlCreateTable = "Create table Bus_info(
		        GpsId char(30),
		        Latitude Double,
		        Longitude Double,
		        primary key(GpsId));";

		        if($conn->query($sqlCreateTable) == true){

		 	            echo "Bus_info table has been created successfully\n";
		        }else{

		 	            echo $conn->error."\n";
		        }

		 

		}

		
	}


?>