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
  `isDeleted` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`stname`),
  INDEX `trainline_idx` (`trainline` ASC) INVISIBLE,
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
  INDEX `from_idx` (`from` ASC) INVISIBLE,
  INDEX `to_idx` (`to` ASC) INVISIBLE,
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

-- -----------------------------------------------------
-- Table `odemetrics`.`anomalies`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `odemetrics`.`anomalies` (
  `from` VARCHAR(50) NOT NULL,
  `to` VARCHAR(45) NOT NULL,
  `year` INT NOT NULL,
  `month` VARCHAR(45) NOT NULL,
  `ticketType` VARCHAR(45) NOT NULL,
  `value` INT NOT NULL,
  `supervised` TINYINT NOT NULL,
  PRIMARY KEY (`from`, `to`, `year`, `month`, `ticketType`))
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
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MARADANA', 'MDA', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('DEMATAGODA', 'DAG', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KELANIYA', 'KLA', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('WANAWASALA', 'WSL', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('HUNUPITIYA', 'HUN', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('ENDERAMULLA', 'EDM', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('HORAPE', 'HRP', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('RAGAMA', 'RGM', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('WALPOLA', 'WPA', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('BATUWATTA', 'BTU', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('BULUGAHAGODA', 'BGH', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('GANEMULLA', 'GAN', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('YAGODA', 'YGD', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('GAMPAHA', 'GPH', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('DARALUWA', 'DRA', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('BEMMULLA', 'BEM', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MAGALEGODA', 'MGG', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('HEENDENIYA PATTIGODA', 'HDP', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('VEYANGODA', 'VGD', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('WADURAWA', 'WRW', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KEENAWALA', 'KEN', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PALLEWELA', 'PLL', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('GANEGODA', 'GND', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('WIJAYA RAJADAHANA', 'WRD', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MIRIGAMA', 'MIR', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('WILWATTA', 'WWT', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('BOTALE', 'BTL', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('AMBEPUSSA', 'APS', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('YATTALGODA', 'YTG', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('BUJJOMUWA', 'BJM', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('ALAWWA', 'ALW', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('WALAKUMBURA', 'WKA', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('POLGAHAWELA', 'PLG', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PANALIYA', 'PNL', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('TISMALPOLA', 'TSM', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('YATAGAMA', 'YGM', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('RAMBUKKANA', 'RBK', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KADIGAMUWA', 'KMA', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('GANGODA', 'GDA', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('IHALAKOTTE', 'IKT', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('GOOD VIEW POINT', 'GVP', 1, false, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('BALANA', 'BNA', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KADUGANNAWA', 'KGW', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PILIMATALAWA', 'PLT', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PERADENIYA JUNCTION', 'PDA', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KOSHINNA', 'KHA', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('GELIOYA', 'GEY', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('GAMPOLA', 'GPL', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('TEMBILIGALA', 'TBL', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('ULAPANE', 'ULP', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('NAWALAPITIYA', 'NVP', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('SALEEM', 'SLP', 1, false, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('INGURU OYA', 'INO', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PENROSE', NULL, 1, false, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('GALBODA', 'GBD', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('DEKINDA', NULL, 1, false, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('WATAWALA', 'WLA', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('IHALA WATAWALA', 'IWL', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('ROZELLA', 'RZL', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('HATTON', 'HTN', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('GALKANDAWATTA', NULL, 1, false, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KOTAGALA', 'KGA', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('TALAWAKELE', 'TKL', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('WATAGODA', 'WTG', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('GREAT WESTERN', 'GWN', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('RADELLA', 'RDL', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('NANU OYA', 'NOA', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PARAKRAMAPURA', 'PKP', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('AMBEWELA', 'ABL', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PATTIPOLA', 'PPL', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('OHIYA', 'OHA', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('IDALGASHINNA', 'IGH', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('HAPUTALE', 'HPT', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('DIYATALAWA', 'DLA', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('BANDARAWELA', 'BDA', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KINIGAMA', 'LNM', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('HEEL OYA', 'HLO', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KITHAL ELLA', 'KEL', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('ELLA', 'ELL', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('DEMODARA', 'DDR', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('UDUWARA', 'UDW', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('HALI ELA', 'HEA', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('BADULLA', 'BAD', 1, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PERALANDA', 'PRL', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KANDANA', 'KAN', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KAPUWATTA', 'KAW', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('JAELA', 'JLA', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('TUDELLA', 'TDU', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KUDAHAKAPOLA', 'KUD', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('ALAWATHUPITIYA', 'AWP', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('SEEDUWA', 'SED', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('LIYANAGEMULLA', 'LGM', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('INVESTMENT PRO ZONE', 'IPZ', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('COLOMBO AIR PORT', 'CAK', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KATUNAYAKA', 'KTK', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KURANA', 'KUR', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('NEGOMBO', 'NGB', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KATTUWA', 'KAT', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KOCHCHIKADE', 'KCH', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('WAIKKALA', 'WKL', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('BOLAWATTA', 'BLT', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('BORALESSA', 'BSA', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('LUNUWILA', 'LWL', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('TUMMODERA', 'TDR', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('NATTANDIYA', 'NAT', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('WALAHAPITIYA', 'WHP', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KADAWEWA', 'KWW', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('NELUMPOKUNA', 'NPK', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MADAMPE', 'MDP', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KAKAPALLIYA', 'KYA', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('SAWARANA', 'SEWR', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('CHILAW', 'CHL', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MANUWANGAMA', 'MNG', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('BANGADENIYA', 'BGY', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('ARACHCHIKATTUWA', 'AKT', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('ANAWILANDAWA', 'AND', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('BATTULU OYA', 'BOA', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PULACHCHIKULAM', 'PCK', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MUNDAL', 'MNL', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MADGALA ELIYA', 'MGE', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MADURANKULIYA', 'MKI', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PALAVI', 'PVI', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('THILLADIYA', 'TDY', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PUTTALAM', 'PTM', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('NOORNAGAR', 'NOR', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('NAGAVILLUWA', 'EPN', 2, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('COLOMBO FORT', 'FOT', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('SECRETARIAT HALT', 'SCR', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KOMPANNAVIDIYA', 'KPN', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KOLLUPITIYA', 'KLP', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('BAMBALAPITIYA', 'BPT', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('WELLAWATTE', 'WTE', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('DEHIWALA', 'DWL', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MOUNT LAVINIA', 'MLV', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('RATMALANA', 'RML', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('ANGULANA', 'AGL', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('LUNAWA', 'LNA', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MORATUWA', 'MRT', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KORALAWELLA', 'KOR', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('EGODA UYANA', 'EYA', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PANADURA', 'PND', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PINWATTE', 'PIN', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('WADDUWA', 'WDA', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('TRAIN HALT NO 1', 'TRH', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KALUTARA NORTH', 'KTN', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KALUTARA SOUTH', 'KTS', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KATUKURUNDA', 'KKD', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PAYAGALA NORTH', 'PGN', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PAYAGALA SOUTH', 'PGS', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MAGGONA', 'MGN', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('BERUWALA', 'BRL', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('HETTIMULLA', 'HML', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('ALUTHGAMA', 'ALT', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('BENTHOTA', 'BNT', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('INDURUWA', 'IDA', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MAHA INDURUWA', 'MWA', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KOSGODA', 'KDA', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PIYAGAMA', 'PYA', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('AHUNGALLA', 'AUH', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PATHA GANGODA', 'PGD', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('BALAPITIYA', 'BPA', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('ANDADOLA', 'AND', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KANDEGODA', 'KGD', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('AMBALANGODA', 'ABA', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MADAMPAGAMA', 'MPA', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('AKURALA', 'AKU', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KAHAWA', 'KWE', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('TELWATTE', 'TWT', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('SEENIGAMA', 'SMA', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('HIKKADUWA', 'HKD', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('THIRANAGAMA', 'TNA', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KUMARAKANDA', 'KMK', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('DODANDUWA', 'DNA', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('RATGAMA', 'RTG', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('BOOSA', 'BSH', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('GINTOTA', 'GNT', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PIYADIGAMA', 'PGM', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('RICHMOND HILL', 'RCH', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('GALLE', 'GLE', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KATUGODA', 'KUG', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('UNAWATUNA', 'UNW', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('TALPE', 'TLP', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('HABARADUWA', 'HBD', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KOGGALA', 'KOG', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KATHALUWA', 'KTL', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('AHANGAMA', 'ANM', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MIDIGAMA', 'MED', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KUMBALAGAMA', 'KMB', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('WELIGAMA', 'WLM', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('POLWATHU MODARA', 'PLR', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MIRISSA', 'MIS', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KAMBURUGAMUWA', 'KMG', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('WALGAMA', 'WLG', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MATARA', 'MTR', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PILADUWA', 'PLD', 3, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KONWEWA', 'KON', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('RANMUKAGAMA', 'RMA', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MORAGOLLAGAMA', 'MLG', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('SIYABALANGAMUWA', 'SYA', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('NEGAMA', 'NGM', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('AUKANA', 'AWK', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KALAWEWA', 'KLW', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KEKIRAWA', 'KRA', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('HORAWILA', NULL, 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PALUGASWEWA', 'PUW', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('HABARANA', 'HBN', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('HATARASKOTUWA', 'HKT', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('GAL OYA JUNCTION', 'GOA', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MINNERIYA', 'MIY', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('HINGARAKGODA', 'HRG', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('JAYANTHIPURA', 'JAP', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('LAKSAUYANA', 'LYA', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PARAKUMUYANA', 'PKU', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('POLONNARUWA', 'PLN', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('GALLELLA', 'GAL', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MANAMPITIYA', 'MPT', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('SEWANAPITIYA', 'SVP', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('WELIKANDA', 'WKD', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PUNANI', 'PNI', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KADADASI NAGARAYA', 'KDN', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('VALACHCHENAI', 'VCH', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KALKUDAH', 'KKH', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('DEVAPURAM', 'DPM', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('ERAVUR', 'EVR', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('BATTICOLOA', 'BCO', 4, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('GIRAMBE', 'GRB', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('TALAWATTEGEDARA', 'TWG', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('POTHUHERA', 'PTA', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('NAILIYA', 'NLY', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KURUNEGALA', 'KRN', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MUTTATUGALA', 'MTG', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('WELLAWA', 'WEL', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('GANEWATTA', 'GNW', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('HIRIYALA', 'HRL', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('NAGOLLAGAMA', 'NAG', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('THIMBIRIYAGEDARA', 'TIM', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MAHO', 'MHO', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('RANDENIGAMA', 'RGA', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('AMBANPOLA', 'ABN', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('GALGAMUWA', 'GLM', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('SENERATHGAMA', 'SGM', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('TAMBUTTEGAMA', 'TBM', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('TALAWA', 'TLA', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('SRAWASTIPURA', 'SRP', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('ANURADHAPURA NEW TOWN', 'APT', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('ANURADHAPURA', 'ANP', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MIHINTALE JUNCTION', 'MHJ', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MIHINTALE', 'MHN', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('SALAIYAPURA', 'SAL', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PARASANGAHAWEWA', 'PHW', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MEDAGAMA', 'MEM', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MADAWACHCHIYA', 'MWH', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('POONEWA', 'PON', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('VAVUNIYA', 'VNA', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('ERATPERIYAKULAM', 'EKM', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('THANDIKULAM', 'TDK', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('OMANTAI', 'OMT', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PULIYANKULAMA', 'PKM', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MANKULAMA', 'MKM', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MURIKANDY', 'MRK', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KILLINOCHOIH', 'KOC', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PARANTHAN', 'PRN', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('ELEPHANT PASS', 'EPS', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PALLAI', 'PAI', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('ELUTHUMATTUVAL', 'EML', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MIRUSUVIL', 'MSL', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KODIKAMAM', 'KKM', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MEESALAI', 'MES', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('SANKATHTHANAI', 'SAK', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('CHAVAKACHCHERI', 'CCH', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('THACHCHANTHOPPU', 'TPH', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('NAVATKULI', 'NVT', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PUNGANKULAM', 'PNK', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('JAFFNA', 'JFN', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KOKUVIL', 'KKV', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KONDAVIL', 'KDV', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('INUVIL', 'INL', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('CHUNNAKAM', 'CKM', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MALLAKAM', 'MAL', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('TELLIPALLAI', 'TPI', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MAVITTAPURAM', 'MVT', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KAN. CEMENT FAC. SID.', 'CFS', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KANKESANTURAL', 'KKS', 5, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('AGBOPURA', 'APR', 6, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KANTALE', 'KNI', 6, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MOLLIPOTANA', 'MPL', 6, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('TAMPALAKAMAM', 'TAN', 6, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('CHINA BAY', 'CBY', 6, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('TRINCOMALEE', 'TCO', 6, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('SARASAVIUYANA', 'SUA', 7, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KANDY', 'KDT', 7, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('ASGIRIYA', NULL, 7, false, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MAHAIYAWA', 'MYA', 7, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KATUGASTOTA ROAD', NULL, 7, false, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MAWILMADA', NULL, 7, false, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KATUGASTOTA', 'KTG', 7, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PALLETALAWINNA', 'PAL', 7, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('UDATALAWINNA', 'UDL', 7, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MEEGAMMANA', 'MEE', 7, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('YATIRAWANA', NULL, 7, false, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('WATTEGAMA', 'WGA', 7, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('YATAWARA', NULL, 7, false, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PATHANPAHA', 'PTP', 7, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MARUKONA', NULL, 7, false, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('UDATHATHAWELA', 'UWL', 7, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('UKUWELA', 'UKL', 7, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('TAWALANK OYA', NULL, 7, false, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('ELWALA', NULL, 7, false, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KOHOMBILIWALA', NULL, 7, false, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MATALE', 'MTL', 7, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('BASELINE ROAD', 'BSL', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('COTTA ROAD', 'CRD', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('NARAHENPITA', 'NHP', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KIRILLAPONE', 'KPE', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('NUGEGODA', 'NUG', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PANGIRIWATTA', 'PRW', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('UDAHAMULLA', 'UHM', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('NAVINNA', 'NWN', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MAHARAGAMA', 'MAG', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PANNIPITIYA', 'PAN', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KOTTWA', 'KOT', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MALAPALLA', 'MPL', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('HOMAGAMA HOSPITAL ROAD', 'HHR', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('HOMAGAMA', 'HMA', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PANAGODA', 'PNG', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('GODAGAMA', 'GGA', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MEEGODA', 'MGD', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('WATAREKA', 'WAT', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PADUKKA', 'PDK', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('ARUKWATTE', 'ARW', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('ANGAMPITIYA', 'APT', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('UGGALLA', 'UGL', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PINNAWALA', 'PNW', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('GAMMANA', 'GMA', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MORAKELE', 'MKP', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('WAGA', 'WGG', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KADUGODA', 'KDG', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('KOSGAMA', 'KSG', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PUWAKPITIYA', 'PWP', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('AVISSAWELLA', 'AVS', 8, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('Neriyankulam', 'NYK', 9, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('CHEDDIKULAM', 'CDK', 9, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MADHU ROAD', 'MRD', 9, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MURUNKAN', 'MUK', 9, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MATHOTTAM', 'MTM', 9, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('THIRUKETHEESWARAM', 'TVM', 9, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('MANNAR', 'MAN', 9, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('THODDAWELI', 'TDV', 9, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('PESALAI', 'PES', 9, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('TALAIMANNAR', 'TLM', 9, true, false);
INSERT INTO `odemetrics`.`stations` (`stname`, `stcode`, `trainline`, `anOrigin`, `isDeleted`) VALUES ('TALAIMANNAR PIER', 'TMP', 9, true, false);

COMMIT;


-- -----------------------------------------------------
-- Data for table `odemetrics`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `odemetrics`;
INSERT INTO `odemetrics`.`users` (`username`, `password`, `role`) VALUES ('SANDUNI', '12345', 'admin');
INSERT INTO `odemetrics`.`users` (`username`, `password`, `role`) VALUES ('KOUMUDI', '12345', 'user');

COMMIT;

