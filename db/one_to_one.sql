create table person_number(
    id serial primary key,
    number int
);

create table employe(
    id serial primary key,
    name varchar(255),
    person_number_id int references person_number(id)
);
