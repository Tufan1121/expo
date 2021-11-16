<?php

/**
 * Representa el la estructura de las Alumnoss
 * almacenadas en la base de datos
 */
require 'Database.php';

class solicitudes
{
    function __construct()
    {
    }

    /**
     * Retorna en la fila especificada de la tabla 'Alumnos'
     *
     * @param $idAlumno Identificador del registro
     * @return array Datos del registro
     */
    public static function getAll()
    {
        $consulta = "SELECT pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente FROM pedido INNER JOIN clientes ON pedido.idcliente=clientes.id_cliente";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }
	
	public static function getAllpedidosdeexpo($expo)
    {
        $consulta = "SELECT pedido.total_pagar,pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente FROM pedido INNER JOIN clientes ON pedido.idcliente=clientes.id_cliente where pedido.idexpo=".$expo." and pedido.estatus=1 ORDER BY pedido.fecha DESC";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }
	
	public static function getAllpedidosfuerdeexpo($expo)
    {
        $consulta = "SELECT pedido.total_pagar,pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente FROM pedido INNER JOIN clientes ON clientes.id_cliente=pedido.idcliente where pedido.estatus !=1 and pedido.estatus!=9 and pedido.idexpo=".$expo." ORDER BY pedido.fecha DESC	";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }
	
public static function getcotiza($expo)
    {
        $consulta = "SELECT pedido.total_pagar,pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente FROM pedido INNER JOIN clientes ON clientes.id_cliente=pedido.idcliente where pedido.estatus=8 and pedido.idexpo=".$expo." ORDER BY pedido.fecha ASC";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }
		


	public static function getAllmodificar()
    {
        $consulta = "SELECT pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente FROM pedido INNER JOIN clientes ON pedido.idcliente=clientes.id_cliente WHERE pedido.estatus=2";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }
//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-	
///*-*-*-*- CONSULTA DE PEDIDOS ESTATUS 1= ENTREGADO EN EXPO
public static function getAllpEXPO()
    {
        $consulta = "SELECT pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente,pedido.estatus FROM pedido INNER JOIN clientes ON pedido.idcliente=clientes.id_cliente WHERE pedido.estatus=1";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }
//*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*--*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

///*-*-*-*- CONSULTA DE PENDIENTE PAGO (ANTICIPO)
public static function getAllpANTICIPO()
    {
        $consulta = "SELECT pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente,pedido.estatus FROM pedido INNER JOIN clientes ON pedido.idcliente=clientes.id_cliente WHERE pedido.estatus=2";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }
//*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*--*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

///*-*-*-*-CONTLTA DE PAGO FORANEO
public static function getAllpFORANEO()
    {
        $consulta = "SELECT pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente,pedido.estatus FROM pedido INNER JOIN clientes ON pedido.idcliente=clientes.id_cliente WHERE pedido.estatus=3";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }
//*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*--*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

///*-*-*-*-PAGADO RECIVOR EN TIENDA
public static function getAllpTIENDA()
    {
        $consulta = "SELECT pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente,pedido.estatus FROM pedido INNER JOIN clientes ON pedido.idcliente=clientes.id_cliente WHERE pedido.estatus=4";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }
//*-*--*-*--*-*-*-*-*-*---*-*-*-*-*-*-*-*-*-*-*--*-*-*--*-*-*--*	
	
	
	
//*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*--*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
public static function getAllexpo()
    {
        $consulta = "select * from expo where activa=1";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }
//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*----*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*/-/-*--*-**	
	
	
	
//*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*--*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
public static function getAllexpod()
    {
        $consulta = "select * from expo WHERE activa=1";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }
//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*----*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*/-/-*--*-**	
	
	
public static function getAlltodoinventarioupdate()
    {
        $consulta = "select clave,stock from inventario";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }
	
	///**-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-++++++
	//*-*-*-*-*-*ESTA FUNCION ME CONSULTA TODOS LOS TADOS DE TABLA PEDIDOS,CLIENTES Y EN QUE EXPO ESTA
	public static function getByIdpedidomas($pedido)
    {
        // Consulta de la tabla Alumnos    pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente 
        $consulta = "SELECT * FROM expo INNER JOIN pedido INNER JOIN clientes ON pedido.idcliente=clientes.id_cliente WHERE expo.idexpo=pedido.idexpo AND pedido.pedidos=?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($pedido));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	//*-*-*--*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*	
	
	//*-*-*-*-*-*ESTA FUNCION ME CONSULTA TODOS LOS TADOS DE TABLA PEDIDOS,CLIENTES Y EN QUE EXPO ESTA
	public static function getByIdpedidomasx($pedido)
    {
        // Consulta de la tabla Alumnos    pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente 
        $consulta = "SELECT * FROM expo INNER JOIN pedido INNER JOIN clientes ON pedido.idcliente=clientes.id_cliente WHERE expo.idexpo=pedido.idexpo AND pedido.pedidos=?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($pedido));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	//*-*-*--*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*	
	
	//*-*-*-*-*-*ESTA FUNCION ME CONSULTA TODOS LOS TADOS DE TABLA PEDIDOS,CLIENTES Y EN QUE EXPO ESTA
	public static function getByIdpedidomasxUpdate($pedido)
    {
        // Consulta de la tabla Alumnos    pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente 
        $consulta = "SELECT * FROM expo INNER JOIN pedido INNER JOIN clientes ON pedido.idcliente=clientes.id_cliente WHERE expo.idexpo=pedido.idexpo AND pedido.pedidos=?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($pedido));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	//*-*-*--*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*	
	
	///**-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-++++++
	//*-*-*-*-*-*ESTA FUNCION ME CONSULTA TODOS LOS TADOS DE TABLA PEDIDOS,CLIENTES Y EN QUE EXPO ESTA
	public static function getByIdpedidomasforaneo($pedido)
    {
        // Consulta de la tabla Alumnos    pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente 
        $consulta = "SELECT * FROM expo INNER JOIN pedido INNER JOIN clientes ON pedido.idcliente=clientes.id_cliente WHERE expo.idexpo=pedido.idexpo AND pedido.pedidos=?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($pedido));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	//*-*-*--*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*	
	
	///**-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-++++++
	//*-*-*-*-*-*ESTA FUNCION ME CONSULTA TODOS LOS TADOS DE TABLA PEDIDOS,CLIENTES Y EN QUE EXPO ESTA
	public static function getByIdpedidomastienda($pedido)
    {
        // Consulta de la tabla Alumnos    pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente 
        $consulta = "SELECT * FROM expo INNER JOIN pedido INNER JOIN clientes ON pedido.idcliente=clientes.id_cliente WHERE expo.idexpo=pedido.idexpo AND pedido.pedidos=?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($pedido));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	//*-*-*--*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*	
	
	
	///**-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-++++++
	public static function getByIdpedido($pedido)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente FROM pedido INNER JOIN clientes ON pedido.idcliente=clientes.id_cliente WHERE pedido.pedidos=?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($pedido));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	//*-*-*--*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*	
	///**-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-++++++
	public static function getcot($pedido,$expo)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT pedido.total_pagar,pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente FROM pedido INNER JOIN clientes ON pedido.idcliente=clientes.id_cliente WHERE pedido.idexpo=".$expo." and pedido.estatus=8 and	 pedido.pedidos like ?";
		$pedido="%".$pedido."%";
		//echo $consulta;
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($pedido));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	
	public static function getcotn($name,$expo)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT pedido.total_pagar,pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente FROM pedido INNER JOIN clientes ON pedido.idcliente=clientes.id_cliente WHERE pedido.idexpo=".$expo." and pedido.estatus=8 and	 clientes.nom_cliente like ?";
		$name="%".$name."%";
		//echo $consulta;
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($name));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	
	//--------------------
	public static function getped($pedido,$expo)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT pedido.total_pagar,pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente FROM pedido INNER JOIN clientes ON pedido.idcliente=clientes.id_cliente WHERE pedido.idexpo=".$expo." and pedido.estatus<>8 and pedido.estatus<>9 and pedido.estatus<>1 and	 pedido.pedidos like ?";
		$pedido="%".$pedido."%";
		//echo $consulta;
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($pedido));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	
	public static function getpedn($name,$expo)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT pedido.total_pagar,pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente FROM pedido INNER JOIN clientes ON pedido.idcliente=clientes.id_cliente WHERE pedido.idexpo=".$expo." and  pedido.estatus<>8 and pedido.estatus<>9 and pedido.estatus<>1 and	 clientes.nom_cliente like ?";
		$name="%".$name."%";
		//echo $consulta;
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($name));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	//--------------------
	
