CREATE SCHEMA app AUTHORIZATION postgres;

CREATE TABLE app.users
(
    login character varying NOT NULL,
    password character varying NOT NULL,
    name character varying NOT NULL,
    date_of_birds timestamp(0) without time zone NOT NULL,
    date_of_create timestamp without time zone NOT NULL,
    role character varying NOT NULL,
    PRIMARY KEY (login)
);

ALTER TABLE IF EXISTS app.users
    OWNER to postgres;

INSERT INTO app.users (login, password, name, date_of_birds, date_of_create, role)
VALUES ('admin', 'admin7722', 'Администратор', '1998-02-10 00:00:00', '2025-07-20 18:15:43.000000', 'ADMIN')