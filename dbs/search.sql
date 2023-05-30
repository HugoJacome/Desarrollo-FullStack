use products;

CREATE TABLE category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL CHECK (price >= 0),
    category_id INT NOT NULL,
    description TEXT DEFAULT NULL,
	quantity_avaliable INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_title (title),
    INDEX idx_category (category_id),
    FOREIGN KEY (category_id) REFERENCES category (id)
);

-- Categories

INSERT INTO category (name, description) VALUES ('Electronics', 'Products related to electronic devices and accessories');
INSERT INTO category (name, description) VALUES ('Clothing', 'Apparel and fashion accessories');
INSERT INTO category (name, description) VALUES ('Home & Kitchen', 'Items for home and kitchen use');
INSERT INTO category (name, description) VALUES ('Books', 'Books across various genres and topics');
INSERT INTO category (name, description) VALUES ('Sports & Fitness', 'Equipment and gear for sports and fitness activities');
INSERT INTO category (name, description) VALUES ('Beauty & Personal Care', 'Cosmetics, skincare, and personal care products');
INSERT INTO category (name, description) VALUES ('Toys & Games', 'Toys, games, and recreational items');
INSERT INTO category (name, description) VALUES ('Automotive', 'Products for automobiles and vehicles');
INSERT INTO category (name, description) VALUES ('Health & Wellness', 'Healthcare and wellness products');
INSERT INTO category (name, description) VALUES ('Jewelry', 'Ornaments and jewelry accessories');

-- Products

INSERT INTO product (title, price, category_id, description,quantity_avaliable) VALUES ('iPhone 12', 999.99, 1, 'Latest model of the iPhone with advanced features',10);
INSERT INTO product (title, price, category_id, description,quantity_avaliable) VALUES ('Mens T-Shirt', 19.99, 2 , 'Comfortable and stylish t-shirt for men',10);
INSERT INTO product (title, price, category_id, description,quantity_avaliable) VALUES ('Kitchen Knife Set', 49.99, 3, 'Set of high-quality stainless steel kitchen knives',10);
INSERT INTO product (title, price, category_id, description,quantity_avaliable) VALUES ('Harry Potter and the Sorcerers Stone', 12.99, 4, 'First book in the Harry Potter series by J.K. Rowling',10);
INSERT INTO product (title, price, category_id, description,quantity_avaliable) VALUES ('Yoga Mat', 29.99, 5, 'Non-slip yoga mat for comfortable workout sessions',10);
INSERT INTO product (title, price, category_id, description,quantity_avaliable) VALUES ('Face Moisturizer', 24.99, 6, 'Hydrating facial moisturizer for all skin types',10);
INSERT INTO product (title, price, category_id, description,quantity_avaliable) VALUES ('LEGO Classic Bricks Set', 59.99, 7, 'Classic LEGO bricks set for creative building',10);
INSERT INTO product (title, price, category_id, description,quantity_avaliable) VALUES ('Car Seat Cover', 39.99, 8, 'Durable and stylish car seat cover for added comfort',10);
INSERT INTO product (title, price, category_id, description,quantity_avaliable) VALUES ('Multivitamin Supplement', 19.99, 9, 'Daily multivitamin supplement for overall health support',10);
INSERT INTO product (title, price, category_id, description,quantity_avaliable) VALUES ('Diamond Pendant Necklace', 299.99, 10, 'Elegant pendant necklace with a sparkling diamond',10);