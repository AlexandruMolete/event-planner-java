-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 13, 2020 at 10:37 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `planificator`
--

-- --------------------------------------------------------

--
-- Table structure for table `cont`
--

CREATE TABLE `cont` (
  `ID` int(11) NOT NULL,
  `Nume` varchar(50) NOT NULL,
  `Parola` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cont`
--

INSERT INTO `cont` (`ID`, `Nume`, `Parola`) VALUES
(1, 'andrei', '[1, 2, 3, 4]'),
(2, 'alexandru', '[9, 8, 7, 6]');

-- --------------------------------------------------------

--
-- Table structure for table `eveniment`
--

CREATE TABLE `eveniment` (
  `ID` int(11) NOT NULL,
  `Nume` varchar(50) NOT NULL,
  `Data` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `ContID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `eveniment`
--

INSERT INTO `eveniment` (`ID`, `Nume`, `Data`, `ContID`) VALUES
(4, 'eveniment2', '2020-05-11 09:07:00', 1),
(5, 'eveniment3', '2007-12-03 08:15:30', 1),
(6, 'eveniment1', '2020-05-11 12:42:30', 1),
(7, 'eveniment4', '2019-01-10 18:20:00', 1),
(8, 'eveniment5', '2007-12-03 08:15:30', 1);

-- --------------------------------------------------------

--
-- Table structure for table `notita`
--

CREATE TABLE `notita` (
  `ID` int(11) NOT NULL,
  `Continut` varchar(100) NOT NULL,
  `EvenimentID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `notita`
--

INSERT INTO `notita` (`ID`, `Continut`, `EvenimentID`) VALUES
(3, 'TEST TEST', 6),
(6, 'test2', 6),
(7, 'Acesta este un text.', 6);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cont`
--
ALTER TABLE `cont`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Nume` (`Nume`),
  ADD UNIQUE KEY `Parola` (`Parola`);

--
-- Indexes for table `eveniment`
--
ALTER TABLE `eveniment`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_CONT` (`ContID`);

--
-- Indexes for table `notita`
--
ALTER TABLE `notita`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_EVENIMENT` (`EvenimentID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cont`
--
ALTER TABLE `cont`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `eveniment`
--
ALTER TABLE `eveniment`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `notita`
--
ALTER TABLE `notita`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `eveniment`
--
ALTER TABLE `eveniment`
  ADD CONSTRAINT `eveniment_ibfk_1` FOREIGN KEY (`ContID`) REFERENCES `cont` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `notita`
--
ALTER TABLE `notita`
  ADD CONSTRAINT `notita_ibfk_1` FOREIGN KEY (`EvenimentID`) REFERENCES `eveniment` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
