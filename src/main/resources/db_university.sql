-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 02, 2024 at 06:55 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_university`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE `appointments` (
  `id` int(11) NOT NULL,
  `studentname` varchar(100) DEFAULT NULL,
  `repname` varchar(100) DEFAULT NULL,
  `universityname` varchar(100) DEFAULT NULL,
  `location` varchar(500) DEFAULT NULL,
  `appointmentdate` varchar(50) DEFAULT NULL,
  `appointmentslot` varchar(50) DEFAULT NULL,
  `createdatetime` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`id`, `studentname`, `repname`, `universityname`, `location`, `appointmentdate`, `appointmentslot`, `createdatetime`) VALUES
(1, 'Bala Chander', 'Anudeep', 'London university', 'Hyderabad', '2024-04-30', '8:00-9:00', '2024-04-29 14:25:37'),
(2, 'Bala Chander', 'Anudeep', 'London university', 'Hyderabad', '2024-04-30', '8:00-9:00', '2024-04-29 14:26:01'),
(3, 'Bala Chander', 'Anudeep', 'London university', 'Hyderabad', '2024-04-30', '8:00-9:00', '2024-04-29 14:26:01'),
(11, 'Bala Chander', 'Anudeep', 'London university', 'Hyderabad', '2024-04-30', '8:00-9:00', '2024-04-29 14:26:03');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `id` int(11) NOT NULL,
  `studentname` varchar(100) DEFAULT NULL,
  `phonenumber` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `studentlocation` varchar(500) DEFAULT NULL,
  `universityname` varchar(500) DEFAULT NULL,
  `location` varchar(500) DEFAULT NULL,
  `feedback` varchar(500) DEFAULT NULL,
  `createdatetime` timestamp NULL DEFAULT NULL,
  `activestatus` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`id`, `studentname`, `phonenumber`, `email`, `studentlocation`, `universityname`, `location`, `feedback`, `createdatetime`, `activestatus`) VALUES
(1, 'bala chander', '12345678', 'bala@gmail.com', 'hyderabag', 'London university', 'London', 'Very nice university', NULL, 0),
(2, 'balachander12', '12334879', NULL, 'London', 'London University', 'London', NULL, '2024-04-27 16:50:49', 1),
(3, 'balachander12', '12334879', NULL, 'London', 'London University', 'London', 'Very nice university', '2024-04-27 16:52:25', 1),
(4, 'balachander12', '12334879', 'balachander@gmail.com', 'London', 'London University', 'London', 'Very nice university', '2024-04-27 16:54:45', 0),
(5, 'balachander12', '12334879', 'balachander@gmail.com', 'London', 'London University', 'London', 'Very nice university', '2024-04-29 16:38:28', 1),
(6, 'balachander12', '12334879', 'balachander@gmail.com', 'London', 'London University', 'London', 'Very nice university', '2024-04-29 16:38:29', 0),
(7, 'balachander12', '12334879', 'balachander@gmail.com', 'London', 'London University', 'London', 'Very nice university', '2024-04-29 16:38:30', 1);

-- --------------------------------------------------------

--
-- Table structure for table `university`
--

CREATE TABLE `university` (
  `id` int(11) NOT NULL,
  `universityname` varchar(500) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `location` varchar(500) DEFAULT NULL,
  `repname` varchar(500) DEFAULT NULL,
  `position` varchar(500) DEFAULT NULL,
  `admissionintake` varchar(500) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `images` varchar(2000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `university`
--

INSERT INTO `university` (`id`, `universityname`, `description`, `location`, `repname`, `position`, `admissionintake`, `username`, `password`.`images`) VALUES
(10, 'London University', 'London University', 'London', 'Shyam', 'Shyam', '2024-10-10', 'bala1205', 'Welcome@123',''),
(15, 'London University', 'London University', 'London', 'Shyam', 'Shyam', '2024-10-10', 'bala1205', 'Welcome@123',''),
(16, 'The University of London is a federal public research university located in London, England, United Kingdom', 'The University of London is a federal public research university located in London, England, United Kingdom', 'The University of London is a federal public research university located in London, England, United Kingdom', 'Shyam', 'Shyam', 'Summer,Spring,Fall', 'asasd', 'asasd',''),
(17, 'University of London', 'The University of London is a federal public research university located in London, England, United Kingdom', 'The University of London is a federal public research university located in London, England, United Kingdom', 'anudeep', 'anudeep', 'Summer,Spring,Fall', 'anudeep', 'anudeep',''),
(18, 'University of London', '', '', 'anudeep', 'anudeep', 'Summer', 'anudeeo', 'anudeeo',''),
(19, 'University of London', 'The University of London is a federal public research university located in London, England, United Kingdom', 'The University of London is a federal public research university located in London, England, United Kingdom', 'Shyam', 'Shyam', 'Summer,Spring,Fall', 'anudeeo', 'anudeeo',''),
(20, 'University of London', 'The University of London is a federal public research university located in London, England, United Kingdom', 'The University of London is a federal public research university located in London, England, United Kingdom', 'Shyam', 'Shyam', 'Summer', 'sefdsd', 'sefdsd',''),
(21, 'University of London', 'The University of London is a federal public research university located in London, England, United Kingdom', 'The University of London is a federal public research university located in London, England, United Kingdom', 'Shyam', 'Shyam', 'Summer,Fall', 'bala@gmail.com', 'bala@gmail.com','');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `createddatetime` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `locked` bit(1) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `updateddatetime` datetime DEFAULT NULL,
  `createtimestamp` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `createddatetime`, `email`, `enabled`, `locked`, `mobile`, `password`, `role`, `updateddatetime`, `createtimestamp`, `username`) VALUES
(5, '2024-05-02 21:43:55', 'balachander1205@gmail.com', b'1', b'0', '8919180283', '$2a$10$9Tk6MQGttWeAizvD52QlreneOYntv4MZxkCXnY7NQY5Hjnz4kqMQK', 'ADMIN', '2024-05-02 21:43:55', NULL, NULL),
(6, '2024-05-02 21:43:55', 'admin@gmail.com', b'1', b'0', '1234567890', '$2a$10$9Tk6MQGttWeAizvD52QlreneOYntv4MZxkCXnY7NQY5Hjnz4kqMQK', 'ADMIN', '2024-05-02 21:43:55', NULL, NULL);;

-- --------------------------------------------------------

--
-- Table structure for table `users_sequence`
--

CREATE TABLE `users_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users_sequence`
--

INSERT INTO `users_sequence` (`next_val`) VALUES
(7);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `university`
--
ALTER TABLE `university`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  ADD UNIQUE KEY `UK_63cf888pmqtt5tipcne79xsbm` (`mobile`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointments`
--
ALTER TABLE `appointments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `university`
--
ALTER TABLE `university`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
