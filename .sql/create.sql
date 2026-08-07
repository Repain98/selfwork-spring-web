create table authors
(
    id        BIGINT auto_increment PRIMARY KEY,
    firstname VARCHAR(100),
    lastname  VARCHAR(100),
    email     VARCHAR(100) not null unique
);

create table posts
(
    id           BIGINT auto_increment PRIMARY KEY,
    title        VARCHAR(100) not NULL,
    body         VARCHAR(1000) not null,
    publish_date CHAR(8),
    author_id    BIGINT,
    FOREIGN KEY (author_id) REFERENCES authors (id)
);

create table comments
(
    id      BIGINT auto_increment PRIMARY KEY,
    email   VARCHAR(100) not null,
    body    VARCHAR(200) not null,
    date    CHAR(8),
    post_id BIGINT not null,
    FOREIGN KEY (post_id) REFERENCES posts (id)
);