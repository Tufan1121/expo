<?php
/**
 * Obtiene todas las alumnos de la base de datos
 */

require 'solicitudes.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
	
	if (isset($_GET['idexpo'])) {

        // Obtener parámetro idalumno
        $parametro = $_GET['idexpo'];

    
    // Manejar peticiÃ³n GET
    $solicitadatos = solicitudes::getAlldias($parametro);

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