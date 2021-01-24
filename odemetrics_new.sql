-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema odemetrics
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema odemetrics
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `odemetrics` ;
USE `odemetrics` ;

-- -----------------------------------------------------
-- Table `odemetrics`.`trainlines`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `odemetrics`.`trainlines` (
  `lineId` INT NOT NULL,
  `name` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`lineId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `odemetrics`.`stations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `odemetrics`.`stations` (
  `stname` VARCHAR(22) NOT NULL,
  `stcode` VARCHAR(16) NULL,
  `trainline` INT NOT NULL,
  `anOrigin` TINYINT NOT NULL,
  PRIMARY KEY (`stname`),
  INDEX `trainline_idx` (`trainline` ASC) VISIBLE,
  CONSTRAINT `trainline`
    FOREIGN KEY (`trainline`)
    REFERENCES `odemetrics`.`trainlines` (`lineId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `odemetrics`.`travelled_tickets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `odemetrics`.`travelled_tickets` (
  `year` INT NOT NULL,
  `month` VARCHAR(22) NOT NULL,
  `from` VARCHAR(22) NOT NULL,
  `to` VARCHAR(22) NOT NULL,
  `1stCls` INT NOT NULL,
  `2ndCls` INT NOT NULL,
  `3rdClsA` INT NOT NULL,
  `3rdClsB` INT NOT NULL,
  `3rdClsC` INT NOT NULL,
  `total` INT NOT NULL,
  PRIMARY KEY (`year`, `month`, `from`, `to`),
  INDEX `from_idx` (`from` ASC) VISIBLE,
  INDEX `to_idx` (`to` ASC) VISIBLE,
  CONSTRAINT `from`
    FOREIGN KEY (`from`)
    REFERENCES `odemetrics`.`stations` (`stname`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `to`
    FOREIGN KEY (`to`)
    REFERENCES `odemetrics`.`stations` (`stname`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `odemetrics`.`aggregate_counts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `odemetrics`.`aggregate_counts` (
  `origin` VARCHAR(22) NOT NULL,
  `month` VARCHAR(22) NOT NULL,
  `year` INT NOT NULL,
  `booked_tkt` INT NOT NULL,
  `returned_tkt` INT NOT NULL,
  PRIMARY KEY (`origin`, `year`, `month`),
  CONSTRAINT `origin`
    FOREIGN KEY (`origin`)
    REFERENCES `odemetrics`.`stations` (`stname`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `odemetrics`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `odemetrics`.`users` (
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `role` VARCHAR(45) NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `odemetrics`.`trainlines`
-- -----------------------------------------------------
START TRANSACTION;
USE `odemetrics`;
INSERT INTO `odemetrics`.`trainlines` (`lineId`, `name`) VALUES (1, 'mainline');
INSERT INTO `odemetrics`.`trainlines` (`lineId`, `name`) VALUES (2, 'puttalam');
INSERT INTO `odemetrics`.`trainlines` (`lineId`, `name`) VALUES (3, 'coast');
INSERT INTO `odemetrics`.`trainlines` (`lineId`, `name`) VALUES (4, 'batticaloa');
INSERT INTO `odemetrics`.`trainlines` (`lineId`, `name`) VALUES (5, 'northern');
INSERT INTO `odemetrics`.`trainlines` (`lineId`, `name`) VALUES (6, 'trincomalee');
INSERT INTO `odemetrics`.`trainlines` (`lineId`, `name`) VALUES (7, 'matale');
INSERT INTO `odemetrics`.`trainlines` (`lineId`, `name`) VALUES (8, 'kelani velly');
INSERT INTO `odemetrics`.`trainlines` (`lineId`, `name`) VALUES (9, 'talaimannar');

COMMIT;


-- -----------------------------------------------------
-- Data for table `odemetrics`.`stations`
-- -----------------------------------------------------
START TRANSACTION;
USE `odemetrics`;
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`) VALUES ('AGBOPURA', 'APR', 6, true);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`) VALUES ('KANTALE', 'KNI', 6, true);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`) VALUES ('MOLLIPOTANA', 'MPL', 6, true);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`) VALUES ('TAMPALAKAMAM', 'TAN', 6, true);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`) VALUES ('CHINA BAY', 'CBY', 6, true);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`) VALUES ('TRINCOMALEE', 'TCO', 6, true);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`) VALUES ('MARADANA', 'MDA', 1, true);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`) VALUES ('DEMATAGODA', 'DAG', 1, true);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`) VALUES ('KELANIYA', 'KLA', 1, true);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`) VALUES ('WANAWASALA', 'WSL', 1, true);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`) VALUES ('HUNUPITIYA', 'HUN', 1, true);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`) VALUES ('SARASAVIUYANA', 'SUA', 7, true);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`) VALUES ('KANDY', 'KDT', 7, true);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`) VALUES ('ASGIRIYA', '', 7, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`) VALUES ('MAHAIYAWA', 'MYA', 7, true);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`) VALUES ('KATUGASTOTA ROAD', '', 7, false);

COMMIT;


-- -----------------------------------------------------
-- Data for table `odemetrics`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `odemetrics`;
INSERT INTO `odemetrics`.`users` (`username`, `password`, `role`) VALUES ('SANDUNI', '12345', 'admin');

COMMIT;