	//--------------------
	public static function getexped($pedido,$expo)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT pedido.total_pagar,pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente FROM pedido INNER JOIN clientes ON pedido.idcliente=clientes.id_cliente WHERE pedido.idexpo=".$expo." and pedido.estatus=1 and	pedido.pedidos like ?";
		$pedido="%".$pedido."%";
		//echo $consulta;
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($pedido));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	
	public static function getexpedn($name,$expo)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT pedido.total_pagar,pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente FROM pedido INNER JOIN clientes ON pedido.idcliente=clientes.id_cliente WHERE pedido.idexpo=".$expo." and pedido.estatus=1 and	 clientes.nom_cliente like ?";
		$name="%".$name."%";
		//echo $consulta;
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($name));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	//--------------------
	
	
	
	//*-*-*--*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*	
	
	//*-*-*-*--* ESTATUS POR PEDIDO CUANDO LA ENTREGA ES EN TIENDA
	public static function getByIdpedidopEXPO($pedido)
    {
        // Consulta de la tabla Alumnos
                $consulta = "SELECT pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente, pedido.estatus FROM pedido INNER JOIN clientes ON pedido.idcliente=clientes.id_cliente WHERE pedido.estatus = 1 AND pedido.pedidos=?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($pedido));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	//*-*-*--*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*	
	
	//*-*-*-*--* ESTATUS POR PEDIDO CUANDO ESTA PENDIENTE EL PAGO O TIENE ANTICIPO
	public static function getByIdpedidopANTICIPO($pedido)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT * FROM pedido INNER JOIN clientes ON pedido.idcliente=clientes.id_cliente WHERE pedido.estatus = 2 AND pedido.pedidos=?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($pedido));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	//*-*-*--*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*	
	//*-*-*-*--* ESTATUS POR PEDIDO CUANDO LA ENTREGA ES EN TIENDA
	public static function getByIdpedidoptienda1($pedido)
    {
        // Consulta de la tabla Alumnos
                $consulta = "SELECT pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente, pedido.estatus FROM pedido INNER JOIN clientes ON pedido.idcliente=clientes.id_cliente WHERE pedido.estatus = 4 AND pedido.pedidos=?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($pedido));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	
	//*-*-*-*--* ESTATUS POR PEDIDOD CUANDO ESTA PAGADO Y SE VA A FORANEO
	public static function getByIdpedidopFORANEO($pedido)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente FROM pedido INNER JOIN clientes ON pedido.idcliente=clientes.id_cliente WHERE pedido.pedidos=?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($pedido));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	//*-*-*--*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*	
	
	//*-*-*-*--* ESTATUS POR PEDIDO CUANDO ESTE SE PAGO Y SE VA A RECOJER EN TIENDA
	public static function getByIdpedidopTIENDA($pedido)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT pedido.pedidos,pedido.fecha,clientes.nom_cliente,clientes.ap_cliente, pedido.estatus FROM pedido INNER JOIN clientes ON pedido.idcliente=clientes.id_cliente WHERE pedido.estatus = 1 AND pedido.pedidos=?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($pedido));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	//*-*-*--*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*	
	
	
	//*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	
	public static function getAllaprobadas()
    {
        $consulta = "SELECT * FROM 	solicitudes WHERE activa = 2";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }
	
	public static function getAlldenegadas()
    {
        $consulta = "SELECT * FROM 	solicitudes WHERE activa = 1";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }
	
	public static function getAlltodo()
    {
        $consulta = "SELECT * FROM 	solicitudes";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }
	//**-*-*-*-*-*OBETNER TODOS LOS DATOS DE TABLA SUBCATEGORIA -*-*-**-**-*-*-*-*-**-*--*---*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public static function getAllinventario()
    {
        $consulta = "SELECT * FROM 	subcategoria";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }
	//*-*-*-*-**-*-*-*-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-**-***-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**-*-*-*-*
	
	
	
	
	public static function getAlltodoclientes()
    {
        $consulta = "SELECT * FROM 	clientes";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }
	
	//-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*
	public static function getAlldias($idexpo)
    {
        $consulta = "SELECT fecha, SUM(anticipo+anticipo2+anticipo3) as suma  FROM 	pedido where idexpo=".$idexpo." GROUP BY fecha ";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }
	//*-*-*-*-*--*-*-*-*-*-*-*-*-*-
	
	public static function getAllanticipos($idexpo)
    {
        $consulta = "SELECT anticipo, anticipo2, anticipo3 FROM pedido where idexpo=".$idexpo;
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }
	
	//****************************************************************************************
	//*****************CONSULTA DE TODOS LOS PEDIDOS XD **************************************
	public static function getAlltodopedidos()
    {
        $consulta = "SELECT * FROM 	pedido";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }
	//***************************************************************************************
	
	//*****************CONSULTA DE TODOS LOS PEDIDOS XD **************************************
	public static function getAlltodocotiza()
    {
        $consulta = "SELECT * FROM 	cotiza";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }
	//***************************************************************************************
	
	
	//****************************************************************************************
	//*****************CONSULTA DE TODOS LOS PEDIDOS XD **************************************
	public static function getAlltododetallepedidos()
    {
        $consulta = "SELECT * FROM 	detallepedido";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }
	//***************************************************************************************
	
	
	
	//****************************************************************************************
	//*****************CONSULTA DE TODOS LOS cotiza XD **************************************
	public static function getAlltododetallecotiza()
    {
        $consulta = "SELECT * FROM 	detallecotiza";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }
	//***************************************************************************************
	
	
	
	public static function getAlltodoLikecliente($xnombre)
    {
        $consulta = "SELECT * FROM 	solicitudes";
        try {
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }
    /**
     * Obtiene los campos de un Alumno con un identificador
     * determinado
     *
     * @param $idAlumno Identificador del alumno
     * @return mixed
     */
    public static function getById($passwordxd)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT * FROM user WHERE pass = ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($passwordxd));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	
	 public static function getByIdEXPO1($nom_expo)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT * FROM expo WHERE nom_expo = ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($nom_expo));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	
	
	
	
	 public static function getByIdcliente($clientname)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT * FROM clientes WHERE nom_cliente like ?";
		$clientname="%".$clientname."%";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($clientname));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	//*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-
	 public static function getByIdfechaxdia($passwordxd)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT * FROM pedido WHERE fecha = ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($passwordxd));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	
	 public static function getByIdcalidad($passwordxd)
    {
        // Consulta de la tabla Alumnos
//      $consulta = "SELECT ibodegas.bodega1,ibodegas.bodega2,ibodegas.bodega3,ibodegas.diseno,ibodegas.producto1,ibodegas.descripcio,ibodegas.medidas,ibodegas.hm,gproducto.pathima1,gproducto.precio1,gproducto.precio2, gproducto.precio3 FROM ibodegas INNER JOIN gproducto WHERE ibodegas.producto=gproducto.producto AND ibodegas.hm>0 AND ibodegas.fulldescrip LIKE ?";
        $consulta = "SELECT ibodegas.bodega1,ibodegas.bodega2,ibodegas.bodega3,ibodegas.bodega4,ibodegas.diseno,ibodegas.producto1,ibodegas.descripcio,ibodegas.medidas,ibodegas.hm,gproducto.pathima1,gproducto.precio1,gproducto.precio2, gproducto.precio3 FROM ibodegas INNER JOIN gproducto WHERE ibodegas.producto=gproducto.producto and ibodegas.hm>0 AND ibodegas.descripcio LIKE ? ";
	//$consulta = "SELECT * from ibodegas inner join gproducto ON producto=ibodegas.producto where ibodegas.hm>0 and ibodegas.descripcio like ? ";
	
	try {
			//echo $passwordxd;
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array('%'.$passwordxd.'%'));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	
	
	//BUSQUEDA DE INVENTARIO DENTRO DE LA EXPO-...............................
	public static function getByIdcalidadexpo($tabla,$passwordxd)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT ".$tabla.".diseno,".$tabla.".producto1,".$tabla.".descripcio,".$tabla.".medidas,".$tabla.".hm, gproducto.pathima1,gproducto.precio1,gproducto.precio2, gproducto.precio3 FROM ".$tabla." INNER JOIN gproducto WHERE ".$tabla.".producto=gproducto.producto AND ".$tabla.".hm>0 AND ".$tabla.".descripcio LIKE ?";
		
        try {
			//echo $passwordxd;
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array('%'.$passwordxd.'%'));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	
	//-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|--|-|-|-|
	public static function getByIdpedidomasdetalle($idpedido)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT * FROM detallepedido WHERE idpedido = ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($idpedido));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	
	//-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|
	//-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|--|-|-|-|
	public static function getByIdpedidomasdetallex($idpedido)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT * FROM detallepedido WHERE idpedido = ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($idpedido));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	
	//-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|
	
	//-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|--|-|-|-|
	public static function getByIdpedidomasdetallexUpdate($idpedido)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT * FROM detallepedido INNER JOIN ibodegas on ibodegas.producto1=detallepedido.clave  where detallepedido.estatus!=5 AND detallepedido.idpedido = ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($idpedido));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	
	//-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|
	
	
	//*-*-*-*-*-*-*-*-*-*-*-*---*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*
	//*-*-*-*-*-PARABUSCAR BUSCAR SUBCATEGORIA DE LA TABLA INVENTARIO
	
	public static function porsubcategoria($idpedido)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT ibodegas.producto,ibodegas.diseno,ibodegas.producto1,ibodegas.descripcio,ibodegas.medidas,ibodegas.hm, gproducto.pathima1,gproducto.precio1,gproducto.precio2,gproducto.precio3 FROM ibodegas INNER JOIN gproducto WHERE ibodegas.producto=gproducto.producto and ibodegas.hm>0 and ibodegas.descripcio=?";
 // Consulta de la tabla Alumnos
       // $consulta = "SELECT * FROM detallepedido INNER JOIN ibodegas on ibodegas.producto1=detallepedido.clave  where detallepedido.estatus!=5 AND detallepedido.idpedido = ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($idpedido));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	
	public static function porsubcategoriaibodegas()
    {
        // Consulta de la tabla Alumnos
		$consulta = "SELECT ibodegas.producto1,ibodegas.descripcio,ibodegas.medidas,ibodegas.hm, gproducto.pathima1 FROM ibodegas INNER JOIN gproducto WHERE ibodegas.producto=gproducto.producto";
        $consulta = "SELECT *, gproducto.pathima1 FROM ibodegas INNER JOIN gproducto WHERE ibodegas.producto=gproducto.producto";


        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
		
    }
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*
	
	public static function getByIdclienterfc($rfc)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT * FROM clientes WHERE rfc_cliente LIKE ?";
		$rfc= "%".$rfc."%";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($rfc));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	
	//............................PEDIDOS*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	/*
	 public static function getByIdpedidopdf($pedido)
    {
        // Consulta de la tabla Alumnos
       // $consulta = "SELECT * FROM pedido WHERE pedidos = ?";

       
	   
	   
    }
	*/
	//****************************************************************************
	
	
	
	
	
