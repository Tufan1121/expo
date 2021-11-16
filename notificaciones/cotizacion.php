<?php
require 'solicitudes.php';
require('pdf/fpdf.php');
    require '../../procedim/mysql_login.php';
// Vamos a pasar una variable $_GET a nuestro ejemplo, en este caso es
// 'aid' para 'actor_id' de nuestra base de datos Sakila. Le vamos a asignar un
// valor predeterminado de 1, y a amoldarla a un integer para evitar inyecciones
// de SQL y/o problemas de seguridad relacionados. El manejo de todo esto va más
// allá del alcance de este sencillo ejemplo:
//   http://example.org/script.php?aid=42

if (isset($_GET['cotiza'])) {
    $aid = $_GET['cotiza'];
} else {
    $aid = 1;
}

 //$aid = "EGI20184880";

// Conectarse a y seleccionar una base de datos de MySQL llamada sakila
// Nombre de host: 127.0.0.1, nombre de usuario: tu_usuario, contraseña: tu_contraseña, bd: sakila
//$mysqli = new mysqli('LOCALHOST', 'root', 'root', 'kaisen');
//$mysqli2 = new mysqli('LOCALHOST', 'root', 'root', 'kaisen');
//$mysqli3 = new mysqli('LOCALHOST', 'root', 'root', 'kaisen');

$mysqli = new mysqli(HOSTNAME,USERNAME,PASSWORD,DATABASE);
$mysqli2 = new mysqli(HOSTNAME,USERNAME,PASSWORD,DATABASE);
$mysqli3 = new mysqli(HOSTNAME,USERNAME,PASSWORD,DATABASE);

//$mysqli = new mysqli('LOCALHOST', 'tapetest_expo', 'Nq37.321.1', 'tapetest_notificaciones');
//$mysqli2 = new mysqli('LOCALHOST', 'tapetest_expo', 'Nq37.321.1', 'tapetest_notificaciones');
//$mysqli3 = new mysqli('LOCALHOST', 'tapetest_expo', 'Nq37.321.1', 'tapetest_notificaciones');

//*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*--/*-*-*-*-*-*-*-9*-*9-*-*-*-*-**-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*+

// ¡Oh, no! Existe un error 'connect_errno', fallando así el intento de conexión
if ($mysqli->connect_errno) {
    // La conexión falló. ¿Que vamos a hacer? 
    // Se podría contactar con uno mismo (¿email?), registrar el error, mostrar una bonita página, etc.
    // No se debe revelar información delicada

    // Probemos esto:
    echo "Lo sentimos, este sitio web está experimentando problemas.";

    // Algo que no se debería de hacer en un sitio público, aunque este ejemplo lo mostrará
    // de todas formas, es imprimir información relacionada con errores de MySQL -- se podría registrar
    echo "Error: Fallo al conectarse a MySQL debido a: \n";
    echo "Errno: " . $mysqli->connect_errno . "\n";
    echo "Error: " . $mysqli->connect_error . "\n";
    
    // Podría ser conveniente mostrar algo interesante, aunque nosotros simplemente saldremos
    exit;
}

// Realizar una consulta SQL
$sql = "SELECT * FROM cotiza inner join expo where cotiza.numerocotizacion = '$aid'";
if (!$resultado = $mysqli->query($sql)) {
    // ¡Oh, no! La consulta falló. 
    echo "Lo sentimos, este sitio web está experimentando problemas.";

    // De nuevo, no hacer esto en un sitio público, aunque nosotros mostraremos
    // cómo obtener información del error
    echo "Error: La ejecución de la consulta falló debido a: \n";
    echo "Query: " . $sql . "\n";
    echo "Errno: " . $mysqli->errno . "\n";
    echo "Error: " . $mysqli->error . "\n";
    exit;
}

// ¡Uf, lo conseguimos!. Sabemos que nuestra conexión a MySQL y nuestra consulta
// tuvieron éxito, pero ¿tenemos un resultado?
if ($resultado->num_rows === 0) {
    // ¡Oh, no ha filas! Unas veces es lo previsto, pero otras
    // no. Nosotros decidimos. En este caso, ¿podría haber sido
    // actor_id demasiado grande? 
    echo "Lo sentimos. NOOOOo se pudo encontrar una coincidencia para el ID $aid. Inténtelo de nuevo.";
    exit;
}

