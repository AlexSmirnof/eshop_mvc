-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema eshop_mvc
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema eshop_mvc
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `eshop_mvc` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `eshop_mvc` ;

-- -----------------------------------------------------
-- Table `eshop_mvc`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eshop_mvc`.`Users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `login_UNIQUE` (`login` ASC))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eshop_mvc`.`Phones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eshop_mvc`.`Phones` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `model` VARCHAR(255) NOT NULL,
  `color` VARCHAR(45) NULL,
  `displaySize` VARCHAR(45) NULL,
  `ph_length` VARCHAR(45) NULL,
  `width` VARCHAR(45) NULL,
  `camera` VARCHAR(45) NULL,
  `price` DECIMAL(10,2) UNSIGNED NOT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `model_UNIQUE` (`model` ASC))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eshop_mvc`.`Orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eshop_mvc`.`Orders` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `deliveryAddress` VARCHAR(255) NOT NULL,
  `contactPhone` VARCHAR(45) NULL,
  `totalPrice` DECIMAL(10,2) NOT NULL,
  `user_id` BIGINT NOT NULL,
PRIMARY KEY (`id`),
INDEX `user_id_idx` (`user_id` ASC),
CONSTRAINT `fk_orders_user`
FOREIGN KEY (`user_id`)
REFERENCES `eshop_mvc`.`Users` (`id`)
ON DELETE CASCADE
ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eshop_mvc`.`OrderItems`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eshop_mvc`.`OrderItems` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `phone_id` BIGINT NOT NULL,
  `quantity` INT NOT NULL,
  `order_id` BIGINT NOT NULL,
PRIMARY KEY (`id`),
INDEX `fk_phone_idx` (`phone_id` ASC),
INDEX `fk_order_idx` (`order_id` ASC),
CONSTRAINT `fk_oi_phone`
FOREIGN KEY (`phone_id`)
REFERENCES `eshop_mvc`.`Phones` (`id`)
ON DELETE CASCADE
ON UPDATE NO ACTION,
CONSTRAINT `fk_oi_order`
FOREIGN KEY (`order_id`)
REFERENCES `eshop_mvc`.`Orders` (`id`)
ON DELETE CASCADE
ON UPDATE NO ACTION)
  ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
