CREATE DATABASE de_moda;

USE de_moda;

CREATE TABLE shops(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    shop_name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL
);

CREATE TABLE products(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    id_shop INT UNSIGNED NOT NULL,
    FOREIGN KEY (id_shop) REFERENCES shops(id) ON DELETE CASCADE
);

CREATE TABLE customers(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    customer_last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE buys(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    buy_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    amount INT NOT NULL,
    id_customer INT UNSIGNED NOT NULL,
    id_product INT UNSIGNED NOT NULL,
    FOREIGN KEY (id_customer) REFERENCES customers(id) ON DELETE CASCADE,
    FOREIGN KEY(id_product) REFERENCES products(id) ON DELETE CASCADE
);

ALTER TABLE products ADD COLUMN stock INT NOT NULL;

INSERT INTO shops(shop_name, location) VALUES ("Riwi Shop", "Bello"), ("Nike", "Itagui"), ("Valenciaga", "El poblado"), ("Dollar City", "Plaza norte");

SELECT * FROM products;