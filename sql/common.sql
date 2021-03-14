DROP TABLE IF EXISTS `com_area`;

CREATE TABLE `com_area`
(`areaID` INT(125) NOT NULL AUTO_INCREMENT,
`areaName` VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL,
`areaLevel` INT(10),
`areaCode` INT(125),
`parentCode` INT(125),
`createDate` DATETIME,
`changeDate` DATETIME,
PRIMARY KEY (`areaID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
