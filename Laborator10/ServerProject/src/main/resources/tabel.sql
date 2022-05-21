CREATE TABLE IF NOT EXISTS client(
	id SERIAL PRIMARY KEY,
	name VARCHAR (50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS relations(
	id_friend1 INTEGER NOT NULL,
	id_friend2 INTEGER NOT NULL,
	CONSTRAINT fk_friend1
		FOREIGN KEY(id_friend1)
			REFERENCES client(id),
	CONSTRAINT fk_friend2
		FOREIGN KEY(id_friend2)
			REFERENCES client(id)
);

CREATE TABLE IF NOT EXISTS messages(
    id SERIAL PRIMARY KEY,
    sender INTEGER NOT NULL ,
    receiver INTEGER NOT NULL ,
    seen boolean,
    CONSTRAINT fk_sender
        FOREIGN KEY(sender)
            REFERENCES client(id),
    CONSTRAINT fk_receiver
        foreign key (receiver)
            REFERENCES client(id)
);
