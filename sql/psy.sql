DROP TABLE IF EXISTS `psy_ceshiType`;
DROP TABLE IF EXISTS `psy_ceshi`;
DROP TABLE IF EXISTS `psy_question`;
DROP TABLE IF EXISTS `psy_questionChoice`;
DROP TABLE IF EXISTS `psy_question_questionChoice`;
DROP TABLE IF EXISTS `psy_specialty`;
DROP TABLE IF EXISTS `psy_doctor`;

CREATE TABLE `psy_ceshiType`
(`ceshiTypeID` INT(125) NOT NULL AUTO_INCREMENT,
`ceshiTypeName` VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL,
`ceshiTypeDesc` VARCHAR(1250) CHARACTER SET utf8mb4,
`createDate` DATETIME,
`changeDate` DATETIME,
PRIMARY KEY (`ceshiTypeID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `psy_ceshi`
(`ceshiID` INT(125) NOT NULL AUTO_INCREMENT,
`ceshiName` VARCHAR(250) CHARACTER SET utf8mb4 NOT NULL,
`ceshiTypeID` INT(125),
`ceshiImgURL` VARCHAR(250) CHARACTER SET utf8mb4,
`ceshiDesc` VARCHAR(1250) CHARACTER SET utf8mb4,
`ceshiPrice` VARCHAR(100) CHARACTER SET utf8mb4,
`ceshiEstimatedTime` VARCHAR(100) CHARACTER SET utf8mb4,
`viewAmount` INT(125),
`questionAmount` INT(125),
`createDate` DATETIME,
`changeDate` DATETIME,
PRIMARY KEY (`ceshiID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `psy_question`
(`questionID` INT(125) AUTO_INCREMENT,
`questionName` VARCHAR(250) CHARACTER SET utf8mb4 NOT NULL,
`ceshiID` INT(125) NOT NULL,
`createDate` DATETIME,
`changeDate` DATETIME,
PRIMARY KEY (`questionID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `psy_questionChoice`
(`questionChoiceID` INT(125) AUTO_INCREMENT,
`questionChoiceName` VARCHAR(250) NOT NULL,
`questionChoiceValue` VARCHAR(250) NOT NULL,
`createDate` DATETIME,
`changeDate` DATETIME,
PRIMARY KEY (`questionChoiceID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `psy_question_questionChoice`
(`qqcID` INT(125) AUTO_INCREMENT,
`questionID` INT(125) NOT NULL,
`questionChoiceID` INT(125) NOT NULL,
`createDate` DATETIME,
`changeDate` DATETIME,
PRIMARY KEY (`qqcID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `psy_specialty`
(`specialtyID` INT(125) NOT NULL AUTO_INCREMENT,
`specialtyName` VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL,
`specialtyLevel` INT(10),
`specialtyCode` INT(125),
`parentCode` INT(125),
`createDate` DATETIME,
`changeDate` DATETIME,
PRIMARY KEY (`areaID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `psy_doctor`
(`doctorID` INT(125) NOT NULL AUTO_INCREMENT,
`doctorName` VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL,
`phoneNumber` VARCHAR(100) CHARACTER SET utf8mb4,
`doctorImgURL` VARCHAR(250) CHARACTER SET utf8mb4,
`gender` VARCHAR(4) CHARACTER SET utf8mb4,
`age` VARCHAR(10) CHARACTER SET utf8mb4,
`levelText` VARCHAR(100) CHARACTER SET utf8mb4,
`levelID` VARCHAR(100) CHARACTER SET utf8mb4,
`description`  VARCHAR(5000) CHARACTER SET utf8mb4,
`areaList` VARCHAR(100) CHARACTER SET utf8mb4,
`specialtyList` VARCHAR(250) CHARACTER SET utf8mb4,
`createDate` DATETIME,
`changeDate` DATETIME,
PRIMARY KEY (`doctorID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