// Ahora, sabemos que existe solamente un único resultado en este ejemplo, por lo
// que vamos a colocarlo en un array asociativo donde las claves del mismo son los
// nombres de las columnas de la tabla
$tablapedido = $resultado->fetch_assoc();
//echo "observaciones " . $actor['observaciones'] . " " . $actor['pedidos'] . " .";
	$fechadeevento=$tablapedido['fecha'];
	$pedidoxcleinte=$tablapedido['numerocotizacion'];
	//$idcliente=$tablapedido['idcliente'];
	$idpedido=$tablapedido['idcotiza'];
	//$observaciones=$tablapedido['observaciones'];
	$evento=$tablapedido['nom_expo'];
	$nombreclientecotiza=$tablapedido["nombre"];
	$nombreappellido=$tablapedido["apellido"];
	$telefonocotiza=$$tablapedido["telefono"];
	$correocotiza=$tablapedido["correo"];
	
	//$anticipo=$tablapedido['anticipo'];
	
// Ahora, vamor a obtener cinco actores aleatorios y a imprimir sus nombres en una lista.
// El manejo de errores va a ser menor aquí, aunque ya sabemos como hacerlo
/*
$sql = "SELECT actor_id, first_name, last_name FROM actor ORDER BY rand() LIMIT 5";
if (!$resultado = $mysqli->query($sql)) {
    echo "Lo sentimos, este sitio web está experimentando problemas.";
    exit;
}

// Imprimir nuestros cinco actores aleatorios en una lista, y enlazar cada uno
echo "<ul>\n";
while ($actor = $resultado->fetch_assoc()) {
    echo "<li><a href='" . $_SERVER['SCRIPT_FILENAME'] . "?aid=" . $actor['actor_id'] . "'>\n";
    echo $actor['first_name'] . ' ' . $actor['last_name'];
    echo "</a></li>\n";
}
echo "</ul>\n";
*/
// El script automáticamente liberará el resultado y cerrará la conexión
// a MySQL cuando finalice, aunque aquí lo vamos a hacer nostros mismos


// Realizar una consulta SQL

// Realizar una consulta SQL
$sql = "SELECT * FROM detallecotiza WHERE idcotiza = $idpedido";
if (!$resultado = $mysqli->query($sql)) {
    // ¡Oh, no! La consulta falló. 
    echo "Lo sentimos, este sitio web está experimentando problemas.";

    // De nuevo, no hacer esto en un sitio público, aunque nosotros mostraremos
    // cómo obtener información del error
    echo "Error: La ejecución de la consulta falló debido a: \n";
    echo "Query: " . $sql . "\n";
    echo "Errno: " . $mysqli->errno . "\n";
    echo "Error: " . $mysqli->error . "\n";
    exit;
}

// ¡Uf, lo conseguimos!. Sabemos que nuestra conexión a MySQL y nuestra consulta
// tuvieron éxito, pero ¿tenemos un resultado?
if ($resultado->num_rows === 0) {
    // ¡Oh, no ha filas! Unas veces es lo previsto, pero otras
    // no. Nosotros decidimos. En este caso, ¿podría haber sido
    // actor_id demasiado grande? 
    echo "Lo sentimos. No se pudo encontrar una coincidencia para el ID $aid. Inténtelo de nuevo.";
    exit;
}
$clavearray=array();
$cantidadarray=array();
$preciovendidoarray=array();
 $descripciontapete=array();
$medidastapete=array();

while ($tabladetallepedido = $resultado->fetch_assoc()) {
   $clavearray[]= $tabladetallepedido['clave'];
   $clavexd=$tabladetallepedido['clave'];
   
				   //**********************************************************************************************************
				   // Realizar una consulta SQL
				$sql2 = "SELECT * FROM ibodegas WHERE producto1 = '$clavexd'";
				if (!$resultado2 = $mysqli2->query($sql2)) {
					// ¡Oh, no! La consulta falló. 
					echo "Lo sentimos, este sitio web está experimentando problemas.";

					// De nuevo, no hacer esto en un sitio público, aunque nosotros mostraremos
					// cómo obtener información del error
					echo "Error: La ejecución de la consulta falló debido a: \n";
					echo "Query: " . $sql2 . "\n";
					echo "Errno: " . $mysqli2->errno . "\n";
					echo "Error: " . $mysqli2->error . "\n";
					exit;
				}

				// ¡Uf, lo conseguimos!. Sabemos que nuestra conexión a MySQL y nuestra consulta
				// tuvieron éxito, pero ¿tenemos un resultado?
				if ($resultado2->num_rows === 0) {
					// ¡Oh, no ha filas! Unas veces es lo previsto, pero otras
					// no. Nosotros decidimos. En este caso, ¿podría haber sido
					// actor_id demasiado grande? 
					echo "Lo sentimos. No se pudo encontrar una coincidencia para el ID $aid. Inténtelo de nuevo.";
					exit;
				}

				// Ahora, sabemos que existe solamente un único resultado en este ejemplo, por lo
				// que vamos a colocarlo en un array asociativo donde las claves del mismo son los
				// nombres de las columnas de la tabla
				$tablainventario = $resultado2->fetch_assoc();
				$descripciontapete[]=$tablainventario['descripcio'];
				$medidastapete[]=$tablainventario['medidas'];
				//echo "des".$tablainventario['descripcion_pro'];
				//echo "<br></br>";
				//echo "medias".$tablainventario['medidas'];
				//echo "<br></br>";
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
   $cantidadarray[]= $tabladetallepedido['cantidad'];
   $preciovendidoarray[]= $tabladetallepedido['precio'];
}

