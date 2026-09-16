INSERT INTO authors (firstname, lastname, email)
VALUES ('Vincenzo', 'Bianchi', 'vincblanche@tin.it'),
       ('Antonio', 'Rossi', 'antonred@mail.it');

INSERT INTO posts (title, body, publish_date, author_id)
SELECT 'Lorem ipsum ...', 'Ciao', NULL, id
FROM authors
WHERE firstname = 'Vincenzo'
  AND lastname = 'Bianchi';

INSERT INTO posts (title, body, publish_date, author_id)
SELECT 'Lorem ipsum ...', 'Non sono Ciao', NULL, id
FROM authors
WHERE firstname = 'Antonio'
  AND lastname = 'Rossi';

INSERT INTO comments (email, body, date, post_id)
VALUES ('antonred@mail.it', 'Lorem ipsum...', '20230315', 1),
       ('antonred@mail.it', 'Lorem ipsum...', '20230315', 2);