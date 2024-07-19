CREATE TABLE tbl_episode (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    number_season INT,
    title VARCHAR(255) NOT NULL,
    released DATE,
    episode VARCHAR(255),
    imdb_rating DOUBLE
);
