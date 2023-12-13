create table teens(
    id serial primary key,
    name varchar(255),
    gender varchar(255)
);

insert into teens(name, gender) values
('Dmitry', 'male'),
('Petr', 'male'),
('Alexey', 'male'),
('Marina', 'female'),
('Elvira', 'female'),
('Tatyana', 'female'),
('Michail', 'male');

select * from teens t1 cross join teens t2 where t1.gender = 'male' and t1.gender != t2.gender;