$resultado->free();
$mysqli->close();


	//$evento="Expo Guadalajara";
	
	
	
	

class PDF extends FPDF
{
// Cargar los datos
function LoadData($file)
{
    // Leer las líneas del fichero
    $lines = file($file);
    $data = array();
    foreach($lines as $line)
        $data[] = explode(';',trim($line));
    return $data;
}

function Header()
{
	global $evento;
	global $fechadeevento;
	global $pedidoxcleinte;
	global $clienteexpo;
	global $direcconcliente;
	global $observaciones;
	global $anticipo;
	global $nombreclientecotiza;
	global $apellidocotiza;
	global $telefonocotiza;
	global $correocotiza;
	// Logo
    $this->Image('encabezado.jpg',10,8,33);
    // Arial bold 15
    $this->SetFont('Arial','B',12);
	//$this2->SetFont('Arial','B',8);
    // Movernos a la derecha
    $this->Cell(80);
    // Título
    //$this->Cell(30,10,'IMPORTADORA MUNDIAL DE TAPETES ORIENTALES',0);
	$this->Image('encabezado.jpg',10,8,182);
	 $this->SetFont('Arial','B',8);
    // Salto de línea
    $this->Ln(12);
	
	
	$this->SetFillColor(217,16,108);
    $this->SetTextColor(120);
    $this->SetDrawColor(128,0,0);
    $this->SetLineWidth(.3);
    $this->SetFont('','',8);
	//$this->MultiCell(45,4,"SANTA FE                                 	C. Comercial Santa Fe Planta Alta, Local 505 Del. Cuajimalpa, CDMX (55) 2167-4145 Horario: Dom a Jue 11:00 a 20:00 Vie y Sab 11:00 a 21:00\n",1);
	//$this->MultiCell(40,4,"PERISUR												C. Comercial Perisur Planta Alta, Local 324 Del. Coyoacan, CDMX(55) 5424-3717 Horario: Lun a Dom 11:00 a 21:00",1);
	
	//$this->Cell(50,10,"SANTA FE"."a",1);
	//$this->Cell(50,10,"PERISUR",1);
	//$this->Cell(50,10,"SAN JERONIMO".$this->MultiCell(50,4,"Periferico Sur 2930 Local 24 y 25 Del. Alvaro Obregon, CDMX (55) 5681-2530 Horario: Lun a Vie 10:30 a 20:00 Sab 11:00 a 19:00",1),1);
	//$this->Cell(50,10,"ATIZAPAN".$this->MultiCell(50,4,"C. Comercial Paseo Interlomas Planta Baja Local PB 27 Huixquilucan, EdoMex (55) 5290-0547 Horario: Lun a Dom 11:00 a 21:00",1),1);
	$this->Ln(12);
	
	
	$this->Ln(12);
	$this->Ln(12);
	
	//$this->Cell(50,10,"INTERLOMAS",0);
	//$this->Cell(50,10,"LERMA",0);
	//$this->Cell(50,10,"QUERETARO",0);
	//$this->Cell(50,10,'Estamos vi fs f fdms fd fsd sd fds sfd endo',1,1,'C');
	
	$this->Ln(10);
	$this->SetFillColor(217,16,108);
    $this->SetTextColor(205);
    $this->SetDrawColor(128,0,0);
    $this->SetLineWidth(.3);
    $this->SetFont('','B',10);
	
	$this->Cell(62,10,"EVENTO: ".$evento,1);
	$this->Cell(52,10,"FECHA:".$fechadeevento,1);
	$this->Cell(71,10,"COTIZACION:".$pedidoxcleinte,1);
	$this->Ln();
	$this->MultiCell(185,10,"CLIENTE: ".$nombreclientecotiza." ".$apellidocotiza."   Direccion:".$direcconcliente,1);
	$this->Ln();
	
}
// Pie de página
function Footer()
{
    // Posición: a 1,5 cm del final
    $this->SetY(-15);
    // Arial italic 8
    $this->SetFont('Arial','I',8);
    // Número de página
	$this->Cell(0,10,'WWW.TAPETESTUFAN.COM',0,0,'C');
	$this->Ln(5);
    $this->Cell(0,10,'Page '.$this->PageNo().'',0,0,'C');
}


// Tabla coloreada
function FancyTable($header, $data,$data2,$data3,$data4,$data5)
{
    // Colores, ancho de línea y fuente en negrita
    $this->SetFillColor(217,16,108);
    $this->SetTextColor(255);
    $this->SetDrawColor(128,0,0);
    $this->SetLineWidth(.3);
    $this->SetFont('','B',8);
    // Cabecera
    $w = array(15, 18, 70, 25,30,30);
    for($i=0;$i<count($header);$i++)
        $this->Cell($w[$i],7,$header[$i],1,0,'C',true);
    $this->Ln();
    // Restauración de colores y fuentes
    $this->SetFillColor(250,226,237);
    $this->SetTextColor(0);
    $this->SetFont('');
    // Datos
    $fill = false;
    /*foreach($data as $row =>$value)
    {*/
	$total=0;
	
	for($i=0;$i<count($data);$i++){
		$preciopiesas=0;
		$this->Cell($w[0],6,$data2[$i],'LR',0,'C',$fill);//celda cantidad
		$this->Cell($w[1],6,$data[$i],'LR',0,'L',$fill);//celda clave
		$this->Cell($w[2],6,$data4[$i],'LR',0,'L',$fill);//celda descripcion del producto
        $this->Cell($w[3],6,$data5[$i],'LR',0,'C',$fill);//celda medias de tapete
		$this->Cell($w[4],6,$data3[$i],'LR',0,'R',$fill);//celda precio unitario
		$preciopiesas=$data2[$i]*$data3[$i];
		
		$this->Cell($w[5],6,$preciopiesas,'LR',0,'R',$fill);//celdas precio total
		
		$total=$total+$preciopiesas;
        $this->Ln();
        $fill = !$fill;	
		
	}
        
global $anticipo;
    // Línea de cierre
	
	$this->Cell($w[0],6,"",'LR',0,'L',$fill);
$xpagar=$total-$anticipo;		
        $this->Cell($w[1],6,"",'LR',0,'L',$fill);
        $this->Cell($w[2],6,"",'LR',0,'R',$fill);
		$this->Cell($w[3],6,"",'LR',0,'L',$fill);
        $this->Cell($w[4],6,"SALDO:",'LR',0,'R',$fill);
		$this->Cell($w[5],6,"$".$total,'LR',0,'R',$fill);
		$this->Cell($w[0],6,"",'LR',0,'L',$fill);
		$this->Ln();
        $fill = !$fill;	
	
    $this->Cell(array_sum($w),0,'','T');
}
function abajo(){
	global $observaciones;
	$this->Ln();
	$this->Cell(30,10,'OBSERVACIONES:',0);
	$this->Ln();
	$this->Cell(30,10,'Cotizacion valida hasta agotar existencias y termino de expo',0);
	$this->Ln();
	//echo 
}
}


	
//**************************************************************************************************************************************************
//******************CREACION DEL PDF	
$pdf = new PDF();
// Títulos de las columnas
$header = array('Cantidad', 'Clave', 'Descripcion', 'Medias','Precio Unitario','Precio Total');
// Carga de datos

