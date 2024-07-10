CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    mobile_number VARCHAR(15) NOT NULL UNIQUE,
    profile_pic VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE user_roles (
    user_id INT,
    role_id INT,
    assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE TABLE addresses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    address_line1 VARCHAR(255) NOT NULL,
    address_line2 VARCHAR(255),
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    postal_code VARCHAR(20) NOT NULL,
    country VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Insert roles
INSERT INTO roles (name, description) VALUES ('ROLE_USER', 'Standard user role');
INSERT INTO roles (name, description) VALUES ('ROLE_ADMIN', 'Administrator role');

-- Insert users
INSERT INTO users (email, password, first_name, last_name, mobile_number, profile_pic, created_at, updated_at) VALUES
('john.doe@example.com', 'hashed_password_1', 'John', 'Doe', '1234567890', 'http://example.com/profile_pic_1.jpg', NOW(), NOW()),
('jane.doe@example.com', 'hashed_password_2', 'Jane', 'Doe', '0987654321', 'http://example.com/profile_pic_2.jpg', NOW(), NOW());

-- Assign roles to users
INSERT INTO user_roles (user_id, role_id) VALUES
((SELECT id FROM users WHERE email = 'john.doe@example.com'), (SELECT id FROM roles WHERE name = 'ROLE_USER')),
((SELECT id FROM users WHERE email = 'john.doe@example.com'), (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
((SELECT id FROM users WHERE email = 'jane.doe@example.com'), (SELECT id FROM roles WHERE name = 'ROLE_USER'));

-- Insert addresses
INSERT INTO addresses (user_id, address_line1, address_line2, city, state, postal_code, country, created_at, updated_at) VALUES
((SELECT id FROM users WHERE email = 'john.doe@example.com'), '123 Main St', 'Apt 4', 'Springfield', 'IL', '62701', 'USA', NOW(), NOW()),
((SELECT id FROM users WHERE email = 'john.doe@example.com'), '456 Elm St', NULL, 'Springfield', 'IL', '62702', 'USA', NOW(), NOW()),
((SELECT id FROM users WHERE email = 'jane.doe@example.com'), '789 Oak St', 'Suite 5', 'Springfield', 'IL', '62703', 'USA', NOW(), NOW());
