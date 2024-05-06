CREATE database if not exists stat_tracker;
USE stat_tracker;

CREATE TABLE `users`
(
    `username` varchar(50) NOT NULL,
    `password` char(68)    NOT NULL,
    `enabled`  tinyint     NOT NULL,
    PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `users`
VALUES ('admin', '{bcrypt}$2a$12$c7zJPY073qVBoPtEAnpzo.9AMQ0hNUxyORtkv2bAM.OwkhCsWt8b6', 1);

CREATE TABLE `authorities`
(
    `username`  varchar(50) NOT NULL,
    `authority` varchar(50) NOT NULL,
    UNIQUE KEY `authorities4_idx_1` (`username`,`authority`),
    CONSTRAINT `authorities4_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `authorities`
VALUES ('admin', 'ROLE_EMPLOYEE'),
       ('admin', 'ROLE_MANAGER'),
       ('admin', 'ROLE_ADMIN');


CREATE TABLE Team (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    team_name VARCHAR(100)
);

CREATE TABLE player (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    height DOUBLE,
    weight DOUBLE,
    current_team_id BIGINT,
    FOREIGN KEY (current_team_id) REFERENCES Team(id)
);

CREATE TABLE stat_team (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    team_id BIGINT,
    FOREIGN KEY (team_id) REFERENCES Team(id)
);

CREATE TABLE stat_player (
	id BIGINT auto_increment primary key,
    player_id BIGINT,
    stat_team_id BIGINT,
    shirt_number INT,
    starting_five boolean,
    points INT,
    time_on_court TIME,
    eval INT,
    plus_minus INT,
    foreign key (player_id) references player(id),
    foreign key (stat_team_id) references stat_team(id)
);

CREATE TABLE plays (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    stat_player_id BIGINT,
    FOREIGN KEY (stat_player_id) REFERENCES stat_player(id)
);


