-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema hotel_service
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hotel_service
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hotel_service` DEFAULT CHARACTER SET utf8 ;
USE `hotel_service` ;

-- -----------------------------------------------------
-- Table `hotel_service`.`Guest`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel_service`.`Guest` ;

CREATE TABLE IF NOT EXISTS `hotel_service`.`Guest` (
  `idGuest` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `pasport` VARCHAR(45) NULL,
  PRIMARY KEY (`idGuest`),
  UNIQUE INDEX `idGuest_UNIQUE` (`idGuest` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel_service`.`Room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel_service`.`Room` ;

CREATE TABLE IF NOT EXISTS `hotel_service`.`Room` (
  `idRoom` INT NOT NULL AUTO_INCREMENT,
  `number` INT NULL,
  `content` INT NULL,
  `stars` INT NULL,
  `price` INT NULL,
  `Status` ENUM('FREE', 'NOTFREE', 'ONREPAIR') NULL DEFAULT 'FREE',
  PRIMARY KEY (`idRoom`),
  UNIQUE INDEX `idRoom_UNIQUE` (`idRoom` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel_service`.`Chek`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel_service`.`Chek` ;

CREATE TABLE IF NOT EXISTS `hotel_service`.`Chek` (
  `idChek` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `dateInSettle` DATE NULL,
  `dateOutSettle` DATE NULL,
  `status` TINYINT(0) NULL DEFAULT 0,
  `Guest_idGuest` INT UNSIGNED NOT NULL,
  `Room_idRoom` INT NOT NULL,
  PRIMARY KEY (`idChek`),
  UNIQUE INDEX `idCheck_UNIQUE` (`idChek` ASC),
  INDEX `fk_Chek_Guest1_idx` (`Guest_idGuest` ASC),
  INDEX `fk_Chek_Room1_idx` (`Room_idRoom` ASC),
  CONSTRAINT `fk_idGuest`
    FOREIGN KEY (`Guest_idGuest`)
    REFERENCES `hotel_service`.`Guest` (`idGuest`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_idRoom`
    FOREIGN KEY (`Room_idRoom`)
    REFERENCES `hotel_service`.`Room` (`idRoom`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel_service`.`Service`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel_service`.`Service` ;

CREATE TABLE IF NOT EXISTS `hotel_service`.`Service` (
  `idService` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` INT NULL,
  `Chek_idChek` INT UNSIGNED NULL,
  PRIMARY KEY (`idService`),
  UNIQUE INDEX `idService_UNIQUE` (`idService` ASC),
  INDEX `fk_Service_Chek1_idx` (`Chek_idChek` ASC),
  CONSTRAINT `fk_idChek`
    FOREIGN KEY (`Chek_idChek`)
    REFERENCES `hotel_service`.`Chek` (`idChek`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel_service`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel_service`.`User` ;

CREATE TABLE IF NOT EXISTS `hotel_service`.`User` (
  `idUser` INT NOT NULL,
  `login` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`idUser`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel_service`.`Audit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel_service`.`Audit` ;

CREATE TABLE IF NOT EXISTS `hotel_service`.`Audit` (
  `idAudit` INT NOT NULL,
  `date` DATE NULL,
  `resources` VARCHAR(45) NULL,
  `User_idUser` INT NOT NULL,
  PRIMARY KEY (`idAudit`),
  INDEX `fk_Audit_User1_idx` (`User_idUser` ASC),
  CONSTRAINT `fk_Audit_User1`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `hotel_service`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `hotel_service`.`Guest`
-- -----------------------------------------------------
START TRANSACTION;
USE `hotel_service`;
INSERT INTO `hotel_service`.`Guest` (`idGuest`, `name`, `pasport`) VALUES (1, 'Oleg', 'AB125');
INSERT INTO `hotel_service`.`Guest` (`idGuest`, `name`, `pasport`) VALUES (2, 'Ann', 'KH148');
INSERT INTO `hotel_service`.`Guest` (`idGuest`, `name`, `pasport`) VALUES (3, 'Petr', 'KH896');
INSERT INTO `hotel_service`.`Guest` (`idGuest`, `name`, `pasport`) VALUES (4, 'Tania', 'AB458');
INSERT INTO `hotel_service`.`Guest` (`idGuest`, `name`, `pasport`) VALUES (5, 'Sveta', 'AS2569');
INSERT INTO `hotel_service`.`Guest` (`idGuest`, `name`, `pasport`) VALUES (6, 'Olia', 'AB1258');
INSERT INTO `hotel_service`.`Guest` (`idGuest`, `name`, `pasport`) VALUES (7, 'Georgio', 'KH7896');
INSERT INTO `hotel_service`.`Guest` (`idGuest`, `name`, `pasport`) VALUES (8, 'Ira', 'KH785');
INSERT INTO `hotel_service`.`Guest` (`idGuest`, `name`, `pasport`) VALUES (9, 'Dima', 'KH741');
INSERT INTO `hotel_service`.`Guest` (`idGuest`, `name`, `pasport`) VALUES (10, 'Pasha', 'AB456');

COMMIT;


-- -----------------------------------------------------
-- Data for table `hotel_service`.`Room`
-- -----------------------------------------------------
START TRANSACTION;
USE `hotel_service`;
INSERT INTO `hotel_service`.`Room` (`idRoom`, `number`, `content`, `stars`, `price`, `Status`) VALUES (1, 1, 2, 3, 3, 'FREE');
INSERT INTO `hotel_service`.`Room` (`idRoom`, `number`, `content`, `stars`, `price`, `Status`) VALUES (2, 2, 3, 3, 5, 'NOTFREE');
INSERT INTO `hotel_service`.`Room` (`idRoom`, `number`, `content`, `stars`, `price`, `Status`) VALUES (3, 3, 2, 4, 4, 'NOTFREE');
INSERT INTO `hotel_service`.`Room` (`idRoom`, `number`, `content`, `stars`, `price`, `Status`) VALUES (4, 4, 3, 3, 3, 'FREE');
INSERT INTO `hotel_service`.`Room` (`idRoom`, `number`, `content`, `stars`, `price`, `Status`) VALUES (5, 5, 4, 4, 5, 'NOTFREE');
INSERT INTO `hotel_service`.`Room` (`idRoom`, `number`, `content`, `stars`, `price`, `Status`) VALUES (6, 6, 2, 3, 5, 'FREE');
INSERT INTO `hotel_service`.`Room` (`idRoom`, `number`, `content`, `stars`, `price`, `Status`) VALUES (7, 7, 3, 4, 5, 'FREE');
INSERT INTO `hotel_service`.`Room` (`idRoom`, `number`, `content`, `stars`, `price`, `Status`) VALUES (8, 8, 3, 5, 8, 'NOTFREE');
INSERT INTO `hotel_service`.`Room` (`idRoom`, `number`, `content`, `stars`, `price`, `Status`) VALUES (9, 9, 4, 3, 5, 'FREE');
INSERT INTO `hotel_service`.`Room` (`idRoom`, `number`, `content`, `stars`, `price`, `Status`) VALUES (10, 10, 3, 4, 7, 'FREE');

COMMIT;


-- -----------------------------------------------------
-- Data for table `hotel_service`.`Chek`
-- -----------------------------------------------------
START TRANSACTION;
USE `hotel_service`;
INSERT INTO `hotel_service`.`Chek` (`idChek`, `dateInSettle`, `dateOutSettle`, `status`, `Guest_idGuest`, `Room_idRoom`) VALUES (1, '2013-03-01', '2013-03-20', 0, 1, 2);
INSERT INTO `hotel_service`.`Chek` (`idChek`, `dateInSettle`, `dateOutSettle`, `status`, `Guest_idGuest`, `Room_idRoom`) VALUES (2, '2013-04-01', '2013-04-05', 0, 2, 3);
INSERT INTO `hotel_service`.`Chek` (`idChek`, `dateInSettle`, `dateOutSettle`, `status`, `Guest_idGuest`, `Room_idRoom`) VALUES (3, '2013-02-01', '2013-02-02', 0, 3, 5);
INSERT INTO `hotel_service`.`Chek` (`idChek`, `dateInSettle`, `dateOutSettle`, `status`, `Guest_idGuest`, `Room_idRoom`) VALUES (4, '2013-03-02', '2013-03-30', 0, 4, 8);

COMMIT;


-- -----------------------------------------------------
-- Data for table `hotel_service`.`Service`
-- -----------------------------------------------------
START TRANSACTION;
USE `hotel_service`;
INSERT INTO `hotel_service`.`Service` (`idService`, `name`, `price`, `Chek_idChek`) VALUES (1, 'settle in room', 5, 1);
INSERT INTO `hotel_service`.`Service` (`idService`, `name`, `price`, `Chek_idChek`) VALUES (2, 'breakfast', 3, 1);
INSERT INTO `hotel_service`.`Service` (`idService`, `name`, `price`, `Chek_idChek`) VALUES (3, 'settle in room', 4, 2);
INSERT INTO `hotel_service`.`Service` (`idService`, `name`, `price`, `Chek_idChek`) VALUES (4, 'settle in room', 5, 3);
INSERT INTO `hotel_service`.`Service` (`idService`, `name`, `price`, `Chek_idChek`) VALUES (5, 'settle in room', 8, 4);
INSERT INTO `hotel_service`.`Service` (`idService`, `name`, `price`, `Chek_idChek`) VALUES (6, 'breakfast', 3, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `hotel_service`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE `hotel_service`;
INSERT INTO `hotel_service`.`User` (`idUser`, `login`, `password`) VALUES (1, 'Olia', '123');
INSERT INTO `hotel_service`.`User` (`idUser`, `login`, `password`) VALUES (2, 'Tania', '555');

COMMIT;

