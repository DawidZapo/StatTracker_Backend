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
    id INT AUTO_INCREMENT PRIMARY KEY,
    team_name VARCHAR(100)
);

CREATE TABLE Player (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    height DOUBLE,
    weight DOUBLE,
    current_team_id INT,
    FOREIGN KEY (current_team_id) REFERENCES Team(id)
);

