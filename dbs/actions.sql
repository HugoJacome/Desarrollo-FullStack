USE actions;

CREATE TABLE customer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250)NOT NULL,
    last_name VARCHAR(250),
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE purchase (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id VARCHAR(50) NOT NULL,
    customer_id BIGINT NOT NULL,
    purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    quantity INT NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

INSERT INTO customer (name, last_name, username, password, email) VALUES
    ('John', 'Doe', 'johndoe', 'password123', 'johndoe@example.com'),
    ('Jane', 'Smith', 'janesmith', 'securepass', 'janesmith@example.com'),
    ('Michael', 'Johnson', 'mikejohnson', 'mypass', 'mikejohnson@example.com');