$pdf->SetFont('Arial','',14);
$pdf->AddPage();
$pdf->FancyTable($header,$clavearray,$cantidadarray,$preciovendidoarray,$descripciontapete,$medidastapete);
$pdf->abajo();
$pdf->Output();
//****************************************************************************************************************************************************
?>
require 'solicitudes.php';
require('pdf/fpdf.php');
// Vamos a pasar una variable $_GET a nuestro ejemplo, en este caso es
// 'aid' para 'actor_id' de nuestra base de datos Sakila. Le vamos a asignar un
// valor predeterminado de 1, y a amoldarla a un integer para evitar inyecciones
// de SQL y/o problemas de seguridad relacionados. El manejo de todo esto va más
// allá del alcance de este sencillo ejemplo:
//   http://example.org/script.php?aid=42

if (isset($_GET['cotiza'])) {
    $aid = $_GET['cotiza'];
} else {
    $aid = 1;
}
//echo $aid;
 //$aid = "EGI20184880";

// Conectarse a y seleccionar una base de datos de MySQL llamada sakila
// Nombre de host: 127.0.0.1, nombre de usuario: tu_usuario, contraseña: tu_contraseña, bd: sakila
$mysqli = new mysqli('LOCALHOST', 'root', 'root', 'kaisen');
$mysqli2 = new mysqli('LOCALHOST', 'root', 'root', 'kaisen');
$mysqli3 = new mysqli('LOCALHOST', 'root', 'root', 'kaisen');

