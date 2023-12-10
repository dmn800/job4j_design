create table person_number(
    id serial primary key,
    number int
);

create table employe(
    id serial primary key,
    name varchar(255)
);

create table number_employe(
    id serial primary key,
    number_id int references person_number(id) unique,
    employe_id int references employe(id) unique
);