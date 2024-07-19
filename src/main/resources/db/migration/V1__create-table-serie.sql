CREATE TABLE tbl_serie (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    total_season INT,
    rating VARCHAR(255),
    category VARCHAR(255),
    actors TEXT,
    plot TEXT,
    poster TEXT
);
