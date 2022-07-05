SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `online`
--

CREATE DATABASE online;

USE online;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `Marketplace` varchar(3) DEFAULT NULL,
  `idPedido` varchar(25) DEFAULT NULL,
  `Fecha` datetime DEFAULT NULL,
  `Nombre_Cliente` varchar(50) DEFAULT NULL,
  `direccion` varchar(59) DEFAULT NULL,
  `DNI` varchar(10) DEFAULT NULL,
  `CP` varchar(10) DEFAULT NULL,
  `Poblacion` varchar(46) DEFAULT NULL,
  `Provincia` varchar(33) DEFAULT NULL,
  `Telefono` varchar(20) DEFAULT NULL,
  `idarticulo` varchar(13) DEFAULT NULL,
  `Descripcion` varchar(199) DEFAULT NULL,
  `Peso` int(3) DEFAULT NULL,
  `Bultos` int(2) DEFAULT NULL,
  `Importe` float DEFAULT NULL,
  `Impporte` float DEFAULT NULL,
  `TotalPedido` float DEFAULT NULL,
  `Comision` float DEFAULT NULL,
  `idArticuloA` varchar(1) DEFAULT NULL,
  `AceptaCambio` tinyint(1) NOT NULL,
  `FechaSalida` date DEFAULT NULL,
  `Albaran` tinyint(1) NOT NULL,
  `AlmacenSalida` varchar(3) DEFAULT NULL,
  `Agencia` varchar(16) DEFAULT NULL,
  `RecogidaUsado` tinyint(1) NOT NULL,
  `Tiquet` varchar(10) DEFAULT NULL,
  `FechaTiquet` date DEFAULT NULL,
  `NumSerie` bigint(20) DEFAULT NULL,
  `NumeroSeguimiento` varchar(19) DEFAULT NULL,
  `HomeLogistics` varchar(15) DEFAULT NULL,
  `IdServicio` varchar(10) DEFAULT NULL,
  `Cancelado` tinyint(1) NOT NULL,
  `RT` varchar(5) DEFAULT NULL,
  `Observaciones` varchar(170) DEFAULT NULL,
  `Sistema` date DEFAULT NULL,
  `PedidoIndot` varchar(10) DEFAULT NULL,
  `CostePortes` float DEFAULT NULL,
  `CosteArticulo` float DEFAULT NULL,
  `Estado` varchar(13) DEFAULT NULL,
  `Proveedor` varchar(10) DEFAULT NULL,
  `FPedido` date DEFAULT NULL,
  `FEntrega` date DEFAULT NULL,
  `Factura` varchar(10) DEFAULT NULL,
  `FechaFactura` date DEFAULT NULL,
  `Validaciones` tinyint(1) NOT NULL,
  `Finalizado` varchar(10) DEFAULT NULL,
  `Cod_Pedido` varchar(10) DEFAULT NULL,
  `Agencia_Proveedor` varchar(10) DEFAULT NULL,
  `URL_tracking` varchar(10) DEFAULT NULL,
  `Tipo_Articulo` varchar(6) DEFAULT NULL,
  `GOi` varchar(16) DEFAULT NULL,
  `ServicioGOi` varchar(40) DEFAULT NULL,
  `Tienda` varchar(15) DEFAULT NULL,
  `TIMESTAMP_Auto` timestamp NULL DEFAULT current_timestamp(),
  `expedicion_dachser` varchar(40) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `volumen` double NOT NULL DEFAULT 0.1,
  `numeroAlbaran` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`Marketplace`, `idPedido`, `Fecha`, `Nombre_Cliente`, `direccion`, `DNI`, `CP`, `Poblacion`, `Provincia`, `Telefono`, `idarticulo`, `Descripcion`, `Peso`, `Bultos`, `Importe`, `Impporte`, `TotalPedido`, `Comision`, `idArticuloA`, `AceptaCambio`, `FechaSalida`, `Albaran`, `AlmacenSalida`, `Agencia`, `RecogidaUsado`, `Tiquet`, `FechaTiquet`, `NumSerie`, `NumeroSeguimiento`, `HomeLogistics`, `IdServicio`, `Cancelado`, `RT`, `Observaciones`, `Sistema`, `PedidoIndot`, `CostePortes`, `CosteArticulo`, `Estado`, `Proveedor`, `FPedido`, `FEntrega`, `Factura`, `FechaFactura`, `Validaciones`, `Finalizado`, `Cod_Pedido`, `Agencia_Proveedor`, `URL_tracking`, `Tipo_Articulo`, `GOi`, `ServicioGOi`, `Tienda`, `TIMESTAMP_Auto`, `expedicion_dachser`, `email`, `volumen`, `numeroAlbaran`) VALUES
