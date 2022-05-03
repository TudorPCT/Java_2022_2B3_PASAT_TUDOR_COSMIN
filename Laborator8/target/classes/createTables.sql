DROP TABLE IF EXISTS cities;

DROP TABLE IF EXISTS countries;

DROP TABLE IF EXISTS continents;

CREATE TABLE IF NOT EXISTS continents(
    id SERIAL PRIMARY KEY,
    name VARCHAR (50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS countries(
    id SERIAL PRIMARY KEY,
    name VARCHAR (50) UNIQUE NOT NULL,
    code VARCHAR (50),
    continent INTEGER NOT NULL,
    CONSTRAINT fk_continent
        FOREIGN KEY(continent)
            REFERENCES continents(id)
);

CREATE TABLE IF NOT EXISTS cities(
    id SERIAL PRIMARY KEY,
    name VARCHAR (50) UNIQUE NOT NULL,
    country INTEGER NOT NULL,
    CONSTRAINT fk_country
        FOREIGN KEY(country)
            REFERENCES countries(id),
    capital INTEGER NOT NULL CHECK ( capital in (0,1) ),
    latitude REAL NOT NULL,
    longitude REAL NOT NULL
);



