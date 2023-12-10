create table country(
    id serial primary key,
    name varchar(255)
);

create table city(
    id serial primary key,
    name varchar(255),
    country_id int references position(id)
);