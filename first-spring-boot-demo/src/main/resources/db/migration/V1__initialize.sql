create table if not exists users
(
    id    uuid,
    name  varchar(255),
    email varchar(5000),
    primary key (id)
);

create table if not exists messages
(
    id      uuid,
    user_id uuid references users (id),
    header  varchar(255),
    text  varchar(5000),
    created timestamp,
    updated timestamp,
    primary key (id)
);
