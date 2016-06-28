create table user (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(45) DEFAULT NULL
);

create table preference (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  preference_type VARCHAR(45) DEFAULT NULL
);

create table preference_user (
  user_id INT NOT NULL,
  preference_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (preference_id) REFERENCES preference(id),
  PRIMARY KEY (user_id, preference_id)
);

create table user_order (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  details VARCHAR(255) DEFAULT NULL,
  user_id INT NOT NULL,
  order_date DATE,
  FOREIGN KEY (user_id) REFERENCES user(id)
);

