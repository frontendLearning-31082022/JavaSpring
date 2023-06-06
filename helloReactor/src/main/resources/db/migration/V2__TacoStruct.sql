CREATE TABLE `taco_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cccvv` varchar(255) DEFAULT NULL,
  `cc_expiration` varchar(255) DEFAULT NULL,
  `cc_number` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `delivery_city` varchar(255) DEFAULT NULL,
  `delivery_name` varchar(255) DEFAULT NULL,
  `delivery_state` varchar(255) DEFAULT NULL,
  `delivery_street` varchar(255) DEFAULT NULL,
  `delivery_zip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `taco` (
 `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
 `name` varchar(255) NOT NULL,
 `taco_order_id` bigint DEFAULT NULL,
  KEY `FKcm584j7aeq83avlfb9vhdqthg` (`taco_order_id`),
  CONSTRAINT `taco_order_id_CONSTRAINT` FOREIGN KEY (`taco_order_id`) REFERENCES `taco_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `ingredient` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `ingredient_chk_1` CHECK ((`type` between 0 and 5))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
