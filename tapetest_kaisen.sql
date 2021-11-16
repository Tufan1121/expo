-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 16-11-2021 a las 09:12:03
-- Versión del servidor: 5.7.23-23
-- Versión de PHP: 7.3.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tapetest_kaisen`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `almacenes`
--

CREATE TABLE `almacenes` (
  `almacen` varchar(3) DEFAULT NULL,
  `descripcio` varchar(40) DEFAULT NULL,
  `bodega` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `busquedas`
--

CREATE TABLE `busquedas` (
  `descripcio` varchar(60) NOT NULL,
  `diseno` varchar(40) NOT NULL,
  `compo` varchar(80) NOT NULL,
  `largo1` float(10,2) NOT NULL,
  `largo2` float(10,2) NOT NULL,
  `ancho1` float(10,2) NOT NULL,
  `ancho2` float(10,2) NOT NULL,
  `precio1` float(10,2) NOT NULL,
  `precio2` float(10,2) NOT NULL,
  `origen` varchar(15) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `who` varchar(40) NOT NULL,
  `cuando` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ccolsku`
--

CREATE TABLE `ccolsku` (
  `coleccion` varchar(60) NOT NULL,
  `color` varchar(15) NOT NULL,
  `empre` varchar(1) NOT NULL,
  `cliente` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id_cliente` varchar(10) NOT NULL,
  `nom_cliente` varchar(50) NOT NULL,
  `ap_cliente` varchar(50) NOT NULL,
  `dir_cliente` varchar(100) NOT NULL,
  `tel_cliente` varchar(60) NOT NULL,
  `correo_cliente` varchar(100) NOT NULL,
  `rfc_cliente` varchar(13) NOT NULL,
  `tipopersona` varchar(60) NOT NULL,
  `usocfdi` varchar(60) NOT NULL,
  `empresa` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `colecciones`
--

CREATE TABLE `colecciones` (
  `descripcio` varchar(60) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  `empre` varchar(1) NOT NULL,
  `cliente` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `colecciontapetes`
--

CREATE TABLE `colecciontapetes` (
  `id` int(10) NOT NULL,
  `nom_coleccion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cotiza`
--

CREATE TABLE `cotiza` (
  `idcotiza` int(10) NOT NULL,
  `numerocotizacion` varchar(50) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `telefono` varchar(50) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `idexpo` int(10) NOT NULL,
  `total` int(50) NOT NULL,
  `idusuario` int(10) NOT NULL,
  `fecha` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallecotiza`
--

CREATE TABLE `detallecotiza` (
  `iddetallecotiza` int(10) NOT NULL,
  `idcotiza` int(10) NOT NULL,
  `clave` varchar(50) NOT NULL,
  `cantidad` int(10) NOT NULL,
  `precio` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallepedido`
--

CREATE TABLE `detallepedido` (
  `iddetallepedido` varchar(10) NOT NULL,
  `idpedido` int(10) NOT NULL,
  `clave` varchar(50) NOT NULL,
  `clave2` varchar(50) NOT NULL,
  `cantidad` varchar(10) NOT NULL,
  `preciovendido` varchar(50) NOT NULL,
  `estatus` int(5) NOT NULL,
  `cerrado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detaped`
--

CREATE TABLE `detaped` (
  `documen` varchar(20) NOT NULL,
  `movimiento` varchar(10) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `producto1` varchar(60) NOT NULL,
  `almacen` varchar(3) NOT NULL,
  `nbodega` int(1) NOT NULL,
  `hm` int(10) NOT NULL,
  `idExpo` int(10) NOT NULL,
  `liberada` int(1) NOT NULL,
  `idPedido` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `errores`
--

CREATE TABLE `errores` (
  `opcion` varchar(60) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `cuando` datetime NOT NULL,
  `who` varchar(30) NOT NULL,
  `almacen` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `errorespedidosconsignacion`
--

CREATE TABLE `errorespedidosconsignacion` (
  `when` datetime NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `tienda` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estatus`
--

CREATE TABLE `estatus` (
  `estatus` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `expo`
--

CREATE TABLE `expo` (
  `idexpo` int(10) NOT NULL,
  `nom_expo` varchar(50) NOT NULL,
  `fecha_inicio` varchar(50) NOT NULL,
  `fecha_fin` varchar(50) NOT NULL,
  `activa` int(10) NOT NULL,
  `iden_pedido` varchar(50) NOT NULL,
  `almacen` varchar(3) NOT NULL,
  `almacen1` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gproducto`
--

CREATE TABLE `gproducto` (
  `producto` varchar(60) NOT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) NOT NULL,
  `diseno` varchar(60) NOT NULL,
  `medidas` varchar(20) NOT NULL,
  `largo` float(10,2) NOT NULL,
  `ancho` float(10,2) NOT NULL,
  `rojo` varchar(10) NOT NULL,
  `precio1` int(20) NOT NULL,
  `precio2` int(20) NOT NULL,
  `precio3` int(20) NOT NULL,
  `pathima1` varchar(240) NOT NULL,
  `pathima2` varchar(240) NOT NULL,
  `pathima3` varchar(240) NOT NULL,
  `desa` int(2) NOT NULL,
  `color1` varchar(10) NOT NULL,
  `color2` varchar(10) NOT NULL,
  `color3` varchar(10) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `compo1` varchar(120) NOT NULL,
  `compo2` varchar(120) NOT NULL,
  `lava1` varchar(120) NOT NULL,
  `lava2` varchar(120) NOT NULL,
  `coo` varchar(20) NOT NULL,
  `origenn` varchar(15) NOT NULL,
  `fecha` date NOT NULL,
  `video1` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gspock`
--

CREATE TABLE `gspock` (
  `producto` varchar(60) NOT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) NOT NULL,
  `diseno` varchar(60) NOT NULL,
  `medidas` varchar(20) NOT NULL,
  `hm` int(20) NOT NULL,
  `largo` float(10,2) NOT NULL,
  `ancho` float(10,2) NOT NULL,
  `precio` float(10,2) NOT NULL,
  `fulldescrip` varchar(60) NOT NULL,
  `almacen` varchar(3) NOT NULL,
  `compos` varchar(80) NOT NULL,
  `origen` varchar(15) NOT NULL,
  `rojo` varchar(10) NOT NULL,
  `observac` varchar(200) NOT NULL,
  `hobserva` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp001`
--

CREATE TABLE `histomp001` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp002`
--

CREATE TABLE `histomp002` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp003`
--

CREATE TABLE `histomp003` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp004`
--

CREATE TABLE `histomp004` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp005`
--

CREATE TABLE `histomp005` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp017`
--

CREATE TABLE `histomp017` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp100`
--

CREATE TABLE `histomp100` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp116`
--

CREATE TABLE `histomp116` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp135`
--

CREATE TABLE `histomp135` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp170`
--

CREATE TABLE `histomp170` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp182`
--

CREATE TABLE `histomp182` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp183`
--

CREATE TABLE `histomp183` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp205`
--

CREATE TABLE `histomp205` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp208`
--

CREATE TABLE `histomp208` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp221`
--

CREATE TABLE `histomp221` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp223`
--

CREATE TABLE `histomp223` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp224`
--

CREATE TABLE `histomp224` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp247`
--

CREATE TABLE `histomp247` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp249`
--

CREATE TABLE `histomp249` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp256`
--

CREATE TABLE `histomp256` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp262`
--

CREATE TABLE `histomp262` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp263`
--

CREATE TABLE `histomp263` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp264`
--

CREATE TABLE `histomp264` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp265`
--

CREATE TABLE `histomp265` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp266`
--

CREATE TABLE `histomp266` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp272`
--

CREATE TABLE `histomp272` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp283`
--

CREATE TABLE `histomp283` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp284`
--

CREATE TABLE `histomp284` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp286`
--

CREATE TABLE `histomp286` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp293`
--

CREATE TABLE `histomp293` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp298`
--

CREATE TABLE `histomp298` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp299`
--

CREATE TABLE `histomp299` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp311`
--

CREATE TABLE `histomp311` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp353`
--

CREATE TABLE `histomp353` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp378`
--

CREATE TABLE `histomp378` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp380`
--

CREATE TABLE `histomp380` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp386`
--

CREATE TABLE `histomp386` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp387`
--

CREATE TABLE `histomp387` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp391`
--

CREATE TABLE `histomp391` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp398`
--

CREATE TABLE `histomp398` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp400`
--

CREATE TABLE `histomp400` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp401`
--

CREATE TABLE `histomp401` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp404`
--

CREATE TABLE `histomp404` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp414`
--

CREATE TABLE `histomp414` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp415`
--

CREATE TABLE `histomp415` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp416`
--

CREATE TABLE `histomp416` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp417`
--

CREATE TABLE `histomp417` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp422`
--

CREATE TABLE `histomp422` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp423`
--

CREATE TABLE `histomp423` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histomp435`
--

CREATE TABLE `histomp435` (
  `movimiento` varchar(6) NOT NULL,
  `documen` varchar(20) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `despro` varchar(40) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `almacen` varchar(6) NOT NULL,
  `desalmacen` varchar(30) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `desfamilia` varchar(30) NOT NULL,
  `clase` varchar(3) NOT NULL,
  `desclase` varchar(30) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `desunidad` varchar(30) NOT NULL,
  `despunto` varchar(30) NOT NULL,
  `cantidad` float(15,5) NOT NULL,
  `precio` float(15,5) NOT NULL,
  `importe` float(15,5) NOT NULL,
  `impuesto` float(15,5) NOT NULL,
  `total` float(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_cap` date NOT NULL,
  `mes` int(2) NOT NULL,
  `es` varchar(1) NOT NULL,
  `considera` tinyint(1) NOT NULL,
  `tipop` varchar(6) NOT NULL,
  `subtipop` varchar(6) NOT NULL,
  `cliente` varchar(8) NOT NULL,
  `descripcio` varchar(80) NOT NULL,
  `document` varchar(20) NOT NULL,
  `camta` int(2) NOT NULL,
  `i0` float(15,5) NOT NULL,
  `i1` float(15,5) NOT NULL,
  `i2` float(15,5) NOT NULL,
  `i18` float(15,5) NOT NULL,
  `otro` int(15) NOT NULL,
  `pedido` varchar(20) NOT NULL,
  `observa` varchar(50) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `cuando` datetime NOT NULL,
  `reci` varchar(30) NOT NULL,
  `lo` tinyint(1) NOT NULL,
  `regg` int(15) NOT NULL,
  `alma2` varchar(6) NOT NULL,
  `movt2` varchar(6) NOT NULL,
  `afectado` varchar(1) NOT NULL,
  `cuentacon` varchar(15) NOT NULL,
  `diseño` varchar(40) NOT NULL,
  `divisa` varchar(4) NOT NULL,
  `newfld` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `who` varchar(30) NOT NULL,
  `contenedor` varchar(10) NOT NULL,
  `crfc` varchar(15) NOT NULL,
  `documenp` varchar(20) NOT NULL,
  `fechap` date NOT NULL,
  `whenp` datetime NOT NULL,
  `whop` varchar(35) NOT NULL,
  `reggp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ibodegas`
--

CREATE TABLE `ibodegas` (
  `producto` varchar(60) NOT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) NOT NULL,
  `diseno` varchar(60) NOT NULL,
  `medidas` varchar(20) NOT NULL,
  `hm` int(20) NOT NULL,
  `largo` float(10,2) NOT NULL,
  `ancho` float(10,2) NOT NULL,
  `rojo` varchar(10) NOT NULL,
  `bodega1` float(10,2) NOT NULL,
  `bodega2` float(10,2) NOT NULL,
  `bodega3` float(10,2) NOT NULL,
  `bodega4` float(10,2) NOT NULL,
  `almacen1` varchar(3) NOT NULL,
  `almacen2` varchar(3) NOT NULL,
  `almacen3` varchar(3) NOT NULL,
  `almacen4` varchar(3) NOT NULL,
  `fulldescrip` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario`
--

CREATE TABLE `inventario` (
  `id_producto` int(10) NOT NULL,
  `clave` varchar(100) NOT NULL,
  `descripcion_pro` varchar(100) NOT NULL,
  `medidas` varchar(100) NOT NULL,
  `stock` int(100) NOT NULL,
  `precioL` double NOT NULL,
  `precioExpo` double NOT NULL,
  `precioMayoreo` double NOT NULL,
  `idsubcategoria` int(10) NOT NULL,
  `img` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lproducto`
--

CREATE TABLE `lproducto` (
  `producto` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodopagoexpo`
--

CREATE TABLE `metodopagoexpo` (
  `idmetodopago` int(10) NOT NULL,
  `metododepago` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mspock`
--

CREATE TABLE `mspock` (
  `producto` varchar(60) NOT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) NOT NULL,
  `diseno` varchar(60) NOT NULL,
  `medidas` varchar(20) NOT NULL,
  `hm` int(20) NOT NULL,
  `largo` float(10,2) NOT NULL,
  `ancho` float(10,2) NOT NULL,
  `precio` float(10,2) NOT NULL,
  `fulldescrip` varchar(60) NOT NULL,
  `almacen` varchar(3) NOT NULL,
  `compos` varchar(80) NOT NULL,
  `origen` varchar(15) NOT NULL,
  `rojo` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `idpedido` varchar(10) NOT NULL,
  `idcliente` varchar(10) NOT NULL,
  `fecha` varchar(50) NOT NULL,
  `idmetodopago` varchar(11) NOT NULL,
  `idmetodopago2` varchar(11) NOT NULL,
  `idmetodopago3` varchar(11) NOT NULL,
  `observaciones` varchar(100) NOT NULL,
  `idusuario` varchar(10) NOT NULL,
  `pedidos` varchar(50) NOT NULL,
  `idexpo` int(10) NOT NULL,
  `estatus` int(10) NOT NULL,
  `anticipo` int(50) NOT NULL,
  `anticipo2` int(50) NOT NULL,
  `anticipo3` int(50) NOT NULL,
  `total_pagar` int(50) NOT NULL,
  `entregado` int(10) NOT NULL,
  `saldo` float(10,2) NOT NULL,
  `dig1` varchar(4) NOT NULL,
  `dig2` varchar(4) NOT NULL,
  `dig3` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedimend`
--

CREATE TABLE `pedimend` (
  `empresa` varchar(1) NOT NULL,
  `pedimento` varchar(20) NOT NULL,
  `descripcio` varchar(60) NOT NULL,
  `diseño` varchar(60) NOT NULL,
  `coo` varchar(20) NOT NULL,
  `compo1` varchar(60) NOT NULL,
  `compo2` varchar(60) NOT NULL,
  `lava1` varchar(80) NOT NULL,
  `lava2` varchar(80) NOT NULL,
  `color1` varchar(10) NOT NULL,
  `color2` varchar(10) NOT NULL,
  `color3` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `prot` varchar(3) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `descripcio` varchar(60) NOT NULL,
  `descripcio1` varchar(60) NOT NULL,
  `descripcio2` varchar(60) NOT NULL,
  `familia` varchar(8) NOT NULL,
  `unidad` varchar(6) NOT NULL,
  `precio1` float(15,2) NOT NULL,
  `precio2` float(15,2) NOT NULL,
  `precio3` float(15,2) NOT NULL,
  `precio4` float(15,2) NOT NULL,
  `precio5` float(15,2) NOT NULL,
  `precio6` varchar(15) NOT NULL,
  `tasa` int(4) NOT NULL,
  `largo` float(10,2) NOT NULL,
  `ancho` float(10,2) NOT NULL,
  `empre` varchar(1) NOT NULL,
  `pedimenn` varchar(20) NOT NULL,
  `fechaa` date NOT NULL,
  `puerto1` varchar(50) NOT NULL,
  `puerto2` varchar(30) NOT NULL,
  `origen` varchar(15) NOT NULL,
  `compo1` varchar(60) NOT NULL,
  `compo2` varchar(60) NOT NULL,
  `lava1` varchar(80) NOT NULL,
  `lava2` varchar(80) NOT NULL,
  `verde` varchar(10) NOT NULL,
  `rojo` varchar(10) NOT NULL,
  `npedimen` int(10) NOT NULL,
  `diseño` varchar(60) NOT NULL,
  `diseño1` varchar(20) NOT NULL,
  `medidas` varchar(20) NOT NULL,
  `coo` varchar(20) NOT NULL,
  `producto1` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reprocesos`
--

CREATE TABLE `reprocesos` (
  `almacen` varchar(3) NOT NULL,
  `descripcio` varchar(30) NOT NULL,
  `fecha` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `skus`
--

CREATE TABLE `skus` (
  `empresa` varchar(1) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `grupo` varchar(20) NOT NULL,
  `descripcio` varchar(45) NOT NULL,
  `color` varchar(15) NOT NULL,
  `medida` varchar(15) NOT NULL,
  `compo` varchar(80) NOT NULL,
  `origen` varchar(15) NOT NULL,
  `pcosto` float(10,2) NOT NULL,
  `pventa` float(10,2) NOT NULL,
  `fc` varchar(1) NOT NULL,
  `estatus` varchar(40) NOT NULL,
  `cuando` datetime NOT NULL,
  `who` varchar(30) NOT NULL,
  `uso` varchar(25) NOT NULL,
  `hecho` varchar(25) NOT NULL,
  `cliente` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock001`
--

CREATE TABLE `spock001` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock002`
--

CREATE TABLE `spock002` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock003`
--

CREATE TABLE `spock003` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock004`
--

CREATE TABLE `spock004` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock005`
--

CREATE TABLE `spock005` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock017`
--

CREATE TABLE `spock017` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock100`
--

CREATE TABLE `spock100` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock116`
--

CREATE TABLE `spock116` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock135`
--

CREATE TABLE `spock135` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock170`
--

CREATE TABLE `spock170` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock182`
--

CREATE TABLE `spock182` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock183`
--

CREATE TABLE `spock183` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock205`
--

CREATE TABLE `spock205` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock208`
--

CREATE TABLE `spock208` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock221`
--

CREATE TABLE `spock221` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock223`
--

CREATE TABLE `spock223` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock224`
--

CREATE TABLE `spock224` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock247`
--

CREATE TABLE `spock247` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock249`
--

CREATE TABLE `spock249` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock256`
--

CREATE TABLE `spock256` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock262`
--

CREATE TABLE `spock262` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock263`
--

CREATE TABLE `spock263` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock264`
--

CREATE TABLE `spock264` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock265`
--

CREATE TABLE `spock265` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock266`
--

CREATE TABLE `spock266` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock272`
--

CREATE TABLE `spock272` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock283`
--

CREATE TABLE `spock283` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock284`
--

CREATE TABLE `spock284` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock286`
--

CREATE TABLE `spock286` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock293`
--

CREATE TABLE `spock293` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock298`
--

CREATE TABLE `spock298` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock299`
--

CREATE TABLE `spock299` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock311`
--

CREATE TABLE `spock311` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock353`
--

CREATE TABLE `spock353` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock378`
--

CREATE TABLE `spock378` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock380`
--

CREATE TABLE `spock380` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock386`
--

CREATE TABLE `spock386` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock387`
--

CREATE TABLE `spock387` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock391`
--

CREATE TABLE `spock391` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock398`
--

CREATE TABLE `spock398` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock400`
--

CREATE TABLE `spock400` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock401`
--

CREATE TABLE `spock401` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock404`
--

CREATE TABLE `spock404` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock414`
--

CREATE TABLE `spock414` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock415`
--

CREATE TABLE `spock415` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock416`
--

CREATE TABLE `spock416` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock417`
--

CREATE TABLE `spock417` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock421`
--

CREATE TABLE `spock421` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) DEFAULT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio1` float DEFAULT NULL,
  `precio2` float DEFAULT NULL,
  `precio3` float DEFAULT NULL,
  `almacen` varchar(3) DEFAULT NULL,
  `almacen1` varchar(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock422`
--

CREATE TABLE `spock422` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock423`
--

CREATE TABLE `spock423` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock435`
--

CREATE TABLE `spock435` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) NOT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio` float(10,2) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock436`
--

CREATE TABLE `spock436` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) DEFAULT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio1` float DEFAULT NULL,
  `precio2` float DEFAULT NULL,
  `precio3` float DEFAULT NULL,
  `almacen` varchar(3) DEFAULT NULL,
  `almacen1` varchar(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock437`
--

CREATE TABLE `spock437` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) DEFAULT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio1` float DEFAULT NULL,
  `precio2` float DEFAULT NULL,
  `precio3` float DEFAULT NULL,
  `almacen` varchar(3) DEFAULT NULL,
  `almacen1` varchar(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock443`
--

CREATE TABLE `spock443` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) DEFAULT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio1` float DEFAULT NULL,
  `precio2` float DEFAULT NULL,
  `precio3` float DEFAULT NULL,
  `almacen` varchar(3) DEFAULT NULL,
  `almacen1` varchar(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock444`
--

CREATE TABLE `spock444` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) DEFAULT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio1` float DEFAULT NULL,
  `precio2` float DEFAULT NULL,
  `precio3` float DEFAULT NULL,
  `almacen` varchar(3) DEFAULT NULL,
  `almacen1` varchar(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock447`
--

CREATE TABLE `spock447` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) DEFAULT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio1` float DEFAULT NULL,
  `precio2` float DEFAULT NULL,
  `precio3` float DEFAULT NULL,
  `almacen` varchar(3) DEFAULT NULL,
  `almacen1` varchar(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock455`
--

CREATE TABLE `spock455` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) DEFAULT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio1` float DEFAULT NULL,
  `precio2` float DEFAULT NULL,
  `precio3` float DEFAULT NULL,
  `almacen` varchar(3) DEFAULT NULL,
  `almacen1` varchar(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spock471`
--

CREATE TABLE `spock471` (
  `producto` varchar(60) DEFAULT NULL,
  `producto1` varchar(20) DEFAULT NULL,
  `descripcio` varchar(60) DEFAULT NULL,
  `diseno` varchar(60) DEFAULT NULL,
  `medidas` varchar(20) DEFAULT NULL,
  `hm` float DEFAULT NULL,
  `largo` float DEFAULT NULL,
  `ancho` float DEFAULT NULL,
  `precio1` float DEFAULT NULL,
  `precio2` float DEFAULT NULL,
  `precio3` float DEFAULT NULL,
  `almacen` varchar(3) DEFAULT NULL,
  `almacen1` varchar(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `subcategoria`
--

CREATE TABLE `subcategoria` (
  `idsubcategoria` int(10) NOT NULL,
  `subcatname` varchar(10) NOT NULL,
  `idexpo` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `idusuario` int(11) NOT NULL,
  `usuario` varchar(50) CHARACTER SET utf8 NOT NULL,
  `pass` varchar(20) CHARACTER SET utf8 NOT NULL,
  `apellidos` varchar(20) CHARACTER SET utf8 NOT NULL,
  `nombre` varchar(50) CHARACTER SET utf8 NOT NULL,
  `tipo` varchar(20) CHARACTER SET utf8 NOT NULL,
  `correo` varchar(50) CHARACTER SET utf8 NOT NULL,
  `sesion` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `_u`
--

CREATE TABLE `_u` (
  `usuario` varchar(10) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `menu` varchar(10) DEFAULT NULL,
  `regg` int(15) DEFAULT NULL,
  `lo` tinyint(1) DEFAULT NULL,
  `empresa` varchar(10) DEFAULT NULL,
  `digsig` varchar(10) DEFAULT NULL,
  `cemp` varchar(8) DEFAULT NULL,
  `adminn` tinyint(1) DEFAULT NULL,
  `superad` tinyint(1) DEFAULT NULL,
  `pro` tinyint(1) DEFAULT NULL,
  `llave` varchar(8) DEFAULT NULL,
  `email` varchar(80) DEFAULT NULL,
  `manager` tinyint(1) DEFAULT NULL,
  `autoriza` tinyint(1) DEFAULT NULL,
  `promo` tinyint(1) DEFAULT NULL,
  `almacen` varchar(6) DEFAULT NULL,
  `carpeta` varchar(100) DEFAULT NULL,
  `depa` int(10) DEFAULT NULL,
  `cambios` tinyint(1) DEFAULT NULL,
  `llavehash` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `almacenes`
--
ALTER TABLE `almacenes`
  ADD UNIQUE KEY `almacen` (`almacen`);

--
-- Indices de la tabla `detaped`
--
ALTER TABLE `detaped`
  ADD PRIMARY KEY (`documen`,`producto`,`movimiento`) USING BTREE,
  ADD UNIQUE KEY `p1` (`documen`,`producto1`);

--
-- Indices de la tabla `expo`
--
ALTER TABLE `expo`
  ADD UNIQUE KEY `activa` (`activa`);

--
-- Indices de la tabla `gproducto`
--
ALTER TABLE `gproducto`
  ADD PRIMARY KEY (`producto`),
  ADD UNIQUE KEY `producto1` (`producto1`),
  ADD KEY `descripcio` (`descripcio`);

--
-- Indices de la tabla `gspock`
--
ALTER TABLE `gspock`
  ADD UNIQUE KEY `producto` (`producto`,`almacen`),
  ADD KEY `descripcio` (`descripcio`),
  ADD KEY `palma` (`producto`,`almacen`),
  ADD KEY `almacen` (`producto`),
  ADD KEY `hm` (`hm`),
  ADD KEY `diseno` (`diseno`),
  ADD KEY `pp` (`descripcio`,`diseno`,`medidas`);

--
-- Indices de la tabla `histomp005`
--
ALTER TABLE `histomp005`
  ADD KEY `documen` (`movimiento`,`documen`,`producto`) USING HASH,
  ADD KEY `docusku` (`movimiento`,`documen`,`producto`,`sku`,`document`) USING BTREE,
  ADD KEY `docuped` (`pedido`,`documen`),
  ADD KEY `movidoc` (`movimiento`,`documen`);

--
-- Indices de la tabla `ibodegas`
--
ALTER TABLE `ibodegas`
  ADD PRIMARY KEY (`producto`),
  ADD UNIQUE KEY `producto1` (`producto1`);

--
-- Indices de la tabla `mspock`
--
ALTER TABLE `mspock`
  ADD UNIQUE KEY `producto` (`producto`) USING BTREE,
  ADD KEY `descripcio` (`descripcio`);

--
-- Indices de la tabla `skus`
--
ALTER TABLE `skus`
  ADD UNIQUE KEY `sku` (`empresa`,`sku`),
  ADD KEY `descripcio` (`descripcio`);

--
-- Indices de la tabla `spock005`
--
ALTER TABLE `spock005`
  ADD UNIQUE KEY `producto` (`producto1`) USING HASH;

--
-- Indices de la tabla `spock444`
--
ALTER TABLE `spock444`
  ADD UNIQUE KEY `producto` (`producto`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
