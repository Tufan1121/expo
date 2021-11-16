<?php
require "r1.php"
echo "hola";

$array1= array (1, 3, 2, 9); 

$solicitadatos = r1::user();
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


?>