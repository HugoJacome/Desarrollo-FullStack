USE actions;

CREATE TABLE purchase (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id VARCHAR(50) NOT NULL,
    purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    quantity INT NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
	customer_name VARCHAR(250)NOT NULL,
    customer_last_name VARCHAR(250),
    customer_email VARCHAR(100) NOT NULL,
    customer_phone VARCHAR(20) NOT NULL,
    customer_address VARCHAR(100) NOT NULL,
    customer_city VARCHAR(50) NOT NULL,
    comments VARCHAR(250) NULL
);