//$mysqli = new mysqli('LOCALHOST', 'tapetest_expo', 'Nq37.321.1', 'tapetest_notificaciones');
//$mysqli2 = new mysqli('LOCALHOST', 'tapetest_expo', 'Nq37.321.1', 'tapetest_notificaciones');
//$mysqli3 = new mysqli('LOCALHOST', 'tapetest_expo', 'Nq37.321.1', 'tapetest_notificaciones');

//*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*--/*-*-*-*-*-*-*-9*-*9-*-*-*-*-**-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*+

// ¡Oh, no! Existe un error 'connect_errno', fallando así el intento de conexión
//*--*-*-*-*-*-*-***********************************************************************************************************************************************************************-*--**-

// ¡Oh, no! Existe un error 'connect_errno', fallando así el intento de conexión
if ($mysqli->connect_errno) {
    // La conexión falló. ¿Que vamos a hacer? 
    // Se podría contactar con uno mismo (¿email?), registrar el error, mostrar una bonita página, etc.
    // No se debe revelar información delicada

    // Probemos esto:
    echo "Lo sentimos, este sitio web está experimentando problemas.";

    // Algo que no se debería de hacer en un sitio público, aunque este ejemplo lo mostrará
    // de todas formas, es imprimir información relacionada con errores de MySQL -- se podría registrar
    echo "Error: Fallo al conectarse a MySQL debido a: \n";
    echo "Errno: " . $mysqli->connect_errno . "\n";
    echo "Error: " . $mysqli->connect_error . "\n";
    
    // Podría ser conveniente mostrar algo interesante, aunque nosotros simplemente saldremos
    exit;
}

// Realizar una consulta SQL
$sql = "SELECT * FROM cotiza WHERE numerocotizacion = '$aid'";
if (!$resultado = $mysqli->query($sql)) {
    // ¡Oh, no! La consulta falló. 
    echo "Lo sentimos, este sitio web está experimentando problemas.";

    // De nuevo, no hacer esto en un sitio público, aunque nosotros mostraremos
    // cómo obtener información del error
    echo "Error: La ejecución de la consulta falló debido a: \n";
    echo "Query: " . $sql . "\n";
    echo "Errno: " . $mysqli->errno . "\n";
    echo "Error: " . $mysqli->error . "\n";
    exit;
}

// ¡Uf, lo conseguimos!. Sabemos que nuestra conexión a MySQL y nuestra consulta
// tuvieron éxito, pero ¿tenemos un resultado?
if ($resultado->num_rows === 0) {
    // ¡Oh, no ha filas! Unas veces es lo previsto, pero otras
    // no. Nosotros decidimos. En este caso, ¿podría haber sido
    // actor_id demasiado grande? 
    echo "Lo sentimos. No se pudo encontrar una coincidencia para el ID $aid. Inténtelo de nuevo.";
    exit;
}

// Ahora, sabemos que existe solamente un único resultado en este ejemplo, por lo
// que vamos a colocarlo en un array asociativo donde las claves del mismo son los
// nombres de las columnas de la tabla
$tablapedido = $resultado->fetch_assoc();
//echo "observaciones " . $actor['observaciones'] . " " . $actor['pedidos'] . " .";
	$fechadeevento=$tablapedido['fecha'];
	$pedidoxcleinte=$tablapedido['numerocotizacion'];
	$nombrecotiza=$tablapedido['nombre'];
	$apellidocotiza=$tablapedido['apellido'];
	$telefonocotiza=$tablapedido['telefono'];
	$correocotiza=$tablapedido['correo'];
	$idpedido=$tablapedido['idcotiza'];
	//$observaciones=$tablapedido[''];
	
