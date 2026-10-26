-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-10-2026 a las 23:25:49
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `flechaflash`
--
CREATE DATABASE IF NOT EXISTS `flechaflash` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `flechaflash`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `colectivos`
--

CREATE TABLE `colectivos` (
  `ID_Colectivo` int(11) NOT NULL,
  `Matricula` varchar(20) NOT NULL,
  `Marca` varchar(50) DEFAULT NULL,
  `Modelo` varchar(50) DEFAULT NULL,
  `Capacidad` int(11) DEFAULT NULL,
  `Chofer` varchar(50) DEFAULT NULL,
  `Estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lugares`
--

CREATE TABLE `lugares` (
  `ID_Asiento` int(11) NOT NULL,
  `ID_Colectivo` int(11) NOT NULL,
  `NumeroAsiento` int(11) DEFAULT NULL,
  `Estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasajeros`
--

CREATE TABLE `pasajeros` (
  `ID_Pasajero` int(11) NOT NULL,
  `DNI` varchar(20) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Apellido` varchar(50) NOT NULL,
  `Correo` varchar(100) DEFAULT NULL,
  `Telefono` varchar(20) DEFAULT NULL,
  `Estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pasajeros`
--

INSERT INTO `pasajeros` (`ID_Pasajero`, `DNI`, `Nombre`, `Apellido`, `Correo`, `Telefono`, `Estado`) VALUES
(1, '30111222', 'Juan', 'Gomez', 'jgomez@gmail.com', '155555111', 1),
(2, '30222333', 'Maria', 'Lopez', 'mlopez@gmail.com', '155555222', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasajes`
--

CREATE TABLE `pasajes` (
  `ID_Pasaje` int(11) NOT NULL,
  `ID_Pasajero` int(11) NOT NULL,
  `ID_Lugar` int(11) NOT NULL,
  `ID_Ruta` int(11) NOT NULL,
  `FechaVenta` date DEFAULT NULL,
  `HoraVenta` time DEFAULT NULL,
  `Precio` decimal(10,2) DEFAULT NULL,
  `Estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `viaje`
--

CREATE TABLE `viaje` (
  `ID_Viaje` int(11) NOT NULL,
  `Origen` varchar(50) DEFAULT NULL,
  `Destino` varchar(50) DEFAULT NULL,
  `HoraSalida` time DEFAULT NULL,
  `HoraLlegada` time DEFAULT NULL,
  `ID_Colectivo` int(11) DEFAULT NULL,
  `Precio` decimal(10,2) DEFAULT NULL,
  `Estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `colectivos`
--
ALTER TABLE `colectivos`
  ADD PRIMARY KEY (`ID_Colectivo`),
  ADD UNIQUE KEY `Matricula` (`Matricula`);

--
-- Indices de la tabla `lugares`
--
ALTER TABLE `lugares`
  ADD PRIMARY KEY (`ID_Asiento`),
  ADD KEY `ID_Colectivo` (`ID_Colectivo`);

--
-- Indices de la tabla `pasajeros`
--
ALTER TABLE `pasajeros`
  ADD PRIMARY KEY (`ID_Pasajero`),
  ADD UNIQUE KEY `DNI` (`DNI`),
  ADD UNIQUE KEY `Correo` (`Correo`);

--
-- Indices de la tabla `pasajes`
--
ALTER TABLE `pasajes`
  ADD PRIMARY KEY (`ID_Pasaje`),
  ADD KEY `ID_Pasajero` (`ID_Pasajero`),
  ADD KEY `ID_Lugar` (`ID_Lugar`),
  ADD KEY `ID_Ruta` (`ID_Ruta`);

--
-- Indices de la tabla `viaje`
--
ALTER TABLE `viaje`
  ADD PRIMARY KEY (`ID_Viaje`),
  ADD KEY `ID_Colectivo` (`ID_Colectivo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `colectivos`
--
ALTER TABLE `colectivos`
  MODIFY `ID_Colectivo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `lugares`
--
ALTER TABLE `lugares`
  MODIFY `ID_Asiento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pasajeros`
--
ALTER TABLE `pasajeros`
  MODIFY `ID_Pasajero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `pasajes`
--
ALTER TABLE `pasajes`
  MODIFY `ID_Pasaje` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `viaje`
--
ALTER TABLE `viaje`
  MODIFY `ID_Viaje` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `lugares`
--
ALTER TABLE `lugares`
  ADD CONSTRAINT `lugares_ibfk_1` FOREIGN KEY (`ID_Colectivo`) REFERENCES `colectivos` (`ID_Colectivo`);

--
-- Filtros para la tabla `pasajes`
--
ALTER TABLE `pasajes`
  ADD CONSTRAINT `pasajes_ibfk_1` FOREIGN KEY (`ID_Pasajero`) REFERENCES `pasajeros` (`ID_Pasajero`),
  ADD CONSTRAINT `pasajes_ibfk_2` FOREIGN KEY (`ID_Lugar`) REFERENCES `lugares` (`ID_Asiento`),
  ADD CONSTRAINT `pasajes_ibfk_3` FOREIGN KEY (`ID_Ruta`) REFERENCES `viaje` (`ID_Viaje`);

--
-- Filtros para la tabla `viaje`
--
ALTER TABLE `viaje`
  ADD CONSTRAINT `viaje_ibfk_1` FOREIGN KEY (`ID_Colectivo`) REFERENCES `colectivos` (`ID_Colectivo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
