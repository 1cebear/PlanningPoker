DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS stories;
DROP TABLE IF EXISTS storyset;

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY AUTO_INCREMENT,
  name       VARCHAR(100) NOT NULL,
  email      VARCHAR(100) NOT NULL,
  password   VARCHAR(100) NOT NULL,
  registered TIMESTAMP           DEFAULT now(),
  enabled    BOOL                DEFAULT TRUE
);

ALTER TABLE users AUTO_INCREMENT = 1;

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(100),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id)
    ON DELETE CASCADE
);

CREATE TABLE storyset
(
  id          INTEGER PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  voted BOOLEAN NOT NULL
);

ALTER TABLE storyset AUTO_INCREMENT = 1;

CREATE TABLE stories
(
  id          INTEGER PRIMARY KEY AUTO_INCREMENT,
  set_id      INTEGER NOT NULL,
  summary     VARCHAR(100) NOT NULL,
  description TEXT,
  link        TEXT,
  FOREIGN KEY (set_id) REFERENCES storyset (id)
  ON DELETE CASCADE
);