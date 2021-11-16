<?php
/**
 * Obtiene todas las alumnos de la base de datos
 */

require 'solicitudes.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
if (isset($_GET['idexpo'])) {
	$parametro=$_GET['idexpo'];
    // Manejar petición GET

    // Manejar petición GET
    $pedidos = solicitudes::getAllpedidosfuerdeexpo($parametro);

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