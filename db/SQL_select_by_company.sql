CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company(id, name) values
(1, 'Tesla'),
(2, 'General Motors'),
(3, 'Safran'),
(4, 'BMW'),
(5, 'Boeing'),
(6, 'Airbus');

insert into person(id, name, company_id) values
(1, 'Petrov', 6), 
(2, 'Sidorov', 5),
(3, 'Ivanov', 4),
(4, 'Sergeev', 3),
(5, 'Smirnov', 2),
(6, 'Alexeev', 1),
(7, 'Antonov', 5),
(8, 'Volkov', 1),
(9, 'Grinev', 4),
(10, 'Bobrov', 1),
(11, 'Volodin', 5);

select p.id, p.name, c.name from person p join company c
on p.company_id = c.id
where c.id != 5;

create view show_count_person
as
select c.name, count(p.id) as count
from company c join person p
on c.id = p.company_id
group by c.name;

select * from show_count_person s
where s.count =
(select max(count) from (
select * from show_count_person));