ALTER TABLE tbl_episode
ADD COLUMN serie_id BIGINT,
ADD CONSTRAINT fk_serie
    FOREIGN KEY (serie_id) REFERENCES tbl_serie(id);
