DROP SCHEMA IF EXISTS `checkers` ;
CREATE SCHEMA IF NOT EXISTS `checkers` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `checkers` ;

-- -----------------------------------------------------
-- Table `checkers`.`Users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `checkers`.`Users` ;

CREATE  TABLE IF NOT EXISTS `checkers`.`Users` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nickname` VARCHAR(20) NOT NULL ,
  `password` CHAR(64) NOT NULL,
  `last_visit` TIMESTAMP NOT NULL,
  `registration_date` TIMESTAMP NOT NULL,
  `rating` SMALLINT NOT NULL DEFAULT 500,
  `win_quantity` INT UNSIGNED NOT NULL DEFAULT 0,
  `lose_quantity` INT UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) ,
  INDEX `full_idx` (`nickname` ASC, `password` ASC, `id` ASC, `rating` ASC, `win_quantity` ASC, `lose_quantity` ASC),
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) )
ENGINE = InnoDB;

USE `checkers` ;
