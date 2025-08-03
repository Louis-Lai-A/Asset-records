CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password CHAR(60) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    full_name VARCHAR(100),
    role VARCHAR(20) NOT NULL,
    enabled BOOLEAN NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL
);


CREATE TABLE IF NOT EXISTS asset_records (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    asset_no VARCHAR(50) NOT NULL UNIQUE,
    project_type VARCHAR(50) NOT NULL,
    project_value DECIMAL(15,2) NOT NULL,
    project_quantity INT NOT NULL,
    origin_time DATETIME NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);


CREATE TABLE IF NOT EXISTS debt_records (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    debt_no VARCHAR(50) NOT NULL UNIQUE,
    debt_type VARCHAR(50) NOT NULL,
    debt_value DECIMAL(15,2) NOT NULL,
    debt_quantity INT NOT NULL,
    origin_time DATETIME NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);


CREATE TABLE IF NOT EXISTS life_records (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    consumption_no VARCHAR(50) NOT NULL UNIQUE,
    consumption_type VARCHAR(50) NOT NULL,
    consumption_value DECIMAL(15,2) NOT NULL,
    consumption_quantity INT NOT NULL,
    origin_time DATETIME NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);


CREATE TABLE IF NOT EXISTS income_records (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    income_no VARCHAR(50) NOT NULL UNIQUE,
    income_type VARCHAR(50) NOT NULL,
    income_value DECIMAL(15,2) NOT NULL,
    income_quantity INT NOT NULL,
    origin_time DATETIME NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);