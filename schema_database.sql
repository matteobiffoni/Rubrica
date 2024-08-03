CREATE DATABASE rubrica;

USE rubrica;

CREATE TABLE utenti (
    uid INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE persone (
    id VARCHAR(100) NOT NULL PRIMARY KEY,
    nome VARCHAR(100),
    cognome VARCHAR(100),
    indirizzo VARCHAR(100),
    telefono VARCHAR(100),
    eta INT,
    created_by INT NOT NULL,
    ts_created TIMESTAMP NOT NULL,
    last_updated_by INT,
    ts_last_updated TIMESTAMP
);

ALTER TABLE persone
    ADD FOREIGN KEY(created_by) REFERENCES utenti(uid);
ALTER TABLE persone
    ADD FOREIGN KEY(last_updated_by) REFERENCES utenti(uid);

INSERT INTO utenti (username, password) VALUES ('utente1', 'password');