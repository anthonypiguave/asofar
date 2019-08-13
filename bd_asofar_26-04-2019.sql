CREATE DATABASE  IF NOT EXISTS `bd_farmacia_desa` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bd_farmacia_desa`;
-- MySQL dump 10.13  Distrib 5.6.24, for Win32 (x86)
--
-- Host: 173.212.207.238    Database: bd_farmacia_desa
-- ------------------------------------------------------
-- Server version	5.7.25-0ubuntu0.18.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `co_detalle_orden_compra`
--

DROP TABLE IF EXISTS `co_detalle_orden_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `co_detalle_orden_compra` (
  `id_detalle_orden_compra` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_orden_compra` bigint(20) DEFAULT NULL,
  `id_empresa` bigint(20) DEFAULT NULL,
  `id_surcusal` bigint(20) DEFAULT NULL,
  `linea_detalle` bigint(20) DEFAULT NULL,
  `descripcion` varchar(70) DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  `id_grupo` bigint(20) DEFAULT NULL,
  `id_subgrupo` bigint(20) DEFAULT NULL,
  `id_articulo` bigint(20) DEFAULT NULL,
  `id_tipo_medidas` bigint(20) DEFAULT NULL,
  `id_tipo_presentacion` bigint(20) DEFAULT NULL,
  `marca` varchar(50) DEFAULT NULL,
  `modelado` varchar(50) DEFAULT NULL,
  `cantidad_total` bigint(20) DEFAULT NULL,
  `precio_unitario` decimal(10,7) DEFAULT NULL,
  `precio_total` decimal(10,7) DEFAULT NULL,
  `estado` char(2) DEFAULT NULL,
  `iva` decimal(10,7) DEFAULT NULL,
  `subtotal` decimal(10,7) DEFAULT NULL,
  `descuento` decimal(10,7) DEFAULT NULL,
  `total` decimal(10,7) DEFAULT NULL,
  PRIMARY KEY (`id_detalle_orden_compra`),
  KEY `fk_cab_det_comp$1` (`id_orden_compra`,`id_empresa`,`id_surcusal`),
  KEY `fk_cab_det_comp$2_idx` (`id_producto`,`id_grupo`,`id_subgrupo`,`id_empresa`,`id_articulo`,`id_tipo_medidas`,`id_tipo_presentacion`),
  CONSTRAINT `fk_cab_det_comp$1` FOREIGN KEY (`id_orden_compra`, `id_empresa`, `id_surcusal`) REFERENCES `co_orden_compras` (`id_orden_compra`, `id_empresa`, `id_sucursal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `co_detalle_orden_compra`
--

LOCK TABLES `co_detalle_orden_compra` WRITE;
/*!40000 ALTER TABLE `co_detalle_orden_compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `co_detalle_orden_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `co_detalle_orden_pedido`
--

DROP TABLE IF EXISTS `co_detalle_orden_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `co_detalle_orden_pedido` (
  `id_detalle_orden_compra` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_orden_pedido` bigint(20) DEFAULT NULL,
  `id_empresa` bigint(20) DEFAULT NULL,
  `id_surcusal` bigint(20) DEFAULT NULL,
  `linea_detalle` bigint(20) DEFAULT NULL,
  `descripcion` varchar(70) DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  `id_grupo` bigint(20) DEFAULT NULL,
  `id_subgrupo` bigint(20) DEFAULT NULL,
  `id_articulo` bigint(20) DEFAULT NULL,
  `id_tipo_presentacion` bigint(20) DEFAULT NULL,
  `id_tipo_medidas` bigint(20) DEFAULT NULL,
  `marca` varchar(50) DEFAULT NULL,
  `modelado` varchar(50) DEFAULT NULL,
  `cantidad_total` bigint(20) DEFAULT NULL,
  `precio_unitario` decimal(10,7) DEFAULT NULL,
  `precio_total` decimal(10,7) DEFAULT NULL,
  `estado` char(2) DEFAULT NULL,
  `iva` decimal(10,7) DEFAULT NULL,
  `subtotal` decimal(10,7) DEFAULT NULL,
  `descuento` decimal(10,7) DEFAULT NULL,
  `total` decimal(10,7) DEFAULT NULL,
  PRIMARY KEY (`id_detalle_orden_compra`),
  KEY `fk_cab_det_ped$1` (`id_orden_pedido`,`id_empresa`,`id_surcusal`),
  KEY `fk_cab_det_ped$2_idx` (`id_empresa`,`id_producto`,`id_grupo`,`id_subgrupo`,`id_articulo`,`id_tipo_presentacion`,`id_tipo_medidas`),
  CONSTRAINT `fk_cab_det_ped$1` FOREIGN KEY (`id_orden_pedido`, `id_empresa`, `id_surcusal`) REFERENCES `co_orden_pedido` (`id_orden_pedido`, `id_empresa`, `id_sucursal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `co_detalle_orden_pedido`
--

LOCK TABLES `co_detalle_orden_pedido` WRITE;
/*!40000 ALTER TABLE `co_detalle_orden_pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `co_detalle_orden_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `co_orden_compras`
--

DROP TABLE IF EXISTS `co_orden_compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `co_orden_compras` (
  `id_orden_compra` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_empresa` bigint(20) NOT NULL,
  `id_sucursal` bigint(20) NOT NULL,
  `codigo_cotizacion_proveedores` bigint(20) DEFAULT NULL,
  `codigo_cotizacion` bigint(20) DEFAULT NULL,
  `id_proveedor` bigint(20) DEFAULT NULL,
  `estado` char(2) DEFAULT NULL,
  `id_tipo_documento` bigint(20) DEFAULT NULL,
  `observacion` varchar(70) DEFAULT NULL,
  `fecha_entrega` date DEFAULT NULL,
  `total_compra` decimal(10,7) DEFAULT NULL,
  `subtotal` decimal(10,7) DEFAULT NULL,
  `total_descuento` decimal(10,7) DEFAULT NULL,
  `total_ice` decimal(10,7) DEFAULT NULL,
  `total_iva` decimal(10,7) DEFAULT NULL,
  `mail_notificador` varchar(70) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_orden_compra`,`id_empresa`,`id_sucursal`),
  KEY `fk_empres_sucursal$11` (`id_empresa`,`id_sucursal`),
  KEY `fk_proveedor$2` (`id_proveedor`),
  CONSTRAINT `fk_empres_sucursal$11` FOREIGN KEY (`id_empresa`, `id_sucursal`) REFERENCES `se_sucursal` (`id_empresa`, `id_sucursal`),
  CONSTRAINT `fk_proveedor$2` FOREIGN KEY (`id_proveedor`) REFERENCES `se_personas` (`id_persona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `co_orden_compras`
--

LOCK TABLES `co_orden_compras` WRITE;
/*!40000 ALTER TABLE `co_orden_compras` DISABLE KEYS */;
/*!40000 ALTER TABLE `co_orden_compras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `co_orden_pedido`
--

DROP TABLE IF EXISTS `co_orden_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `co_orden_pedido` (
  `id_orden_pedido` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_empresa` bigint(20) NOT NULL,
  `id_sucursal` bigint(20) NOT NULL,
  `codigo_cotizacion_proveedores` bigint(20) DEFAULT NULL,
  `codigo_cotizacion` bigint(20) DEFAULT NULL,
  `id_proveedor` bigint(20) DEFAULT NULL,
  `estado` char(2) DEFAULT NULL,
  `id_documento` bigint(20) DEFAULT NULL,
  `observacion` varchar(70) DEFAULT NULL,
  `fecha_entrega` date DEFAULT NULL,
  `total_compra` decimal(10,7) DEFAULT NULL,
  `subtotal` decimal(10,7) DEFAULT NULL,
  `total_descuento` decimal(10,7) DEFAULT NULL,
  `total_ice` decimal(10,7) DEFAULT NULL,
  `total_iva` decimal(10,7) DEFAULT NULL,
  `mail_notificador` varchar(70) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_orden_pedido`,`id_empresa`,`id_sucursal`),
  KEY `fk_empres_sucursal$10` (`id_empresa`,`id_sucursal`),
  CONSTRAINT `fk_empres_sucursal$10` FOREIGN KEY (`id_empresa`, `id_sucursal`) REFERENCES `se_sucursal` (`id_empresa`, `id_sucursal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `co_orden_pedido`
--

LOCK TABLES `co_orden_pedido` WRITE;
/*!40000 ALTER TABLE `co_orden_pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `co_orden_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `in_bodega`
--

DROP TABLE IF EXISTS `in_bodega`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `in_bodega` (
  `id_bodega` bigint(11) NOT NULL AUTO_INCREMENT,
  `id_tipo_bodega` bigint(11) NOT NULL,
  `id_empresa` bigint(11) NOT NULL,
  `id_sucursal` bigint(11) NOT NULL,
  `nombre_bodega` varchar(70) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  `id_usuario_creacion` bigint(11) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `id_usuario_actualizacion` bigint(11) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_bodega`,`id_tipo_bodega`,`id_empresa`,`id_sucursal`),
  KEY `fk_bodega_tipo` (`id_tipo_bodega`),
  KEY `fk_empresa_sucursal$8` (`id_empresa`,`id_sucursal`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `in_bodega`
--

LOCK TABLES `in_bodega` WRITE;
/*!40000 ALTER TABLE `in_bodega` DISABLE KEYS */;
/*!40000 ALTER TABLE `in_bodega` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `in_detalle_movimiento`
--

DROP TABLE IF EXISTS `in_detalle_movimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `in_detalle_movimiento` (
  `id_detalle_movimiento` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_movimientos` bigint(20) DEFAULT NULL,
  `id_tipo_movimiento` bigint(20) DEFAULT NULL,
  `id_tipo_documento` bigint(20) DEFAULT NULL,
  `id_numero_documento` bigint(20) DEFAULT NULL,
  `id_empresa` bigint(20) DEFAULT NULL,
  `anio_documento` varchar(10) DEFAULT NULL,
  `linea_detalle` bigint(20) DEFAULT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  `cantidad` bigint(20) DEFAULT NULL,
  `precio_unitario` decimal(10,7) DEFAULT NULL,
  `estado` char(2) DEFAULT NULL,
  `despachado` char(2) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_detalle_movimiento`),
  KEY `fk_in_detalle_movimiento_1_idx` (`id_movimientos`,`id_tipo_documento`,`id_empresa`,`id_numero_documento`),
  KEY `fk_det_mov$1_idx` (`id_tipo_movimiento`),
  CONSTRAINT `fk_det_mov$1` FOREIGN KEY (`id_tipo_movimiento`) REFERENCES `in_tipo_movimiento` (`id_tipo_movimiento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_in_detalle_movimiento_1` FOREIGN KEY (`id_movimientos`, `id_tipo_documento`, `id_empresa`, `id_numero_documento`) REFERENCES `in_movimientos` (`id_movimientos`, `id_tipo_documento`, `id_empresa`, `id_numero_documento`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `in_detalle_movimiento`
--

LOCK TABLES `in_detalle_movimiento` WRITE;
/*!40000 ALTER TABLE `in_detalle_movimiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `in_detalle_movimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `in_kardex`
--

DROP TABLE IF EXISTS `in_kardex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `in_kardex` (
  `id_kardex` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_empresa` bigint(20) NOT NULL,
  `id_sucursal` bigint(20) NOT NULL,
  `id_tipo_bodega` bigint(20) DEFAULT NULL,
  `id_bodega` bigint(20) NOT NULL,
  `id_prestaciones` bigint(20) NOT NULL,
  `fecha_movimiento` datetime DEFAULT NULL,
  `id_tipo_movimiento` bigint(20) NOT NULL,
  `id_tipo_documento` bigint(20) NOT NULL,
  `anio_documento` varchar(4) DEFAULT NULL,
  `numero_documento` bigint(20) DEFAULT NULL,
  `fecha_sistema` datetime DEFAULT NULL,
  `cantidad` bigint(20) DEFAULT NULL,
  `saldo_actual` bigint(20) DEFAULT NULL,
  `saldo_anterior` bigint(20) DEFAULT NULL,
  `saldo_actual_bodega` bigint(20) DEFAULT NULL,
  `saldo_anterior_bodega` bigint(20) DEFAULT NULL,
  `saldo_actual_empresa` bigint(20) DEFAULT NULL,
  `saldo_anterior_empresa` bigint(20) DEFAULT NULL,
  `costo_actual` decimal(10,7) DEFAULT NULL,
  `costo_anterior` decimal(10,7) DEFAULT NULL,
  `costo_promedio` decimal(10,7) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_kardex`,`id_empresa`,`id_sucursal`,`id_bodega`,`id_prestaciones`,`id_tipo_movimiento`,`id_tipo_documento`),
  KEY `fk_empresa_sucursal$9` (`id_empresa`,`id_sucursal`),
  KEY `fk_tipo_movimiento$2` (`id_tipo_movimiento`),
  KEY `fk_tipo_documentos$2` (`id_tipo_documento`),
  KEY `fk_bodega$3` (`id_bodega`,`id_empresa`,`id_sucursal`,`id_tipo_bodega`),
  KEY `fk_prestaciones$3` (`id_prestaciones`,`id_empresa`),
  CONSTRAINT `fk_empresa_sucursal$9` FOREIGN KEY (`id_empresa`, `id_sucursal`) REFERENCES `se_sucursal` (`id_empresa`, `id_sucursal`),
  CONSTRAINT `fk_prestaciones$3` FOREIGN KEY (`id_prestaciones`, `id_empresa`) REFERENCES `pr_prestaciones` (`id_prestacion`, `id_empresa`),
  CONSTRAINT `fk_tipo_documentos$2` FOREIGN KEY (`id_tipo_documento`) REFERENCES `in_tipo_documento` (`id_tipo_documento`),
  CONSTRAINT `fk_tipo_movimiento$2` FOREIGN KEY (`id_tipo_movimiento`) REFERENCES `in_tipo_movimiento` (`id_tipo_movimiento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `in_kardex`
--

LOCK TABLES `in_kardex` WRITE;
/*!40000 ALTER TABLE `in_kardex` DISABLE KEYS */;
/*!40000 ALTER TABLE `in_kardex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `in_movimientos`
--

DROP TABLE IF EXISTS `in_movimientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `in_movimientos` (
  `id_movimientos` bigint(11) NOT NULL AUTO_INCREMENT,
  `id_numero_documento` bigint(11) NOT NULL,
  `id_tipo_documento` bigint(11) NOT NULL,
  `anio_documento` varchar(45) DEFAULT NULL,
  `id_empresa` bigint(11) NOT NULL,
  `id_sucursal` bigint(11) NOT NULL,
  `id_bodega` bigint(11) DEFAULT NULL,
  `id_usuario` bigint(11) DEFAULT NULL,
  `id_tipo_bodega` bigint(11) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  `bodega_destino` varchar(50) DEFAULT NULL,
  `sucursal_destino` varchar(45) DEFAULT NULL,
  `observacion` varchar(45) DEFAULT NULL,
  `id_proveedor` bigint(11) DEFAULT NULL,
  `id_orden_compra` bigint(11) DEFAULT NULL,
  `id_usuario_creacion` bigint(11) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `id_usuario_actualizacion` bigint(11) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_movimientos`,`id_tipo_documento`,`id_empresa`,`id_numero_documento`),
  KEY `fk_usuario$6` (`id_usuario`),
  KEY `fk_provedor$1` (`id_proveedor`),
  KEY `fk_compra$2` (`id_orden_compra`,`id_empresa`,`id_sucursal`),
  KEY `fk_tipo_documento$5_idx` (`id_tipo_documento`),
  KEY `fk_movimiento_bode$7_idx` (`id_bodega`,`id_tipo_bodega`,`id_empresa`,`id_sucursal`),
  CONSTRAINT `fk_compra$2` FOREIGN KEY (`id_orden_compra`, `id_empresa`, `id_sucursal`) REFERENCES `co_orden_compras` (`id_orden_compra`, `id_empresa`, `id_sucursal`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_provedor$1` FOREIGN KEY (`id_proveedor`) REFERENCES `se_personas` (`id_persona`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tipo_documento$5` FOREIGN KEY (`id_tipo_documento`) REFERENCES `in_tipo_documento` (`id_tipo_documento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario$6` FOREIGN KEY (`id_usuario`) REFERENCES `se_usuarios` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `in_movimientos`
--

LOCK TABLES `in_movimientos` WRITE;
/*!40000 ALTER TABLE `in_movimientos` DISABLE KEYS */;
/*!40000 ALTER TABLE `in_movimientos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `in_tipo_bodega`
--

DROP TABLE IF EXISTS `in_tipo_bodega`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `in_tipo_bodega` (
  `id_tipo_bodega` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(70) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  `id_usuario_creacion` bigint(11) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `id_usuario_actualizacion` bigint(11) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_tipo_bodega`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `in_tipo_bodega`
--

LOCK TABLES `in_tipo_bodega` WRITE;
/*!40000 ALTER TABLE `in_tipo_bodega` DISABLE KEYS */;
/*!40000 ALTER TABLE `in_tipo_bodega` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `in_tipo_documento`
--

DROP TABLE IF EXISTS `in_tipo_documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `in_tipo_documento` (
  `id_tipo_documento` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre_documento` varchar(45) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_tipo_documento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `in_tipo_documento`
--

LOCK TABLES `in_tipo_documento` WRITE;
/*!40000 ALTER TABLE `in_tipo_documento` DISABLE KEYS */;
/*!40000 ALTER TABLE `in_tipo_documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `in_tipo_movimiento`
--

DROP TABLE IF EXISTS `in_tipo_movimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `in_tipo_movimiento` (
  `id_tipo_movimiento` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre_movimiento` varchar(70) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actuliazacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_tipo_movimiento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `in_tipo_movimiento`
--

LOCK TABLES `in_tipo_movimiento` WRITE;
/*!40000 ALTER TABLE `in_tipo_movimiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `in_tipo_movimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pr_articulo`
--

DROP TABLE IF EXISTS `pr_articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pr_articulo` (
  `id_articulo` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_grupo` bigint(20) NOT NULL,
  `id_subgrupo` bigint(20) NOT NULL,
  `nombre_articulo` varchar(70) DEFAULT NULL,
  `estado` char(2) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_articulo`,`id_grupo`,`id_subgrupo`),
  KEY `fk_gru_sub_articulo$1` (`id_grupo`,`id_subgrupo`),
  CONSTRAINT `fk_gru_sub_articulo$1` FOREIGN KEY (`id_grupo`, `id_subgrupo`) REFERENCES `pr_subgrupos` (`id_subgrupo`, `id_grupo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_articulo`
--

LOCK TABLES `pr_articulo` WRITE;
/*!40000 ALTER TABLE `pr_articulo` DISABLE KEYS */;
INSERT INTO `pr_articulo` VALUES (1,1,1,'pastilla','A',1,'2019-04-18 15:42:36',1,'2019-04-24 15:42:36'),(2,1,1,'kelly','A',NULL,NULL,NULL,NULL),(3,1,1,'prueba','A',NULL,NULL,NULL,NULL),(4,2,1,'CREMAS','A',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `pr_articulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pr_detalle_tarifario`
--

DROP TABLE IF EXISTS `pr_detalle_tarifario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pr_detalle_tarifario` (
  `id_detalle_tarifario` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_tarifario` bigint(20) NOT NULL,
  `id_prestacion` bigint(20) NOT NULL,
  `id_empresa` bigint(20) NOT NULL,
  `id_surcusal` bigint(20) NOT NULL,
  `id_unidad_servicio` bigint(20) NOT NULL,
  `valor_costo` double(10,7) DEFAULT NULL,
  `valor_min_venta` double(10,7) DEFAULT NULL,
  `valor_venta` double(10,7) DEFAULT NULL,
  `valor_descuento` double(10,7) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_detalle_tarifario`,`id_prestacion`,`id_tarifario`,`id_empresa`,`id_surcusal`,`id_unidad_servicio`),
  KEY `fk_tarifario$1` (`id_tarifario`),
  KEY `fk_tarifario_empresa_sucursal$1` (`id_tarifario`,`id_empresa`,`id_surcusal`),
  KEY `fk_unidad_servicio$2` (`id_unidad_servicio`),
  KEY `fk_prestacion$1` (`id_prestacion`,`id_empresa`),
  CONSTRAINT `fk_prestacion$1` FOREIGN KEY (`id_prestacion`, `id_empresa`) REFERENCES `pr_prestaciones` (`id_prestacion`, `id_empresa`),
  CONSTRAINT `fk_tarifario_empresa_sucursal$1` FOREIGN KEY (`id_tarifario`, `id_empresa`, `id_surcusal`) REFERENCES `pr_tarifario` (`id_tarifario`, `id_empresa`, `id_surcusal`),
  CONSTRAINT `fk_unidad_servicio$2` FOREIGN KEY (`id_unidad_servicio`) REFERENCES `ve_unidad_servicio` (`id_unidad_servicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_detalle_tarifario`
--

LOCK TABLES `pr_detalle_tarifario` WRITE;
/*!40000 ALTER TABLE `pr_detalle_tarifario` DISABLE KEYS */;
/*!40000 ALTER TABLE `pr_detalle_tarifario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pr_fabricante`
--

DROP TABLE IF EXISTS `pr_fabricante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pr_fabricante` (
  `id_fabricante` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(70) DEFAULT NULL,
  `estado` char(2) DEFAULT NULL,
  `id_usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `id_usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_fabricante`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_fabricante`
--

LOCK TABLES `pr_fabricante` WRITE;
/*!40000 ALTER TABLE `pr_fabricante` DISABLE KEYS */;
/*!40000 ALTER TABLE `pr_fabricante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pr_grupos`
--

DROP TABLE IF EXISTS `pr_grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pr_grupos` (
  `id_grupo` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_empresa` bigint(20) DEFAULT NULL,
  `nombre` varchar(60) DEFAULT NULL,
  `estado` char(2) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_grupo`),
  KEY `fk_empresa_grupo$1` (`id_empresa`),
  CONSTRAINT `fk_empresa_grupo$1` FOREIGN KEY (`id_empresa`) REFERENCES `se_empresa` (`id_empresa`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_grupos`
--

LOCK TABLES `pr_grupos` WRITE;
/*!40000 ALTER TABLE `pr_grupos` DISABLE KEYS */;
INSERT INTO `pr_grupos` VALUES (1,1,'MEDICINA','A',1,'2019-04-18 15:42:36',1,'2019-04-23 15:42:36');
