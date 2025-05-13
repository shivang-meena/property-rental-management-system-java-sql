CREATE TABLE `communication` (
  `message_id` int NOT NULL AUTO_INCREMENT,
  `tanents_id` varchar(50) NOT NULL DEFAULT '',
  `landlord_id` varchar(50) NOT NULL DEFAULT '',
  `message_content` text NOT NULL,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `staff_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`message_id`),
  KEY `tanents_id` (`tanents_id`),
  KEY `landlord_id` (`landlord_id`),
  KEY `fk_staff_idd` (`staff_id`),
  CONSTRAINT `communication_ibfk_1` FOREIGN KEY (`tanents_id`) REFERENCES `user_table` (`user_id`),
  CONSTRAINT `communication_ibfk_2` FOREIGN KEY (`landlord_id`) REFERENCES `user_table` (`user_id`),
  CONSTRAINT `fk_staff_idd` FOREIGN KEY (`staff_id`) REFERENCES `maintanace_details` (`maintaince_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




CREATE TABLE `maintaiance` (
  `Request_id_var` int NOT NULL AUTO_INCREMENT,
  `Request_id` varchar(10) DEFAULT NULL,
  `tanent_id` varchar(50) DEFAULT NULL,
  `property_ids` varchar(50) DEFAULT NULL,
  `request_date` date DEFAULT NULL,
  `about_description` varchar(300) DEFAULT NULL,
  `Request_status` varchar(50) DEFAULT 'not approved',
  `landlord_id` varchar(50) DEFAULT NULL,
  `assign_request` varchar(50) DEFAULT NULL,
  `staff_id` varchar(50) DEFAULT NULL,
  `task_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Request_id_var`),
  UNIQUE KEY `Request_id` (`Request_id`),
  KEY `fk_user_id` (`landlord_id`),
  KEY `fk_property_id` (`property_ids`),
  KEY `fk_tanent_id` (`tanent_id`),
  KEY `fk_assign_request` (`assign_request`),
  KEY `fk_new_foreign_key_name` (`staff_id`),
  CONSTRAINT `fk_new_foreign_key_name` FOREIGN KEY (`staff_id`) REFERENCES `maintanace_details` (`maintaince_id`),
  CONSTRAINT `fk_property_id` FOREIGN KEY (`property_ids`) REFERENCES `properties_details` (`property_id`),
  CONSTRAINT `fk_tanent_id` FOREIGN KEY (`tanent_id`) REFERENCES `user_table` (`user_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`landlord_id`) REFERENCES `user_table` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `maintanace_details` (
  `maintaince_id` varchar(50) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `join_date` date DEFAULT NULL,
  `landlord_id_assign` varchar(50) DEFAULT NULL,
  `property_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`maintaince_id`),
  KEY `landlord_id_assign` (`landlord_id_assign`),
  KEY `property_id` (`property_id`),
  CONSTRAINT `maintanace_details_ibfk_1` FOREIGN KEY (`landlord_id_assign`) REFERENCES `user_table` (`user_id`),
  CONSTRAINT `maintanace_details_ibfk_3` FOREIGN KEY (`property_id`) REFERENCES `properties_details` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `payment_table` (
  `payment_id` int NOT NULL AUTO_INCREMENT,
  `tanent_id` varchar(50) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `payment_ammount` bigint DEFAULT NULL,
  `payment_method` enum('UPI','CASH','Check') DEFAULT NULL,
  `payment_to` varchar(6) DEFAULT NULL,
  `rent_status` varchar(50) DEFAULT NULL,
  `property_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `tanent_id` (`tanent_id`),
  KEY `fk_payment_to` (`payment_to`),
  KEY `fk_department` (`property_id`),
  CONSTRAINT `fk_department` FOREIGN KEY (`property_id`) REFERENCES `properties_details` (`property_id`),
  CONSTRAINT `fk_payment_to` FOREIGN KEY (`payment_to`) REFERENCES `user_table` (`user_id`),
  CONSTRAINT `payment_table_ibfk_1` FOREIGN KEY (`tanent_id`) REFERENCES `user_table` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `properties_details` (
  `property_id` varchar(50) NOT NULL,
  `landlord_id` varchar(50) DEFAULT NULL,
  `property_addres` varchar(200) DEFAULT NULL,
  `size` varchar(10) DEFAULT NULL,
  `rent_amount` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `tnaents_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`property_id`),
  KEY `landlord_id` (`landlord_id`),
  CONSTRAINT `properties_details_ibfk_1` FOREIGN KEY (`landlord_id`) REFERENCES `user_table` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `tasks` (
  `task_id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `status` varchar(50) DEFAULT NULL,
  `assigned_to` varchar(50) DEFAULT NULL,
  `assigned_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `landlord_id` varchar(50) DEFAULT NULL,
  `Request_id` varchar(50) NOT NULL,
  `detail_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`task_id`),
  UNIQUE KEY `Request_id` (`Request_id`),
  KEY `assigned_to` (`assigned_to`),
  KEY `fk_landlord_id` (`landlord_id`),
  CONSTRAINT `fk_landlord_id` FOREIGN KEY (`landlord_id`) REFERENCES `user_table` (`user_id`),
  CONSTRAINT `fk_REqest_ID` FOREIGN KEY (`Request_id`) REFERENCES `maintaiance` (`Request_id`),
  CONSTRAINT `tasks_ibfk_1` FOREIGN KEY (`assigned_to`) REFERENCES `maintanace_details` (`maintaince_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `user_table` (
  `user_id` varchar(6) NOT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `Role` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone_no` varchar(50) DEFAULT NULL,
  `Address` varchar(200) DEFAULT NULL,
  `Pan_no` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `unique_email` (`email`),
  UNIQUE KEY `unique_phonenumber` (`phone_no`),
  UNIQUE KEY `unique_panno` (`Pan_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
