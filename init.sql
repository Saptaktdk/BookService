USE book_db;

CREATE TABLE books(
    id int NOT NULL,
    book_name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    price double NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO books(book_name,description,author,price)
VALUES ("DemoBook","This is a demo","unknown",109)