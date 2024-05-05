USE stat_tracker;

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

