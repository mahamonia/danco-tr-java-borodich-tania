-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Hotel_DB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Hotel_DB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Hotel_DB` DEFAULT CHARACTER SET utf8 ;
USE `Hotel_DB` ;

-- -----------------------------------------------------
-- Table `Hotel_DB`.`Guest`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Hotel_DB`.`Guest` ;

CREATE TABLE IF NOT EXISTS `Hotel_DB`.`Guest` (
  `idGuest` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `pasport` VARCHAR(45) NULL,
  PRIMARY KEY (`idGuest`),
  UNIQUE INDEX `idGuest_UNIQUE` (`idGuest` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Hotel_DB`.`Status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Hotel_DB`.`Status` ;

CREATE TABLE IF NOT EXISTS `Hotel_DB`.`Status` (
  `idStatus` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`idStatus`),
  UNIQUE INDEX `idStatus_UNIQUE` (`idStatus` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Hotel_DB`.`Room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Hotel_DB`.`Room` ;

CREATE TABLE IF NOT EXISTS `Hotel_DB`.`Room` (
  `number` INT UNSIGNED NOT NULL,
  `content` INT NULL,
  `stars` INT NULL,
  `price` INT NULL,
  `Status_idStatus` INT NULL,
  PRIMARY KEY (`number`),
  INDEX `fk_Room_Status1_idx` (`Status_idStatus` ASC),
  UNIQUE INDEX `number_UNIQUE` (`number` ASC),
  CONSTRAINT `fk_Room_Status1`
    FOREIGN KEY (`Status_idStatus`)
    REFERENCES `Hotel_DB`.`Status` (`idStatus`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Hotel_DB`.`DailService`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Hotel_DB`.`DailService` ;

CREATE TABLE IF NOT EXISTS `Hotel_DB`.`DailService` (
  `idDailService` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` INT NOT NULL,
  PRIMARY KEY (`idDailService`),
  UNIQUE INDEX `idDailService_UNIQUE` (`idDailService` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Hotel_DB`.`AdditionalService`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Hotel_DB`.`AdditionalService` ;

CREATE TABLE IF NOT EXISTS `Hotel_DB`.`AdditionalService` (
  `idAdditionalService` INT UNSIGNED NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `price` INT NOT NULL,
  `description` VARCHAR(50) NULL,
  `addPrice` INT NOT NULL,
  PRIMARY KEY (`idAdditionalService`),
  UNIQUE INDEX `idAdditionalService_UNIQUE` (`idAdditionalService` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Hotel_DB`.`Check`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Hotel_DB`.`Check` ;

CREATE TABLE IF NOT EXISTS `Hotel_DB`.`Check` (
  `idCheck` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Guest_idGuest` INT NOT NULL,
  `DailService_idDailService` INT UNSIGNED NOT NULL,
  `AdditionalService_idAdditionalService` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idCheck`),
  UNIQUE INDEX `idCheck_UNIQUE` (`idCheck` ASC),
  INDEX `fk_Check_Guest1_idx` (`Guest_idGuest` ASC),
  INDEX `fk_Check_DailService1_idx` (`DailService_idDailService` ASC),
  INDEX `fk_Check_AdditionalService1_idx` (`AdditionalService_idAdditionalService` ASC),
  CONSTRAINT `fk_Check_Guest1`
    FOREIGN KEY (`Guest_idGuest`)
    REFERENCES `Hotel_DB`.`Guest` (`idGuest`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Check_DailService1`
    FOREIGN KEY (`DailService_idDailService`)
    REFERENCES `Hotel_DB`.`DailService` (`idDailService`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Check_AdditionalService1`
    FOREIGN KEY (`AdditionalService_idAdditionalService`)
    REFERENCES `Hotel_DB`.`AdditionalService` (`idAdditionalService`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Hotel_DB`.`History_Room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Hotel_DB`.`History_Room` ;

CREATE TABLE IF NOT EXISTS `Hotel_DB`.`History_Room` (
  `idHistory_Room` INT NOT NULL AUTO_INCREMENT,
  `Date_In_Settle` DATE NULL,
  `Date_Out_Settle` DATE NULL,
  `Room_number` INT UNSIGNED NOT NULL,
  `Guest_idGuest` INT NOT NULL,
  PRIMARY KEY (`idHistory_Room`),
  INDEX `fk_History_Room_Room1_idx` (`Room_number` ASC),
  INDEX `fk_History_Room_Guest1_idx` (`Guest_idGuest` ASC),
  CONSTRAINT `fk_History_Room_Room1`
    FOREIGN KEY (`Room_number`)
    REFERENCES `Hotel_DB`.`Room` (`number`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_History_Room_Guest1`
    FOREIGN KEY (`Guest_idGuest`)
    REFERENCES `Hotel_DB`.`Guest` (`idGuest`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `Hotel_DB`.`Guest`
-- -----------------------------------------------------
START TRANSACTION;
USE `Hotel_DB`;
INSERT INTO `Hotel_DB`.`Guest` (`idGuest`, `name`, `pasport`) VALUES (1, 'Pavel', 'AB123');
INSERT INTO `Hotel_DB`.`Guest` (`idGuest`, `name`, `pasport`) VALUES (2, 'Olga', 'KH125');
INSERT INTO `Hotel_DB`.`Guest` (`idGuest`, `name`, `pasport`) VALUES (3, 'Vasia', 'KH236');
INSERT INTO `Hotel_DB`.`Guest` (`idGuest`, `name`, `pasport`) VALUES (4, 'Tania', 'AB256');
INSERT INTO `Hotel_DB`.`Guest` (`idGuest`, `name`, `pasport`) VALUES (5, 'Oleg', 'KH111');
INSERT INTO `Hotel_DB`.`Guest` (`idGuest`, `name`, `pasport`) VALUES (6, 'Ann', 'KH159');
INSERT INTO `Hotel_DB`.`Guest` (`idGuest`, `name`, `pasport`) VALUES (7, 'Ivan', 'AB147');
INSERT INTO `Hotel_DB`.`Guest` (`idGuest`, `name`, `pasport`) VALUES (8, 'Petr', 'KH222');
INSERT INTO `Hotel_DB`.`Guest` (`idGuest`, `name`, `pasport`) VALUES (9, 'Join', 'KH333');
INSERT INTO `Hotel_DB`.`Guest` (`idGuest`, `name`, `pasport`) VALUES (10, 'Fedor', 'KH505');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Hotel_DB`.`Status`
-- -----------------------------------------------------
START TRANSACTION;
USE `Hotel_DB`;
INSERT INTO `Hotel_DB`.`Status` (`idStatus`, `name`) VALUES (1, 'free');
INSERT INTO `Hotel_DB`.`Status` (`idStatus`, `name`) VALUES (2, 'notFree');
INSERT INTO `Hotel_DB`.`Status` (`idStatus`, `name`) VALUES (3, 'onrepair');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Hotel_DB`.`Room`
-- -----------------------------------------------------
START TRANSACTION;
USE `Hotel_DB`;
INSERT INTO `Hotel_DB`.`Room` (`number`, `content`, `stars`, `price`, `Status_idStatus`) VALUES (1, 2, 3, 5, 2);
INSERT INTO `Hotel_DB`.`Room` (`number`, `content`, `stars`, `price`, `Status_idStatus`) VALUES (2, 3, 3, 10, 2);
INSERT INTO `Hotel_DB`.`Room` (`number`, `content`, `stars`, `price`, `Status_idStatus`) VALUES (3, 3, 3, 10, 2);
INSERT INTO `Hotel_DB`.`Room` (`number`, `content`, `stars`, `price`, `Status_idStatus`) VALUES (4, 2, 2, 5, 2);
INSERT INTO `Hotel_DB`.`Room` (`number`, `content`, `stars`, `price`, `Status_idStatus`) VALUES (5, 4, 2, 10, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Hotel_DB`.`DailService`
-- -----------------------------------------------------
START TRANSACTION;
USE `Hotel_DB`;
INSERT INTO `Hotel_DB`.`DailService` (`idDailService`, `name`, `price`) VALUES (1, 'room', 5);
INSERT INTO `Hotel_DB`.`DailService` (`idDailService`, `name`, `price`) VALUES (2, 'breakfast', 3);
INSERT INTO `Hotel_DB`.`DailService` (`idDailService`, `name`, `price`) VALUES (3, 'dinner', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Hotel_DB`.`AdditionalService`
-- -----------------------------------------------------
START TRANSACTION;
USE `Hotel_DB`;
INSERT INTO `Hotel_DB`.`AdditionalService` (`idAdditionalService`, `name`, `price`, `description`, `addPrice`) VALUES (11, 'animals', 3, 'walk the dog', 2);
INSERT INTO `Hotel_DB`.`AdditionalService` (`idAdditionalService`, `name`, `price`, `description`, `addPrice`) VALUES (12, 'animals', 3, 'feed the dog', 5);
INSERT INTO `Hotel_DB`.`AdditionalService` (`idAdditionalService`, `name`, `price`, `description`, `addPrice`) VALUES (13, 'transport', 5, 'taxi', 2);
INSERT INTO `Hotel_DB`.`AdditionalService` (`idAdditionalService`, `name`, `price`, `description`, `addPrice`) VALUES (14, 'transport', 5, 'put on the parking', 6);

COMMIT;