('FNA', '1WWT552GLW6', '2022-06-10 20:01:26', 'JUAN JOSE MOTA', 'CALLE FERNANDO EL CATOLICO 6', '', '28015', 'MADRID', NULL, '600123456', '4242005237265', 'Secadora BOSCH', 46, 1, 700.00, 0, 700.00, 73.31, NULL, 0, NULL, 0, '003', 'TDL', 0, NULL, NULL, NULL, '1045551375', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 'PEDIDO', NULL, '2022-06-11', '2022-06-16', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Zasca', '2022-06-10 21:58:55', NULL, '\"c-47135624-108dfdab@marketplace.com\"', 0.3, ''),
('PCC', '241145-A', '2022-06-10 21:14:39', 'Alberto Muñoz', 'Avenida de Europa 133', '', '28905', 'Getafe', NULL, '612345678', '4719512064375', 'DISIPADOR COOLERMASTER', NULL, 1, 29.99, 0, 29.99, 3.52, NULL, 0, '2022-03-11', 0, '000', 'DROP GLOBO', 0, NULL, NULL, NULL, '#100456432.', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, 'PEDIDO', NULL, '2022-06-11', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Zasca', '2022-06-10 21:58:56', '2411459-A', NULL, 0.1, ''),
('PCC', '241140-B', '2022-06-10 20:46:05', 'Iván Saltos', 'Calle camino de la guija 500 Nif: 05001002C', '', '13002', 'Ciudad real', NULL, '696969696', '8421152127247', 'Microondas Teka INOX - Integración, 20 litros, 8 menús, 900W, grill 1000W', NULL, 1, 199.95, 0, 199.95, 21.25, NULL, 0, NULL, 0, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Electro', '2022-06-10 21:58:57', '2411400-B', NULL, 0.1, ''),
('PCC', '241140-B', '2022-06-10 20:46:05', 'Iván Saltos', 'Calle camino de la guija 500 Nif: 05001002C', '', '13002', 'Ciudad real', NULL, '696969696', '8434778013252', 'Campana decorativa vertical TEKA DVT 98660 TBS BK de aspiración perimetral función FresAir en 90cm', NULL, 1, 362.2, 0, 362.2, 25.35, NULL, 0, NULL, 0, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Electro', '2022-06-11 21:58:57', '2411400-C', NULL, 0.1, ''),
('WEB', 'GYYOLDND', '2022-06-10 22:23:09', 'Oleh Petryshyn', 'Calle Lleida 24', '00000000C', '08242', 'Manresa', NULL, '654321123', '6971408152858', 'Xiaomi Mi 165.1 cm (65\") 4K Ultra HD Smart Wifi Gris', NULL, 1, 625, 0, 625, 0, NULL, 0, '2022-06-11', 0, '000', 'DROP DEPUA', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, 'PEDIDO', NULL, '2022-06-11', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Electro', '2022-06-10 22:03:01', 'GYYOLDND', 'everlands@gmail.com', 0.1, ''),
('AMA', '171-9908633-80299', '2022-06-10 21:43:54', 'Loli Mengibar', 'poligono san carlos', '', '04008', 'almeria', NULL, '600000009', '8430709180338', 'Cointra Termo 80Lts, C, 809 x 450 x 472 cms.', 20, 1, 145.56, 0, 145.56, NULL, NULL, 0, '2022-06-11', -1, '003', 'TDL', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Zasca', '2022-06-10 22:58:53', '171-9908633-80299', '0cgtc33s2s3nsv5@marketplace.es', 0.14, ''),
('AMA', '403-5980567-67283', '2022-06-10 21:41:11', '  Alexey', 'Camino de la Cantera', '', '03581', 'L\'Albir', NULL, '600000007', '8434778013610', 'Lavavajillas Integración TEKA WH', 46, 1, 359.29, 0, 359.29, NULL, NULL, 0, '2022-06-11', -1, '003', 'TDL', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Zasca', '2022-06-10 22:58:53', '403-5980567-67283', 'xm0jsx5y6dp5m93@marketplace.es', 0.3, ''),
('AMA', '403-9425198-78219', '2022-06-10 21:21:07', 'Alvaro velasco', 'Calle Maestro Torres', '', '46195', 'Llombai', NULL, '600000005', '8806091217844', 'TV LED LG - TV de 32\'\' (80cm) HD-Ready. Sistema de Inteligencia Artificial SmartTV', NULL, 1, 299.67, 0, 299.67, NULL, NULL, 0, '2022-06-11', -1, '003', 'TILSA', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Zasca', '2022-06-10 22:58:53', '403-9425198-78219', '7v4hn60lj3dm76k@marketplace.es', 0.1, ''),
('AMA', '407-2147694-12387', '2022-06-09 21:36:44', 'ruben cruz', 'Calle Bonaparte 19', '', '08490', 'Tordera', NULL, '600000006', '8434778013252', 'Campana decorativa vertical TEKA TBS BK de aspiración perimetral función FresAir en 90cm', NULL, 1, 374.99, 0, 374.99, NULL, NULL, 0, '2022-03-11', -1, '003', 'TDL', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Zasca', '2022-06-10 22:58:53', '407-2147694-12387', 'qjkq2q3dm7gjlzm@marketplace.es', 0.1, ''),
('CAR', '5280102-A', '2022-06-10 21:25:09', 'Gustavo Soza', 'CALLE LA CALMA Nº50', '', '39627', 'SOBARZO', NULL, '600000004', '7332543748907', 'Frigorifico Combi AEG, No Frost, 2,01 mts, Blanco, A+++,  Twintech, Multiflow, Display L', 100, 1, 672.24, 0, 672.24, 81.34, NULL, 0, '2022-03-11', 0, '000', 'DROP ELECTROLUX', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, 'PEDIDO', NULL, '2022-06-11', '2022-06-16', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Zasca', '2022-06-10 22:58:55', '5280102-A', 'rmz0m3p4sb5@notification.net', 0.7, ''),
('PCC', '241163-A', '2022-06-10 22:50:44', 'Daniel López', 'Temprano,13,', '', '08030', 'Barcelona', NULL, '600000003', '4719512101476', 'DISIPADOR COOLERMASTER HYPER', NULL, 1, 38.35, 0, 38.35, 4.6, NULL, 0, '2022-06-11', 0, '000', 'DROP GLOBO', 0, NULL, NULL, NULL, '#100456434.', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, 'PEDIDO', NULL, '2022-06-11', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Zasca', '2022-06-10 23:58:52', '2411639-A', NULL, 0.1, ''),
('CAR', '5293446-A', '2022-06-10 22:31:30', 'Sandra Coronas', 'CALLE BONES Nº31', '', '08940', 'CORNELLA DE LLOBREGAT', NULL, '666666666', '4242005042999', 'Encimera Bosch - inducción, 2 zonas+2 de 28, memoria, sprint, 4,6kw', 10, 1, 368.51, 0, 820.34, 99.26, NULL, 0, '2022-06-11', -1, '003', 'TDL', 0, NULL, NULL, NULL, NULL, NULL, '25', 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Electro', '2022-06-10 23:58:53', '5293446-A', 'rdppjgym6py@notification.net', 0.1, ''),
('CAR', '5293446-A', '2022-06-10 22:31:30', 'Sandra Coronas', 'CALLE BONES Nº31', '', '08940', 'CORNELLA DE LLOBREGAT', NULL, '666666666', '8434778013573', 'Lavavajillas TEKA WH', 46, 1, 451.83, 0, 820.34, 99.26, NULL, 0, '2022-03-11', -1, '003', 'TDL', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Electro', '2022-06-10 23:58:53', '5293446-A', 'rdppjgym6py.dw5t9@notification.net', 0.3, ''),
('WEB', 'QDNTLXNK', '2022-06-10 23:02:11', 'Francesc Xavier', 'Passatge, 2', '40000000Q', '08188', 'Valllromanes', NULL, '600000003', '4242006254346', 'Balay hobs Negro Integrado Con placa de inducción 3 zona(s)', NULL, 1, 296.62, 0, 296.62, 0, NULL, 0, '2022-06-11', -1, '003', 'TILSA', 0, NULL, NULL, NULL, NULL, NULL, '20', 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Electro', '2022-06-11 00:03:01', 'QDNTLXNK', 'xavier@gmail.com', 0.1, ''),
('AMA', '406-1950931-02299', '2022-06-10 23:30:22', 'juan carlos', 'Rambla modolell 50', '', '08840', 'Viladecans', NULL, '+34 631123321', '8421152143865', 'Combi Teka NFL - NoFrost, 1880x595x635, antibacterias, electrónico, 295l, A+', 100, 1, 379.99, 0, 379.99, NULL, NULL, 0, '2022-03-11', -1, '003', 'TDL', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Zasca', '2022-06-11 00:58:51', '406-1950931-02299', 'wfrb9ztq4n2@marketplace.es', 0.7, ''),
('THE', 'C2203110001-A', '2022-06-10 23:04:05', 'Nikolay', 'Calle Piñonero, 59 ', '', '45005', 'Toledo', NULL, '633112011', '8421152149256', 'Termo TEKA - Eléctrico, 100 Litros, Vertical, B', 20, 1, 157.24, 0, 157.24, 11.41, NULL, 0, '2022-03-11', -1, '003', 'TDL', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Electro', '2022-06-11 00:58:55', 'C2203110001-A', NULL, 0.14, ''),
('THE', 'C2203110001-A', '2022-06-10 23:04:36', 'RAFAEL OTAÑO', 'Calle Gasset', '', '34004', 'PALENCIA', NULL, '615506901', '8434778013610', 'Lavavajillas Integración TEKA WH', 46, 1, 324.87, 0, 324.87, 23.58, NULL, 0, '2022-03-11', -1, '003', 'TDL', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Electro', '2022-06-11 00:58:55', 'C2203110001-A', NULL, 0.3, ''),
('PCC', '241177-A', '2022-06-11 01:18:23', 'Eloi López', 'c/Hospital 56, 1a Planta Nif: 41000000B', '', '08460', 'Santa Maria de Palautordera', NULL, '600000001', '4719512050125', 'DISIPADOR COOLERMASTER', NULL, 1, 38.98, 0, 38.98, 4.68, NULL, 0, '2022-03-11', 0, '000', 'DROP GLOBOMATIK', 0, NULL, NULL, NULL, '#100456435.', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, 'PEDIDO', NULL, '2022-03-11', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Electro', '2022-06-11 01:58:51', '241177-A', NULL, 0.1, ''),
('AMA', '406-5544998-39187', '2022-06-10 06:48:45', 'Nuria Gonzalez', 'C/ MAYOR, 27 ', '', '16639', 'SANTA MARIA DE LOS LLANOS', NULL, '6991112222', '4242006276515', 'Horno Balay - Multifunción, 71L, 7Funciones, A, Encastrable, Aqualisis,Panel Autolimpiante', 32, 1, 298.52, 0, 298.52, NULL, NULL, 0, '2022-03-11', -1, '003', 'TDL', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Zasca', '2022-06-11 07:58:48', '406-5544998-39187', 'xf00nkdsy436@marketplace.es', 0.21, ''),
('PCC', '241186-A', '2022-06-11 07:26:44', 'Cristina Ayala', 'C. Luis 2 Nif: 00000000W', '', '28805', 'Alcalá de Henares', NULL, '622333444', '6970885350061', 'Proyector Portátil - 150 Lúmenes/ HD/ HDMI/ Blanco', NULL, 1, 118.26, 0, 118.26, 8.28, NULL, 0, '2022-06-11', 0, '000', 'DROP DEPAU', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, 'ENTREGADO', NULL, '2022-03-11', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Electro', '2022-06-11 07:58:52', '2411862-A', NULL, 0.1, ''),
('FNA', '1WWT552GLW7', '2022-06-11 20:01:26', 'JUAN JOSE MOTA', 'CALLE FERNANDO EL CATOLICO 6', '', '28015', 'MADRID', NULL, '600123456', '4242005237265', 'Secadora BOSCH', 46, 1, 700.00, 0, 700.00, 73.31, NULL, 0, NULL, 0, '003', 'TDL', 0, NULL, NULL, NULL, '1045551375', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 'PEDIDO', NULL, '2022-06-11', '2022-06-16', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Zasca', '2022-06-11 21:58:55', NULL, '\"c-47135624-108dfdab@marketplace.com\"', 0.3, ''),
('PCC', '241146-A', '2022-06-11 21:14:39', 'Alberto Muñoz', 'Avenida de Europa 133', '', '28905', 'Getafe', NULL, '612345678', '4719512064375', 'DISIPADOR COOLERMASTER', NULL, 1, 29.99, 0, 29.99, 3.52, NULL, 0, '2022-03-11', 0, '000', 'DROP GLOBO', 0, NULL, NULL, NULL, '#100456432.', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, 'PEDIDO', NULL, '2022-06-11', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Zasca', '2022-06-11 21:58:56', '2411446-A', NULL, 0.1, ''),
('PCC', '241141-B', '2022-06-11 20:46:05', 'Iván Saltos', 'Calle camino de la guija 500 Nif: 05001002C', '', '13002', 'Ciudad real', NULL, '696969696', '8421152127247', 'Microondas Teka INOX - Integración, 20 litros, 8 menús, 900W, grill 1000W', NULL, 1, 199.95, 0, 199.95, 21.25, NULL, 0, NULL, 0, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Electro', '2022-06-11 21:58:57', '2411401-B', NULL, 0.1, ''),
('PCC', '241141-B', '2022-06-11 20:46:05', 'Iván Saltos', 'Calle camino de la guija 500 Nif: 05001002C', '', '13002', 'Ciudad real', NULL, '696969696', '8434778013252', 'Campana decorativa vertical TEKA DVT 98660 TBS BK de aspiración perimetral función FresAir en 90cm', NULL, 1, 362.2, 0, 362.2, 25.35, NULL, 0, NULL, 0, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Electro', '2022-06-11 21:58:57', '2411401-C', NULL, 0.1, ''),
('WEB', 'GYYOLDNF', '2022-06-11 22:23:09', 'Oleh Petryshyn', 'Calle Lleida 24', '00000000C', '08242', 'Manresa', NULL, '654321123', '6971408152858', 'Xiaomi Mi 165.1 cm (65\") 4K Ultra HD Smart Wifi Gris', NULL, 1, 625, 0, 625, 0, NULL, 0, '2022-06-11', 0, '000', 'DROP DEPUA', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, 'PEDIDO', NULL, '2022-06-11', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Electro', '2022-06-11 22:03:01', 'GYYOLDNF', 'everlands@gmail.com', 0.1, ''),
('AMA', '171-9908633-80300', '2022-06-11 21:43:54', 'Loli Mengibar', 'poligono san carlos', '', '04008', 'almeria', NULL, '600000009', '8430709180338', 'Cointra Termo 80Lts, C, 809 x 450 x 472 cms.', 20, 1, 145.56, 0, 145.56, NULL, NULL, 0, '2022-06-11', -1, '003', 'TDL', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Zasca', '2022-06-11 22:58:53', '171-9908633-80300', '0cgtc33s2s3nsv5@marketplace.es', 0.14, ''),
('AMA', '403-5980567-67284', '2022-06-11 21:41:11', '  Alexey', 'Camino de la Cantera', '', '03581', 'L\'Albir', NULL, '600000007', '8434778013610', 'Lavavajillas Integración TEKA WH', 46, 1, 359.29, 0, 359.29, NULL, NULL, 0, '2022-06-11', -1, '003', 'TDL', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Zasca', '2022-06-11 22:58:53', '403-5980567-67284', 'xm0jsx5y6dp5m93@marketplace.es', 0.3, ''),
('AMA', '403-9425198-78220', '2022-06-11 21:21:07', 'Alvaro velasco', 'Calle Maestro Torres', '', '46195', 'Llombai', NULL, '600000005', '8806091217844', 'TV LED LG - TV de 32\'\' (80cm) HD-Ready. Sistema de Inteligencia Artificial SmartTV', NULL, 1, 299.67, 0, 299.67, NULL, NULL, 0, '2022-06-11', -1, '003', 'TILSA', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Zasca', '2022-06-11 22:58:53', '403-9425198-78220', '7v4hn60lj3dm76k@marketplace.es', 0.1, ''),
('AMA', '407-2147694-12388', '2022-06-10 21:36:44', 'ruben cruz', 'Calle Bonaparte 19', '', '08490', 'Tordera', NULL, '600000006', '8434778013252', 'Campana decorativa vertical TEKA TBS BK de aspiración perimetral función FresAir en 90cm', NULL, 1, 374.99, 0, 374.99, NULL, NULL, 0, '2022-03-11', -1, '003', 'TDL', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Zasca', '2022-06-11 22:58:53', '407-2147694-12388', 'qjkq2q3dm7gjlzm@marketplace.es', 0.1, ''),
('CAR', '5280103-A', '2022-06-11 21:25:09', 'Gustavo Soza', 'CALLE LA CALMA Nº50', '', '39627', 'SOBARZO', NULL, '600000004', '7332543748907', 'Frigorifico Combi AEG, No Frost, 2,01 mts, Blanco, A+++,  Twintech, Multiflow, Display L', 100, 1, 672.24, 0, 672.24, 81.34, NULL, 0, '2022-03-11', 0, '000', 'DROP ELECTROLUX', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, 'PEDIDO', NULL, '2022-06-11', '2022-06-16', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Zasca', '2022-06-10 22:58:55', '5280103-A', 'rmz0m3p4sb5@notification.net', 0.7, ''),
('PCC', '241164-A', '2022-06-11 22:50:44', 'Daniel López', 'Temprano,13,', '', '08030', 'Barcelona', NULL, '600000003', '4719512101476', 'DISIPADOR COOLERMASTER HYPER', NULL, 1, 38.35, 0, 38.35, 4.6, NULL, 0, '2022-06-11', 0, '000', 'DROP GLOBO', 0, NULL, NULL, NULL, '#100456434.', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, 'PEDIDO', NULL, '2022-06-11', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Zasca', '2022-06-10 23:58:52', '2411640-A', NULL, 0.1, ''),
('CAR', '5293447-A', '2022-06-11 22:31:30', 'Sandra Coronas', 'CALLE BONES Nº31', '', '08940', 'CORNELLA DE LLOBREGAT', NULL, '666666666', '4242005042999', 'Encimera Bosch - inducción, 2 zonas+2 de 28, memoria, sprint, 4,6kw', 10, 1, 368.51, 0, 820.34, 99.26, NULL, 0, '2022-06-11', -1, '003', 'TDL', 0, NULL, NULL, NULL, NULL, NULL, '25', 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Electro', '2022-06-10 23:58:53', '5293447-A', 'rdppjgym6py@notification.net', 0.1, ''),
('CAR', '5293447-A', '2022-06-11 22:31:30', 'Sandra Coronas', 'CALLE BONES Nº31', '', '08940', 'CORNELLA DE LLOBREGAT', NULL, '666666666', '8434778013573', 'Lavavajillas TEKA WH', 46, 1, 451.83, 0, 820.34, 99.26, NULL, 0, '2022-03-11', -1, '003', 'TDL', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Electro', '2022-06-10 23:58:53', '5293447-A', 'rdppjgym6py.dw5t9@notification.net', 0.3, ''),
('WEB', 'QDNTLXNL', '2022-06-11 23:02:11', 'Francesc Xavier', 'Passatge, 2', '40000000Q', '08188', 'Valllromanes', NULL, '600000003', '4242006254346', 'Balay hobs Negro Integrado Con placa de inducción 3 zona(s)', NULL, 1, 296.62, 0, 296.62, 0, NULL, 0, '2022-06-11', -1, '003', 'TILSA', 0, NULL, NULL, NULL, NULL, NULL, '20', 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Electro', '2022-06-11 00:03:01', 'QDNTLXNL', 'xavier@gmail.com', 0.1, ''),
('AMA', '406-1950931-02300', '2022-06-11 23:30:22', 'juan carlos', 'Rambla modolell 50', '', '08840', 'Viladecans', NULL, '+34 631123321', '8421152143865', 'Combi Teka NFL - NoFrost, 1880x595x635, antibacterias, electrónico, 295l, A+', 100, 1, 379.99, 0, 379.99, NULL, NULL, 0, '2022-03-11', -1, '003', 'TDL', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Zasca', '2022-06-11 00:58:51', '406-1950931-02300', 'wfrb9ztq4n2@marketplace.es', 0.7, ''),
('THE', 'C2203110002-A', '2022-06-12 23:04:05', 'Nikolay', 'Calle Piñonero, 59 ', '', '45005', 'Toledo', NULL, '633112011', '8421152149256', 'Termo TEKA - Eléctrico, 100 Litros, Vertical, B', 20, 1, 157.24, 0, 157.24, 11.41, NULL, 0, '2022-03-11', -1, '003', 'TDL', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Electro', '2022-06-11 00:58:55', 'C2203110002-A', NULL, 0.14, ''),
('THE', 'C2203110002-A', '2022-06-12 23:04:36', 'RAFAEL OTAÑO', 'Calle Gasset', '', '34004', 'PALENCIA', NULL, '615506901', '8434778013610', 'Lavavajillas Integración TEKA WH', 46, 1, 324.87, 0, 324.87, 23.58, NULL, 0, '2022-03-11', -1, '003', 'TDL', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Electro', '2022-06-11 00:58:55', 'C2203110002-A', NULL, 0.3, ''),
('PCC', '241178-A', '2022-06-12 01:18:23', 'Eloi López', 'c/Hospital 56, 1a Planta Nif: 41000000B', '', '08460', 'Santa Maria de Palautordera', NULL, '600000001', '4719512050125', 'DISIPADOR COOLERMASTER', NULL, 1, 38.98, 0, 38.98, 4.68, NULL, 0, '2022-03-11', 0, '000', 'DROP GLOBOMATIK', 0, NULL, NULL, NULL, '#100456435.', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, 'PEDIDO', NULL, '2022-03-11', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Electro', '2022-06-11 01:58:51', '241178-A', NULL, 0.1, ''),
('AMA', '406-5544998-39188', '2022-06-11 06:48:45', 'Nuria Gonzalez', 'C/ MAYOR, 27 ', '', '16639', 'SANTA MARIA DE LOS LLANOS', NULL, '6991112222', '4242006276515', 'Horno Balay - Multifunción, 71L, 7Funciones, A, Encastrable, Aqualisis,Panel Autolimpiante', 32, 1, 298.52, 0, 298.52, NULL, NULL, 0, '2022-03-11', -1, '003', 'TDL', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Zasca', '2022-06-11 07:58:48', '406-5544998-39188', 'xf00nkdsy436@marketplace.es', 0.21, ''),
('PCC', '241187-A', '2022-06-12 07:26:44', 'Cristina Ayala', 'C. Luis 2 Nif: 00000000W', '', '28805', 'Alcalá de Henares', NULL, '622333444', '6970885350061', 'Proyector Portátil - 150 Lúmenes/ HD/ HDMI/ Blanco', NULL, 1, 118.26, 0, 118.26, 8.28, NULL, 0, '2022-06-11', 0, '000', 'DROP DEPAU', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, 'ENTREGADO', NULL, '2022-03-11', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Electro', '2022-06-11 07:58:52', '241187-A', NULL, 0.1, '');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