//----------------------------------------------------------------------------------------------------------------
	//esta es para una cadena de conexion de claves 
/*	
	public static function getById($passwordxd)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT * FROM user WHERE pass = ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($passwordxd));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	*/
	/**********************************************************************************************************************************************/
	//----------------------------------------------------------------------------------------------------------------
	//esta es para una cadena de conexion de claves 
	
	public static function getByIdxd($clavexd)
    {
        // Consulta de la tabla inventario que saca la clave de producto
		
        //$consulta = "SELECT spock418.diseno,spock418.producto1,spock418.descripcio,spock418.medidas,spock418.hm,gproducto.precio1,gproducto.precio2,gproducto.precio3,gproducto.pathima1 FROM spock418 INNER JOIN gproducto WHERE spock418.producto=gproducto.producto AND spock418.producto=?";
		$consulta = "SELECT spock436.diseno,spock436.producto1,spock436.descripcio,spock436.medidas,spock436.hm,gproducto.precio1,gproducto.precio2,gproducto.precio3,gproducto.pathima1 FROM spock436 INNER JOIN gproducto WHERE spock436.producto=gproducto.producto AND spock436.producto=?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($clavexd));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
			echo $e;
            return -1;
        }
    }
	
	public static function getByIdxdiii($clavexd)
    {
        // Consulta de la tabla inventario que saca la clave de producto
		
        $consulta = "SELECT ibodegas.diseno,ibodegas.producto1,ibodegas.descripcio,ibodegas.medidas,ibodegas.hm,gproducto.precio1,gproducto.precio2,gproducto.precio3,gproducto.pathima1,gproducto.desa FROM ibodegas INNER JOIN gproducto WHERE ibodegas.producto=gproducto.producto AND ibodegas.producto=?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($clavexd));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
			echo $e;
            return -1;
        }
    }
	
	public static function getByIdxdzcorta($clavexd)
    {
        // Consulta de la tabla inventario que saca la clave de producto
		
        $consulta = "SELECT spock418.diseno,spock418.producto1,spock418.descripcio,spock418.medidas,spock418.hm,gproducto.precio1,gproducto.precio2,gproducto.precio3,gproducto.pathima1 FROM spock418 INNER JOIN gproducto WHERE spock418.producto=gproducto.producto AND spock418.producto1=?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($clavexd));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
			echo $e;
            return -1;
        }
    }
	public static function getByIdxdzbodegas($clavexd)
    {
        // Consulta de la tabla inventario que saca la clave de producto
		
        //$consulta = "SELECT spock418.diseno,spock418.producto1,spock418.descripcio,spock418.medidas,spock418.hm,gproducto.precio1,gproducto.precio2,gproducto.precio3,gproducto.pathima1 FROM spock418 INNER JOIN gproducto WHERE spock418.producto=gproducto.producto AND spock418.producto=?";
		$consulta = "SELECT ibodegas.producto1,ibodegas.descripcio,ibodegas.diseno, ibodegas.medidas,ibodegas.hm,gproducto.precio1,gproducto.precio2,gproducto.precio3,gproducto.pathima1 FROM ibodegas INNER JOIN gproducto on ibodegas.producto=gproducto.producto where ibodegas.producto =?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($clavexd));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
			echo $e;
            return -1;
        }
    }
	public static function getByIdxdlista2xclavecorta($clavexd)
    {
        // Consulta de la tabla inventario que saca la clave de producto
		
        $consulta = "SELECT ibodegas.producto,ibodegas.diseno,ibodegas.producto1,ibodegas.descripcio,ibodegas.medidas,ibodegas.hm,gproducto.precio1,gproducto.precio2,gproducto.precio3,gproducto.pathima1,gproducto.desa FROM ibodegas INNER JOIN gproducto WHERE ibodegas.producto=gproducto.producto AND ibodegas.producto1=?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($clavexd));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
			echo $e;
            return -1;
        }
    }
	
	public static function getByIdxdlista2($clavexd)
    {
        // Consulta de la tabla inventario que saca la clave de producto
		
        $consulta = "SELECT ibodegas.producto,ibodegas.diseno,ibodegas.producto1,ibodegas.descripcio,ibodegas.medidas,ibodegas.hm,gproducto.precio1,gproducto.precio2,gproducto.precio3,gproducto.pathima1,gproducto.desa FROM ibodegas INNER JOIN gproducto WHERE ibodegas.producto=gproducto.producto AND ibodegas.producto=?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($clavexd));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
			echo $e;
            return -1;
        }
    }
	
	public static function getByIdxdlista2CORTA($clavexd)
    {
        // Consulta de la tabla inventario que saca la clave de producto
		
        $consulta = "SELECT ibodegas.producto,ibodegas.diseno,ibodegas.producto1,ibodegas.descripcio,ibodegas.medidas,ibodegas.hm,gproducto.precio1,gproducto.precio2,gproducto.precio3,gproducto.pathima1,gproducto.desa FROM ibodegas INNER JOIN gproducto WHERE ibodegas.producto=gproducto.producto AND ibodegas.producto1=?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($clavexd));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
			echo $e;
            return -1;
        }
    }
	
	/*.............................................................................................................................*/
	/**********************************************************************************************************************************************/
	//----------------------------------------------------------------------------------------------------------------
	//esta es para una cadena de conexion de claves 
	
	public static function getByIdxdenibodegasprecio($clavexd)
    {
        // Consulta de la tabla inventario que saca la clave de producto
		
        $consulta = "SELECT ibodegas.producto,ibodegas.diseno,ibodegas.producto1,ibodegas.descripcio,ibodegas.medidas,ibodegas.hm,gproducto.precio1,gproducto.precio2,gproducto.precio3,gproducto.pathima1 FROM ibodegas INNER JOIN gproducto WHERE ibodegas.producto=gproducto.producto AND ibodegas.producto1=?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($clavexd));
            // Capturar primera fila del resultadogetByIdxdenibodegasprecio
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
			echo $e;
            return -1;
        }
    }
	
	
	
	public static function getByIdxdenexpo($tabla,$clavexd)
    {
        // Consulta de la tabla inventario que saca la clave de producto
		
        $consulta = "SELECT ".$tabla.".producto,".$tabla.".diseno,".$tabla.".producto1,".$tabla.".descripcio,".$tabla.".medidas,".$tabla.".hm,gproducto.precio1,gproducto.precio2,gproducto.precio3,gproducto.pathima1,gproducto.desa FROM ".$tabla." INNER JOIN gproducto WHERE ".$tabla.".producto=gproducto.producto AND ".$tabla.".producto1=?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($clavexd));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
			echo $e;
            return -1;
        }
    }

	//*-*-**-*-*-*-*-*--*--*-*-
	//PARA CLAVE LARGA PRODUCTO
	public static function getByIdxdenexpoLG($tabla,$clavexd)
    {
        // Consulta de la tabla inventario que saca la clave de producto
		
        $consulta = "SELECT ".$tabla.".producto,".$tabla.".diseno,".$tabla.".producto1,".$tabla.".descripcio,".$tabla.".medidas,".$tabla.".hm,gproducto.precio1,gproducto.precio2,gproducto.precio3,gproducto.pathima1,gproducto.desa FROM ".$tabla." INNER JOIN gproducto WHERE ".$tabla.".producto=gproducto.producto AND ".$tabla.".producto=?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($clavexd));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
			echo $e;
            return -1;
        }
    }

	
	//*-*-*-*-*-*--*--*-*-*-*-
	public static function getByIdxdenexpoybodegas2($tabla,$clavexd)
    {
        // Consulta de la tabla inventario que saca la clave de producto
		
        $consulta = "SELECT gproducto.producto1,".$tabla.".descripcio,".$tabla.".diseno, gproducto.medidas,".$tabla.".hm,gproducto.precio1,gproducto.precio2,gproducto.precio3,gproducto.pathima1, gproducto.desa FROM ".$tabla." INNER JOIN ibodegas INNER JOIN gproducto on gproducto.producto=".$tabla.".producto where ".$tabla.".producto = gproducto.producto AND ".$tabla.".producto =?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($clavexd));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
			echo $e;
            return -1;
        }
    }

	
	public static function getByIdxdenexpoybodegas($tabla,$clavexd)
    {
        // Consulta de la tabla inventario que saca la clave de producto
		
        $consulta = "SELECT ibodegas.producto1,".$tabla.".descripcio,".$tabla.".diseno, ibodegas.medidas,ibodegas.hm,gproducto.precio1,gproducto.precio2,gproducto.precio3,gproducto.pathima1, gproducto.desa FROM ".$tabla." INNER JOIN ibodegas INNER JOIN gproducto on ibodegas.producto=".$tabla.".producto where ".$tabla.".producto = gproducto.producto AND ".$tabla.".producto1 =?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($clavexd));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
			echo $e;
            return -1;
        }
    }

	
	public static function getByIdxdfuera($clavexd)
    {
        // Consulta de la tabla inventario que saca la clave de producto
		
        $consulta = "SELECT spock418.diseno,spock418.producto1,spock418.descripcio,spock418.medidas,spock418.hm,gproducto.precio1,gproducto.precio2,gproducto.precio3,gproducto.pathima1 FROM spock418 INNER JOIN gproducto WHERE spock418.producto=gproducto.producto AND spock418.producto1=?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($clavexd));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
			echo $e;
            return -1;
        }
    }
	
	
	
	
	
	/*.............................................................................................................................*/
	
	/**********************************************************************************************************************************************/
	//----------------------------------------------------------------------------------------------------------------
	//esta es para una cadena de conexion de claves 
	
	public static function getByIdinventario($clave)
    {
        // Consulta de la tabla inventario que saca la clave de producto
		
        $consulta = "SELECT * FROM inventario WHERE clave = ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($clave));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	
	/*.............................................................................................................................*/
	
	/**********************************************************************************************************************************************/
	//----------------------------------------------------------------------------------------------------------------
	//esta es para una cadena de conexion de claves 
	
	public static function getByIdinventariosub($idsubcategoria)
    {
        // Consulta de la tabla inventario que saca la clave de producto
		
        $consulta = "SELECT * FROM inventario WHERE idsubcategoria = ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($idsubcategoria));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	
	/*.............................................................................................................................*/
	
	
	
	
	/**********************************************************************************************************************************************/
	//----------------------------------------------------------------------------------------------------------------
	//esta es para una cadena de conexion de claves 
	
	public static function getByIdxddetalle($clavexd)
    {
        // Consulta de la tabla inventario que saca la clave de producto
		
        $consulta = "SELECT * FROM detallepedido WHERE clave = ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($clavexd));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
			
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	
	/*.............................................................................................................................*/
	  /**
     * Actualiza un registro de la bases de datos basado
     * en los nuevos valores relacionados con un identificador
     *********************************************************************************************************************************************************/
	 
   public static function updateSesion(
        $pass,
        $sesion
    )
    {
        // Creando consulta UPDATE
        $consulta = "UPDATE user" .
            " SET sesion=? " .
            "WHERE pass=?";

        // Preparar la sentencia
        $cmd = Database::getInstance()->getDb()->prepare($consulta);

        // Relacionar y ejecutar la sentencia
        $cmd->execute(array($sesion, $pass));

        return $cmd;
    }
//**************************************************************************************************************************************************************
  

  /*.............................................................................................................................*/
	  /**
     * Actualiza un registro de la bases de datos basado
     * en los nuevos valores relacionados con un identificador
     *********************************************************************************************************************************************************/
	 
   public static function updatePedidoestatusx(
        $idpedido,
		$estatus,
        $anticipo2
    )
    {
        // Creando consulta UPDATE
        $consulta = "UPDATE pedido" .
            " SET estatus=? ," .
			" anticipo2=? " .
            "WHERE idpedido=?";

        // Preparar la sentencia
        $cmd = Database::getInstance()->getDb()->prepare($consulta);

        // Relacionar y ejecutar la sentencia
        $cmd->execute(array($estatus, $anticipo2, $idpedido));

        return $cmd;
    }
	
	
	public static function updatePedidoestatusxEXPOENTREGA(
        $idpedido,
        $entregado
    )
    {
        // Creando consulta UPDATE
        $consulta = "UPDATE pedido" .
            " SET entregado=? " .
            "WHERE idpedido=?";

        // Preparar la sentencia
        $cmd = Database::getInstance()->getDb()->prepare($consulta);

        // Relacionar y ejecutar la sentencia
        $cmd->execute(array($entregado, $idpedido));

        return $cmd;
    }
//**************************************************************************************************************************************************************
  


  /*.............................................................................................................................*/
	  /**
     * Actualiza un registro de la bases de datos basado
     * en los nuevos valores relacionados con un identificador
     *********************************************************************************************************************************************************/
	 
   public static function updatepedidoAinventario(
        $clave,
		$stock
    )
    {
        // Creando consulta UPDATE
        $consulta = "UPDATE inventario" .
            " SET stock=? " .
            "WHERE clave=?";

        // Preparar la sentencia
        $cmd = Database::getInstance()->getDb()->prepare($consulta);

        // Relacionar y ejecutar la sentencia
        $cmd->execute(array($stock, $clave));

        return $cmd;
    }
//**************************************************************************************************************************************************************
  



  /*.............................................................................................................................*/
	  /**
     * Actualiza un registro de la bases de datos basado
     * en los nuevos valores relacionados con un identificador
     *********************************************************************************************************************************************************/
	 
   public static function updatepedidoAdetallexpieza(
        $iddetallepedido,
		$cantidad
    )
    {
        // Creando consulta UPDATE
        $consulta = "UPDATE detallepedido" .
            " SET estatus=? " .
            "WHERE iddetallepedido=?";

        // Preparar la sentencia
        $cmd = Database::getInstance()->getDb()->prepare($consulta);

        // Relacionar y ejecutar la sentencia
        $cmd->execute(array($cantidad, $iddetallepedido));

        return $cmd;
    }
//**************************************************************************************************************************************************************
  
/*.............................................................................................................................*/
	  /**
     * Actualiza un registro de la bases de datos basado
     * en los nuevos valores relacionados con un identificador
     *********************************************************************************************************************************************************/
	 
   public static function updatepedidototalpagar(
        $idpedido,
		$total_pagar
    )
    {
        // Creando consulta UPDATE
        $consulta = "UPDATE pedido" .
            " SET total_pagar=? " .
            "WHERE idpedido=?";

        // Preparar la sentencia
        $cmd = Database::getInstance()->getDb()->prepare($consulta);

        // Relacionar y ejecutar la sentencia
        $cmd->execute(array($total_pagar, $idpedido));

        return $cmd;
    }
//**************************************************************************************************************************************************************
  

  
   public static function updatepedidoAestatus(
        $idpedido,
		$estatus
    )
    {
        // Creando consulta UPDATE
        $consulta = "UPDATE pedido" .
            " SET estatus=? " .
            "WHERE idpedido=?";

        // Preparar la sentencia
        $cmd = Database::getInstance()->getDb()->prepare($consulta);

        // Relacionar y ejecutar la sentencia
        $cmd->execute(array($estatus, $idpedido));

        return $cmd;
    }
//**************************************************************************************************************************************************************
  


   public static function updateinventario(
        $tabla,
		$producto1,
        $hm
    )
    {
        // Creando consulta UPDATE
        $consulta = "UPDATE ".$tabla." SET hm=? " .
            "WHERE producto1=?";

        // Preparar la sentencia
        $cmd = Database::getInstance()->getDb()->prepare($consulta);

        // Relacionar y ejecutar la sentencia
        $cmd->execute(array($hm, $producto1));

        return $cmd;
    }
//**************************************************************************************************************************************************************
  

   /**
     * Insertar un nuevo Alumno
     *
     * @param $nombre      nombre del nuevo registro
     * @param $direccion dirección del nuevo registro
     * @return PDOStatement
     */
    
	
	//***********************************************************************************************************************************************************************
	////////////////////////////////FUNCION INSERT CLIENTE///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static function insert(
        $id_cliente,
        $nom_cliente,
		$ap_cliente,
		$dir_cliente,
		$tel_cliente,
		$correo_cliente,
		$rfc_cliente,
		$tipopersona,
		$usocfdi		
    )
	
    {
        // Sentencia INSERT
        $comando = "INSERT INTO clientes ( " .
            "id_cliente," .
			"nom_cliente," .
			"ap_cliente," .
			"dir_cliente," .
			"tel_cliente," .
			"correo_cliente," .
			"rfc_cliente," .
			"tipopersona," .
            " usocfdi)" .
            " VALUES( ?,?,?,?,?,?,?,?,?)";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(
            array(
                $id_cliente,
				$nom_cliente,
				$ap_cliente,
				$dir_cliente,
				$tel_cliente,
				$correo_cliente,
				$rfc_cliente,
				$tipopersona,
				$usocfdi		
            )
        );

    }

	//*-*-**-*-*-*-*-*-*-*-*-**--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*9-*-*-*-*-*-*-*-*-*
	
	public static function insertqrcliente(
        $id_cliente,
        $nom_cliente,
		$tel_cliente,
		$correo_cliente,
		$empresa		
    )
	
    {
        // Sentencia INSERT
        $comando = "INSERT INTO clientes ( " .
            "id_cliente," .
			"nom_cliente," .
			"tel_cliente," .
			"correo_cliente," .
			" empresa)" .
            " VALUES( ?,?,?,?,?)";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(
            array(
                $id_cliente,
				$nom_cliente,
				$tel_cliente,
				$correo_cliente,
				$empresa		
            )
        );

    }

	
	
	
	
	
	
	//***********************************************************************************************************************************************************************
	////////////////////////////////FUNCION INSERT PEDIDOSSSSSSSS///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static function insertpedido(
        $idpedido,
        $idcliente,
		$fecha,
		$idmetodopago,
		$idmetodopago2,
		$idmetodopago3,
		$observaciones,
		$idusuario,
		$pedidos,
		$idexpo,
		$estatus,
		$anticipo,
		$anticipo2,
		$anticipo3,	
		$total_pagar,
		$entregado
    )
	
    {
        // Sentencia INSERT
        $comando = "INSERT INTO pedido ( " .
            "idpedido," .
			"idcliente," .
			"fecha," .
			"idmetodopago," .
			"idmetodopago2," .
			"idmetodopago3," .
			"observaciones," .
			"idusuario," .
			"pedidos," .
			"idexpo," .
			"estatus," .
			"anticipo," .
			"anticipo2," .
			"anticipo3," .
			"total_pagar," .
			"entregado)" .
            " VALUES( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(
            array(
                $idpedido,
				$idcliente,
				$fecha,
				$idmetodopago,
				$idmetodopago2,
				$idmetodopago3,
				$observaciones,
				$idusuario,
				$pedidos,
				$idexpo,
				$estatus,
				$anticipo,
				$anticipo2,
				$anticipo3,
				$total_pagar,
				$entregado
            )
        );


    }
