-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Employee
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Employee
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Employee` DEFAULT CHARACTER SET utf8 ;
USE `Employee` ;

-- -----------------------------------------------------
-- Table `Employee`.`Employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Employee`.`Employee` ;

CREATE TABLE IF NOT EXISTS `Employee`.`Employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `f_name` VARCHAR(45) NULL,
  `l_name` VARCHAR(45) NULL,
  `b_date` DATE NULL,
  `gender` ENUM('FEMALE', 'MAN') NULL,
  `title` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
