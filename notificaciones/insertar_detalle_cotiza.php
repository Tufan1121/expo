<?php
/**
 * Insertar un nuevo alumno en la base de datos
 */

require 'solicitudes.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    // Decodificando formato Json
    $body = json_decode(file_get_contents("php://input"), true);

    // Insertar Alumno
    $retorno = solicitudes::insertdetallescotizar(
        $body['iddetallecotiza'],
        $body['idcotiza'],
		$body['clave'],
        $body['cantidad'],
		$body['precio']
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