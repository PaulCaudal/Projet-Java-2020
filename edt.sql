-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 07 juin 2020 à 17:56
-- Version du serveur :  5.7.26
-- Version de PHP :  7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `edt`
--

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

DROP TABLE IF EXISTS `cours`;
CREATE TABLE IF NOT EXISTS `cours` (
  `IDCours` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  `IDPromo` int(11) NOT NULL,
  `IDtype` int(11) NOT NULL,
  PRIMARY KEY (`IDCours`),
  KEY `IDPromo` (`IDPromo`),
  KEY `IDtype` (`IDtype`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `cours`
--

INSERT INTO `cours` (`IDCours`, `Nom`, `IDPromo`, `IDtype`) VALUES
(1, 'POO Java', 1, 2),
(2, 'programmation C++', 2, 2),
(3, 'Analyse de Fourier', 1, 1),
(4, 'Analyse', 2, 1),
(5, 'Traitement du signal', 1, 3),
(6, 'Physique Appliqué', 3, 3),
(7, 'Thermodynamique', 1, 4),
(8, 'Electromagnétique', 2, 4),
(9, 'Mecanique', 3, 4),
(10, 'Programmation C', 3, 2),
(11, 'Système bouclé', 2, 3),
(12, 'Mathématiques 2', 3, 1),
(13, 'English', 4, 5);

-- --------------------------------------------------------

--
-- Structure de la table `enseignant`
--

DROP TABLE IF EXISTS `enseignant`;
CREATE TABLE IF NOT EXISTS `enseignant` (
  `IDUtilisateur` int(11) NOT NULL,
  `IDCours` int(11) NOT NULL,
  KEY `IDCours` (`IDCours`),
  KEY `IDUtilisateur` (`IDUtilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `enseignant`
--

INSERT INTO `enseignant` (`IDUtilisateur`, `IDCours`) VALUES
(13, 1),
(13, 2),
(14, 3),
(14, 4),
(15, 5),
(15, 6),
(13, 10),
(17, 7),
(17, 8),
(17, 9),
(15, 11),
(14, 12),
(18, 13);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

DROP TABLE IF EXISTS `etudiant`;
CREATE TABLE IF NOT EXISTS `etudiant` (
  `IDUtilisateur` int(11) NOT NULL,
  `Numero` int(11) NOT NULL,
  `IDGroupe` int(11) NOT NULL,
  UNIQUE KEY `Numero` (`Numero`),
  KEY `IDGroupe` (`IDGroupe`),
  KEY `IDUtilisateur` (`IDUtilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`IDUtilisateur`, `Numero`, `IDGroupe`) VALUES
(10, 21378, 5),
(1, 32070, 1),
(8, 44131, 4),
(2, 45665, 1),
(3, 47975, 2),
(7, 57048, 4),
(4, 58415, 2),
(11, 62520, 6),
(9, 65085, 5),
(6, 78903, 3),
(12, 80185, 6),
(5, 87208, 3);

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

DROP TABLE IF EXISTS `groupe`;
CREATE TABLE IF NOT EXISTS `groupe` (
  `IDGroupe` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  `IDPromo` int(11) NOT NULL,
  PRIMARY KEY (`IDGroupe`),
  KEY `IDPromo` (`IDPromo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `groupe`
--

INSERT INTO `groupe` (`IDGroupe`, `Nom`, `IDPromo`) VALUES
(1, 'Ing3Gr06', 1),
(2, 'Ing3Gr07', 1),
(3, 'Ing2Gr06', 2),
(4, 'Ing2Gr07', 2),
(5, 'Ing1Gr06', 3),
(6, 'Ing1Gr07', 3);

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
CREATE TABLE IF NOT EXISTS `promotion` (
  `IDPromo` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` int(11) NOT NULL,
  PRIMARY KEY (`IDPromo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `promotion`
--

INSERT INTO `promotion` (`IDPromo`, `Nom`) VALUES
(1, 2022),
(2, 2023),
(3, 2024),
(4, 0);

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

DROP TABLE IF EXISTS `salle`;
CREATE TABLE IF NOT EXISTS `salle` (
  `IDSalle` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  `Capacite` int(11) NOT NULL,
  `IDSite` int(11) NOT NULL,
  PRIMARY KEY (`IDSalle`),
  KEY `IDSite` (`IDSite`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `salle`
--

INSERT INTO `salle` (`IDSalle`, `Nom`, `Capacite`, `IDSite`) VALUES
(1, 'SC008', 2, 1),
(2, 'SC009', 2, 1),
(3, 'P345', 2, 2),
(4, 'P348', 2, 2),
(5, 'G018', 4, 3),
(6, 'G019', 4, 3);

-- --------------------------------------------------------

--
-- Structure de la table `seance`
--

DROP TABLE IF EXISTS `seance`;
CREATE TABLE IF NOT EXISTS `seance` (
  `IDSeance` int(11) NOT NULL AUTO_INCREMENT,
  `Semaine` int(11) NOT NULL,
  `jour` varchar(255) NOT NULL,
  `HDebut` time NOT NULL,
  `HFin` time NOT NULL,
  `Etat` int(11) NOT NULL,
  `IDCours` int(11) NOT NULL,
  `IDType` int(11) NOT NULL,
  PRIMARY KEY (`IDSeance`),
  KEY `IDcours` (`IDCours`),
  KEY `IDtype` (`IDType`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `seance`
--

INSERT INTO `seance` (`IDSeance`, `Semaine`, `jour`, `HDebut`, `HFin`, `Etat`, `IDCours`, `IDType`) VALUES
(1, 21, 'lun', '08:30:00', '10:00:00', 1, 1, 2),
(2, 21, 'mar', '10:15:00', '11:45:00', 2, 3, 1),
(3, 21, 'lun', '10:15:00', '11:45:00', 1, 5, 3),
(4, 21, 'lun', '10:15:00', '11:45:00', 0, 2, 2),
(5, 22, 'lun', '08:30:00', '10:00:00', 0, 7, 4),
(6, 22, 'lun', '10:15:00', '11:45:00', 0, 1, 2),
(7, 22, 'mar', '13:45:00', '15:15:00', 0, 13, 5),
(8, 22, 'mer', '08:30:00', '10:15:00', 0, 3, 1),
(9, 22, 'mer', '10:15:00', '11:45:00', 0, 5, 3),
(10, 22, 'jed', '13:45:00', '15:15:00', 0, 7, 4),
(11, 22, 'jed', '15:30:00', '17:00:00', 0, 1, 2),
(12, 22, 'ven', '08:30:00', '10:00:00', 0, 13, 5),
(13, 22, 'ven', '13:45:00', '15:15:00', 0, 3, 1),
(14, 22, 'ven', '15:30:00', '17:00:00', 0, 5, 3),
(15, 22, 'lun', '13:45:00', '15:15:00', 0, 7, 4),
(16, 22, 'lun', '15:30:00', '17:00:00', 0, 1, 2),
(17, 22, 'mar', '10:15:00', '11:45:00', 0, 13, 5),
(18, 22, 'mer', '13:45:00', '15:15:00', 0, 3, 1),
(19, 22, 'mer', '15:30:00', '17:00:00', 0, 5, 3),
(20, 22, 'jed', '08:30:00', '10:00:00', 0, 7, 4),
(21, 22, 'jed', '10:15:00', '11:45:00', 0, 1, 2),
(22, 22, 'ven', '08:30:00', '10:00:00', 0, 3, 1),
(23, 22, 'ven', '10:15:00', '11:45:00', 0, 5, 3),
(24, 22, 'ven', '13:45:00', '15:15:00', 0, 13, 5),
(25, 22, 'lun', '08:30:00', '10:00:00', 0, 4, 1),
(26, 22, 'lun', '10:15:00', '11:45:00', 0, 11, 3),
(27, 22, 'mar', '08:30:00', '10:00:00', 0, 13, 5),
(28, 22, 'mar', '13:45:00', '15:15:00', 0, 2, 2),
(29, 22, 'mar', '15:30:00', '17:00:00', 0, 8, 4),
(30, 22, 'mer', '13:45:00', '15:15:00', 0, 11, 3),
(31, 22, 'mer', '15:30:00', '17:00:00', 0, 4, 1),
(32, 22, 'jed', '08:30:00', '10:00:00', 0, 2, 2),
(33, 22, 'jed', '10:15:00', '11:45:00', 0, 8, 4),
(34, 22, 'ven', '15:30:00', '17:00:00', 0, 13, 5),
(35, 22, 'lun', '13:45:00', '15:15:00', 0, 4, 1),
(36, 22, 'lun', '15:30:00', '17:00:00', 0, 11, 3),
(37, 22, 'mar', '08:30:00', '10:00:00', 0, 2, 2),
(38, 22, 'mar', '10:15:00', '11:45:00', 0, 8, 4),
(39, 22, 'mar', '15:30:00', '17:00:00', 0, 13, 5),
(40, 22, 'mer', '08:30:00', '10:00:00', 0, 11, 3),
(41, 22, 'mer', '10:15:00', '11:45:00', 0, 4, 1),
(42, 22, 'jed', '13:45:00', '15:15:00', 0, 2, 2),
(43, 22, 'jed', '15:15:00', '17:00:00', 0, 8, 4),
(44, 22, 'ven', '10:15:00', '11:45:00', 0, 13, 5),
(45, 22, 'lun', '08:30:00', '10:00:00', 0, 10, 2),
(46, 22, 'lun', '10:15:00', '11:45:00', 0, 9, 4),
(47, 22, 'lun', '13:45:00', '15:15:00', 0, 13, 5),
(48, 22, 'mar', '10:15:00', '11:45:00', 0, 6, 3),
(49, 22, 'mar', '13:45:00', '15:15:00', 0, 12, 1),
(50, 22, 'mer', '08:30:00', '10:00:00', 0, 13, 5),
(51, 22, 'jed', '10:15:00', '11:45:00', 0, 12, 1),
(52, 22, 'jed', '13:45:00', '15:15:00', 0, 6, 3),
(53, 22, 'ven', '08:30:00', '10:00:00', 0, 9, 4),
(54, 22, 'ven', '15:30:00', '17:00:00', 0, 10, 2),
(55, 22, 'lun', '08:30:00', '10:00:00', 0, 13, 5),
(56, 22, 'lun', '13:45:00', '15:15:00', 0, 10, 2),
(57, 22, 'lun', '15:30:00', '17:00:00', 0, 9, 4),
(58, 22, 'mar', '10:15:00', '11:45:00', 0, 12, 1),
(59, 22, 'mar', '13:45:00', '15:15:00', 0, 6, 3),
(60, 22, 'mer', '13:45:00', '15:15:00', 0, 13, 5),
(61, 22, 'jed', '10:15:00', '11:45:00', 0, 6, 3),
(62, 22, 'jed', '13:45:00', '15:15:00', 0, 12, 1),
(63, 22, 'ven', '08:30:00', '10:00:00', 0, 10, 2),
(64, 22, 'ven', '15:30:00', '17:00:00', 0, 9, 4),
(65, 21, 'lun', '12:00:00', '13:30:00', 0, 7, 4),
(66, 21, 'mar', '12:00:00', '13:30:00', 2, 13, 5);

-- --------------------------------------------------------

--
-- Structure de la table `seancee`
--

DROP TABLE IF EXISTS `seancee`;
CREATE TABLE IF NOT EXISTS `seancee` (
  `IDSeance` int(11) NOT NULL,
  `IDUtilisateur` int(11) NOT NULL,
  KEY `IDSeance` (`IDSeance`),
  KEY `IDUtilisateur` (`IDUtilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `seancee`
--

INSERT INTO `seancee` (`IDSeance`, `IDUtilisateur`) VALUES
(1, 13),
(2, 14),
(3, 15),
(4, 13),
(5, 17),
(6, 13),
(7, 18),
(8, 14),
(9, 15),
(10, 17),
(11, 13),
(12, 18),
(13, 14),
(14, 15),
(15, 17),
(16, 13),
(17, 18),
(18, 14),
(19, 15),
(20, 17),
(21, 13),
(22, 14),
(23, 15),
(24, 18),
(25, 14),
(26, 15),
(27, 18),
(28, 13),
(29, 17),
(30, 15),
(31, 14),
(32, 13),
(33, 17),
(34, 18),
(35, 14),
(36, 15),
(37, 13),
(38, 17),
(39, 18),
(40, 15),
(41, 14),
(42, 13),
(43, 17),
(44, 18),
(45, 13),
(46, 17),
(47, 18),
(48, 15),
(49, 14),
(50, 18),
(51, 14),
(52, 15),
(53, 17),
(54, 13),
(55, 18),
(56, 13),
(57, 17),
(58, 14),
(59, 15),
(60, 18),
(61, 15),
(62, 14),
(63, 13),
(64, 17),
(65, 17),
(66, 18);

-- --------------------------------------------------------

--
-- Structure de la table `seanceg`
--

DROP TABLE IF EXISTS `seanceg`;
CREATE TABLE IF NOT EXISTS `seanceg` (
  `IDSeance` int(11) NOT NULL,
  `IDGroupe` int(11) NOT NULL,
  KEY `IDGroupe` (`IDGroupe`),
  KEY `IDSeance` (`IDSeance`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `seanceg`
--

INSERT INTO `seanceg` (`IDSeance`, `IDGroupe`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 4),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(10, 1),
(11, 1),
(12, 1),
(13, 1),
(14, 1),
(17, 2),
(18, 2),
(19, 2),
(20, 2),
(21, 2),
(22, 2),
(23, 2),
(24, 2),
(16, 2),
(15, 2),
(25, 3),
(26, 3),
(27, 3),
(28, 3),
(29, 3),
(30, 3),
(31, 3),
(32, 3),
(33, 3),
(34, 3),
(35, 4),
(36, 4),
(37, 4),
(38, 4),
(39, 4),
(40, 4),
(41, 4),
(42, 4),
(43, 4),
(44, 4),
(45, 5),
(46, 5),
(47, 5),
(48, 5),
(49, 5),
(50, 5),
(51, 5),
(52, 5),
(53, 5),
(54, 5),
(55, 6),
(56, 6),
(57, 6),
(58, 6),
(59, 6),
(60, 6),
(61, 6),
(62, 6),
(63, 6),
(64, 6),
(65, 1),
(1, 2),
(66, 1);

-- --------------------------------------------------------

--
-- Structure de la table `seances`
--

DROP TABLE IF EXISTS `seances`;
CREATE TABLE IF NOT EXISTS `seances` (
  `IDSeance` int(11) NOT NULL,
  `IDSalle` int(11) NOT NULL,
  KEY `IDSalle` (`IDSalle`),
  KEY `IDSeance` (`IDSeance`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `seances`
--

INSERT INTO `seances` (`IDSeance`, `IDSalle`) VALUES
(2, 3),
(3, 2),
(4, 1),
(5, 1),
(6, 2),
(7, 3),
(8, 4),
(9, 5),
(10, 6),
(11, 1),
(12, 2),
(13, 3),
(14, 4),
(15, 1),
(16, 2),
(17, 3),
(18, 4),
(19, 5),
(20, 1),
(21, 2),
(22, 3),
(23, 4),
(24, 5),
(25, 2),
(26, 1),
(27, 3),
(28, 4),
(29, 1),
(30, 1),
(31, 2),
(32, 3),
(33, 4),
(34, 5),
(35, 2),
(36, 1),
(37, 4),
(38, 5),
(39, 3),
(40, 5),
(41, 5),
(42, 1),
(43, 2),
(44, 3),
(45, 3),
(46, 4),
(47, 5),
(48, 6),
(49, 5),
(50, 1),
(51, 3),
(52, 2),
(53, 6),
(54, 2),
(55, 4),
(56, 6),
(57, 5),
(58, 1),
(59, 2),
(60, 3),
(61, 6),
(62, 5),
(63, 4),
(64, 1),
(65, 1),
(1, 6),
(66, 1);

-- --------------------------------------------------------

--
-- Structure de la table `site`
--

DROP TABLE IF EXISTS `site`;
CREATE TABLE IF NOT EXISTS `site` (
  `IDSite` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  PRIMARY KEY (`IDSite`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `site`
--

INSERT INTO `site` (`IDSite`, `Nom`) VALUES
(1, 'Eiffel1'),
(2, 'Eiffel2'),
(3, 'Eiffel4');

-- --------------------------------------------------------

--
-- Structure de la table `typecours`
--

DROP TABLE IF EXISTS `typecours`;
CREATE TABLE IF NOT EXISTS `typecours` (
  `IDType` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  PRIMARY KEY (`IDType`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `typecours`
--

INSERT INTO `typecours` (`IDType`, `Nom`) VALUES
(1, 'Mathématique'),
(2, 'Informatique'),
(3, 'Electronique'),
(4, 'Physique'),
(5, 'Anglais');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `IDUtilisateur` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(255) NOT NULL,
  `MdP` varchar(255) NOT NULL,
  `Nom` varchar(255) NOT NULL,
  `Prenom` varchar(255) NOT NULL,
  `Droit` int(11) NOT NULL,
  PRIMARY KEY (`IDUtilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`IDUtilisateur`, `Email`, `MdP`, `Nom`, `Prenom`, `Droit`) VALUES
(1, 'paul.caudal@edu.ece.fr', 'root', 'Caudal', 'Paul', 0),
(2, 'valentin.guisnet@edu.ece.fr\r\n', 'root', 'Guisnet', 'Valentin', 0),
(3, 'nicolas.tronson@edu.ece.fr', 'root', 'Tronson', 'Nicolas', 0),
(4, 'maxime.pavard@edu.ece.fr', 'root', 'Pavard', 'Maxime', 0),
(5, 'ethane.kalifa@edu.ece.fr', 'root', 'Kalifa', 'Ethane', 0),
(6, 'aurelien.davodet@edu.ece.fr', 'root', 'Davodet', 'Aurelien', 0),
(7, 'julien.marechal@edu.ece.fr', 'root', 'Marechal', 'Julien', 0),
(8, 'jean.bonbeur@edu.ece.fr', 'root', 'Bonbeur', 'Jean', 0),
(9, 'paul.aroide@edu.ece.fr', 'root', 'Aroide', 'Paum', 0),
(10, 'jules.thomas@edu.ece.fr', 'root', 'Thomas', 'Jules', 0),
(11, 'david.smith@edu.ece.fr', 'root', 'Smith', 'David', 0),
(12, 'stan.march@edu.ece.fr', 'root', 'March', 'Stan', 0),
(13, 'jeanpierre.segado@edu.ece.fr', 'root', 'Segado', 'Jean-Pierre', 1),
(14, 'fabienne.coudray@edu.ece.fr', 'root', 'Coudray', 'Fabienne', 1),
(15, 'arash.mokhber@edu.ece.fr', 'root', 'Mokhber', 'Arash', 1),
(16, 'mopl', 'root', 'g', 'g', 0),
(17, 'christine.crambe@edu.ece.fr', 'root', 'Crambe', 'Christine', 1),
(18, 'carly.assaelalexander@edu.ece.fr', 'root', 'Assael Alexander', 'Carly', 1),
(19, 'admin@edu.ece.fr', 'root', 'f', 'f', 2);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `cours`
--
ALTER TABLE `cours`
  ADD CONSTRAINT `cours_ibfk_1` FOREIGN KEY (`IDPromo`) REFERENCES `promotion` (`IDPromo`) ON DELETE CASCADE,
  ADD CONSTRAINT `cours_ibfk_2` FOREIGN KEY (`IDtype`) REFERENCES `typecours` (`IDType`) ON DELETE CASCADE;

--
-- Contraintes pour la table `enseignant`
--
ALTER TABLE `enseignant`
  ADD CONSTRAINT `enseignant_ibfk_1` FOREIGN KEY (`IDCours`) REFERENCES `cours` (`IDCours`) ON DELETE CASCADE,
  ADD CONSTRAINT `enseignant_ibfk_2` FOREIGN KEY (`IDUtilisateur`) REFERENCES `utilisateur` (`IDUtilisateur`) ON DELETE CASCADE;

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `etudiant_ibfk_1` FOREIGN KEY (`IDGroupe`) REFERENCES `groupe` (`IDGroupe`) ON DELETE CASCADE,
  ADD CONSTRAINT `etudiant_ibfk_2` FOREIGN KEY (`IDUtilisateur`) REFERENCES `utilisateur` (`IDUtilisateur`) ON DELETE CASCADE;

--
-- Contraintes pour la table `groupe`
--
ALTER TABLE `groupe`
  ADD CONSTRAINT `groupe_ibfk_1` FOREIGN KEY (`IDPromo`) REFERENCES `promotion` (`IDPromo`) ON DELETE CASCADE;

--
-- Contraintes pour la table `salle`
--
ALTER TABLE `salle`
  ADD CONSTRAINT `salle_ibfk_1` FOREIGN KEY (`IDSite`) REFERENCES `site` (`IDSite`) ON DELETE CASCADE;

--
-- Contraintes pour la table `seance`
--
ALTER TABLE `seance`
  ADD CONSTRAINT `seance_ibfk_1` FOREIGN KEY (`IDCours`) REFERENCES `cours` (`IDCours`) ON DELETE CASCADE,
  ADD CONSTRAINT `seance_ibfk_2` FOREIGN KEY (`IDType`) REFERENCES `typecours` (`IDType`) ON DELETE CASCADE;

--
-- Contraintes pour la table `seancee`
--
ALTER TABLE `seancee`
  ADD CONSTRAINT `seancee_ibfk_1` FOREIGN KEY (`IDSeance`) REFERENCES `seance` (`IDSeance`) ON DELETE CASCADE,
  ADD CONSTRAINT `seancee_ibfk_2` FOREIGN KEY (`IDUtilisateur`) REFERENCES `utilisateur` (`IDUtilisateur`) ON DELETE CASCADE;

--
-- Contraintes pour la table `seanceg`
--
ALTER TABLE `seanceg`
  ADD CONSTRAINT `seanceg_ibfk_1` FOREIGN KEY (`IDGroupe`) REFERENCES `groupe` (`IDGroupe`) ON DELETE CASCADE,
  ADD CONSTRAINT `seanceg_ibfk_2` FOREIGN KEY (`IDSeance`) REFERENCES `seance` (`IDSeance`) ON DELETE CASCADE;

--
-- Contraintes pour la table `seances`
--
ALTER TABLE `seances`
  ADD CONSTRAINT `seances_ibfk_1` FOREIGN KEY (`IDSalle`) REFERENCES `salle` (`IDSalle`) ON DELETE CASCADE,
  ADD CONSTRAINT `seances_ibfk_2` FOREIGN KEY (`IDSeance`) REFERENCES `seance` (`IDSeance`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
