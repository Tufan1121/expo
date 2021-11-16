<?php
/**
 * Obtiene todas las alumnos de la base de datos
 */

require 'solicitudes.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    // Manejar petición GET
    $pedidos = solicitudes::getAllpTIENDA();

    if ($pedidos) {

        $datos["estado"] = 1;
        $datos["solicita"] = $pedidos;

        print json_encode($datos);
    } else {
        print json_encode(array(
            "estado" => 2,
            "mensaje" => "Ha ocurrido un error"
        ));
    }
}

?>