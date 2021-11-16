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
        $consulta = "SELECT * FROM 	solicitudes WHERE activa = 3";
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
	
	 public static function getByIdcliente($passwordxd)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT * FROM clientes WHERE nom_cliente = ?";

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
	public static function getByIdclienterfc($passwordxd)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT * FROM clientes WHERE rfc_cliente = ?";

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
		
        $consulta = "SELECT * FROM inventario WHERE clave = ?";

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
            $row = $comando->fetch(PDO::FETCH_ASSOC);
			
			
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
     *
     * @param $idAlumno      identificador
     * @param $nombre      nuevo nombre
     * @param $direccion nueva direccion
     
     *********************************************************************************************************************************************************/
	 
    public static function updateinventario(
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

	
	
	//***********************************************************************************************************************************************************************
	////////////////////////////////FUNCION INSERT PEDIDOSSSSSSSS///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static function insertpedido(
        $idpedido,
        $idcliente,
		$fecha,
		$idmetodopago,
		$observaciones,
		$idusuario,
		$pedidos		
    )
	
    {
        // Sentencia INSERT
        $comando = "INSERT INTO pedido ( " .
            "idpedido," .
			"idcliente," .
			"fecha," .
			"idmetodopago," .
			"observaciones," .
			"idusuario," .
			"pedidos)" .
            " VALUES( ?,?,?,?,?,?,?)";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(
            array(
                $idpedido,
				$idcliente,
				$fecha,
				$idmetodopago,
				$observaciones,
				$idusuario,
				$pedidos		
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
		$cantidad		
    )
	
    {
        // Sentencia INSERT
        $comando = "INSERT INTO detallepedido ( " .
            "iddetallepedido," .
			"idpedido," .
			"clave," .
			"cantidad)" .
            " VALUES( ?,?,?,?)";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(
            array(
                $iddetallepedido,
				$idpedido,
				$clave,
				$cantidad		
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
    public static function delete($idAlumno)
    {
        // Sentencia DELETE
        $comando = "DELETE FROM alumnos WHERE idalumno=?";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(array($idAlumno));
    }
	
}

?>