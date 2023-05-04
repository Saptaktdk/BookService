import mysql.connector

conn = mysql.connector.connect(
    host="localhost",
    user="saptak",
    password="saptak",
    database="book_db"
)

cursor = conn.cursor()

cursor.execute("""
    CREATE TABLE books(
        id int NOT NULL AUTO_INCREMENT,
        book_name VARCHAR(255) NOT NULL,
        description VARCHAR(255) NOT NULL,
        author VARCHAR(255) NOT NULL,
        price double NOT NULL,
        PRIMARY KEY(id)
    )
""")

cursor.execute("""
    INSERT INTO books(book_name,description,author,price)
    VALUES ("DemoBook","This is a demo","unknown",109)
""")

conn.close()