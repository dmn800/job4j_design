create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);

create or replace function tax()
	returns trigger as 
$$
	BEGIN
		update products
		set price = price + price * 0.2
		where id = (select  id from inserted);
		return new;
	END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert on products
	referencing new table as inserted
    for each statement
    execute procedure tax();

create or replace function tax()
	returns trigger as
$$
	BEGIN
		new.price = new.price + new.price * 0.2;
		return new;
	END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger_row
    before insert on products
    for each row
    execute procedure tax();

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);


create or replace function tax_add()
	returns trigger as
$$
	BEGIN
		insert into history_of_price (name, price, date)
		values (new.name, new.price, current_date);
		return new;
	END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger_row_add_price
    after insert on products
    for each row
    execute procedure tax_add();
