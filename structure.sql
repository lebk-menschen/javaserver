DROP DATABASE IF EXISTS Battleship;

CREATE DATABASE IF NOT EXISTS Battleship 
 DEFAULT CHARACTER SET = 'utf8' DEFAULT COLLATE 'utf8_general_ci';
 
USE Battleship;

CREATE TABLE `Game` (
  `uid` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `startTime` TIMESTAMP,
  `finishTime` TIMESTAMP,
  `winnerPlayerId` INT
);

CREATE TABLE `Player` (
  `uid` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` TEXT,
  `token` TEXT
);

CREATE TABLE `GamePlayer` (
  `gameId` INT,
  `playerId` INT,
  `score` INT
);

CREATE TABLE `Ship` (
  `uid` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` TEXT,
  `length` INT,
  `points` INT
);

CREATE TABLE `GamePlayerShip` (
  `uid` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `gameId` INT,
  `playerId` INT,
  `shipId` INT,
  `line` TEXT,
  `hits` INT
);

CREATE TABLE `Result` (
  `uid` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `description` TEXT
);

CREATE TABLE `GamePlayerShot` (
  `uid` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `gameId` INT,
  `playerId` INT,
  `point` TEXT,
  `resultId` INT
);

ALTER TABLE `Game` ADD CONSTRAINT `Game_fk1` FOREIGN KEY (`winnerPlayerId`) REFERENCES Player(`uid`);

ALTER TABLE `GamePlayer` ADD CONSTRAINT `GamePlayer_fk1` FOREIGN KEY (`gameId`) REFERENCES Game(`uid`);
ALTER TABLE `GamePlayer` ADD CONSTRAINT `GamePlayer_fk2` FOREIGN KEY (`playerId`) REFERENCES Player(`uid`);

ALTER TABLE `GamePlayerShip` ADD CONSTRAINT `GamePlayerShip_fk1` FOREIGN KEY (`gameId`) REFERENCES Game(`uid`);
ALTER TABLE `GamePlayerShip` ADD CONSTRAINT `GamePlayerShip_fk2` FOREIGN KEY (`playerId`) REFERENCES Player(`uid`);
ALTER TABLE `GamePlayerShip` ADD CONSTRAINT `GamePlayerShip_fk3` FOREIGN KEY (`shipId`) REFERENCES Ship(`uid`);

ALTER TABLE `GamePlayerShot` ADD CONSTRAINT `GamePlayerShot_fk1` FOREIGN KEY (`gameId`) REFERENCES Game(`uid`);
ALTER TABLE `GamePlayerShot` ADD CONSTRAINT `GamePlayerShot_fk2` FOREIGN KEY (`playerId`) REFERENCES Player(`uid`);
ALTER TABLE `GamePlayerShot` ADD CONSTRAINT `GamePlayerShot_fk3` FOREIGN KEY (`resultId`) REFERENCES Result(`uid`);

INSERT INTO Ship(name, length, points) VALUES("Patrol boat", 2, 20);
INSERT INTO Ship(name, length, points) VALUES("Destroyer", 3, 30);
INSERT INTO Ship(name, length, points) VALUES("Submarine", 3, 30);
INSERT INTO Ship(name, length, points) VALUES("Battleship", 4, 40);
INSERT INTO Ship(name, length, points) VALUES("Aircraft carrier", 5, 50);

INSERT INTO Result(description) VALUES("Miss");
INSERT INTO Result(description) VALUES("Hit");
INSERT INTO Result(description) VALUES("Sunk");
INSERT INTO Result(description) VALUES("Won");
INSERT INTO Result(description) VALUES("ERROR");


INSERT INTO Player(name, token) VALUES("Robin", "098f6bcd4621d373cade4e832627b4f6"); --test
INSERT INTO Player(name, token) VALUES("Freddy", "5a105e8b9d40e1329780d62ea2265d8a"); --test1
INSERT INTO Player(name, token) VALUES("Felix", "ad0234829205b9033196ba818f7a872b"); --test2
INSERT INTO Player(name, token) VALUES("Lukas", "8ad8757baa8564dc136c1e07507f4a98"); --tes3
INSERT INTO Player(name, token) VALUES("Svenja", "86985e105f79b95d6bc918fb45ec7727"); --tes4
INSERT INTO Player(name, token) VALUES("Andreas", "e3d704f3542b44a621ebed70dc0efe13"); --test5
