-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema creditdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema creditdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `creditdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `creditdb` ;

-- -----------------------------------------------------
-- Table `creditdb`.`education`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `creditdb`.`education` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` ENUM('Среднее общее', 'Среднее профессиональное', 'Высшее') NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `creditdb`.`gender`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `creditdb`.`gender` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `gender` ENUM('Мужской', 'Женский') NOT NULL,
  `gender_le` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `creditdb`.`position`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `creditdb`.`position` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` ENUM('руководитель', 'специалист', 'другая') NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `creditdb`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `creditdb`.`client` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `phone` VARCHAR(255) NOT NULL,
  `dob` DATETIME NOT NULL,
  `registration` VARCHAR(255) NOT NULL,
  `experience` INT NOT NULL,
  `income` DECIMAL(10,2) NOT NULL,
  `dependents` INT NOT NULL,
  `gender` INT NOT NULL,
  `education` INT NOT NULL,
  `position` INT NOT NULL,
  `marital` TINYINT NOT NULL,
  `full_name` VARCHAR(255) NOT NULL,
  `own_housing` BIT(1) NOT NULL,
  `passport_id` VARCHAR(255) NOT NULL,
  `passport_series` VARCHAR(255) NOT NULL,
  `work_place` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `FK_EDUCATION_idx` (`education` ASC) VISIBLE,
  INDEX `FK_GENDER_idx` (`gender` ASC) VISIBLE,
  INDEX `FK_POSITION_idx` (`position` ASC) VISIBLE,
  CONSTRAINT `FK_EDUCATION`
    FOREIGN KEY (`education`)
    REFERENCES `creditdb`.`education` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_GENDER`
    FOREIGN KEY (`gender`)
    REFERENCES `creditdb`.`gender` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_POSITION`
    FOREIGN KEY (`position`)
    REFERENCES `creditdb`.`position` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 33
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `creditdb`.`rate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `creditdb`.`rate` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rate` DECIMAL(4,2) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `creditdb`.`terms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `creditdb`.`terms` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `term` VARCHAR(10) NOT NULL,
  `months_term` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `creditdb`.`credit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `creditdb`.`credit` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `amount` DECIMAL(19,2) NOT NULL,
  `term` INT NULL DEFAULT NULL,
  `client_id` INT NULL DEFAULT NULL,
  `rate` INT NOT NULL,
  `approved` TINYINT NOT NULL,
  `monthly_payment` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_TERM_idx` (`term` ASC) VISIBLE,
  INDEX `FK_CLIENT` (`client_id` ASC) VISIBLE,
  INDEX `FK_RATE_idx` (`rate` ASC) VISIBLE,
  CONSTRAINT `FK_CLIENT`
    FOREIGN KEY (`client_id`)
    REFERENCES `creditdb`.`client` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_RATE`
    FOREIGN KEY (`rate`)
    REFERENCES `creditdb`.`rate` (`id`),
  CONSTRAINT `FK_TERM`
    FOREIGN KEY (`term`)
    REFERENCES `creditdb`.`terms` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 30
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `creditdb`.`odds`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `creditdb`.`odds` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `age` DOUBLE NULL DEFAULT NULL,
  `gender` DOUBLE NULL DEFAULT NULL,
  `marital` DOUBLE NULL DEFAULT NULL,
  `dependents` DOUBLE NULL DEFAULT NULL,
  `income` DOUBLE NULL DEFAULT NULL,
  `experience` DOUBLE NULL DEFAULT NULL,
  `real_estate` DOUBLE NULL DEFAULT NULL,
  `monthly_payment` DOUBLE NULL DEFAULT NULL,
  `intercept` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
