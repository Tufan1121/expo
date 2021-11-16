<?php
/**
 * Insertar un nuevo alumno en la base de datos
 */

require 'solicitudes.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    // Decodificando formato Json
    $body = json_decode(file_get_contents("php://input"), true);

    // Insertar Alumno
    $retorno = solicitudes::insert(
        $body['id_cliente'],
        $body['nom_cliente'],
		$body['ap_cliente'],
        $body['dir_cliente'],
		$body['tel_cliente'],
        $body['correo_cliente'],
		$body['rfc_cliente'],
        $body['tipopersona'],
		$body['usocfdi']
		);
		

    if ($retorno) {
        $json_string = json_encode(array("estado" => 1,"mensaje" => "Creacion correcta"));
		echo $json_string;
    } else {
        $json_string = json_encode(array("estado" => 2,"mensaje" => "No se creo el registro"));
		echo $json_string;
    }
}

?>