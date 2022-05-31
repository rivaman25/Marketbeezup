CREATE TABLE `agencias` (
  `idAgencia` varchar(16) NOT NULL,
  PRIMARY KEY (`idAgencia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `almacenes` (
  `idAlmacen` varchar(3) NOT NULL,
  PRIMARY KEY (`idAlmacen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `incentivos` (
  `codigoArticulo` varchar(13) NOT NULL,
  `proveedor` varchar(10) NOT NULL,
  `importe` float NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  PRIMARY KEY (`codigoArticulo`,`fechaInicio`,`fechaFin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `pedidos` (
  `tienda` varchar(15) NOT NULL,
  `marketplace` varchar(3) NOT NULL,
  `idPedido` varchar(25) NOT NULL,
  `fechaPedido` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dni` varchar(10) DEFAULT NULL,
  `nombreApellidos` varchar(50) NOT NULL,
  `direccion` varchar(59) NOT NULL,
  `cp` varchar(10) NOT NULL,
  `poblacion` varchar(46) DEFAULT NULL,
  `provincia` varchar(36) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `importe` float NOT NULL,
  `comision` float DEFAULT NULL,
  `costePorte` float DEFAULT NULL,
  PRIMARY KEY (`idPedido`,`marketplace`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `provincias` (
  `codigo` varchar(2) NOT NULL,
  `nombre` varchar(36) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `rappels` (
  `idFamilia` varchar(10) NOT NULL,
  `idSubfamilia` varchar(10) NOT NULL,
  `marca` varchar(50) NOT NULL,
  `porcentaje` float NOT NULL,
  PRIMARY KEY (`idFamilia`,`idSubfamilia`,`marca`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `observaciones` (
  `titulo` varchar(170) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fechaHora` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `idPedido` varchar(25) NOT NULL,
  `marketplace` varchar(3) NOT NULL,
  PRIMARY KEY (`titulo`,`idPedido`,`marketplace`),
  KEY `obs_fk` (`idPedido`,`marketplace`),
  CONSTRAINT `obs_fk` FOREIGN KEY (`idPedido`, `marketplace`) REFERENCES `pedidos` (`idPedido`, `marketplace`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `articulos` (
  `codigoArticulo` varchar(13) NOT NULL,
  `descripcion` varchar(199) NOT NULL,
  `precio` float NOT NULL,
  `cantidad` int(2) NOT NULL,
  `estado` varchar(15) NOT NULL,
  `puc` float DEFAULT NULL,
  `tipoArticulo` varchar(6) DEFAULT NULL,
  `fechaHoraImpr` timestamp NULL DEFAULT NULL,
  `idFamilia` varchar(10) DEFAULT NULL,
  `idSubfamilia` varchar(10) DEFAULT NULL,
  `marca` varchar(50) DEFAULT NULL,
  `idPedido` varchar(25) NOT NULL,
  `marketplace` varchar(3) NOT NULL,
  PRIMARY KEY (`codigoArticulo`,`idPedido`,`marketplace`),
  KEY `idPedido` (`idPedido`,`marketplace`),
  CONSTRAINT `articulos_ibfk_1` FOREIGN KEY (`idPedido`, `marketplace`) REFERENCES `pedidos` (`idPedido`, `marketplace`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `albaranesventa` (
  `fechaAlbaran` date NOT NULL,
  `numeroAlbaran` varchar(10) NOT NULL,
  `codigoArticulo` varchar(13) NOT NULL,
  `idPedido` varchar(25) NOT NULL,
  `marketplace` varchar(3) NOT NULL,
  PRIMARY KEY (`codigoArticulo`,`idPedido`,`marketplace`),
  CONSTRAINT `alb_fk` FOREIGN KEY (`codigoArticulo`, `idPedido`, `marketplace`) REFERENCES `articulos` (`codigoArticulo`, `idPedido`, `marketplace`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `compras` (
  `idCompra` varchar(19) NOT NULL,
  `proveedor` varchar(10) DEFAULT NULL,
  `fechaCompra` date NOT NULL,
  `fechaEntrada` date DEFAULT NULL,
  `codigoArticulo` varchar(13) NOT NULL,
  `idPedido` varchar(25) NOT NULL,
  `marketplace` varchar(3) NOT NULL,
  PRIMARY KEY (`codigoArticulo`,`idPedido`,`marketplace`),
  CONSTRAINT `com_fk` FOREIGN KEY (`codigoArticulo`, `idPedido`, `marketplace`) REFERENCES `articulos` (`codigoArticulo`, `idPedido`, `marketplace`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `documentosventa` (
  `numeroVenta` varchar(10) NOT NULL,
  `fechaVenta` date NOT NULL,
  `codigoArticulo` varchar(13) NOT NULL,
  `idPedido` varchar(25) NOT NULL,
  `marketplace` varchar(3) NOT NULL,
  PRIMARY KEY (`codigoArticulo`,`idPedido`,`marketplace`),
  CONSTRAINT `doc_fk` FOREIGN KEY (`codigoArticulo`, `idPedido`, `marketplace`) REFERENCES `articulos` (`codigoArticulo`, `idPedido`, `marketplace`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `envios` (
  `fechaSalida` date NOT NULL,
  `idAgencia` varchar(16) NOT NULL,
  `idAlmacen` varchar(3) NOT NULL,
  `codigoArticulo` varchar(13) NOT NULL,
  `idPedido` varchar(25) NOT NULL,
  `marketplace` varchar(3) NOT NULL,
  PRIMARY KEY (`codigoArticulo`,`idPedido`,`marketplace`),
  KEY `env_age_fk` (`idAgencia`),
  KEY `env_alm_fk` (`idAlmacen`),
  CONSTRAINT `env_age_fk` FOREIGN KEY (`idAgencia`) REFERENCES `agencias` (`idAgencia`) ON DELETE CASCADE,
  CONSTRAINT `env_alm_fk` FOREIGN KEY (`idAlmacen`) REFERENCES `almacenes` (`idAlmacen`) ON DELETE CASCADE,
  CONSTRAINT `env_fk` FOREIGN KEY (`codigoArticulo`, `idPedido`, `marketplace`) REFERENCES `articulos` (`codigoArticulo`, `idPedido`, `marketplace`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;







