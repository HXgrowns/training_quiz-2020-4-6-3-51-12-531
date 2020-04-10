CREATE DATABASE parking_manage DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

CREATE TABLE parking_lot (
id INT(10) PRIMARY KEY AUTO_INCREMENT,
lot_no CHAR(1),
space_no INT(10),
car_number CHAR(50)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

SELECT lot_no, space_no, COUNT(space_no) AS space_no_count FROM parking_lot WHERE car_number is NULL GROUP BY lot_no ORDER BY space_no_count DESC, space_no ASC LIMIT 1;
INNER JOIN
SELECT space_no from parking_lot where lot_no = ? AND car_number is null ORDER BY space_no ASC LIMIT 1

