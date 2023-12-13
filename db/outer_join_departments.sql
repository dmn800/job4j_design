create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments(name) values ('201');
insert into departments(name) values ('202');
insert into departments(name) values ('203');
insert into departments(name) values ('204');
insert into departments(name) values ('205');

insert into employees(name, department_id) values ('Employee 1', null);
insert into employees(name, department_id) values ('Employee 2', 4);
insert into employees(name, department_id) values ('Employee 3', 3);
insert into employees(name, department_id) values ('Employee 4', 1);
insert into employees(name, department_id) values ('Employee 5', null);
insert into employees(name, department_id) values ('Employee 6', 2);
insert into employees(name, department_id) values ('Employee 7', 2);
insert into employees(name, department_id) values ('Employee 8', 1);
insert into employees(name, department_id) values ('Employee 9', null);
insert into employees(name, department_id) values ('Employee 10', 4);

select * from employees e left join departments d on e.department_id = d.id;
select * from employees e right join departments d on e.department_id = d.id;
select * from employees e full join departments d on e.department_id = d.id;
select * from employees e cross join departments d;

select * from departments d left join employees e on d.id = e.department_id where e.id is null;

select * from employees e left join departments d on e.department_id = d.id;
select e.id, e.name, e.department_id, d.id, d.name from departments d right join employees e on d.id = e.department_id;
