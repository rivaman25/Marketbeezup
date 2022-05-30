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
  `TIMESTAMP_Auto` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `expedicion_dachser` varchar(40) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `volumen` double NOT NULL DEFAULT '0.1',
  `numeroAlbaran` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;