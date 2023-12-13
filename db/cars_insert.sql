select c.id, 
c.name car_name, 
b.name body_name, 
e.name engine_name,
t.name transsmission_name 
from cars c 
left join car_bodies b on c.body_id = b.id
left join car_engines e on c.engine_id = e.id
left join car_transmissions t on c.transmission_id = t.id;

select b.name body_name
from cars c
right join car_bodies b
on c.body_id = b.id
where c.id is null;

select e.name engine_name
from cars c
full join car_engines e
on c.engine_id = e.id
where c.id is null;

select t.name transmission_name
from cars c
full join car_transmissions t
on c.transmission_id = t.id
where c.id is null;
