
drop database dgac;
create database dgac;
use dgac;
CREATE TABLE `acervo` (
  `idReg` bigint(20) NOT NULL AUTO_INCREMENT,
  `fechaHoraInsercion` bigint(20) NOT NULL,
  `nuevaColocacionBoveda` varchar(20) NOT NULL,
  `TITULO_ORIGINAL` varchar(500) DEFAULT NULL,
  `TITULO_EN_ESPA` varchar(400) DEFAULT NULL,
  `REALIZADOR` varchar(500) DEFAULT NULL,
  `PAIS_DE_REALIZACION` varchar(375) DEFAULT NULL,
  `A` varchar(30) DEFAULT NULL,
  `FORMATO` varchar(25) DEFAULT NULL,
  `SOPORTE` varchar(7) DEFAULT NULL,
  `EMULSION` varchar(32) DEFAULT NULL,
  `COLOR` varchar(32) DEFAULT NULL,
  `AUDIO` varchar(175) DEFAULT NULL,
  `TAMA` varchar(125) DEFAULT NULL,
  `METRAJE` varchar(25) DEFAULT NULL,
  `DA` varchar(2500) DEFAULT NULL,
  `ESTADO_FISICO` varchar(50) DEFAULT NULL,
  `NORMA` varchar(125) DEFAULT NULL,
  `CUADRO` varchar(25) DEFAULT NULL,
  `DURACION` varchar(37) DEFAULT NULL,
  `REGION` varchar(25) DEFAULT NULL,
  `PANTALLA` varchar(12) DEFAULT NULL,
  `FUENTE` varchar(75) DEFAULT NULL,
  `ORIGEN` varchar(250) DEFAULT NULL,
  `FECHA_DE_GRABACION` varchar(50) DEFAULT NULL,
  `FECHA_DE_REVISION` varchar(50) DEFAULT NULL,
  `FECHA_DE_INGRESO` varchar(62) DEFAULT NULL,
  `FECHA_DE_ACIDEZ` varchar(62) DEFAULT NULL,
  `FECHA_DE_RESULTADO` varchar(62) DEFAULT NULL,
  `FECHA_DE_CAPTURA` varchar(62) DEFAULT NULL,
  `FECHA_DE_BAJA` varchar(62) DEFAULT NULL,
  `ORIGEN500` varchar(250) DEFAULT NULL,
  `COLOCACION` varchar(250) DEFAULT NULL,
  `DISTRIBUCION` varchar(250) DEFAULT NULL,
  `PROGRAMACION` varchar(250) DEFAULT NULL,
  `BIBLIOTECA` varchar(250) DEFAULT NULL,
  `METRAJE_ORIGINAL` varchar(50) DEFAULT NULL,
  `INCOM_IMG` varchar(25) DEFAULT NULL,
  `ST` varchar(25) DEFAULT NULL,
  `OTROS` varchar(250) DEFAULT NULL,
  `REPARACIONES` varchar(2100) DEFAULT NULL,
  `OBSERVACIONES` varchar(4125) DEFAULT NULL,
  `NOTAS_HISTORICAS` varchar(4125) DEFAULT NULL,
  `PRESTAMO` varchar(145) DEFAULT NULL,
  `INSTITUCION_PRESTAMO` varchar(375) DEFAULT NULL,
  `CONDUCTO_PRESTAMO` varchar(250) DEFAULT NULL,
  `FECHA_DE_SALIDA` varchar(62) DEFAULT NULL,
  `FECHA_DE_RETORNO` varchar(62) DEFAULT NULL,
  `NO_CEDULA_RECIBO` varchar(75) DEFAULT NULL,
  `NOMBRE_REVISION` varchar(625) DEFAULT NULL,
  `NOMBRE_CAPTURA` varchar(625) DEFAULT NULL,
  `POSIBILIDAD_DE_EXHIBICION` varchar(2) DEFAULT NULL,
  `POSIBILIDAD_DE_PRESTAMO` varchar(2) DEFAULT NULL,
  `RESTRICCIONES` varchar(125) DEFAULT NULL,
  `EXTRAS` varchar(125) DEFAULT NULL,
  `STATUS_ACERVO` varchar(2) DEFAULT NULL,
  `STATUS_DISTRIBUCION` varchar(2) DEFAULT NULL,
  `STATUS_PROGRAMACION` varchar(2) DEFAULT NULL,
  `STATUS_BIBLIOTECA` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`idReg`)
) ENGINE=InnoDB AUTO_INCREMENT=49042 DEFAULT CHARSET=utf8;
select count(*) from acervo;