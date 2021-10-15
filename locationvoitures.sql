-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 15 oct. 2021 à 21:12
-- Version du serveur : 10.4.21-MariaDB
-- Version de PHP : 7.4.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `locationvoitures`
--

-- --------------------------------------------------------

--
-- Structure de la table `authenticates`
--

CREATE TABLE `authenticates` (
  `id` bigint(20) NOT NULL,
  `account_non_expired` bit(1) NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `credentials_non_expired` bit(1) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `authenticates`
--

INSERT INTO `authenticates` (`id`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`, `password`, `roles`, `username`, `user_id`) VALUES
(1, b'1', b'1', b'1', b'1', '$2a$10$Z3//7bxDO/jkvaDycITivujTY8vS3P6QRnweDUPdhFYS3SdjrJdlm', 'ROLE_ADMIN', 'admin@test.com', 1),
(2, b'1', b'1', b'1', b'1', '$2a$10$pxQAZhZesF30c.vfmsBRiuE1LaYZcFDouABAuwrXZeXB.BGIbO6vC', 'ROLE_GERANT', 'gerant@test.com', 2),
(3, b'1', b'1', b'1', b'1', '$2a$10$gyKTHPI4IbSikYyeC3T5WOpHRN0tmuj3XniVMqvtxUTrB3g/ldliK', 'ROLE_UTILISATEUR', 'client@test.com', 3),
(4, b'1', b'1', b'1', b'1', '$2a$10$g7D6UJwrrFFnfnJ56jndFuy10H9zzobo7lR2ndFMcp6wLL.0uOSd.', 'ROLE_GERANT', 'gerant2@test.com', 4),
(5, b'1', b'1', b'1', b'1', '$2a$10$40WFTefgskRfafjArPwrAehVWpG/QMibyMldqM1cdgHuvBHPnKpsK', 'ROLE_UTILISATEUR', 'papa@test.com', 5),
(6, b'1', b'1', b'1', b'1', '$2a$10$miPnujvzCOpqGOWeB/rPH.QNy.ZzDDmDflLW2rVKCVoaTulM.J.aq', 'ROLE_UTILISATEUR', 'baba@test.com', 6),
(7, b'1', b'1', b'1', b'1', '$2a$10$gyqzqWbapWoEnExpkmsmxuzt/b/j08V1sHgpasjWfaFR9tsYXKk.u', 'ROLE_UTILISATEUR', 'diarra@test.com', 7),
(8, b'1', b'1', b'1', b'1', '$2a$10$pXIieAwEpLuWoyngIoLt.e3Bd5NZeUtUA7EaWxFf53KilEOOnHX0a', 'ROLE_UTILISATEUR', 'bakary@test.com', 8),
(9, b'1', b'1', b'1', b'1', '$2a$10$IkztCFYbMGPzLGAXKOcIYu1MurHJlAqOAbx2rUnEJJacc4NF8Xz6.', 'ROLE_GERANT', 'omar@test.com', 9),
(10, b'1', b'1', b'1', b'1', '$2a$10$ngrrKtwwH/gehun8pMN0SOuvbtr40ZAw7RgACH9Y/BHNrLz.oQf7a', 'ROLE_UTILISATEUR', 'seydou@test.com', 10),
(11, b'1', b'1', b'1', b'1', '$2a$10$1.F0Ic9UjBE.S8ZQXeh.Uu.rMVsgKj3qE8zajl7Pv4uriaLOg9a3u', 'ROLE_UTILISATEUR', 'ciss@test.com', 11);

-- --------------------------------------------------------

--
-- Structure de la table `categories`
--

CREATE TABLE `categories` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `user_created_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `categories`
--

INSERT INTO `categories` (`id`, `description`, `nom`, `photo`, `user_created_id`) VALUES
(1, 'On trouve tout d’abord celles que l’on nomme les mini-citadines du type Smart Fortwo.', 'mini-citadines', 'e55d5fd31a7e80aa981d8dc68159390d-s1-le-guide-des-categories-385739.jpg', 1),
(2, '44', 'toyata', 'f759965ed11e76cbda5aae1f90751417-kisspng-toyota-.jpg', 1),
(3, 'Des voitures luxes à votre dispositions', 'Ndiaye', 'cebc42ad5fbfdab8c276fd1559f09da2-000195946_624x337_c.jpg', 1),
(4, 'On trouve tout d’abord celles que l’on nomme les mini-citadines', 'titi', 'c15d1946ab976cd0f2d259166635e83c-renault-twingoklj.jpg', 1),
(5, 'Community. When most startups around you aren\'t using Java, it\'s difficult to be the one or two holdouts when time comes to meetups and knowledge sharing.', 'fera', '8be145942299f6d6d2798e635065e270-téléchargement.jpg', 1);

-- --------------------------------------------------------

--
-- Structure de la table `reservations`
--

CREATE TABLE `reservations` (
  `id` bigint(20) NOT NULL,
  `adresse_depart` varchar(255) DEFAULT NULL,
  `adresse_retour` varchar(255) DEFAULT NULL,
  `avec_chauffeur` bit(1) NOT NULL,
  `date_depart` datetime DEFAULT NULL,
  `date_reservation` datetime DEFAULT NULL,
  `date_retour` datetime DEFAULT NULL,
  `heure_depart` datetime DEFAULT NULL,
  `heure_retour` datetime DEFAULT NULL,
  `nombre_personne` tinyint(4) NOT NULL,
  `status_reservation` tinyint(4) NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `gerer_by_id` bigint(20) DEFAULT NULL,
  `user_created_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reservations`
--

INSERT INTO `reservations` (`id`, `adresse_depart`, `adresse_retour`, `avec_chauffeur`, `date_depart`, `date_reservation`, `date_retour`, `heure_depart`, `heure_retour`, `nombre_personne`, `status_reservation`, `category_id`, `gerer_by_id`, `user_created_id`) VALUES
(1, 'Pikine', 'Keur Mbaye', b'0', '2021-09-12 00:00:00', '2021-09-11 20:09:07', '2021-09-18 00:00:00', '1970-01-01 23:10:00', '1970-01-01 00:11:00', 0, 2, 1, 2, 1),
(2, 'Pikine', 'Keur Mbaye', b'0', '2021-09-12 00:00:00', '2021-09-11 20:09:09', '2021-09-18 00:00:00', '1970-01-01 23:10:00', '1970-01-01 00:11:00', 0, 3, 1, 2, 1),
(3, 'Keur Mbaye', 'Saint-Louis', b'0', '2021-09-20 00:00:00', '2021-09-11 20:13:01', '2021-10-20 00:00:00', '1970-01-01 00:13:00', '1970-01-01 00:13:00', 0, 2, 1, 2, 3),
(4, 'Keur Mbaye', 'Saint-Louis', b'0', '2021-09-20 00:00:00', '2021-09-11 20:13:04', '2021-10-20 00:00:00', '1970-01-01 00:13:00', '1970-01-01 00:13:00', 0, 2, 1, 9, 3),
(5, 'Keur Mbaye', 'Saint-Louis', b'0', '2021-09-20 00:00:00', '2021-09-11 20:13:05', '2021-10-20 00:00:00', '1970-01-01 00:13:00', '1970-01-01 00:13:00', 0, 2, 1, 2, 3),
(6, 'Keur Mbaye', 'Saint-Louis', b'0', '2021-09-20 00:00:00', '2021-09-11 20:13:08', '2021-10-20 00:00:00', '1970-01-01 00:13:00', '1970-01-01 00:13:00', 0, 1, 1, NULL, 3),
(7, 'paymar', 'keur madiabel', b'0', '2021-09-01 00:00:00', '2021-09-12 18:01:54', '2021-09-05 00:00:00', '1970-01-01 01:01:00', '1970-01-01 02:01:00', 0, 1, 2, NULL, 2),
(8, 'liberté 4', 'liberté 4', b'0', '2021-09-17 00:00:00', '2021-09-16 10:16:47', '2021-09-21 00:00:00', '1970-01-01 13:16:00', '1970-01-01 15:16:00', 0, 2, 2, 2, 6),
(9, 'Pikine', 'louga', b'0', '2021-10-04 00:00:00', '2021-10-03 17:24:27', '2021-10-06 00:00:00', '1970-01-01 20:24:00', '1970-01-01 21:24:00', 0, 1, 2, NULL, 5),
(10, 'louga', 'touba', b'0', '2021-10-04 00:00:00', '2021-10-03 18:33:20', '2021-10-07 00:00:00', '1970-01-01 21:33:00', '1970-01-01 23:33:00', 0, 1, 3, NULL, 5),
(11, 'kl', 'louga', b'0', '2021-10-08 00:00:00', '2021-10-03 18:40:32', '2021-10-12 00:00:00', '1970-01-01 23:40:00', '1970-01-01 00:40:00', 0, 1, 2, NULL, 5),
(12, 'Keur Mbaye', 'touba', b'0', '2021-10-11 00:00:00', '2021-10-04 20:16:42', '2021-10-18 00:00:00', '1970-01-01 04:16:00', '1970-01-01 03:16:00', 0, 2, 2, 2, 8),
(13, 'leona', 'leona', b'0', '2021-10-13 00:00:00', '2021-10-12 20:51:19', '2021-10-16 00:00:00', '1970-01-01 00:51:00', '1970-01-01 04:51:00', 0, 2, 2, 2, 10),
(14, 'kl', 'lo', b'0', '2021-10-27 00:00:00', '2021-10-14 14:42:04', '2021-10-28 00:00:00', '1970-01-01 16:46:00', '1970-01-01 21:41:00', 0, 1, 1, NULL, 11);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `cni` varchar(255) DEFAULT NULL,
  `created_ad` datetime DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_connected_at` datetime DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `status_user` tinyint(4) NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `user_created_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `address`, `cni`, `created_ad`, `first_name`, `last_connected_at`, `last_name`, `phone_number`, `status_user`, `updated_at`, `user_created_id`) VALUES
(1, NULL, NULL, '2021-09-11 19:48:27', 'Admin', NULL, 'Admin', NULL, 1, NULL, NULL),
(2, NULL, NULL, '2021-09-11 19:57:06', 'Moussa', NULL, 'NDIAYE', '777828630', 1, NULL, 1),
(3, 'Keur Mbaye FALL', '1597256600245', '2021-09-11 20:11:09', 'Assane', NULL, 'KA', '775054827', 1, NULL, NULL),
(4, NULL, NULL, '2021-09-12 17:40:56', 'laity', NULL, 'diouf', '75719895', 1, NULL, 1),
(5, 'doffane', '1888888888', '2021-09-12 18:08:25', 'papa', NULL, 'sene', '783658145', 1, NULL, NULL),
(6, 'liberté 4', '123456789', '2021-09-16 10:11:26', 'mr', NULL, ' ba', '770711690', 1, NULL, NULL),
(7, 'madiabel', '14789654', '2021-09-17 10:54:42', 'bakary', NULL, 'diarra', '776134469', 1, NULL, NULL),
(8, 'Mbao', '12345678', '2021-10-04 20:02:22', 'Bakary', NULL, 'Diarra', '', 1, NULL, NULL),
(9, NULL, NULL, '2021-10-04 20:26:13', 'omar', NULL, 'diarra', '770711690', 1, NULL, 1),
(10, 'leona', '125225245', '2021-10-12 20:49:34', 'seydou', NULL, 'ka', '786000001', 1, NULL, NULL),
(11, 'leona', '1420200200225', '2021-10-14 14:38:02', 'momor', NULL, 'ciss', '785716969', 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `voitures`
--

CREATE TABLE `voitures` (
  `id` bigint(20) NOT NULL,
  `annee` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `marque` varchar(255) DEFAULT NULL,
  `modele` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `photo_default` varchar(255) DEFAULT NULL,
  `photos` text DEFAULT NULL,
  `tarif` double NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `user_created_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `voitures`
--

INSERT INTO `voitures` (`id`, `annee`, `description`, `marque`, `modele`, `nom`, `photo_default`, `photos`, `tarif`, `category_id`, `user_created_id`) VALUES
(1, 2019, 'nouvelle rebhjhkdnkze', 'Renault', 'X54B', 'Renault Twingo', 'c09e9184efa06bb3c8a7bc717b18e89d-kisspng-toyota-.jpg', 'e55d5fd31a7e80aa981d8dc68159390d-s1-le-guide-des-categories-385739.jpg,8fce37e45d8d7e7ea14fae506f31df72-000195946_624x337_c.jpg,f84ff4199024680aa5ea4eca650d795b-renault-twingoklj.jpg,c09e9184efa06bb3c8a7bc717b18e89d-kisspng-toyota-.jpg', 20000, 1, 1),
(2, 2020, 'rouler en securité', 'toyota', '44', '44', 'f759965ed11e76cbda5aae1f90751417-kisspng-toyota-.jpg', NULL, 40000, 2, 1),
(3, 2021, 'les vitures de luxes', 'yota', '504', 'tata', 'e55d5fd31a7e80aa981d8dc68159390d-s1-le-guide-des-categories-385739.jpg', NULL, 25000, 1, 2);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `authenticates`
--
ALTER TABLE `authenticates`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK7688p6nw5kkg8skpg955b78be` (`username`) USING HASH,
  ADD KEY `FK3kk7horjvdu6p1m1aqf6l69vg` (`user_id`);

--
-- Index pour la table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2fypq8crkicjcj0b0r7b2m7lw` (`user_created_id`);

--
-- Index pour la table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkhx24mp5qo0m2mp1of2a38khi` (`category_id`),
  ADD KEY `FKn9rrx48ftxaaln4no4k7p2y3d` (`gerer_by_id`),
  ADD KEY `FKrvi2x3929it5y173c97jjwaq8` (`user_created_id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKswmf0x02771u8pqfx791fd1qc` (`user_created_id`);

--
-- Index pour la table `voitures`
--
ALTER TABLE `voitures`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3yohp680jarkcehfsi074y6ao` (`category_id`),
  ADD KEY `FK278kbmduvbx3coe4s6wecpp8b` (`user_created_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `authenticates`
--
ALTER TABLE `authenticates`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `voitures`
--
ALTER TABLE `voitures`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
