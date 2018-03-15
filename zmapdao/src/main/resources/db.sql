drop database if exists zmap;
CREATE DATABASE `zmap` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
use zmap;
CREATE TABLE `patientregistration` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `original_id` varchar(50) DEFAULT NULL,
  `registration_number` int(11) DEFAULT NULL,
  `registration_time` date DEFAULT NULL,
  `doctor_id` varchar(50) DEFAULT NULL,
  `serviceDepartment_id` varchar(50) DEFAULT NULL,
  `patient_card_number` varchar(50) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB