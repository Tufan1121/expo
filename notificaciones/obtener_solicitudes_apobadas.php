<?php
/**
 * Obtiene todas las alumnos de la base de datos
 */

require 'solicitudes.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    // Manejar petición GET
    $solicitadatos = solicitudes::getAllaprobadas();

    if ($solicitadatos) {

        $datos["estado"] = 1;
        $datos["solicita"] = $solicitadatos;

        print json_encode($datos);
    } else {
        print json_encode(array(
            "estado" => 2,
            "mensaje" => "Ha ocurrido un error"
        ));
    }
}

?>