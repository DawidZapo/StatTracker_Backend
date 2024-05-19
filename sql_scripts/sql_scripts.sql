CREATE database if not exists stat_tracker;
USE stat_tracker;

CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(50),
    password VARCHAR(120),
    username VARCHAR(20),
    PRIMARY KEY (id),
    UNIQUE KEY (email),
    UNIQUE KEY (username)
);


CREATE TABLE roles (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(20),
    PRIMARY KEY (id)
);


CREATE TABLE user_roles (
    user_id BIGINT,
    role_id INT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

CREATE TABLE team (
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
    game_id BIGINT,
    FOREIGN KEY (team_id) REFERENCES Team(id),
    FOREIGN KEY (game_id) REFERENCES game(id)
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

CREATE TABLE game (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    home_id BIGINT,
    away_id BIGINT,
    FOREIGN KEY (home_id) REFERENCES stat_team(id),
    FOREIGN KEY (away_id) references stat_team(id)
);

CREATE TABLE plays (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    game_id BIGINT,
    stat_player_id BIGINT,
    FOREIGN KEY (game_id) REFERENCES game(id),
    FOREIGN KEY (stat_player_id) REFERENCES stat_player(id)
);

CREATE TABLE score (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    stat_team_id BIGINT,
    worth INT,
    part INT,
    FOREIGN KEY (stat_team_id) REFERENCES stat_team(id)
);

CREATE TABLE shot_play (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    game_id BIGINT,
    stat_player_id BIGINT,
    duration BIGINT,
    comments TEXT,
    shot_type VARCHAR(255),
    hand VARCHAR(255),
    zone VARCHAR(255),
    made BOOLEAN,
    contested VARCHAR(255),
    worth INT,
    FOREIGN KEY (game_id) REFERENCES game(id),
    FOREIGN KEY (stat_player_id) REFERENCES stat_player(id)
);
CREATE TABLE rebound (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    game_id BIGINT,
    stat_player_id BIGINT,
    duration BIGINT,
    comments TEXT,
    is_offensive boolean,
    FOREIGN KEY (game_id) REFERENCES game(id),
    FOREIGN KEY (stat_player_id) REFERENCES stat_player(id)
);
CREATE TABLE assist (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    game_id BIGINT,
    stat_player_id BIGINT,
    duration BIGINT,
    comments TEXT,
    to_stat_player_id BIGINT,
    assist_type VARCHAR(255),
    FOREIGN KEY (game_id) REFERENCES game(id),
    FOREIGN KEY (stat_player_id) REFERENCES stat_player(id),
    FOREIGN KEY (to_stat_player_id) REFERENCES stat_player(id)
);
CREATE TABLE `block` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    game_id BIGINT,
    stat_player_id BIGINT,
    duration BIGINT,
    comments TEXT,
    blocked_stat_player_id BIGINT,
    within_perimeter BOOLEAN,
    FOREIGN KEY (game_id) REFERENCES game(id),
    FOREIGN KEY (stat_player_id) REFERENCES stat_player(id),
    FOREIGN KEY (blocked_stat_player_id) REFERENCES stat_player(id)
);
CREATE TABLE foul (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    game_id BIGINT,
    stat_player_id BIGINT,
    duration BIGINT,
    comments TEXT,
    foul_on_stat_player_id BIGINT,
    foul_type VARCHAR(255),
    FOREIGN KEY (game_id) REFERENCES game(id),
    FOREIGN KEY (stat_player_id) REFERENCES stat_player(id),
    FOREIGN KEY (foul_on_stat_player_id) REFERENCES stat_player(id)
);
CREATE TABLE steal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    game_id BIGINT,
    stat_player_id BIGINT,
    duration BIGINT,
    comments TEXT,
    stolen_from_player_id BIGINT,
    FOREIGN KEY (game_id) REFERENCES game(id),
    FOREIGN KEY (stat_player_id) REFERENCES stat_player(id),
    FOREIGN KEY (stolen_from_player_id) REFERENCES stat_player(id)
);


