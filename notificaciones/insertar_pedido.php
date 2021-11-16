<?php
/**
 * Insertar un nuevo alumno en la base de datos
 */

require 'solicitudes.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    // Decodificando formato Json
    $body = json_decode(file_get_contents("php://input"), true);

    // Insertar Alumno
    $retorno = solicitudes::insertpedido(
        $body['idpedido'],
        $body['idcliente'],
		$body['fecha'],
        $body['idmetodopago'],
		$body['idmetodopago2'],
		$body['idmetodopago3'],
		$body['observaciones'],
        $body['idusuario'],
		$body['pedidos'],
		$body['idexpo'],
		$body['estatus'],
		$body['anticipo'],
		$body['anticipo2'],
		$body['anticipo3'],
		$body['total_pagar'],
		$body['entregado'],
		$body['dig1']
		
		//$body['dig1'],
		//$body['dig2'],
		//$body['dig3']
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