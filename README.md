# Customer-Inteca

CREATE TABLE Customer(
    firstName CHAR NOT NULL,
    id INT UNSIGNED,
    lastName CHAR NOT NULL,
    pesel VARCHAR(11) PRIMARY KEY //przyjmując, że to polski pesel
    )
