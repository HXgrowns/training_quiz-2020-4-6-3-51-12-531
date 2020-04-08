CREATE DATABASE parking_manage DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

CREATE TABLE parking_lot (
id INT(10) PRIMARY KEY AUTO_INCREMENT,
lot_no CHAR(1),
space_no INT(10),
car_number CHAR(50)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;