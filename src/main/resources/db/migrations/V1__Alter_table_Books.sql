DROP TABLE tb_books;

CREATE TABLE tb_books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    genre VARCHAR(255),
    date_start DATE,
    date_end DATE,
    evaluation TEXT,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES tb_users(id)
);