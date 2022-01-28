CREATE TABLE if not exists users
(
    id uuid,
    username   NVARCHAR(12 ) NOT NULL,
    email   NVARCHAR(30 ) NOT NULL,
    password NVARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE if not exists notes
(
    id uuid,
    note   NVARCHAR(4000 ) NOT NULL,
    user UUID NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE if not exists templates
(
    id uuid,
    eventName   NVARCHAR(100 ),
    eventDescription   NVARCHAR(100 ),
    fromDate   DATE,
    toDate   DATE,
    user UUID NOT NULL,
    PRIMARY KEY (id)
    );