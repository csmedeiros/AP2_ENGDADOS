-- MySQL Script generated by MySQL Workbench
-- Tue Nov  7 15:39:09 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema academia
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema academia
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `academia` DEFAULT CHARACTER SET utf8 ;
USE `academia` ;

-- -----------------------------------------------------
-- Table `academia`.`Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academia`.`Funcionario` (
  `nome` VARCHAR(60) NOT NULL,
  `cpf` VARCHAR(10) NOT NULL,
  `salario` FLOAT NOT NULL,
  `cargo` VARCHAR(45) NOT NULL,
  `registro` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`cpf`, `registro`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) VISIBLE,
  UNIQUE INDEX `registro_UNIQUE` (`registro` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academia`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academia`.`Cliente` (
  `matricula` VARCHAR(12) NOT NULL,
  `cpf` VARCHAR(10) NOT NULL,
  `telefone` VARCHAR(11) NOT NULL,
  `nome` VARCHAR(60) NOT NULL,
  `endereco` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`matricula`, `cpf`),
  UNIQUE INDEX `matricula_UNIQUE` (`matricula` ASC) VISIBLE,
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academia`.`Instrutor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academia`.`Instrutor` (
  `nome` VARCHAR(60) NOT NULL,
  `cpf` VARCHAR(10) NOT NULL,
  `salario` FLOAT NOT NULL,
  `registro` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`cpf`, `registro`),
  UNIQUE INDEX `registro_UNIQUE` (`registro` ASC) VISIBLE,
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academia`.`Aula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academia`.`Aula` (
  `data` DATETIME NOT NULL,
  `modalidade` VARCHAR(45) NOT NULL,
  `fk_instrutor` VARCHAR(12) NOT NULL,
  `id` INT NOT NULL,
  `fk_cliente` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_aula_instrutor_idx` (`fk_instrutor` ASC) VISIBLE,
  INDEX `fk_aula_cliente_idx` (`fk_cliente` ASC) VISIBLE,
  CONSTRAINT `fk_aula_instrutor`
    FOREIGN KEY (`fk_instrutor`)
    REFERENCES `academia`.`Instrutor` (`registro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aula_cliente`
    FOREIGN KEY (`fk_cliente`)
    REFERENCES `academia`.`Cliente` (`matricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academia`.`Pagamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academia`.`Pagamento` (
  `valor` FLOAT NOT NULL,
  `data` DATE NOT NULL,
  `modalidade` VARCHAR(45) NOT NULL,
  `fk_gerente` VARCHAR(12) NOT NULL,
  `fk_cliente` VARCHAR(12) NOT NULL,
  `plano` INT NOT NULL,
  INDEX `fk_pagamento_cliente_idx` (`fk_cliente` ASC) VISIBLE,
  INDEX `fk_pagamento_funcionario_idx` (`fk_gerente` ASC) VISIBLE,
  UNIQUE INDEX `data_UNIQUE` (`data` ASC) VISIBLE,
  UNIQUE INDEX `modalidade_UNIQUE` (`modalidade` ASC) VISIBLE,
  PRIMARY KEY (`data`, `fk_cliente`),
  CONSTRAINT `fk_pagamento_cliente`
    FOREIGN KEY (`fk_cliente`)
    REFERENCES `academia`.`Cliente` (`matricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pagamento_gerente`
    FOREIGN KEY (`fk_gerente`)
    REFERENCES `academia`.`Funcionario` (`registro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
