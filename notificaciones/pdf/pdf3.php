<?php
require('pdf/fpdf.php');
require 'solicitudes.php';

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
	// Logo
    $this->Image('logot.png',10,8,33);
    // Arial bold 15
    $this->SetFont('Arial','B',12);
	//$this2->SetFont('Arial','B',8);
    // Movernos a la derecha
    $this->Cell(80);
    // Título
    $this->Cell(30,10,'IMPORTADORA MUNDIAL DE TAPETES ORIENTALES',0);
	
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
	$this->Cell(50,10,"INTERLOMAS",0);
	$this->Cell(50,10,"LERMA",0);
	$this->Cell(50,10,"QUERETARO",0);
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
	$this->MultiCell(185,10,"CLIENTE: ".$clienteexpo."   Direccion:".$direcconcliente,1);
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
function FancyTable($header, $data)
{
    // Colores, ancho de línea y fuente en negrita
    $this->SetFillColor(217,16,108);
    $this->SetTextColor(255);
    $this->SetDrawColor(128,0,0);
    $this->SetLineWidth(.3);
    $this->SetFont('','B',10);
    // Cabecera
    $w = array(20, 25, 70, 35,35);
    for($i=0;$i<count($header);$i++)
        $this->Cell($w[$i],7,$header[$i],1,0,'C',true);
    $this->Ln();
    // Restauración de colores y fuentes
    $this->SetFillColor(250,226,237);
    $this->SetTextColor(0);
    $this->SetFont('');
    // Datos
    $fill = false;
    foreach($data as $row)
    {
        $this->Cell($w[0],6,"2",'LR',0,'L',$fill);
        $this->Cell($w[1],6,"T-10550",'LR',0,'L',$fill);
        $this->Cell($w[2],6,"tapetes Castelo 8760-9 Azul",'LR',0,'L',$fill);
        $this->Cell($w[3],6,"1.50 X 0.68",'LR',0,'R',$fill);
		$this->Cell($w[3],6,"$ 122,500.00",'LR',0,'R',$fill);
        $this->Ln();
        $fill = !$fill;
    }
    // Línea de cierre
	
	$this->Cell($w[0],6,"",'LR',0,'L',$fill);
        $this->Cell($w[1],6,"",'LR',0,'L',$fill);
        $this->Cell($w[2],6,"",'LR',0,'R',$fill);
        $this->Cell($w[3],6,"TOTAL:",'LR',0,'R',$fill);
		$this->Cell($w[3],6,"$ 122,500.00",'LR',0,'R',$fill);
        $this->Ln();
        $fill = !$fill;
	
    $this->Cell(array_sum($w),0,'','T');
}
}


if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    if (isset($_GET['clave'])) {

        // Obtener parámetro idalumno
        $parametro = $_GET['clave'];

        // Tratar retorno
        $retorno = solicitudes::getByIdxddetalle($parametro);


        if ($retorno) {

            $alumno["estado"] = 1;		// cambio "1" a 1 porque no coge bien la cadena.
            $alumno["alumno"] = $retorno;
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




	$evento="Expo Guadalajara XDXD";
	$fechadeevento="11/11/2018";
	$pedidoxcleinte="EXPNHS1000202";
	$clienteexpo="Hugo Valencia Samaniego";
	$direcconcliente ="Melchor Ocampo SN Barrio de Guadaldupe San Mateo Atenco";
	
//**************************************************************************************************************************************************
//******************CREACION DEL PDF	
$pdf = new PDF();
// Títulos de las columnas
$header = array('Cantidad', 'Clave', 'Descripcion', 'Medias','Precio');
// Carga de datos
$data = $pdf->LoadData('paises.txt');
$pdf->SetFont('Arial','',14);
$pdf->AddPage();
$pdf->FancyTable($header,$data);
$pdf->Output();
//****************************************************************************************************************************************************
?>