CREATE TABLE IF NOT EXISTS reservations (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  client_full_name VARCHAR(255) NOT NULL,
  room_number INT NOT NULL
);

CREATE TABLE IF NOT EXISTS reservation_dates (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  date DATE,
  reservation_id INT,
  FOREIGN KEY (reservation_id) REFERENCES reservations(id)
);


INSERT INTO reservations (client_full_name, room_number)
VALUES ('Alan Ramirez', 101);

INSERT INTO reservation_dates (date, reservation_id)
VALUES ('2023-05-20', 1);

INSERT INTO reservation_dates (date, reservation_id)
VALUES ('2023-05-14', 1);

INSERT INTO reservation_dates (date, reservation_id)
VALUES ('2023-05-14', 1);

INSERT INTO reservations (client_full_name, room_number)
VALUES ('Alan 678', 202);

INSERT INTO reservation_dates (date, reservation_id)
VALUES ('2023-05-15', 2);

INSERT INTO reservation_dates (date, reservation_id)
VALUES ('2023-05-16', 2);