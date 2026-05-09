/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.18-MariaDB : Database - bicikli
*********************************************************************
*/

SET NAMES utf8;
SET SQL_MODE='';

CREATE DATABASE IF NOT EXISTS `bicikli` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `bicikli`;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `StavkaNarudzbenice`;
DROP TABLE IF EXISTS `DobTip`;
DROP TABLE IF EXISTS `Narudzbenica`;
DROP TABLE IF EXISTS `Bicikl`;
DROP TABLE IF EXISTS `TipProizvoda`;
DROP TABLE IF EXISTS `Prodavac`;
DROP TABLE IF EXISTS `Drzava`;
DROP TABLE IF EXISTS `Dobavljac`;
SET FOREIGN_KEY_CHECKS = 1;

-- DOBAVLJAC
CREATE TABLE `Dobavljac` (
  `DobavljacID`   INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime`           VARCHAR(50) NOT NULL,
  `Prezime`       VARCHAR(50) NOT NULL,
  `KorisnickoIme` VARCHAR(50) NOT NULL,
  `Lozinka`       VARCHAR(100) NOT NULL,
  PRIMARY KEY (`DobavljacID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `Dobavljac` (`DobavljacID`, `Ime`, `Prezime`, `KorisnickoIme`, `Lozinka`) VALUES
(1, 'Lazar', 'Mihajlovic', 'lazar', 'lazar'),
(2, 'Sara', 'Milic', 'sara', 'sara'),
(3, 'Nikola', 'Jovanovic', 'nikola', 'nikola'),
(4, 'Jelena', 'Petrovic', 'jelena', 'jelena');

-- DRZAVA
CREATE TABLE `Drzava` (
  `DrzavaID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv`    VARCHAR(80) NOT NULL,
  PRIMARY KEY (`DrzavaID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `Drzava` (`DrzavaID`, `Naziv`) VALUES
(1, 'Srbija'),
(2, 'Hrvatska'),
(3, 'Bosna i Hercegovina'),
(4, 'Crna Gora');

-- PRODAVAC
CREATE TABLE `Prodavac` (
  `ProdavacID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime`        VARCHAR(50) NOT NULL,
  `Prezime`    VARCHAR(50) NOT NULL,
  `Email`      VARCHAR(80) NOT NULL,
  `Telefon`    VARCHAR(30) NOT NULL,
  `DrzavaID`   INT UNSIGNED NOT NULL,
  PRIMARY KEY (`ProdavacID`),
  CONSTRAINT `fk_prodavac_drzava`
    FOREIGN KEY (`DrzavaID`) REFERENCES `Drzava`(`DrzavaID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `Prodavac` (`ProdavacID`, `Ime`, `Prezime`, `Email`, `Telefon`, `DrzavaID`) VALUES
(1, 'Ivana', 'Simic', 'ivana@bike.rs', '063111222', 1),
(2, 'Dusan', 'Stojanovic', 'dusan@bike.rs', '063333444', 1),
(3, 'Marija', 'Ilic', 'marija@bike.rs', '063555666', 2),
(4, 'Vladimir', 'Nikolic', 'vladimir@bike.rs', '063777888', 3);

-- TIP PROIZVODA
CREATE TABLE `TipProizvoda` (
  `TipProizvodaID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv`          VARCHAR(80) NOT NULL,
  `Opis`           VARCHAR(255),
  PRIMARY KEY (`TipProizvodaID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `TipProizvoda` (`TipProizvodaID`, `Naziv`, `Opis`) VALUES
(1, 'E-bike', 'Elektricni bicikli za grad i trekking'),
(2, 'Trekking bicikl', 'Komfor i stabilnost na duzim turama'),
(3, 'BMX', 'Bicikli za trikove i park voznju'),
(4, 'Gradski bicikl', 'Prakticni bicikli za svakodnevnu voznju'),
(5, 'Oprema', 'Prateca oprema i dodatci za bicikle');

-- BICIKL
CREATE TABLE `Bicikl` (
  `BiciklID`         INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv`            VARCHAR(120) NOT NULL,
  `Opis`             VARCHAR(255),
  `CenaPoJedinici`   DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`BiciklID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `Bicikl` (`BiciklID`, `Naziv`, `Opis`, `CenaPoJedinici`) VALUES
(1, 'E-Trekking 28', 'Baterija 36V 12Ah, disk kocnice', 164990.00),
(2, 'Trekking 28 Alivio', 'Podesiva lula, 3x9 brzina', 74990.00),
(3, 'BMX 20 Freestyle', 'Hi-ten ram, rotor', 34990.00),
(4, 'Gradski 28 Nexus 3', 'Unutrasnje brzine, blatobrani', 58990.00),
(5, 'MTB 29 Sport', 'Aluminijumski ram, hidraulicne kocnice', 89990.00),
(6, 'Deciji bicikl 24', 'Lagan ram i V-brake sistem', 29990.00);

-- NARUDZBENICA
CREATE TABLE `Narudzbenica` (
  `NarudzbenicaID`    INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `DatumVremeFormiranja` DATE NOT NULL,
  `DatumNarudzbine`   DATE NOT NULL,
  `UkupnaCena`        DECIMAL(10,2) NOT NULL,
  `DobavljacID`       INT UNSIGNED NOT NULL,
  `ProdavacID`        INT UNSIGNED NOT NULL,
  PRIMARY KEY (`NarudzbenicaID`),
  CONSTRAINT `fk_narudzbenica_dobavljac`
    FOREIGN KEY (`DobavljacID`) REFERENCES `Dobavljac`(`DobavljacID`),
  CONSTRAINT `fk_narudzbenica_prodavac`
    FOREIGN KEY (`ProdavacID`) REFERENCES `Prodavac`(`ProdavacID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `Narudzbenica` (`NarudzbenicaID`, `DatumVremeFormiranja`, `DatumNarudzbine`, `UkupnaCena`, `DobavljacID`, `ProdavacID`) VALUES
(1, '2026-03-01', '2026-03-02', 239980.00, 1, 1),
(2, '2026-03-03', '2026-03-04', 93980.00, 2, 2),
(3, '2026-03-05', '2026-03-06', 124980.00, 3, 3),
(4, '2026-03-07', '2026-03-08', 194980.00, 4, 4);

-- DOBTIP
CREATE TABLE `DobTip` (
  `DobavljacID`     INT UNSIGNED NOT NULL,
  `TipProizvodaID`  INT UNSIGNED NOT NULL,
  `DatumNabavke`    DATE NOT NULL,
  PRIMARY KEY (`DobavljacID`, `TipProizvodaID`),
  CONSTRAINT `fk_dobtip_dobavljac`
    FOREIGN KEY (`DobavljacID`) REFERENCES `Dobavljac`(`DobavljacID`),
  CONSTRAINT `fk_dobtip_tipproizvoda`
    FOREIGN KEY (`TipProizvodaID`) REFERENCES `TipProizvoda`(`TipProizvodaID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `DobTip` (`DobavljacID`, `TipProizvodaID`, `DatumNabavke`) VALUES
(1, 1, '2026-02-10'),
(1, 2, '2026-02-11'),
(2, 3, '2026-02-15'),
(2, 5, '2026-02-16'),
(3, 4, '2026-02-18'),
(3, 2, '2026-02-19'),
(4, 1, '2026-02-21'),
(4, 5, '2026-02-22');

-- STAVKA NARUDZBENICE
CREATE TABLE `StavkaNarudzbenice` (
  `NarudzbenicaID`   INT UNSIGNED NOT NULL,
  `Rb`               INT NOT NULL,
  `Kolicina`         INT NOT NULL,
  `CenaPoJedinici`   DECIMAL(10,2) NOT NULL,
  `Iznos`            DECIMAL(10,2) NOT NULL,
  `BiciklID`         INT UNSIGNED NOT NULL,
  PRIMARY KEY (`NarudzbenicaID`, `Rb`),
  CONSTRAINT `fk_stavka_narudzbenica`
    FOREIGN KEY (`NarudzbenicaID`) REFERENCES `Narudzbenica`(`NarudzbenicaID`) ON DELETE CASCADE,
  CONSTRAINT `fk_stavka_bicikl`
    FOREIGN KEY (`BiciklID`) REFERENCES `Bicikl`(`BiciklID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `StavkaNarudzbenice` (`NarudzbenicaID`, `Rb`, `Kolicina`, `CenaPoJedinici`, `Iznos`, `BiciklID`) VALUES
(1, 1, 1, 164990.00, 164990.00, 1),
(1, 2, 1, 74990.00, 74990.00, 2),

(2, 1, 1, 34990.00, 34990.00, 3),
(2, 2, 1, 58990.00, 58990.00, 4),

(3, 1, 1, 89990.00, 89990.00, 5),
(3, 2, 1, 34990.00, 34990.00, 3),

(4, 1, 1, 164990.00, 164990.00, 1),
(4, 2, 1, 29990.00, 29990.00, 6);