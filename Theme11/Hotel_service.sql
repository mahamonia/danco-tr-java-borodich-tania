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
-- Table `hotel_service`.`Status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel_service`.`Status` ;

CREATE TABLE IF NOT EXISTS `hotel_service`.`Status` (
  `idStatus` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`idStatus`),
  UNIQUE INDEX `idStatus_UNIQUE` (`idStatus` ASC))
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
  `idStatus` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idRoom`),
  UNIQUE INDEX `idRoom_UNIQUE` (`idRoom` ASC),
  INDEX `fk_Room_Status1_idx` (`idStatus` ASC),
  CONSTRAINT `fk_Room_Status1`
    FOREIGN KEY (`idStatus`)
    REFERENCES `hotel_service`.`Status` (`idStatus`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel_service`.`Chek`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel_service`.`Chek` ;

CREATE TABLE IF NOT EXISTS `hotel_service`.`Chek` (
  `idChek` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `date_in_settle` DATE NULL,
  `date_out_settle` DATE NULL,
  `status` TINYINT(0) NULL DEFAULT 0,
  `Room_idRoom` INT NOT NULL,
  `Guest_idGuest` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idChek`),
  UNIQUE INDEX `idCheck_UNIQUE` (`idChek` ASC),
  INDEX `fk_Chek_Room1_idx` (`Room_idRoom` ASC),
  INDEX `fk_Chek_Guest1_idx` (`Guest_idGuest` ASC),
  CONSTRAINT `fk_Chek_Room1`
    FOREIGN KEY (`Room_idRoom`)
    REFERENCES `hotel_service`.`Room` (`idRoom`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Chek_Guest1`
    FOREIGN KEY (`Guest_idGuest`)
    REFERENCES `hotel_service`.`Guest` (`idGuest`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel_service`.`Service`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotel_service`.`Service` ;

CREATE TABLE IF NOT EXISTS `hotel_service`.`Service` (
  `idService` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `price` VARCHAR(45) NULL,
  `Chek_idChek` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idService`),
  UNIQUE INDEX `idService_UNIQUE` (`idService` ASC),
  INDEX `fk_Service_Chek1_idx` (`Chek_idChek` ASC),
  CONSTRAINT `fk_Service_Chek1`
    FOREIGN KEY (`Chek_idChek`)
    REFERENCES `hotel_service`.`Chek` (`idChek`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
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
-- Data for table `hotel_service`.`Status`
-- -----------------------------------------------------
START TRANSACTION;
USE `hotel_service`;
INSERT INTO `hotel_service`.`Status` (`idStatus`, `name`) VALUES (1, 'free');
INSERT INTO `hotel_service`.`Status` (`idStatus`, `name`) VALUES (2, 'notFree');
INSERT INTO `hotel_service`.`Status` (`idStatus`, `name`) VALUES (3, 'onrepair');

COMMIT;


-- -----------------------------------------------------
-- Data for table `hotel_service`.`Room`
-- -----------------------------------------------------
START TRANSACTION;
USE `hotel_service`;
INSERT INTO `hotel_service`.`Room` (`idRoom`, `number`, `content`, `stars`, `price`, `idStatus`) VALUES (1, 1, 2, 3, 3, 1);
INSERT INTO `hotel_service`.`Room` (`idRoom`, `number`, `content`, `stars`, `price`, `idStatus`) VALUES (2, 2, 3, 3, 5, 2);
INSERT INTO `hotel_service`.`Room` (`idRoom`, `number`, `content`, `stars`, `price`, `idStatus`) VALUES (3, 3, 2, 4, 4, 1);
INSERT INTO `hotel_service`.`Room` (`idRoom`, `number`, `content`, `stars`, `price`, `idStatus`) VALUES (4, 4, 3, 3, 3, 1);
INSERT INTO `hotel_service`.`Room` (`idRoom`, `number`, `content`, `stars`, `price`, `idStatus`) VALUES (5, 5, 4, 4, 5, 1);
INSERT INTO `hotel_service`.`Room` (`idRoom`, `number`, `content`, `stars`, `price`, `idStatus`) VALUES (6, 6, 2, 3, 5, 1);
INSERT INTO `hotel_service`.`Room` (`idRoom`, `number`, `content`, `stars`, `price`, `idStatus`) VALUES (7, 7, 3, 4, 5, 1);
INSERT INTO `hotel_service`.`Room` (`idRoom`, `number`, `content`, `stars`, `price`, `idStatus`) VALUES (8, 8, 3, 5, 8, 1);
INSERT INTO `hotel_service`.`Room` (`idRoom`, `number`, `content`, `stars`, `price`, `idStatus`) VALUES (9, 9, 4, 3, 5, 1);
INSERT INTO `hotel_service`.`Room` (`idRoom`, `number`, `content`, `stars`, `price`, `idStatus`) VALUES (10, 10, 3, 4, 7, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `hotel_service`.`Chek`
-- -----------------------------------------------------
START TRANSACTION;
USE `hotel_service`;
INSERT INTO `hotel_service`.`Chek` (`idChek`, `date_in_settle`, `date_out_settle`, `status`, `Room_idRoom`, `Guest_idGuest`) VALUES (1, '2013.03.01', '2013.03.20', false, 2, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `hotel_service`.`Service`
-- -----------------------------------------------------
START TRANSACTION;
USE `hotel_service`;
INSERT INTO `hotel_service`.`Service` (`idService`, `name`, `price`, `Chek_idChek`) VALUES (1, 'settle in room', '5', 1);

COMMIT;

