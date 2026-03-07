CREATE DATABASE IF NOT EXISTS calc_data;
USE calc_data;

CREATE TABLE IF NOT EXISTS calc_results (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    operation VARCHAR(20)       NOT NULL,
    number1   DOUBLE            NOT NULL,
    number2   DOUBLE            NOT NULL,
    result    DOUBLE            NOT NULL,
    created_at TIMESTAMP        DEFAULT CURRENT_TIMESTAMP
);