// Ahora, vamor a obtener cinco actores aleatorios y a imprimir sus nombres en una lista.
// El manejo de errores va a ser menor aquí, aunque ya sabemos como hacerlo
/*
$sql = "SELECT actor_id, first_name, last_name FROM actor ORDER BY rand() LIMIT 5";
if (!$resultado = $mysqli->query($sql)) {
    echo "Lo sentimos, este sitio web está experimentando problemas.";
    exit;
}

// Imprimir nuestros cinco actores aleatorios en una lista, y enlazar cada uno
echo "<ul>\n";
while ($actor = $resultado->fetch_assoc()) {
    echo "<li><a href='" . $_SERVER['SCRIPT_FILENAME'] . "?aid=" . $actor['actor_id'] . "'>\n";
    echo $actor['first_name'] . ' ' . $actor['last_name'];
    echo "</a></li>\n";
}
echo "</ul>\n";
*/
// El script automáticamente liberará el resultado y cerrará la conexión
// a MySQL cuando finalice, aunque aquí lo vamos a hacer nostros mismos


// Realizar una consulta SQL
/*$sql = "SELECT * FROM clientes WHERE id_cliente = '$idcliente'";
if (!$resultado = $mysqli->query($sql)) {
    // ¡Oh, no! La consulta falló. 
    echo "Lo sentimos, este sitio web está experimentando problemas.";

    // De nuevo, no hacer esto en un sitio público, aunque nosotros mostraremos
    // cómo obtener información del error
    echo "Error: La ejecución de la consulta falló debido a: \n";
    echo "Query: " . $sql . "\n";
    echo "Errno: " . $mysqli->errno . "\n";
    echo "Error: " . $mysqli->error . "\n";
    exit;
}

// ¡Uf, lo conseguimos!. Sabemos que nuestra conexión a MySQL y nuestra consulta
// tuvieron éxito, pero ¿tenemos un resultado?
if ($resultado->num_rows === 0) {
    // ¡Oh, no ha filas! Unas veces es lo previsto, pero otras
    // no. Nosotros decidimos. En este caso, ¿podría haber sido
    // actor_id demasiado grande? 
    echo "Lo sentimos. No se pudo encontrar una coincidencia para el ID $aid. Inténtelo de nuevo.";
    exit;
}
*/
// Ahora, sabemos que existe solamente un único resultado en este ejemplo, por lo
// que vamos a colocarlo en un array asociativo donde las claves del mismo son los
// nombres de las columnas de la tabla
/*
$tablaclientes = $resultado->fetch_assoc();
$clienteexpo=$tablaclientes['nom_cliente']." ".$tablaclientes['ap_cliente'];
$rfccliente=$tablaclientes['rfc_cliente'];
$direcconcliente=$tablaclientes['dir_cliente']." Telefono: ".$tablaclientes['tel_cliente']."  Correo: ".$tablaclientes['correo_cliente'];
*/

// Realizar una consulta SQL
$sql = "SELECT * FROM detallecotiza WHERE idcotiza = $idpedido";
if (!$resultado = $mysqli->query($sql)) {
    // ¡Oh, no! La consulta falló. 
    echo "Lo sentimos, este sitio web está experimentando problemas.";

    // De nuevo, no hacer esto en un sitio público, aunque nosotros mostraremos
    // cómo obtener información del error
    echo "Error: La ejecución de la consulta falló debido a: \n";
    echo "Query: " . $sql . "\n";
    echo "Errno: " . $mysqli->errno . "\n";
    echo "Error: " . $mysqli->error . "\n";
    exit;
}

// ¡Uf, lo conseguimos!. Sabemos que nuestra conexión a MySQL y nuestra consulta
// tuvieron éxito, pero ¿tenemos un resultado?
if ($resultado->num_rows === 0) {
    // ¡Oh, no ha filas! Unas veces es lo previsto, pero otras
    // no. Nosotros decidimos. En este caso, ¿podría haber sido
    // actor_id demasiado grande? 
    echo "Lo sentimos. No se pudo encontrar una coincidencia para el ID $aid. Inténtelo de nuevo.";
    exit;
}
$clavearray=array();
$cantidadarray=array();
$preciovendidoarray=array();
 $descripciontapete=array();
$medidastapete=array();

