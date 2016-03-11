
-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Status` (
  `idStatus` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idStatus`),
  UNIQUE INDEX `idStatus_UNIQUE` (`idStatus` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Room` (
  `number` INT UNSIGNED NOT NULL,
  `content` INT NOT NULL,
  `stars` INT NOT NULL,
  `price` INT NOT NULL,
  `Status_idStatus` INT NOT NULL,
  PRIMARY KEY (`number`),
  INDEX `fk_Room_Status1_idx` (`Status_idStatus` ASC),
  UNIQUE INDEX `idRoom_UNIQUE` (`number` ASC),
  CONSTRAINT `fk_Room_Status1`
    FOREIGN KEY (`Status_idStatus`)
    REFERENCES `mydb`.`Status` (`idStatus`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`History_Room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`History_Room` (
  `idHistory_Room` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Room_idRoom` INT NOT NULL,
  PRIMARY KEY (`idHistory_Room`),
  INDEX `fk_History_Room_Room1_idx` (`Room_idRoom` ASC),
  UNIQUE INDEX `idHistory_Room_UNIQUE` (`idHistory_Room` ASC),
  CONSTRAINT `fk_History_Room_Room1`
    FOREIGN KEY (`Room_idRoom`)
    REFERENCES `mydb`.`Room` (`number`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Guest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Guest` (
  `idGuest` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `pasport` VARCHAR(45) NOT NULL,
  `dateInSettle` DATE NULL,
  `dateOutSettle` DATE NULL,
  `History_Room_idHistory_Room` INT NOT NULL,
  PRIMARY KEY (`idGuest`),
  INDEX `fk_Guest_History_Room1_idx` (`History_Room_idHistory_Room` ASC),
  UNIQUE INDEX `idGuest_UNIQUE` (`idGuest` ASC),
  CONSTRAINT `fk_Guest_History_Room1`
    FOREIGN KEY (`History_Room_idHistory_Room`)
    REFERENCES `mydb`.`History_Room` (`idHistory_Room`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Check`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Check` (
  `idCheck` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `sumPrice` INT NULL,
  `Room_idRoom` INT NOT NULL,
  `Guest_idGuest` INT NOT NULL,
  PRIMARY KEY (`idCheck`),
  INDEX `fk_Check_Room1_idx` (`Room_idRoom` ASC),
  INDEX `fk_Check_Guest1_idx` (`Guest_idGuest` ASC),
  UNIQUE INDEX `idCheck_UNIQUE` (`idCheck` ASC),
  CONSTRAINT `fk_Check_Room1`
    FOREIGN KEY (`Room_idRoom`)
    REFERENCES `mydb`.`Room` (`number`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Check_Guest1`
    FOREIGN KEY (`Guest_idGuest`)
    REFERENCES `mydb`.`Guest` (`idGuest`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`DailService`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`DailService` (
  `idDailService` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` INT NOT NULL,
  `Check_idCheck` INT NOT NULL,
  PRIMARY KEY (`idDailService`),
  INDEX `fk_DailService_Check1_idx` (`Check_idCheck` ASC),
  UNIQUE INDEX `idDailService_UNIQUE` (`idDailService` ASC),
  CONSTRAINT `fk_DailService_Check1`
    FOREIGN KEY (`Check_idCheck`)
    REFERENCES `mydb`.`Check` (`idCheck`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`AdditionalService`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`AdditionalService` (
  `idAdditionalService` INT UNSIGNED NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `price` INT NOT NULL,
  `description` VARCHAR(50) NULL,
  `addPrice` INT NOT NULL,
  `Check_idCheck` INT NOT NULL,
  PRIMARY KEY (`idAdditionalService`),
  INDEX `fk_AdditionalService_Check1_idx` (`Check_idCheck` ASC),
  UNIQUE INDEX `idAdditionalService_UNIQUE` (`idAdditionalService` ASC),
  CONSTRAINT `fk_AdditionalService_Check1`
    FOREIGN KEY (`Check_idCheck`)
    REFERENCES `mydb`.`Check` (`idCheck`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `mydb`.`Status`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Status` (`idStatus`, `name`) VALUES (1, 'free');
INSERT INTO `mydb`.`Status` (`idStatus`, `name`) VALUES (2, 'notFree');
INSERT INTO `mydb`.`Status` (`idStatus`, `name`) VALUES (3, 'onrepair');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Room`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Room` (`number`, `content`, `stars`, `price`, `Status_idStatus`) VALUES (1, 2, 3, 5, DEFAULT);
INSERT INTO `mydb`.`Room` (`number`, `content`, `stars`, `price`, `Status_idStatus`) VALUES (2, 3, 3, 10, DEFAULT);
INSERT INTO `mydb`.`Room` (`number`, `content`, `stars`, `price`, `Status_idStatus`) VALUES (3, 3, 3, 10, DEFAULT);
INSERT INTO `mydb`.`Room` (`number`, `content`, `stars`, `price`, `Status_idStatus`) VALUES (4, 2, 2, 5, DEFAULT);
INSERT INTO `mydb`.`Room` (`number`, `content`, `stars`, `price`, `Status_idStatus`) VALUES (5, 4, 2, 10, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Guest`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Guest` (`idGuest`, `name`, `pasport`, `dateInSettle`, `dateOutSettle`, `History_Room_idHistory_Room`) VALUES (1, 'Pavel', 'AB123', NULL, NULL, DEFAULT);
INSERT INTO `mydb`.`Guest` (`idGuest`, `name`, `pasport`, `dateInSettle`, `dateOutSettle`, `History_Room_idHistory_Room`) VALUES (2, 'Olga', 'KH125', NULL, NULL, DEFAULT);
INSERT INTO `mydb`.`Guest` (`idGuest`, `name`, `pasport`, `dateInSettle`, `dateOutSettle`, `History_Room_idHistory_Room`) VALUES (3, 'Vasia', 'KH236', NULL, NULL, DEFAULT);
INSERT INTO `mydb`.`Guest` (`idGuest`, `name`, `pasport`, `dateInSettle`, `dateOutSettle`, `History_Room_idHistory_Room`) VALUES (4, 'Tania', 'AB256', NULL, NULL, DEFAULT);
INSERT INTO `mydb`.`Guest` (`idGuest`, `name`, `pasport`, `dateInSettle`, `dateOutSettle`, `History_Room_idHistory_Room`) VALUES (5, 'Oleg', 'KH111', NULL, NULL, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`DailService`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`DailService` (`idDailService`, `name`, `price`, `Check_idCheck`) VALUES (1, 'room', 5, DEFAULT);
INSERT INTO `mydb`.`DailService` (`idDailService`, `name`, `price`, `Check_idCheck`) VALUES (2, 'breakfast', 3, DEFAULT);
INSERT INTO `mydb`.`DailService` (`idDailService`, `name`, `price`, `Check_idCheck`) VALUES (3, 'dinner', 2, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`AdditionalService`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`AdditionalService` (`idAdditionalService`, `name`, `price`, `description`, `addPrice`, `Check_idCheck`) VALUES (11, 'animals', 3, 'walk the dog', 2, DEFAULT);
INSERT INTO `mydb`.`AdditionalService` (`idAdditionalService`, `name`, `price`, `description`, `addPrice`, `Check_idCheck`) VALUES (12, 'animals', 3, 'feed the dog', 5, DEFAULT);
INSERT INTO `mydb`.`AdditionalService` (`idAdditionalService`, `name`, `price`, `description`, `addPrice`, `Check_idCheck`) VALUES (13, 'transport', 5, 'taxi', 2, DEFAULT);
INSERT INTO `mydb`.`AdditionalService` (`idAdditionalService`, `name`, `price`, `description`, `addPrice`, `Check_idCheck`) VALUES (14, 'transport', 5, 'put on the parking', 6, DEFAULT);

COMMIT;

