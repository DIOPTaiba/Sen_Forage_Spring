-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : jeu. 05 nov. 2020 à 17:01
-- Version du serveur :  10.4.14-MariaDB
-- Version de PHP : 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `senforagespring`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `nom_famille` varchar(255) DEFAULT NULL,
  `num_tel` varchar(255) DEFAULT NULL,
  `id_village` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `adresse`, `nom_famille`, `num_tel`, `id_village`) VALUES
(1, 'Taiba', 'DIOP', '773511003', 'TN100'),
(3, 'Ndiayenne', 'NDIAYE', '123456789', 'TH147'),
(5, 'Keur Mor 2', 'DIEYE', '32165497', 'KM100'),
(6, 'Ouakam', 'SAGNA', '123456789', 'OK300'),
(7, 'Malicka', 'GADIAGA', '123456789', 'MK200'),
(8, 'Keur Massar', 'FALL', '123456789', 'KM100'),
(9, 'Liberté 5', 'NIANG', '123456789', 'TH147'),
(10, 'Pire', 'DIOP', '123456789', 'PR123'),
(11, 'Parcelle', 'SOW', '123456789', 'MB123');

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `roles`
--

INSERT INTO `roles` (`id`, `nom`) VALUES
(1, 'COMPTEUR'),
(2, 'ADMIN'),
(3, 'COMMERCIAL'),
(4, 'CLIENTEL');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `etat` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `url_photo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `email`, `etat`, `nom`, `password`, `prenom`, `url_photo`) VALUES
(1, 'mor@gmail.sn', 1, 'DIOP', '$2a$10$B/5jDBxMKSKZ9WZVxSpXgeRoesnhuT0A/FpBnAxpfSEZcq4ONiABS', 'Mor', 'template/images'),
(2, 'cheikh@gmail.sn', 1, 'MBOW', '$2a$10$B/5jDBxMKSKZ9WZVxSpXgeRoesnhuT0A/FpBnAxpfSEZcq4ONiABS', 'Cheikh', 'template/images'),
(3, 'tapha@simplon.co', 0, 'DIEYE', '$2a$10$B/5jDBxMKSKZ9WZVxSpXgeRoesnhuT0A/FpBnAxpfSEZcq4ONiABS', 'Moustapha', 'template/images');

-- --------------------------------------------------------

--
-- Structure de la table `user_role`
--

CREATE TABLE `user_role` (
  `id_user` int(11) NOT NULL,
  `id_role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user_role`
--

INSERT INTO `user_role` (`id_user`, `id_role`) VALUES
(1, 1),
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(3, 1),
(3, 2),
(3, 3),
(3, 4);

-- --------------------------------------------------------

--
-- Structure de la table `village`
--

CREATE TABLE `village` (
  `id_village` varchar(255) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `village`
--

INSERT INTO `village` (`id_village`, `nom`, `id_user`) VALUES
('KM100', 'Keur Massar', 1),
('KM25', 'Keur Mor Ndiaye', 1),
('MB123', 'Parcelle', 3),
('MK200', 'Malicka', 2),
('ND25', 'Grand Yoff', 1),
('OK300', 'Ouakam', 3),
('PR123', 'Pire', 2),
('TH147', 'Liberté 5', 2),
('TN100', 'Taiba Ndiaye', 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKobxqx4cfdm2u47l55crjk402c` (`id_village`);

--
-- Index pour la table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user_role`
--
ALTER TABLE `user_role`
  ADD KEY `FK2yqlxhjhgilevh7qvt2ve6udh` (`id_role`),
  ADD KEY `FKfhxaael2m459kbk8lv8smr5iv` (`id_user`);

--
-- Index pour la table `village`
--
ALTER TABLE `village`
  ADD PRIMARY KEY (`id_village`),
  ADD KEY `FKbsmqhq9eyunhesrh00m8r8dvc` (`id_user`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FKobxqx4cfdm2u47l55crjk402c` FOREIGN KEY (`id_village`) REFERENCES `village` (`id_village`);

--
-- Contraintes pour la table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK2yqlxhjhgilevh7qvt2ve6udh` FOREIGN KEY (`id_role`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `FKfhxaael2m459kbk8lv8smr5iv` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `village`
--
ALTER TABLE `village`
  ADD CONSTRAINT `FKbsmqhq9eyunhesrh00m8r8dvc` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
