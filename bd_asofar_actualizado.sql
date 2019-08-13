CREATE DATABASE  IF NOT EXISTS `bd_asofar` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bd_asofar`;
-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: localhost    Database: bd_asofar
-- ------------------------------------------------------
-- Server version	5.7.25-0ubuntu0.16.04.2

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
  CONSTRAINT `fk_movimiento_bode$7` FOREIGN KEY (`id_bodega`, `id_tipo_bodega`, `id_empresa`, `id_sucursal`) REFERENCES `in_bodega` (`id_bodega`, `id_tipo_bodega`, `id_empresa`, `id_sucursal`) ON DELETE NO ACTION ON UPDATE NO ACTION,
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
  `id_tipo_bodega` bigint(20) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_articulo`
--

LOCK TABLES `pr_articulo` WRITE;
/*!40000 ALTER TABLE `pr_articulo` DISABLE KEYS */;
INSERT INTO `pr_articulo` VALUES (1,1,1,'pastilla','A',1,'2019-04-18 15:42:36',1,'2019-04-24 15:42:36');
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
/*!40000 ALTER TABLE `pr_grupos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pr_medidas`
--

DROP TABLE IF EXISTS `pr_medidas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pr_medidas` (
  `id_articulo` bigint(20) NOT NULL,
  `id_grupo` bigint(20) NOT NULL,
  `id_subgrupo` bigint(20) NOT NULL,
  `id_tipo_presentacion` bigint(20) NOT NULL,
  `id_tipo_medidas` bigint(20) NOT NULL,
  `estado` char(2) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_articulo`,`id_grupo`,`id_subgrupo`,`id_tipo_presentacion`,`id_tipo_medidas`),
  KEY `fk_tipoPre_medi$2` (`id_tipo_presentacion`),
  KEY `fk_tipoMed_medi$3_idx` (`id_tipo_medidas`),
  CONSTRAINT `fk_articulo_medida$1` FOREIGN KEY (`id_articulo`, `id_grupo`, `id_subgrupo`) REFERENCES `pr_articulo` (`id_articulo`, `id_grupo`, `id_subgrupo`),
  CONSTRAINT `fk_tipoMed_medi$3` FOREIGN KEY (`id_tipo_medidas`) REFERENCES `pr_tipo_medidas` (`id_tipo_medidas`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tipoPre_medi$2` FOREIGN KEY (`id_tipo_presentacion`) REFERENCES `pr_tipo_presentacion` (`id_tipo_presentacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_medidas`
--

LOCK TABLES `pr_medidas` WRITE;
/*!40000 ALTER TABLE `pr_medidas` DISABLE KEYS */;
/*!40000 ALTER TABLE `pr_medidas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pr_prestaciones`
--

DROP TABLE IF EXISTS `pr_prestaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pr_prestaciones` (
  `id_prestacion` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_empresa` bigint(20) NOT NULL,
  `id_poducto` bigint(20) DEFAULT NULL,
  `nombre_prestacion` varchar(70) DEFAULT NULL,
  `estado` char(2) DEFAULT NULL,
  `aplica_iva` char(2) DEFAULT NULL,
  PRIMARY KEY (`id_prestacion`,`id_empresa`),
  KEY `fk_empresa$3` (`id_empresa`),
  CONSTRAINT `fk_empresa$3` FOREIGN KEY (`id_empresa`) REFERENCES `se_empresa` (`id_empresa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_prestaciones`
--

LOCK TABLES `pr_prestaciones` WRITE;
/*!40000 ALTER TABLE `pr_prestaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `pr_prestaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pr_producto_bodega`
--

DROP TABLE IF EXISTS `pr_producto_bodega`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pr_producto_bodega` (
  `id_producto_bodega` bigint(11) NOT NULL AUTO_INCREMENT,
  `id_bodega` bigint(11) NOT NULL,
  `id_producto` bigint(11) NOT NULL,
  `id_empresa` bigint(11) NOT NULL,
  `id_sucursal` bigint(11) NOT NULL,
  `id_tipo_bodega` bigint(11) NOT NULL,
  `estado` varchar(2) DEFAULT NULL,
  `stock_minimo` bigint(11) DEFAULT NULL,
  `stock_maximo` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id_producto_bodega`,`id_bodega`,`id_producto`,`id_empresa`,`id_sucursal`,`id_tipo_bodega`),
  KEY `fk_empresa_sucursal$7` (`id_empresa`,`id_sucursal`),
  CONSTRAINT `fk_empresa_sucursal$7` FOREIGN KEY (`id_empresa`, `id_sucursal`) REFERENCES `se_sucursal` (`id_empresa`, `id_sucursal`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_producto_bodega`
--

LOCK TABLES `pr_producto_bodega` WRITE;
/*!40000 ALTER TABLE `pr_producto_bodega` DISABLE KEYS */;
/*!40000 ALTER TABLE `pr_producto_bodega` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pr_productos`
--

DROP TABLE IF EXISTS `pr_productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pr_productos` (
  `id_producto` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigo_barra` text,
  `id_empresa` bigint(20) NOT NULL,
  `id_articulo` bigint(20) NOT NULL,
  `id_grupo` bigint(20) NOT NULL,
  `id_subgrupo` bigint(20) NOT NULL,
  `id_tipo_presentacion` bigint(20) NOT NULL,
  `id_tipo_medidas` bigint(20) NOT NULL,
  `cod_fabricante` text,
  `descontinuado` char(2) DEFAULT NULL,
  `nombre_producto` varchar(70) DEFAULT NULL,
  `registro_sanitario_local` text,
  `registro_sanitario_extranjero` text,
  `receta` char(3) DEFAULT NULL,
  `estado` char(2) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_producto`,`id_empresa`,`id_articulo`,`id_grupo`,`id_subgrupo`,`id_tipo_presentacion`,`id_tipo_medidas`),
  KEY `fk_tipo_presentacion$2` (`id_tipo_presentacion`),
  KEY `fk_empresa$6` (`id_empresa`),
  KEY `fk_medida_product$1` (`id_articulo`,`id_grupo`,`id_subgrupo`,`id_tipo_presentacion`,`id_tipo_medidas`),
  CONSTRAINT `fk_empresa$6` FOREIGN KEY (`id_empresa`) REFERENCES `se_empresa` (`id_empresa`),
  CONSTRAINT `fk_medida_product$1` FOREIGN KEY (`id_articulo`, `id_grupo`, `id_subgrupo`, `id_tipo_presentacion`, `id_tipo_medidas`) REFERENCES `pr_medidas` (`id_articulo`, `id_grupo`, `id_subgrupo`, `id_tipo_presentacion`, `id_tipo_medidas`),
  CONSTRAINT `fk_tipo_presentacion$2` FOREIGN KEY (`id_tipo_presentacion`) REFERENCES `pr_tipo_presentacion` (`id_tipo_presentacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_productos`
--

LOCK TABLES `pr_productos` WRITE;
/*!40000 ALTER TABLE `pr_productos` DISABLE KEYS */;
/*!40000 ALTER TABLE `pr_productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pr_subgrupos`
--

DROP TABLE IF EXISTS `pr_subgrupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pr_subgrupos` (
  `id_subgrupo` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_grupo` bigint(20) NOT NULL,
  `id_empresa` bigint(20) DEFAULT NULL,
  `nombre` varchar(70) DEFAULT NULL,
  `estado` char(2) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_subgrupo`,`id_grupo`),
  KEY `fk_grupo_sub$1` (`id_grupo`),
  KEY `fk_empres$4` (`id_empresa`),
  CONSTRAINT `fk_empres$4` FOREIGN KEY (`id_empresa`) REFERENCES `se_empresa` (`id_empresa`),
  CONSTRAINT `fk_grupo_sub$1` FOREIGN KEY (`id_grupo`) REFERENCES `pr_grupos` (`id_grupo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_subgrupos`
--

LOCK TABLES `pr_subgrupos` WRITE;
/*!40000 ALTER TABLE `pr_subgrupos` DISABLE KEYS */;
INSERT INTO `pr_subgrupos` VALUES (1,1,1,'PASTILLAS','A',1,'2019-04-18 15:43:43',NULL,NULL);
/*!40000 ALTER TABLE `pr_subgrupos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pr_tarifario`
--

DROP TABLE IF EXISTS `pr_tarifario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pr_tarifario` (
  `id_tarifario` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_empresa` bigint(20) NOT NULL,
  `id_surcusal` bigint(20) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `fecha_inicio_vigente` datetime DEFAULT NULL,
  `fecha_fin_vigente` datetime DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_tarifario`,`id_empresa`,`id_surcusal`),
  KEY `fk_empresa_sucursal$5` (`id_empresa`,`id_surcusal`),
  CONSTRAINT `fk_empresa_sucursal$5` FOREIGN KEY (`id_empresa`, `id_surcusal`) REFERENCES `se_sucursal` (`id_empresa`, `id_sucursal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_tarifario`
--

LOCK TABLES `pr_tarifario` WRITE;
/*!40000 ALTER TABLE `pr_tarifario` DISABLE KEYS */;
/*!40000 ALTER TABLE `pr_tarifario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pr_tipo_medidas`
--

DROP TABLE IF EXISTS `pr_tipo_medidas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pr_tipo_medidas` (
  `id_tipo_medidas` bigint(20) NOT NULL,
  `id_empresa` bigint(20) DEFAULT NULL,
  `nombre_tipo_medida` varchar(70) DEFAULT NULL,
  `estado` char(2) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_tipo_medidas`),
  KEY `fk_empre$4_idx` (`id_empresa`),
  CONSTRAINT `fk_empre$4` FOREIGN KEY (`id_empresa`) REFERENCES `se_empresa` (`id_empresa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_tipo_medidas`
--

LOCK TABLES `pr_tipo_medidas` WRITE;
/*!40000 ALTER TABLE `pr_tipo_medidas` DISABLE KEYS */;
INSERT INTO `pr_tipo_medidas` VALUES (1,1,'ML','A',1,'2019-04-18 15:42:36',1,'2019-04-24 15:42:36');
/*!40000 ALTER TABLE `pr_tipo_medidas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pr_tipo_presentacion`
--

DROP TABLE IF EXISTS `pr_tipo_presentacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pr_tipo_presentacion` (
  `id_tipo_presentacion` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(70) DEFAULT NULL,
  `estado` char(2) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_tipo_presentacion`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_tipo_presentacion`
--

LOCK TABLES `pr_tipo_presentacion` WRITE;
/*!40000 ALTER TABLE `pr_tipo_presentacion` DISABLE KEYS */;
INSERT INTO `pr_tipo_presentacion` VALUES (1,'FRASCO','A',1,'2019-04-18 15:42:36',1,'2019-04-24 15:42:36');
/*!40000 ALTER TABLE `pr_tipo_presentacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `se_empresa`
--

DROP TABLE IF EXISTS `se_empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `se_empresa` (
  `id_empresa` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre_comercial` varchar(70) DEFAULT NULL,
  `ruc` varchar(13) DEFAULT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `direccion` varchar(70) DEFAULT NULL,
  `correo` varchar(70) DEFAULT NULL,
  `usuario_creacion` bigint(70) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  `estado` char(1) DEFAULT NULL,
  PRIMARY KEY (`id_empresa`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `se_empresa`
--

LOCK TABLES `se_empresa` WRITE;
/*!40000 ALTER TABLE `se_empresa` DISABLE KEYS */;
INSERT INTO `se_empresa` VALUES (1,'empresa literal','943894384389','09872737','laala','lalalalsk',1,'2019-04-17 16:34:56',NULL,NULL,NULL);
/*!40000 ALTER TABLE `se_empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `se_opciones_menu`
--

DROP TABLE IF EXISTS `se_opciones_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `se_opciones_menu` (
  `id_opciones_menu` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(70) DEFAULT NULL,
  `descripcion` varchar(70) DEFAULT NULL,
  `estado` char(1) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_opciones_menu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `se_opciones_menu`
--

LOCK TABLES `se_opciones_menu` WRITE;
/*!40000 ALTER TABLE `se_opciones_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `se_opciones_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `se_opciones_roles`
--

DROP TABLE IF EXISTS `se_opciones_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `se_opciones_roles` (
  `id_opciones_roles` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_opciones_menu` bigint(20) NOT NULL,
  `id_rol` bigint(20) NOT NULL,
  `estado` char(1) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_opciones_roles`,`id_opciones_menu`,`id_rol`),
  KEY `opc_roles` (`id_rol`),
  KEY `opc_menu` (`id_opciones_menu`),
  CONSTRAINT `opc_menu` FOREIGN KEY (`id_opciones_menu`) REFERENCES `se_opciones_menu` (`id_opciones_menu`),
  CONSTRAINT `opc_roles` FOREIGN KEY (`id_rol`) REFERENCES `se_roles` (`id_roles`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `se_opciones_roles`
--

LOCK TABLES `se_opciones_roles` WRITE;
/*!40000 ALTER TABLE `se_opciones_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `se_opciones_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `se_personas`
--

DROP TABLE IF EXISTS `se_personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `se_personas` (
  `id_persona` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_tipo_persona` bigint(20) DEFAULT NULL,
  `cedula` varchar(10) DEFAULT NULL,
  `nombres` varchar(50) DEFAULT NULL,
  `apellidos` varchar(50) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `telefono2` varchar(10) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  `estado` char(1) DEFAULT NULL,
  PRIMARY KEY (`id_persona`),
  KEY `fk_se_personas_tipo` (`id_tipo_persona`),
  CONSTRAINT `fk_se_personas_tipo` FOREIGN KEY (`id_tipo_persona`) REFERENCES `se_tipo_persona` (`id_tipo_persona`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `se_personas`
--

LOCK TABLES `se_personas` WRITE;
/*!40000 ALTER TABLE `se_personas` DISABLE KEYS */;
/*!40000 ALTER TABLE `se_personas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `se_roles`
--

DROP TABLE IF EXISTS `se_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `se_roles` (
  `id_roles` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `estado` char(1) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_roles`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `se_roles`
--

LOCK TABLES `se_roles` WRITE;
/*!40000 ALTER TABLE `se_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `se_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `se_sucursal`
--

DROP TABLE IF EXISTS `se_sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `se_sucursal` (
  `id_sucursal` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_empresa` bigint(20) NOT NULL,
  `nombre_comercial` varchar(50) DEFAULT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `direccion` varchar(70) DEFAULT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  `estado` char(1) DEFAULT NULL,
  PRIMARY KEY (`id_sucursal`,`id_empresa`),
  KEY `sucursal_empresa` (`id_empresa`),
  CONSTRAINT `fk_empresa_sucursal$1` FOREIGN KEY (`id_empresa`) REFERENCES `se_empresa` (`id_empresa`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `se_sucursal`
--

LOCK TABLES `se_sucursal` WRITE;
/*!40000 ALTER TABLE `se_sucursal` DISABLE KEYS */;
INSERT INTO `se_sucursal` VALUES (1,1,'alejandro meza','2736726727','tu mama','la nalga de ambar',1,'2019-04-17 18:10:03',NULL,NULL,NULL),(2,1,'alejandro 2 mesa 3','8438438438','tu nalga','la nalga de reina',1,'2019-04-17 19:21:02',NULL,NULL,NULL);
/*!40000 ALTER TABLE `se_sucursal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `se_tipo_persona`
--

DROP TABLE IF EXISTS `se_tipo_persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `se_tipo_persona` (
  `id_tipo_persona` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `estado` char(1) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_tipo_persona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `se_tipo_persona`
--

LOCK TABLES `se_tipo_persona` WRITE;
/*!40000 ALTER TABLE `se_tipo_persona` DISABLE KEYS */;
/*!40000 ALTER TABLE `se_tipo_persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `se_usuario_sucur_rol`
--

DROP TABLE IF EXISTS `se_usuario_sucur_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `se_usuario_sucur_rol` (
  `id_usuario_sucur_rol` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_sucursal` bigint(20) NOT NULL,
  `id_empresa` bigint(20) NOT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  `id_roles` bigint(20) DEFAULT NULL,
  `estado` char(1) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_usuario_sucur_rol`,`id_empresa`,`id_sucursal`),
  KEY `usuario_rol` (`id_roles`),
  KEY `usuario_user` (`id_usuario`),
  KEY `fk_sucursal_empresa_rol_usuario$1` (`id_sucursal`,`id_empresa`),
  CONSTRAINT `fk_roles$1` FOREIGN KEY (`id_roles`) REFERENCES `se_roles` (`id_roles`),
  CONSTRAINT `fk_sucursal_empresa_rol_usuario$1` FOREIGN KEY (`id_sucursal`, `id_empresa`) REFERENCES `se_sucursal` (`id_sucursal`, `id_empresa`),
  CONSTRAINT `fk_usuario$1` FOREIGN KEY (`id_usuario`) REFERENCES `se_usuarios` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `se_usuario_sucur_rol`
--

LOCK TABLES `se_usuario_sucur_rol` WRITE;
/*!40000 ALTER TABLE `se_usuario_sucur_rol` DISABLE KEYS */;
/*!40000 ALTER TABLE `se_usuario_sucur_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `se_usuarios`
--

DROP TABLE IF EXISTS `se_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `se_usuarios` (
  `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_persona` bigint(20) DEFAULT NULL,
  `id_tipo_persona` bigint(20) DEFAULT NULL,
  `estado` char(1) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  `correo` varchar(70) DEFAULT NULL,
  `telefono` varchar(70) DEFAULT NULL,
  `telefono2` varchar(70) DEFAULT NULL,
  `departamento` varchar(70) DEFAULT NULL,
  `nombre_usuario` varchar(70) DEFAULT NULL,
  `password` text,
  `foto` binary(1) DEFAULT NULL,
  `sueldo` double(10,7) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `usuario_persona` (`id_persona`),
  CONSTRAINT `fk_usuario_tp$1` FOREIGN KEY (`id_persona`) REFERENCES `se_personas` (`id_persona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `se_usuarios`
--

LOCK TABLES `se_usuarios` WRITE;
/*!40000 ALTER TABLE `se_usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `se_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ve_factura`
--

DROP TABLE IF EXISTS `ve_factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ve_factura` (
  `id_factura` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_caja` bigint(20) DEFAULT NULL,
  `id_empresa` bigint(20) NOT NULL,
  `id_sucursal` bigint(20) DEFAULT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  `id_cliente` bigint(20) DEFAULT NULL,
  `fecha_facturacion` datetime DEFAULT NULL,
  `numero_establecimiento_sri` varchar(45) DEFAULT NULL,
  `punto_emision_sri` varchar(45) DEFAULT NULL,
  `secuencia_sri` varchar(45) DEFAULT NULL,
  `subtotal` double(10,7) DEFAULT NULL,
  `total_ice` double(10,7) DEFAULT NULL,
  `total_descuento` double(10,7) DEFAULT NULL,
  `total_base_iva` double(10,7) DEFAULT NULL,
  `total_base_no_iva` double(10,7) DEFAULT NULL,
  `total_iva` double(10,7) DEFAULT NULL,
  `total_facturado` double(10,7) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `despachado` varchar(45) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_factura`,`id_empresa`),
  KEY `fk_sucursal$2` (`id_sucursal`),
  KEY `fk_usuario$2` (`id_usuario`),
  KEY `fk_cliente$2` (`id_cliente`),
  KEY `fk_empresa_sucursal$2` (`id_empresa`,`id_sucursal`),
  CONSTRAINT `fk_cliente$2` FOREIGN KEY (`id_cliente`) REFERENCES `se_personas` (`id_persona`),
  CONSTRAINT `fk_empresa_sucursal$2` FOREIGN KEY (`id_empresa`, `id_sucursal`) REFERENCES `se_sucursal` (`id_empresa`, `id_sucursal`),
  CONSTRAINT `fk_usuario$2` FOREIGN KEY (`id_usuario`) REFERENCES `se_usuarios` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ve_factura`
--

LOCK TABLES `ve_factura` WRITE;
/*!40000 ALTER TABLE `ve_factura` DISABLE KEYS */;
/*!40000 ALTER TABLE `ve_factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ve_factura_detalle`
--

DROP TABLE IF EXISTS `ve_factura_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ve_factura_detalle` (
  `id_factura_detalle` bigint(20) NOT NULL AUTO_INCREMENT,
  `linea_detalle` bigint(20) NOT NULL,
  `id_factura` bigint(20) NOT NULL,
  `id_empresa` bigint(20) NOT NULL,
  `id_sucursal` bigint(20) NOT NULL,
  `id_prestaciones` bigint(20) NOT NULL,
  `id_unidad_servicio` bigint(20) NOT NULL,
  `cantidad` bigint(20) DEFAULT NULL,
  `precio_unitario_venta` double(10,7) DEFAULT NULL,
  `valor_ice` double(10,7) DEFAULT NULL,
  `valor_iva` double(10,7) DEFAULT NULL,
  `valor_descuento` double(10,7) DEFAULT NULL,
  `valor_total` double(10,7) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_factura_detalle`,`id_factura`,`id_empresa`,`id_sucursal`,`id_prestaciones`,`id_unidad_servicio`),
  KEY `fk_unidad_servicio$1` (`id_unidad_servicio`),
  KEY `fk_empresa_sucursal$3` (`id_empresa`,`id_sucursal`),
  KEY `fk_factura$1` (`id_factura`,`id_empresa`),
  KEY `fk_prestaciones$1` (`id_prestaciones`,`id_empresa`),
  CONSTRAINT `fk_empresa_sucursal$3` FOREIGN KEY (`id_empresa`, `id_sucursal`) REFERENCES `se_sucursal` (`id_empresa`, `id_sucursal`),
  CONSTRAINT `fk_factura$1` FOREIGN KEY (`id_factura`, `id_empresa`) REFERENCES `ve_factura` (`id_factura`, `id_empresa`),
  CONSTRAINT `fk_prestaciones$1` FOREIGN KEY (`id_prestaciones`, `id_empresa`) REFERENCES `pr_prestaciones` (`id_prestacion`, `id_empresa`),
  CONSTRAINT `fk_unidad_servicio$1` FOREIGN KEY (`id_unidad_servicio`) REFERENCES `ve_unidad_servicio` (`id_unidad_servicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ve_factura_detalle`
--

LOCK TABLES `ve_factura_detalle` WRITE;
/*!40000 ALTER TABLE `ve_factura_detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `ve_factura_detalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ve_unidad_servicio`
--

DROP TABLE IF EXISTS `ve_unidad_servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ve_unidad_servicio` (
  `id_unidad_servicio` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_empresa` bigint(20) DEFAULT NULL,
  `nombre_unidad_servicio` varchar(45) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `usuario_creacion` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_actualizacion` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_unidad_servicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ve_unidad_servicio`
--

LOCK TABLES `ve_unidad_servicio` WRITE;
/*!40000 ALTER TABLE `ve_unidad_servicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `ve_unidad_servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bd_asofar'
--

--
-- Dumping routines for database 'bd_asofar'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-24 17:22:23
