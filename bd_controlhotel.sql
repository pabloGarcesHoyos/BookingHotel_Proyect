-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-05-2024 a las 15:11:21
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
-- Base de datos: `bd_controlhotel`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `booking`
--

CREATE TABLE `booking` (
  `id` int(11) NOT NULL,
  `user` varchar(50) NOT NULL,
  `room` varchar(50) NOT NULL,
  `checkInDate` varchar(50) NOT NULL,
  `checkOutDate` varchar(50) NOT NULL,
  `reservationStatus` tinyint(1) NOT NULL DEFAULT 0,
  `totalPrice` int(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hotel`
--

CREATE TABLE `hotel` (
  `id` int(15) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `classification` varchar(100) DEFAULT NULL,
  `amenities` varchar(50) DEFAULT NULL,
  `images` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `hotel`
--

INSERT INTO `hotel` (`id`, `name`, `address`, `classification`, `amenities`, `images`) VALUES
(1, 'jasper', 'Cr5', 'A', 'Todas', 'jasper'),
(2, 'Carlos', 'Cr4', 'A', 'todas', 'Carlos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `id` int(11) NOT NULL,
  `id_hotel` int(11) DEFAULT NULL,
  `fecha_entrada` date DEFAULT NULL,
  `fecha_salida` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservation`
--

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `room_id` int(11) DEFAULT NULL,
  `check_in_date` date DEFAULT NULL,
  `check_out_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `room`
--

CREATE TABLE `room` (
  `id` int(11) NOT NULL,
  `roomNumber` int(150) NOT NULL,
  `roomType` varchar(50) NOT NULL,
  `pricePerNight` int(150) NOT NULL,
  `availability` tinyint(1) NOT NULL DEFAULT 0,
  `amenitiesDetails` varchar(150) NOT NULL,
  `hotel` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `room`
--

INSERT INTO `room` (`id`, `roomNumber`, `roomType`, `pricePerNight`, `availability`, `amenitiesDetails`, `hotel`) VALUES
(1, 234, 'dsds', 234, 0, 'f', ''),
(2, 256, 'Indivual', 26, 1, 'todas', ''),
(3, 89, 'Doble', 8956, 1, 'todas', 'xxzx');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rooms`
--

CREATE TABLE `rooms` (
  `id` int(11) NOT NULL,
  `room_number` varchar(20) NOT NULL,
  `room_type` varchar(50) NOT NULL,
  `price_per_night` decimal(10,2) NOT NULL,
  `amenities_details` varchar(255) DEFAULT NULL,
  `hotel_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rooms`
--

INSERT INTO `rooms` (`id`, `room_number`, `room_type`, `price_per_night`, `amenities_details`, `hotel_id`) VALUES
(1, '213', 'Individual', 50.00, 'Todas', 1),
(2, '151', 'Individual', 25.00, 'Todas', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` int(150) NOT NULL,
  `username` varchar(25) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(150) DEFAULT NULL,
  `contactDetails` varchar(50) DEFAULT NULL,
  `rol` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `username`, `email`, `password`, `contactDetails`, `rol`) VALUES
(131, 'carlos', 'carlos@text.com', '123', 'Detalles adicionales aquí', 'Cliente'),
(132, '3', '3', '3', 'Detalles adicionales aquí', 'Cliente'),
(133, 'nicolas', 'nicolas@gnail.com', '123', 'Detalles adicionales aquí', 'Cliente'),
(134, 'nicolas', 'nicolas@gmail.com', '123', 'Detalles adicionales aquí', 'Cliente');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_hotel` (`id_hotel`);

--
-- Indices de la tabla `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_room_id` (`room_id`);

--
-- Indices de la tabla `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`id`),
  ADD KEY `hotel_id` (`hotel_id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `booking`
--
ALTER TABLE `booking`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80950;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `room`
--
ALTER TABLE `room`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `rooms`
--
ALTER TABLE `rooms`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=77767;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` int(150) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=135;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `fk_hotel` FOREIGN KEY (`id_hotel`) REFERENCES `hotel` (`id`);

--
-- Filtros para la tabla `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `fk_room_id` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`);

--
-- Filtros para la tabla `rooms`
--
ALTER TABLE `rooms`
  ADD CONSTRAINT `rooms_ibfk_1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