while ($tabladetallepedido = $resultado->fetch_assoc()) {
   $clavearray[]= $tabladetallepedido['clave'];
   $clavexd=$tabladetallepedido['clave'];
   
				   //**********************************************************************************************************
				   // Realizar una consulta SQL
				$sql2 = "SELECT * FROM inventario WHERE clave = '$clavexd'";
				if (!$resultado2 = $mysqli2->query($sql2)) {
					// ¡Oh, no! La consulta falló. 
					echo "Lo sentimos, este sitio web está experimentando problemas.";

					// De nuevo, no hacer esto en un sitio público, aunque nosotros mostraremos
					// cómo obtener información del error
					echo "Error: La ejecución de la consulta falló debido a: \n";
					echo "Query: " . $sql2 . "\n";
					echo "Errno: " . $mysqli2->errno . "\n";
					echo "Error: " . $mysqli2->error . "\n";
					exit;
				}

				// ¡Uf, lo conseguimos!. Sabemos que nuestra conexión a MySQL y nuestra consulta
				// tuvieron éxito, pero ¿tenemos un resultado?
				if ($resultado2->num_rows === 0) {
					// ¡Oh, no ha filas! Unas veces es lo previsto, pero otras
					// no. Nosotros decidimos. En este caso, ¿podría haber sido
					// actor_id demasiado grande? 
					echo "Lo sentimos. No se pudo encontrar una coincidencia para el ID $aid. Inténtelo de nuevo.";
					exit;
				}

				// Ahora, sabemos que existe solamente un único resultado en este ejemplo, por lo
				// que vamos a colocarlo en un array asociativo donde las claves del mismo son los
				// nombres de las columnas de la tabla
				$tablainventario = $resultado2->fetch_assoc();
				$descripciontapete[]=$tablainventario['descripcion_pro'];
				$medidastapete[]=$tablainventario['medidas'];
				//echo "des".$tablainventario['descripcion_pro'];
				//echo "<br></br>";
				//echo "medias".$tablainventario['medidas'];
				//echo "<br></br>";
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
   $cantidadarray[]= $tabladetallepedido['cantidad'];
   $preciovendidoarray[]= $tabladetallepedido['precio'];
}

$resultado->free();
$mysqli->close();


	//$evento="Expo Guadalajara";
	
	
	
	

