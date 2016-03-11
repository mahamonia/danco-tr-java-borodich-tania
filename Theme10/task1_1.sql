
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`product` ;

CREATE TABLE IF NOT EXISTS `mydb`.`product` (
  `maker` VARCHAR(10) NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  `model` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`model`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`pc`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`pc` ;

CREATE TABLE IF NOT EXISTS `mydb`.`pc` (
  `code` INT NOT NULL AUTO_INCREMENT,
  `speed` INT NOT NULL,
  `ram` INT NOT NULL,
  `hd` REAL NOT NULL,
  `cd` VARCHAR(10) NOT NULL,
  `price` INT NULL,
  `model` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`code`),
  INDEX `fk_pc_product1_idx` (`model` ASC),
  CONSTRAINT `fk_pc_product1`
    FOREIGN KEY (`model`)
    REFERENCES `mydb`.`product` (`model`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`laptop`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`laptop` ;

CREATE TABLE IF NOT EXISTS `mydb`.`laptop` (
  `code` INT NOT NULL,
  `speed` INT NOT NULL,
  `ram` INT NOT NULL,
  `hd` REAL NOT NULL,
  `price` INT NULL,
  `screen` INT NOT NULL,
  `model` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`code`),
  INDEX `fk_laptop_product1_idx` (`model` ASC),
  CONSTRAINT `fk_laptop_product1`
    FOREIGN KEY (`model`)
    REFERENCES `mydb`.`product` (`model`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`printer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`printer` ;

CREATE TABLE IF NOT EXISTS `mydb`.`printer` (
  `code` INT NOT NULL AUTO_INCREMENT,
  `color` CHAR(1) NOT NULL,
  `type` VARCHAR(10) NOT NULL,
  `price` INT NULL,
  `model` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`code`),
  INDEX `fk_printer_product_idx` (`model` ASC),
  CONSTRAINT `fk_printer_product`
    FOREIGN KEY (`model`)
    REFERENCES `mydb`.`product` (`model`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Data for table `mydb`.`product`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('A', 'pc', 'pc1232');
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('A', 'pc', 'pc1256');
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('A', 'laptop', 'l2365');
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('C', 'printer', 'p3698');
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('E', 'printer', 'p3578');
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('C', 'laptop', 'l2589');
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('B', 'pc', 'pc1456');
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('C', 'laptop', 'l2546');
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('C', 'printer', 'p3125');
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('D', 'pc', 'pc1255');
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('A', 'pc', 'pc1477');
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('A', 'pc', 'pc1233');
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('B', 'pc', 'pc1111');
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('C', 'laptop', 'l2587');
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('C', 'laptop', 'l2111');
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('A', 'laptop', 'l2888');
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('C', 'laptop', 'l2333');
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('B', 'printer', 'p3569');
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('D', 'printer', 'p3211');
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('C', 'printer', 'p3333');
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('A', 'printer', 'p3444');
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('C', 'pc', 'pc1444');
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('C', 'pc', 'pc1999');
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('D', 'laptop', 'l2555');
INSERT INTO `mydb`.`product` (`maker`, `type`, `model`) VALUES ('B', 'laptop', 'l2666');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`pc`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`pc` (`code`, `speed`, `ram`, `hd`, `cd`, `price`, `model`) VALUES (1, 500, 64, 120, '4x', 450, 'pc1232');
INSERT INTO `mydb`.`pc` (`code`, `speed`, `ram`, `hd`, `cd`, `price`, `model`) VALUES (2, 600, 32, 32, '8x', 1500, 'pc1256');
INSERT INTO `mydb`.`pc` (`code`, `speed`, `ram`, `hd`, `cd`, `price`, `model`) VALUES (3, 450, 32, 32, '12x', 500, 'pc1456');
INSERT INTO `mydb`.`pc` (`code`, `speed`, `ram`, `hd`, `cd`, `price`, `model`) VALUES (4, 650, 64, 16, '24x', 450, 'pc1255');
INSERT INTO `mydb`.`pc` (`code`, `speed`, `ram`, `hd`, `cd`, `price`, `model`) VALUES (5, 450, 64, 500, '12x', 500, 'pc1477');
INSERT INTO `mydb`.`pc` (`code`, `speed`, `ram`, `hd`, `cd`, `price`, `model`) VALUES (6, 650, 64, 240, '4x', 1000, 'pc1233');
INSERT INTO `mydb`.`pc` (`code`, `speed`, `ram`, `hd`, `cd`, `price`, `model`) VALUES (7, 450, 32, 120, '8x', 1200, 'pc1111');
INSERT INTO `mydb`.`pc` (`code`, `speed`, `ram`, `hd`, `cd`, `price`, `model`) VALUES (8, 750, 64, 120, '12x', 1200, 'pc1444');
INSERT INTO `mydb`.`pc` (`code`, `speed`, `ram`, `hd`, `cd`, `price`, `model`) VALUES (9, 750, 32, 32, '12x', 1000, 'pc1999');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`laptop`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`laptop` (`code`, `speed`, `ram`, `hd`, `price`, `screen`, `model`) VALUES (1, 350, 32, 16, 700, 11, 'l2365');
INSERT INTO `mydb`.`laptop` (`code`, `speed`, `ram`, `hd`, `price`, `screen`, `model`) VALUES (2, 500, 64, 120, 500, 12, 'l2589');
INSERT INTO `mydb`.`laptop` (`code`, `speed`, `ram`, `hd`, `price`, `screen`, `model`) VALUES (3, 600, 64, 8, 500, 14, 'l2546');
INSERT INTO `mydb`.`laptop` (`code`, `speed`, `ram`, `hd`, `price`, `screen`, `model`) VALUES (4, 450, 32, 120, 1000, 13, 'l2587');
INSERT INTO `mydb`.`laptop` (`code`, `speed`, `ram`, `hd`, `price`, `screen`, `model`) VALUES (5, 450, 32, 500, 1500, 12, 'l2111');
INSERT INTO `mydb`.`laptop` (`code`, `speed`, `ram`, `hd`, `price`, `screen`, `model`) VALUES (6, 550, 64, 120, 1200, 21, 'l2888');
INSERT INTO `mydb`.`laptop` (`code`, `speed`, `ram`, `hd`, `price`, `screen`, `model`) VALUES (7, 350, 32, 500, 1800, 14, 'l2333');
INSERT INTO `mydb`.`laptop` (`code`, `speed`, `ram`, `hd`, `price`, `screen`, `model`) VALUES (8, 750, 64, 120, 1500, 22, 'l2666');
INSERT INTO `mydb`.`laptop` (`code`, `speed`, `ram`, `hd`, `price`, `screen`, `model`) VALUES (9, 750, 32, 120, 120, 21, 'l2555');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`printer`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`printer` (`code`, `color`, `type`, `price`, `model`) VALUES (1, 'n', 'laser', 400, 'p3698');
INSERT INTO `mydb`.`printer` (`code`, `color`, `type`, `price`, `model`) VALUES (2, 'y', 'matrix', 1500, 'p3578');
INSERT INTO `mydb`.`printer` (`code`, `color`, `type`, `price`, `model`) VALUES (3, 'y', 'jet', 600, 'p3125');
INSERT INTO `mydb`.`printer` (`code`, `color`, `type`, `price`, `model`) VALUES (4, 'n', 'jet', 1200, 'p3569');
INSERT INTO `mydb`.`printer` (`code`, `color`, `type`, `price`, `model`) VALUES (5, 'y', 'laser', 1500, 'p3211');
INSERT INTO `mydb`.`printer` (`code`, `color`, `type`, `price`, `model`) VALUES (6, 'y', 'laser', 1300, 'p3333');
INSERT INTO `mydb`.`printer` (`code`, `color`, `type`, `price`, `model`) VALUES (7, 'y', 'laser', 800, 'p3444');

COMMIT;

