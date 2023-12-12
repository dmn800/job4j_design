create table users(
    id serial primary key,
    first_name text,
    second_name text,
	age int,
	phone varchar(11)
);

create table items(
    id serial primary key,
    seat varchar(3),
    date date,
	user_id int references users(id)
);