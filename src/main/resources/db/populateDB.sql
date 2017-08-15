DELETE FROM user_roles;
DELETE FROM votes;
DELETE FROM users;
DELETE FROM stories;
DELETE FROM storyset;


ALTER TABLE users
  AUTO_INCREMENT = 1;

ALTER TABLE storyset
  AUTO_INCREMENT = 1;

ALTER TABLE stories
  AUTO_INCREMENT = 1;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 1),
  ('ROLE_ADMIN', 2),
  ('ROLE_USER', 2);

INSERT INTO storyset (name, voted) VALUES
  ("1st set", FALSE),
  ("2nd set", FALSE);

INSERT INTO stories (set_id, summary, description, link) VALUES
  (1, "task 1", "task 1", ""),
  (1, "task 2", "task 2", ""),
  (1, "task 3", "task 3", ""),
  (2, "task 4", "task 4", ""),
  (2, "task 5", "task 5", "");

INSERT INTO votes (user_id, story_id, vote) VALUES
  (1, 1, "0"),
  (1, 2, "1"),
  (2, 1, "1"),
  (2, 2, "2");