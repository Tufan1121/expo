<?php

require 'solicitudes.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
	//parametro - descripcio
    if (isset($_GET['descripcio'])) {
		$descripcio=$_GET['descripcio'];
	} else{
		$descripcio="";
	}

	//parametro - diseno
    if (isset($_GET['diseno'])) {
		$diseno=$_GET['diseno'];
	} else{
		$diseno="";
	}
	
	//parametro - largo1
    if (isset($_GET['largo1'])) {
		$largo1=$_GET['largo1'];
	} else{
		$largo1=0;
	}
	
	//parametro - largo2
    if (isset($_GET['largo2'])) {
		$largo2=$_GET['largo2'];
	} else{
		$largo2=0;
	}

	//parametro - ancho1
    if (isset($_GET['ancho1'])) {
		$ancho1=$_GET['ancho1'];
	} else{
		$ancho1=0;
	}

	//parametro - ancho2
    if (isset($_GET['ancho2'])) {
		$ancho2=$_GET['ancho2'];
	} else{
		$ancho2=0;
	}
	if (empty($descripcio) && empty($diseno) && empty($largo1) && empty($largo2) && empty($ancho1) && empty($ancho2)){
        print json_encode(
	
            array(
                'estado' => '3',
                'mensaje' => 'Se necesitan mas parametros de busqueda.'
            )
        );
		exit;		
	}
	
	
    //if (isset($_GET['descripcio'])) {

        // Tratar retorno


        $retorno1 = solicitudes::busquedaGlobal($descripcio,$diseno,$largo1,$largo2,$ancho1,$ancho2);
		
        if ($retorno1) {
            $alumno["estado"] = "1";		// cambio "1" a 1 porque no coge bien la cadena.
            $alumno["alumno"] = $retorno1;
            // Enviar objeto json del alumno
            print json_encode($alumno);
        } else {
            // Enviar respuesta de error general
            print json_encode(
                array(
                    'estado' => '2',
                    'mensaje' => 'No se obtuvo el registro'
                )
            );
        }

		//mysqli::close ( void ) : bool;
   // } else {
        // Enviar respuesta de error
    //    print json_encode(
    //        array(
    //            'estado' => '3',
    //            'mensaje' => 'Se necesita un identificador'
    //        )
    //    );
    //}
}
?>
