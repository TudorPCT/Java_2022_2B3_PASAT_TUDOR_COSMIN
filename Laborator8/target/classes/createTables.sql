DROP TABLE countries;

DROP TABLE continents;

CREATE TABLE IF NOT EXISTS countries(
    id SERIAL PRIMARY KEY,
    name VARCHAR (50) UNIQUE NOT NULL,
    code VARCHAR (50),
    continent INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS continents(
        id SERIAL PRIMARY KEY,
        name VARCHAR (50) UNIQUE NOT NULL
);

