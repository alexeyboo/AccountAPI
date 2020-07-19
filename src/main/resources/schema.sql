DROP TABLE account;
CREATE TABLE IF NOT EXISTS account
(
    id          VARCHAR(50)   NOT NULL   PRIMARY KEY,
    firstname   VARCHAR(50)   NOT NULL,
    lastname    VARCHAR(50)   NOT NULL
);