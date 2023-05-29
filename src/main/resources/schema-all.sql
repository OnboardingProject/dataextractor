DROP TABLE transaction IF EXISTS;

CREATE TABLE transaction(
    transactionId BIGINT IDENTITY NOT NULL PRIMARY KEY,
    transactionDate VARCHAR(20),
    transactionType VARCHAR(20)
);
