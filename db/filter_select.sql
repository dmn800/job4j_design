select * from product p join type t on p.type_id = t.id
where t.name like 'СЫР';

select * from product p where p.name like '%Мороженое%';

select * from product p where current_date > p.expired_date;

select * from product where price = (select max(price)
									from product);

select t.name, count(p) from product p
join type t
on p.type_id = t.id
group by t.name;

select * from product p
join type t
on p.type_id = t.id
where t.name = 'СЫР' or t.name = 'МЯСО';

select t.name, count(p) from product p
join type t
on p.type_id = t.id
group by t.name
having count(p) <= 3;

select * from product p
join type t
on p.type_id = t.id;
