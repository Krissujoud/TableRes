CREATE TABLE restaurant_table (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  name VARCHAR(50),
                                  seats INT,
                                  zone VARCHAR(20),
                                  x INT,
                                  y INT,
                                  window_seat BOOLEAN,
                                  quiet BOOLEAN,
                                  near_kids BOOLEAN,
                                  accessible BOOLEAN
);

CREATE TABLE reservation (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             table_id BIGINT NOT NULL,
                             start_time TIMESTAMP NOT NULL,
                             end_time TIMESTAMP NOT NULL,
                             FOREIGN KEY (table_id) REFERENCES restaurant_table(id)
);

INSERT INTO restaurant_table (id, name, seats, zone, x, y, window_seat, quiet, near_kids, accessible) VALUES
                                                                                                (1, 'T1', 2, 'WINDOW', 1, 1, TRUE, FALSE, FALSE, TRUE),
                                                                                                (2, 'T2', 4, 'WINDOW', 3, 1, TRUE, FALSE, FALSE, TRUE),
                                                                                                (3, 'T3', 2, 'MAIN_HALL', 1, 3, FALSE, TRUE, FALSE, TRUE),
                                                                                                (4, 'T4', 6, 'MAIN_HALL', 4, 3, FALSE, FALSE, FALSE, TRUE),
                                                                                                (5, 'T5', 4, 'TERRACE', 1, 5, FALSE, FALSE, FALSE, TRUE),
                                                                                                (6, 'T6', 8, 'TERRACE', 4, 5, FALSE, FALSE, FALSE, TRUE),
                                                                                                (7, 'T7', 4, 'NEAR_KIDS', 1, 7, FALSE, FALSE, TRUE, TRUE),
                                                                                                (8, 'T8', 6, 'NEAR_KIDS', 4, 7, FALSE, FALSE, TRUE, TRUE),
                                                                                                (9, 'T9', 2, 'WINDOW', 1, 9, TRUE, FALSE, FALSE, TRUE),
                                                                                                (10,'T10',4, 'MAIN_HALL', 3, 9, FALSE, TRUE, FALSE, TRUE),
                                                                                                (11,'T11',2, 'WINDOW', 1, 11, TRUE, FALSE, FALSE, TRUE),
                                                                                                (12,'T12',4, 'TERRACE', 3, 11, FALSE, FALSE, FALSE, TRUE),
                                                                                                (13,'T13',6, 'MAIN_HALL', 5, 1, FALSE, FALSE, FALSE, TRUE),
                                                                                                (14,'T14',2, 'WINDOW', 2, 2, TRUE, FALSE, FALSE, TRUE),
                                                                                                (15,'T15',4, 'MAIN_HALL', 4, 2, FALSE, TRUE, FALSE, TRUE),
                                                                                                (16,'T16',2, 'WINDOW', 1, 4, TRUE, FALSE, FALSE, TRUE),
                                                                                                (17,'T17',6, 'TERRACE', 5, 4, FALSE, FALSE, FALSE, TRUE),
                                                                                                (18,'T18',4, 'NEAR_KIDS', 2, 6, FALSE, FALSE, TRUE, TRUE),
                                                                                                (19,'T19',2, 'WINDOW', 1, 8, TRUE, FALSE, FALSE, TRUE),
                                                                                                (20,'T20',4, 'MAIN_HALL', 3, 8, FALSE, TRUE, FALSE, TRUE);

INSERT INTO reservation (table_id,start_time,end_time)
VALUES (2,'2026-03-08T18:00:00','2026-03-08T20:00:00');

INSERT INTO reservation (table_id,start_time,end_time)
VALUES (5,'2026-03-08T18:00:00','2026-03-08T21:00:00');