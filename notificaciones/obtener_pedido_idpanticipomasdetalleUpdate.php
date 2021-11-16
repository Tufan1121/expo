<?php
/**
 * Obtiene el detalle de un alumno especificado por
 * su identificador "idalumno"
 */

require 'solicitudes.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    if (isset($_GET['idpedido'])) {

        // Obtener parámetro idalumno
        $parametro = $_GET['idpedido'];

        // Tratar retorno
        // Manejar petición GET
    $solicitadatos = solicitudes::getByIdpedidomasdetallexUpdate($parametro);

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

    } else {
        // Enviar respuesta de error
        print json_encode(
            array(
                'estado' => '3',
                'mensaje' => 'Se necesita un identificador'
            )
        );
    }
}
?>