class PDF extends FPDF
{
// Cargar los datos
function LoadData($file)
{
    // Leer las líneas del fichero
    $lines = file($file);
    $data = array();
    foreach($lines as $line)
        $data[] = explode(';',trim($line));
    return $data;
}

function Header()
{
	global $evento;
	global $fechadeevento;
	global $pedidoxcleinte;
	global $clienteexpo;
	global $direcconcliente;
	global $observaciones;
	global $nombrecotiza;
	global $apellidocotiza;
	global $telefonocotiza;
	global $correocotiza;
	// Logo
    $this->Image('logot.png',10,8,33);
    // Arial bold 15
    $this->SetFont('Arial','B',12);
	//$this2->SetFont('Arial','B',8);
    // Movernos a la derecha
    $this->Cell(80);
    // Título
    //$this->Cell(30,10,'IMPORTADORA MUNDIAL DE TAPETES ORIENTALES',0);
	$this->Image('encabezado.jpg',10,8,182);
	 $this->SetFont('Arial','B',8);
    // Salto de línea
    $this->Ln(12);
	$this->SetFillColor(217,16,108);
    $this->SetTextColor(120);
    $this->SetDrawColor(128,0,0);
    $this->SetLineWidth(.3);
    $this->SetFont('','',8);
	//$this->MultiCell(45,4,"SANTA FE                                 	C. Comercial Santa Fe Planta Alta, Local 505 Del. Cuajimalpa, CDMX (55) 2167-4145 Horario: Dom a Jue 11:00 a 20:00 Vie y Sab 11:00 a 21:00\n",1);
	//$this->MultiCell(40,4,"PERISUR												C. Comercial Perisur Planta Alta, Local 324 Del. Coyoacan, CDMX(55) 5424-3717 Horario: Lun a Dom 11:00 a 21:00",1);
	
	//$this->Cell(50,10,"SANTA FE"."a",1);
	//$this->Cell(50,10,"PERISUR",1);
	//$this->Cell(50,10,"SAN JERONIMO".$this->MultiCell(50,4,"Periferico Sur 2930 Local 24 y 25 Del. Alvaro Obregon, CDMX (55) 5681-2530 Horario: Lun a Vie 10:30 a 20:00 Sab 11:00 a 19:00",1),1);
	//$this->Cell(50,10,"ATIZAPAN".$this->MultiCell(50,4,"C. Comercial Paseo Interlomas Planta Baja Local PB 27 Huixquilucan, EdoMex (55) 5290-0547 Horario: Lun a Dom 11:00 a 21:00",1),1);
	$this->Ln();
	
	
	$this->Ln(12);
	$this->Ln(12);
	
	//$this->Cell(50,10,"INTERLOMAS",0);
	//$this->Cell(50,10,"LERMA",0);
	//$this->Cell(50,10,"QUERETARO",0);
	//$this->Cell(50,10,'Estamos vi fs f fdms fd fsd sd fds sfd endo',1,1,'C');
	
	$this->Ln(10);
	$this->SetFillColor(217,16,108);
    $this->SetTextColor(205);
    $this->SetDrawColor(128,0,0);
    $this->SetLineWidth(.3);
    $this->SetFont('','B',10);
	
	$this->Cell(62,10,"EVENTO: ".$evento,1);
	$this->Cell(52,10,"FECHA:".$fechadeevento,1);
	$this->Cell(71,10,"PEDIDO:".$pedidoxcleinte,1);
	$this->Ln();
	$this->MultiCell(185,10,"CLIENTE: ".$nombrecotiza." ".$apellidocotiza."  Telefono:".$telefonocotiza."  Correo:".$correocotiza,1);
	$this->Ln();
	
}
// Pie de página
function Footer()
{
    // Posición: a 1,5 cm del final
    $this->SetY(-15);
    // Arial italic 8
    $this->SetFont('Arial','I',8);
    // Número de página
	$this->Cell(0,10,'WWW.TAPETESTUFAN.COM',0,0,'C');
	$this->Ln(5);
    $this->Cell(0,10,'Page '.$this->PageNo().'',0,0,'C');
}


// Tabla coloreada
function FancyTable($header, $data,$data2,$data3,$data4,$data5)
{
    // Colores, ancho de línea y fuente en negrita
    $this->SetFillColor(217,16,108);
    $this->SetTextColor(255);
    $this->SetDrawColor(128,0,0);
    $this->SetLineWidth(.3);
    $this->SetFont('','B',8);
    // Cabecera
    $w = array(15, 18, 70, 25,30,30);
    for($i=0;$i<count($header);$i++)
        $this->Cell($w[$i],7,$header[$i],1,0,'C',true);
    $this->Ln();
    // Restauración de colores y fuentes
    $this->SetFillColor(250,226,237);
    $this->SetTextColor(0);
    $this->SetFont('');
    // Datos
    $fill = false;
    /*foreach($data as $row =>$value)
    {*/
	$total=0;
	
	for($i=0;$i<count($data);$i++){
		$preciopiesas=0;
		$this->Cell($w[0],6,$data2[$i],'LR',0,'C',$fill);//celda cantidad
		$this->Cell($w[1],6,$data[$i],'LR',0,'L',$fill);//celda clave
		$this->Cell($w[2],6,$data4[$i],'LR',0,'L',$fill);//celda descripcion del producto
        $this->Cell($w[3],6,$data5[$i],'LR',0,'C',$fill);//celda medias de tapete
		$this->Cell($w[4],6,$data3[$i],'LR',0,'R',$fill);//celda precio unitario
		$preciopiesas=$data2[$i]*$data3[$i];
		
		$this->Cell($w[5],6,$preciopiesas,'LR',0,'R',$fill);//celdas precio total
		
		$total=$total+$preciopiesas;
        $this->Ln();
        $fill = !$fill;	
		
	}
        

    // Línea de cierre
	
		$this->Cell($w[0],6,"",'LR',0,'L',$fill);
		
        $this->Cell($w[1],6,"",'LR',0,'L',$fill);
        $this->Cell($w[2],6,"",'LR',0,'R',$fill);
		$this->Cell($w[3],6,"",'LR',0,'L',$fill);
        $this->Cell($w[4],6,"TOTAL:",'LR',0,'R',$fill);
		$this->Cell($w[5],6,"$".$total,'LR',0,'R',$fill);
        $this->Ln();
        $fill = !$fill;
	
    $this->Cell(array_sum($w),0,'','T');
}
function abajo(){
	global $observaciones;
	$this->Ln();
	$this->Cell(30,10,'OBSERVACIONES:',0);
	$this->Ln();
	$this->Cell(30,10, utf8_encode("Esta cotizacion sólo es válida hasta fin de existencias de los productos válidos y antes que termine la Expo. "),0);
	$this->Ln();
	//echo 
}
}


	
//**************************************************************************************************************************************************
//******************CREACION DEL PDF	
$pdf = new PDF();
// Títulos de las columnas
$header = array('Cantidad', 'Clave', 'Descripcion', 'Medias','Precio Unitario','Precio Total');
// Carga de datos

$pdf->SetFont('Arial','',14);
$pdf->AddPage();
$pdf->FancyTable($header,$clavearray,$cantidadarray,$preciovendidoarray,$descripciontapete,$medidastapete);
$pdf->abajo();
$pdf->Output();
//****************************************************************************************************************************************************
?>