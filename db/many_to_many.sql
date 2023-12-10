create table cinema(
    id serial primary key,
    name varchar(255)
);

create table films(
    id serial primary key,
    name varchar(255)
);

create table cinema_films(
    id serial primary key,
    cinema_id int references cinema(id),
    film_id int references films(id)
);