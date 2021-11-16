<?php

//include_once './Connect.php';
require 'solicitudes.php';

function updatelecturas(){
	
    //$db = new DbConnect();
    // array for json response
    $response = array();
	
    if (isset($_GET['json'])){
		$json = $_GET['json'];
		
		$lecturaas = json_decode($json, true);
		//print_r ($lecturaas) ;
		
		//$ids = implode(',', array_keys($lecturaas ));
		
		//echo 	$ids;
		
		$sql = "UPDATE inventario SET stock = CASE clave "; 
		$ids = implode(',', array_keys($lecturaas));
		$ids = "";

		
				//toma el campo de usuario, l_tomada
				for ($i=0; $i < count($lecturaas["alumnos"]); $i++) { 
		            $sql.= sprintf("WHEN %s THEN %s ", $lecturaas["alumnos"][$i]["clave"], $lecturaas["alumnos"][$i]["stock"]);					
					$ids.= sprintf("%s%s", $lecturaas["alumnos"][$i]["clave"], ",");					
				}
				
				
				
				$ids = substr ($ids, 0, strlen($ids) - 1);				

				$sql .= "END WHERE clave IN ($ids)";					
				
		        echo $sql;
				$sql='UPDATE inventario SET stock = CASE clave WHEN "F-21053" THEN 300 WHEN "F-21054" THEN 107 WHEN "F-21055" THEN 44 WHEN "F-21050" THEN 87 WHEN "F-21051" THEN 88 END WHERE clave IN ("F-21053","F-21054" ,"F-21055" ,"F-21050","F-21051")'
				
				 // Tratar retorno
        $retorno = solicitudes::updateinventario($sql);

				if ($retorno) {
        $json_string = json_encode(array("estado" => 1,"mensaje" => "Actualizacion correcta"));
		echo $json_string;
    } else {
        $json_string = json_encode(array("estado" => 2,"mensaje" => "No se actualizo el registro"));
		echo $json_string;
    }
				
				/*
				$result = mysqli_query($db,$sql);
				
					if ($result) {
						$response["error"] = false;
						$response["message"] = "Lecturas actualizadas correctemente";
					} else {
						$response["error"] = true;
						$response["message"] = "Error al actualizar lecturas";
					}	
					*/
				
				
			header('Content-Type: application/json');    
			
			
			//mysqli_close($result);
			
} else {		
		//$response["error"] = true;
        //$response["message"] = "Ingrese todos los datos";
	}	
	 //echo json_encode($response);
}

updatelecturas();

?>










































