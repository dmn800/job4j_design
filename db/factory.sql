create table factory(
 	id serial primary key,
	position text,
	count int,
	insalubrity boolean
);
insert into factory(position, count, insalubrity) values('engineer', 200, true);
select * from factory;
update factory set count = 355;
select * from factory;
delete from factory;