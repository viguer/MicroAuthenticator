--
-- Base de datos: `DBTest`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `phones`
--

CREATE TABLE `phones` (
  `phone_id` binary(16) NOT NULL,
  `city_code` int(11) DEFAULT NULL,
  `country_code` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `user_id` binary(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `user_id` binary(16) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `estado` enum('ACTIVO','INACTIVO') DEFAULT NULL,
  `last_login_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indices de la tabla `phones`
--
ALTER TABLE `phones`
  ADD PRIMARY KEY (`phone_id`),
  ADD KEY `FKmg6d77tgqfen7n1g763nvsqe3` (`user_id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Filtros para la tabla `phones`
--

ALTER TABLE `phones`
  ADD CONSTRAINT `FKmg6d77tgqfen7n1g763nvsqe3` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
COMMIT;
