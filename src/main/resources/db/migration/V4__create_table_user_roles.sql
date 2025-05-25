CREATE TABLE users_roles (
    user_id BIGSERIAL,
    role_id SERIAL,
    CONSTRAINT fk_user_role_user FOREIGN KEY(user_id) REFERENCES users(id),
    CONSTRAINT fk_user_role_role FOREIGN KEY(role_id) REFERENCES roles(id)
);