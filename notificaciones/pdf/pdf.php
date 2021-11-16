<?php
require('pdf/fpdf.php');

class PDF extends FPDF
{
// Cabecera de página
function Header()
{
    // Logo
    $this->Image('logot.png',10,8,33);
    // Arial bold 15
    $this->SetFont('Arial','B',12);
	//$this2->SetFont('Arial','B',8);
    // Movernos a la derecha
    $this->Cell(80);
    // Título
    $this->Cell(30,10,'IMPORTADORA MUNDIAL DE TAPETES ORIENTALES',0);
	
	$this->SetFont('Arial','B',7);
    // Salto de línea
    $this->Ln(12);
	$this->Cell(50,10,"SANTA FE",0);
	//$this2->Cell(50,10,"jajajaja",0);
	$this->Cell(50,10,"PERISUR",0);
	$this->Cell(50,10,"SAN JERONIMO",0);
	$this->Cell(50,10,"ATIZAPAN",0);
	$this->Ln(12);
	$this->Cell(50,10,"INTERLOMAS",0);
	$this->Cell(50,10,"LERMA",0);
	$this->Cell(50,10,"QUERETARO",0);
	
	
	$this->Ln(10);
	
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
    $this->Cell(0,10,'Page '.$this->PageNo().'/{nb}',0,0,'C');
}
}

// Creación del objeto de la clase heredada
$pdf = new PDF();
$pdf->AliasNbPages();
$pdf->AddPage();
$pdf->SetFont('Times','',12);
for($i=1;$i<=40;$i++)
    $pdf->Cell(0,10,'Imprimiendo línea número '.$i,0,1);
$pdf->Output();
?>