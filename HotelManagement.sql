-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 19, 2019 at 01:11 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `HotelManagement`
--

-- --------------------------------------------------------

--
-- Table structure for table `RoomList`
--

CREATE TABLE `RoomList` (
  `RoomNo` int(11) NOT NULL,
  `Type` varchar(15) NOT NULL,
  `Category` varchar(15) NOT NULL,
  `CostPerDay` double NOT NULL,
  `NumberOfBeds` int(11) NOT NULL,
  `MaximumAllow` int(11) NOT NULL,
  `Status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `RoomList`
--

INSERT INTO `RoomList` (`RoomNo`, `Type`, `Category`, `CostPerDay`, `NumberOfBeds`, `MaximumAllow`, `Status`) VALUES
(2, 'Single', '.    AC   .', 800, 2, 2, 1),
(101, 'Single', '.    AC   .', 800, 1, 2, 0),
(102, 'Single', '.Non AC.', 500, 1, 2, 0),
(201, 'Couple', '.Non AC.', 1500, 1, 3, 0),
(202, 'Couple', '.    AC   .', 2000, 2, 3, 0),
(301, 'Family', '.    AC   .', 5000, 3, 6, 0),
(302, 'Family', '.Non AC.', 4000, 4, 5, 0),
(404, 'Couple', '.    AC   .', 5000, 2, 3, 1),
(999, 'Family', '.    AC   .', 5000, 3, 4, 1);

-- --------------------------------------------------------

--
-- Table structure for table `Submissions`
--

CREATE TABLE `Submissions` (
  `RoomNo` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Phone` varchar(50) NOT NULL,
  `NID` varchar(50) NOT NULL,
  `DaysNo` int(11) NOT NULL,
  `Costs` double NOT NULL,
  `PayMethod` varchar(15) NOT NULL,
  `TxID` varchar(20) NOT NULL,
  `Flag` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Submissions`
--

INSERT INTO `Submissions` (`RoomNo`, `Name`, `Phone`, `NID`, `DaysNo`, `Costs`, `PayMethod`, `TxID`, `Flag`) VALUES
(999, 'Fahmida Afrin', '01923483921', '1122334455', 5, 25000, 'bKash', 'TX01', 1),
(404, 'Sayeda Jannath Mim', '01234567890', '1111000458', 2, 10000, 'bKash', 'TX02', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `RoomList`
--
ALTER TABLE `RoomList`
  ADD PRIMARY KEY (`RoomNo`);

--
-- Indexes for table `Submissions`
--
ALTER TABLE `Submissions`
  ADD PRIMARY KEY (`TxID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
