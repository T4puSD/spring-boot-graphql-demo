create table author (
    id bigint primary key auto_increment,
    name varchar not null,
    thumbnail varchar
);

create table post (
    id bigint primary key auto_increment,
    title varchar not null,
    text text,
    category varchar,
    author_id bigint not null
);