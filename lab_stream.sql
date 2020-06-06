-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 06, 2020 at 10:43 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lab_stream`
--

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE `company` (
  `id` int(4) NOT NULL,
  `company_name` char(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`id`, `company_name`) VALUES
(1, 'Stream'),
(2, 'Wongnai'),
(3, 'apple'),
(4, 'nike'),
(5, 'adidas');

-- --------------------------------------------------------

--
-- Table structure for table `order_product`
--

CREATE TABLE `order_product` (
  `id` int(4) NOT NULL,
  `orderID` char(4) NOT NULL,
  `product_code` char(6) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `product` char(255) NOT NULL,
  `company` char(255) NOT NULL,
  `price_per_unit` int(10) NOT NULL,
  `unit` int(4) NOT NULL,
  `vat` float NOT NULL,
  `total_price` int(20) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_product`
--

INSERT INTO `order_product` (`id`, `orderID`, `product_code`, `product_name`, `product`, `company`, `price_per_unit`, `unit`, `vat`, `total_price`, `active`) VALUES
(1, '0001', '000001', 'Test', 'Something', 'Stream', 5, 30, 13, 150, 0),
(2, '0002', '000002', 'Air Jordan 1', 'Shoe', 'nike', 5600, 10, 13, 56000, 0),
(3, '0004', '000004', 'Air Max 270', 'Shoe', 'nike', 5500, 20, 13, 110000, 0),
(4, '0005', '000005', 'Ultra Boost', 'Shoe', 'adidas', 6000, 25, 13, 150000, 0),
(5, '0006', '000006', 'MacBook Pro', 'laptop', 'apple', 42900, 10, 13, 429000, 0),
(6, '0007', '000007', 'iPhone', 'smart phone', 'apple', 39900, 15, 13, 598500, 0);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(4) NOT NULL,
  `product_name` char(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `product_name`) VALUES
(1, 'Shoe'),
(2, 'Something'),
(3, 'laptop'),
(4, 'smart phone');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_product`
--
ALTER TABLE `order_product`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `order_product`
--
ALTER TABLE `order_product`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