//******************************************************************************************************
//***********************************************************************************************************************************************************************
	////////////////////////////////FUNCION INSERT COTIZACION///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static function insertCOTIZACION(
        $idcotiza,
        $numerocotizacion,
		$nombre,
		$apellido,
		$telefono,
		$correo,
		$idexpo,
		$total,
		$idusuario,
		$fecha		
    )
	
    {
        // Sentencia INSERT
        $comando = "INSERT INTO cotiza ( " .
            "idcotiza," .
			"numerocotizacion," .
			"nombre," .
			"apellido," .
			"telefono," .
			"correo," .
			"idexpo," .
			"total," .
			"idusuario," .
			"fecha)" .
            " VALUES( ?,?,?,?,?,?,?,?,?,?)";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(
            array(
                $idcotiza,
				$numerocotizacion,
				$nombre,
				$apellido,
				$telefono,
				$correo,
				$idexpo,
				$total,
				$idusuario,
				$fecha	
            )
        );

    }
//******************************************************************************************************

//***********************************************************************************************************************************************************************
	////////////////////////////////FUNCION INSERT DETTALLES DE PEDIDOSSSSSSSS///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static function insertdetallespedido(
        $iddetallepedido,
        $idpedido,
		$clave,
		$clave2,
		$cantidad,
		$preciovendido,
		$estatus
		
    )
	
    {
        // Sentencia INSERT
        $comando = "INSERT INTO detallepedido ( " .
            "iddetallepedido," .
			"idpedido," .
			"clave," .
			"clave2," .
			"cantidad," .
			"preciovendido," .
			"estatus)".
            " VALUES( ?,?,?,?,?,?,?)";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(
            array(
                $iddetallepedido,
				$idpedido,
				$clave,
				$clave2,
				$cantidad,
				$preciovendido,
				$estatus
            )
        );

    }
