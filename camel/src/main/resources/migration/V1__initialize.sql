DROP TABLE IF EXISTS requests;
CREATE TABLE requests (
    id              bigserial,
    endpoint        varchar(80),
    text            varchar(80),
    created         timestamp default current_timestamp,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS some_data;
CREATE TABLE some_data (
    id              bigserial,
    text            varchar(80),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS exceptions;
CREATE TABLE exceptions (
    id              bigserial,
    text            varchar(1000),
    created         timestamp default current_timestamp,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS weather_requests;
CREATE TABLE weather_requests (
    id              bigserial,
    sender          varchar(80),
    city            varchar(80),
    created         timestamp default current_timestamp,
    PRIMARY KEY (id)
);

INSERT INTO some_data(text)
VALUES ('First'), ('Second'), ('Third');