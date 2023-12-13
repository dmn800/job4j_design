create table car_bodies(
    id serial primary key,
    name varchar(255)
);

create table car_engines(
    id serial primary key,
    name varchar(255)
);

create table car_transmissions(
    id serial primary key,
    name varchar(255)
);

create table cars(
    id serial primary key,
    name varchar(255),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('Sedan'), ('Crossover'), ('Jeep');
insert into car_engines(name) values ('1.6L'), ('1.8L'), ('2.0L'), ('4.0L');
insert into car_transmissions(name) values ('Mechanic'), ('Automatic'), ('Variator');

insert into cars(name, body_id, engine_id, transmission_id) values
('Lada', 1, 1, 1),
('Mersedes', 1, null, 2),
('BWM', 3, 4, 2),
('Skoda', 1, 1, 1),
('Lada', null, null, null),
('BMW', 1, 2, 2),
('KIA', 1, 2, 2),
('Lexus', 3, 4, null);