//******************************************************************************************************


//***********************************************************************************************************************************************************************
	////////////////////////////////FUNCION INSERT DETTALLES DE PEDIDOSSSSSSSS///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static function insertdetallescotizar(
        $iddetallecotiza,
        $idcotiza,
		$clave,
		$cantidad,
		$precio
    )
	
    {
        // Sentencia INSERT
        $comando = "INSERT INTO detallecotiza ( " .
            "iddetallecotiza," .
			"idcotiza," .
			"clave," .
			"cantidad," .
			"precio)".
            " VALUES( ?,?,?,?,?)";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(
            array(
                $iddetallecotiza,
				$idcotiza,
				$clave,
				$cantidad,
				$precio
            )
        );

    }
//******************************************************************************************************

    /**
     * Eliminar el registro con el identificador especificado
     *
     * @param $idAlumno identificador de la tabla Alumnos
     * @return bool Respuesta de la eliminación
     */
    public static function delete($iddetallepedido)
    {
        // Sentencia DELETE
        $comando = "DELETE FROM detallepedido WHERE iddetallepedido=?";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(array($iddetallepedido));
    }

	 public static function bg($passwordxd)
    {
        // Consulta de la tabla Alumnos
//      $consulta = "SELECT ibodegas.bodega1,ibodegas.bodega2,ibodegas.bodega3,ibodegas.diseno,ibodegas.producto1,ibodegas.descripcio,ibodegas.medidas,ibodegas.hm,gproducto.pathima1,gproducto.precio1,gproducto.precio2, gproducto.precio3 FROM ibodegas INNER JOIN gproducto WHERE ibodegas.producto=gproducto.producto AND ibodegas.hm>0 AND ibodegas.fulldescrip LIKE ?";
        $consulta = "SELECT ibodegas.bodega1,ibodegas.bodega2,ibodegas.bodega3,ibodegas.diseno,ibodegas.producto1,ibodegas.descripcio,ibodegas.medidas,ibodegas.hm,gproducto.pathima1,gproducto.precio1,gproducto.precio2, gproducto.precio3 FROM ibodegas INNER JOIN gproducto WHERE ibodegas.producto=gproducto.producto and ibodegas.hm>0 AND ibodegas.descripcio LIKE ? ";
	//$consulta = "SELECT * from ibodegas inner join gproducto ON producto=ibodegas.producto where ibodegas.hm>0 and ibodegas.descripcio like ? ";
	
	try {
			//echo $passwordxd;
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array('%'.$passwordxd.'%'));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	
	

	public static function getByIdClave($clave)
	{		
        $consulta = "SELECT producto,descripcio,medidas,precio1,precio2,precio3,precio4,precio5 FROM producto WHERE producto= ? LIMIT 1";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($clave));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
	
	public static function busquedaGlobal($descripcio,$diseno,$largo1,$largo2,$ancho1,$ancho2)
	{
		$consulta="NADA";
		//Checa si viene vacia alguna medida del rango
		
		if (!empty($descripcio)){
			$medidas=1;
		}else{
			$medidas=0;	
		}
		$medidas=0;
		if (!empty($largo1)){
			$medidas=1;
			$largo1=$largo1-.01;
		}else{
			$medidas=0;	
		}
		if (!empty($largo2)){
			$medidas=1;
			$largo2=$largo2+.01;
		}else{
			$medidas=0;	
		}
		if (!empty($ancho1)){
			$medidas=1;
			$ancho1=$ancho1-.01;
		}else{
			$medidas=0;	
		}
		if (!empty($ancho2)){
			$medidas=1;
			$ancho2=$ancho2+.01;
		}else{
			$medidas=0;	
		}
		
		//Busqueda solo por descripcion
		if(!empty($descripcio) && empty($diseno) && empty($medidas)){
			//SELECT *,_al.descripcio as desalmacen FROM gspock INNER JOIN gproducto as gp INNER JOIN almacenes AS _al
			//$consulta = "SELECT * FROM gspock INNER JOIN gproducto as gp WHERE gspock.producto=gp.producto AND gspock.descripcio LIKE ? AND gspock.hm>0 ORDER BY hm DESC";
			//$consulta = "SELECT *,gspock.descripcio as despro, almacenes.descripcio as desalmacen FROM gspock INNER JOIN gproducto ON gproducto.producto=gspock.producto INNER JOIN almacenes ON almacenes.almacen=gspock.almacen WHERE gspock.descripcio LIKE ? AND gspock.hm>0 ORDER BY gspock.hm DESC";
			$consulta = "SELECT gspock.producto1,gspock.medidas,hm,pathima1,gspock.diseno,precio1,precio2,precio3,gspock.descripcio as despro, almacenes.descripcio as desalmacen FROM gspock INNER JOIN gproducto ON gproducto.producto=gspock.producto INNER JOIN almacenes ON almacenes.almacen=gspock.almacen WHERE gspock.descripcio LIKE ? AND gspock.hm>0 ORDER BY gspock.diseno,gspock.almacen,gspock.hm DESC";
		}

		//Busqueda por descripcion y diseno, sin medidas
		if(!empty($descripcio) && !empty($diseno) && empty($medidas)){
			//$consulta = "SELECT * FROM gspock INNER JOIN gproducto WHERE gproducto.producto=gspock.producto AND gspock.hm>0 AND gspock.descripcio LIKE ? AND gspock.diseno LIKE ?";
			//$consulta = "SELECT * FROM gspock INNER JOIN gproducto as gp WHERE gspock.producto=gp.producto AND gspock.descripcio LIKE ? AND gspock.diseno LIKE ? AND gspock.hm>0 ORDER BY hm DESC";
			//$consulta = "SELECT *,gspock.descripcio as despro, almacenes.descripcio as desalmacen FROM gspock INNER JOIN gproducto ON gproducto.producto=gspock.producto INNER JOIN almacenes ON almacenes.almacen=gspock.almacen WHERE gspock.descripcio LIKE ? AND gspock.diseno LIKE ? AND gspock.hm>0 ORDER BY hm DESC";
			$consulta = "SELECT gspock.producto1,gspock.medidas,hm,pathima1,gspock.diseno,precio1,precio2,precio3,gspock.descripcio as despro, almacenes.descripcio as desalmacen FROM gspock INNER JOIN gproducto ON gproducto.producto=gspock.producto INNER JOIN almacenes ON almacenes.almacen=gspock.almacen WHERE gspock.descripcio LIKE ? AND gspock.diseno LIKE ? AND gspock.hm>0 ORDER BY gspock.diseno,gspock.almacen,gspock.hm DESC";
		}
		
		//Busqueda sin descripcion con diseno, sin medidas
		if(empty($descripcio) && !empty($diseno) && empty($medidas)){
			//$consulta = "SELECT * FROM gspock INNER JOIN gproducto WHERE gproducto.producto=gspock.producto AND gspock.hm>0 AND gspock.diseno LIKE ?";
			//$consulta = "SELECT * FROM gspock INNER JOIN gproducto as gp WHERE gspock.producto=gp.producto AND gspock.diseno LIKE ? AND gspock.hm>0 ORDER BY hm DESC";
			//$consulta = "SELECT *,gspock.descripcio as despro, almacenes.descripcio as desalmacen FROM gspock INNER JOIN gproducto ON gproducto.producto=gspock.producto INNER JOIN almacenes ON almacenes.almacen=gspock.almacen WHERE gspock.diseno LIKE ? AND gspock.hm>0 ORDER BY hm DESC";
			$consulta = "SELECT gspock.producto1,gspock.medidas,hm,pathima1,gspock.diseno,precio1,precio2,precio3,gspock.descripcio as despro, almacenes.descripcio as desalmacen FROM gspock INNER JOIN gproducto ON gproducto.producto=gspock.producto INNER JOIN almacenes ON almacenes.almacen=gspock.almacen WHERE  gspock.diseno LIKE ? AND gspock.hm>0 ORDER BY gspock.diseno,gspock.almacen,gspock.hm DESC";
		}

		//Busqueda sin descripcion con diseno, con medidas
		if(empty($descripcio) && !empty($diseno) && !empty($medidas)){
			//$consulta = "SELECT * FROM gspock INNER JOIN gproducto WHERE gproducto.producto=gspock.producto AND gspock.hm>0 AND gspock.diseno LIKE ? AND gspock.largo BETWEEN ? AND ? AND gspock.ancho BETWEEN ? AND ?";
			//$consulta = "SELECT * FROM gspock INNER JOIN gproducto as gp WHERE gspock.producto=gp.producto AND gspock.diseno LIKE ? AND gspock.largo BETWEEN ? AND ? AND gspock.ancho BETWEEN ? AND ? AND gspock.hm>0 ORDER BY hm DESC";
			//$consulta = "SELECT *,gspock.descripcio as despro, almacenes.descripcio as desalmacen FROM gspock INNER JOIN gproducto ON gproducto.producto=gspock.producto INNER JOIN almacenes ON almacenes.almacen=gspock.almacen WHERE gspock.diseno LIKE ? AND gspock.largo BETWEEN ? AND ? AND gspock.ancho BETWEEN ? AND ? AND gspock.hm>0 ORDER BY hm DESC";
			$consulta = "SELECT gspock.producto1,gspock.medidas,hm,pathima1,gspock.diseno,precio1,precio2,precio3,gspock.descripcio as despro, almacenes.descripcio as desalmacen FROM gspock INNER JOIN gproducto ON gproducto.producto=gspock.producto INNER JOIN almacenes ON almacenes.almacen=gspock.almacen WHERE gspock.diseno LIKE ? AND gspock.largo BETWEEN ? AND ? AND gspock.ancho BETWEEN ? AND ? AND gspock.hm>0 ORDER BY gspock.diseno,gspock.almacen,gspock.hm DESC";
		}

		//Busqueda con descripcion sin diseno, con medidas
		if(!empty($descripcio) && empty($diseno) && !empty($medidas)){
			//$consulta = "SELECT * FROM gspock INNER JOIN gproducto WHERE gproducto.producto=gspock.producto AND gspock.hm>0 AND gspock.diseno LIKE ? AND gspock.largo BETWEEN ? AND ? AND gspock.ancho BETWEEN ? AND ?";
			//$consulta = "SELECT * FROM gspock INNER JOIN gproducto as gp WHERE gspock.producto=gp.producto AND gspock.diseno LIKE ? AND gspock.largo BETWEEN ? AND ? AND gspock.ancho BETWEEN ? AND ? AND gspock.hm>0 ORDER BY hm DESC";
			//$consulta = "SELECT *,gspock.descripcio as despro, almacenes.descripcio as desalmacen FROM gspock INNER JOIN gproducto ON gproducto.producto=gspock.producto INNER JOIN almacenes ON almacenes.almacen=gspock.almacen WHERE gspock.descripcio LIKE ? AND gspock.largo BETWEEN ? AND ? AND gspock.ancho BETWEEN ? AND ? AND gspock.hm>0 ORDER BY hm DESC";
			$consulta = "SELECT gspock.producto1,gspock.medidas,hm,pathima1,gspock.diseno,precio1,precio2,precio3,gspock.descripcio as despro, almacenes.descripcio as desalmacen FROM gspock INNER JOIN gproducto ON gproducto.producto=gspock.producto INNER JOIN almacenes ON almacenes.almacen=gspock.almacen WHERE gspock.descripcio LIKE ? AND gspock.largo BETWEEN ? AND ? AND gspock.ancho BETWEEN ? AND ? AND gspock.hm>0 ORDER BY gspock.diseno,gspock.almacen,gspock.hm DESC";
		}
		
		//Busqueda solo con medidas
		if(empty($descripcio) && empty($diseno) && !empty($medidas)){
			//$consulta = "SELECT * FROM gspock INNER JOIN gproducto WHERE gproducto.producto=gspock.producto AND gspock.hm>0 AND gspock.descripcio LIKE ? AND gspock.diseno LIKE ? AND gspock.largo BETWEEN ? AND ? AND gspock.ancho BETWEEN ? AND ?";
			//$consulta = "SELECT * FROM gspock INNER JOIN gproducto as gp WHERE gspock.producto=gp.producto AND gspock.descripcio LIKE ? AND gspock.diseno LIKE ? AND gspock.largo BETWEEN ? AND ? AND gspock.ancho BETWEEN ? AND ? AND gspock.hm>0 ORDER BY hm DESC";
			$consulta = "SELECT gspock.producto1,gspock.medidas,hm,pathima1,gspock.diseno,precio1,precio2,precio3,gspock.descripcio as despro, almacenes.descripcio as desalmacen FROM gspock INNER JOIN gproducto ON gproducto.producto=gspock.producto INNER JOIN almacenes ON almacenes.almacen=gspock.almacen WHERE gspock.largo BETWEEN ? AND ? AND gspock.ancho BETWEEN ? AND ? AND gspock.hm>0 ORDER BY gspock.diseno,gspock.almacen,gspock.hm DESC";
		}

		//Busqueda con descripcion con diseno con medidas
		if(!empty($descripcio) && !empty($diseno) && !empty($medidas)){
			//$consulta = "SELECT * FROM gspock INNER JOIN gproducto WHERE gproducto.producto=gspock.producto AND gspock.hm>0 AND gspock.descripcio LIKE ? AND gspock.diseno LIKE ? AND gspock.largo BETWEEN ? AND ? AND gspock.ancho BETWEEN ? AND ?";
			//$consulta = "SELECT * FROM gspock INNER JOIN gproducto as gp WHERE gspock.producto=gp.producto AND gspock.descripcio LIKE ? AND gspock.diseno LIKE ? AND gspock.largo BETWEEN ? AND ? AND gspock.ancho BETWEEN ? AND ? AND gspock.hm>0 ORDER BY hm DESC";
			//$consulta = "SELECT *,gspock.descripcio as despro, almacenes.descripcio as desalmacen FROM gspock INNER JOIN gproducto ON gproducto.producto=gspock.producto INNER JOIN almacenes ON almacenes.almacen=gspock.almacen WHERE gspock.descripcio LIKE ? AND gspock.diseno LIKE ? AND gspock.largo BETWEEN ? AND ? AND gspock.ancho BETWEEN ? AND ? AND gspock.hm>0 ORDER BY hm DESC";
			$consulta = "SELECT gspock.producto1,gspock.medidas,hm,pathima1,gspock.diseno,precio1,precio2,precio3,gspock.descripcio as despro, almacenes.descripcio as desalmacen FROM gspock INNER JOIN gproducto ON gproducto.producto=gspock.producto INNER JOIN almacenes ON almacenes.almacen=gspock.almacen WHERE gspock.descripcio LIKE ? AND gspock.diseno LIKE ? AND gspock.largo BETWEEN ? AND ? AND gspock.ancho BETWEEN ? AND ? AND gspock.hm>0 ORDER BY gspock.diseno,gspock.almacen,gspock.hm DESC";
		}
		
		
		//echo $consulta;
		//echo "<br>";
		try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);

			//Busqueda solo por descripcion
			if(!empty($descripcio) && empty($diseno) && empty($medidas)){
				//$comando->execute(array('%'.$descripcio.'%'));	
				$comando->execute(array($descripcio.'%'));
			}

			//Busqueda por descripcion y diseno, sin medidas
			if(!empty($descripcio) && !empty($diseno) && empty($medidas)){
				//$comando->execute(array('%'.$descripcio.'%','%'.$diseno.'%'));
				$comando->execute(array($descripcio.'%','%'.$diseno.'%'));
			}
			
			//Busqueda sin descripcion con diseno, sin medidas (mmmm mira la cara con que te ve conan)
			if(empty($descripcio) && !empty($diseno) && empty($medidas)){
				//$comando->execute(array('%'.$diseno.'%'));
				$comando->execute(array($diseno.'%'));
			}

			//Busqueda com descripcion sin diseno, con medidas
			if(!empty($descripcio) && empty($diseno) && !empty($medidas)){
				//$comando->execute(array('%'.$diseno.'%'));
				$comando->execute(array($descripcio.'%',$largo1,$largo2,$ancho1,$ancho2));
			}

			//Busqueda sin descripcion con diseno con medidas
			if(empty($descripcio) && !empty($diseno) && !empty($medidas)){
				$comando->execute(array('%'.$diseno.'%',$largo1,$largo2,$ancho1,$ancho2));
			}

			//Busqueda solo con medidas
			if(empty($descripcio) && empty($diseno) && !empty($medidas)){
				$comando->execute(array($largo1,$largo2,$ancho1,$ancho2));
			}


			//Busqueda con descripcion con diseno con medidas
			if(!empty($descripcio) && !empty($diseno) && !empty($medidas)){
				//$comando->execute(array('%'.$descripcio.'%','%'.$diseno.'%',$largo1,$largo2,$ancho1,$ancho2));
				$comando->execute(array($descripcio.'%','%'.$diseno.'%',$largo1,$largo2,$ancho1,$ancho2));
			}
			
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;
			
			//Cierra conexion a base de datos
			mysqli::close;

        } catch (PDOException $e) {
            return -1;
        }
    }

}	
?>
