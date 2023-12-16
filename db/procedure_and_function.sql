create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
    insert into products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
    END
$$;

call insert_data('product_1', 'producer_1', 15, 32);
call insert_data('product_2', 'producer_2', 30, 50);
call insert_data('product_3', 'producer_3', 45, 75);
call insert_data('product_4', 'producer_4', 55, 100);
call insert_data('product_5', 'producer_5', 65, 150);

create or replace procedure update_data(u_count integer, tax float, u_id integer)
language 'plpgsql'
as $$
    BEGIN
        if u_count > 0 THEN
            update products set count = count - u_count where id = u_id;
        end if;
        if tax > 0 THEN
            update products set price = price + price * tax;
        end if;
    END;
$$;

create or replace procedure delete_data(u_id integer)
language 'plpgsql'
as $$
    BEGIN
        delete from products where id = u_id;
    END;
$$;

create or replace function f_delete_data(u_count integer)
returns varchar(50)
language 'plpgsql'
as
$$
    declare
        result varchar(50);
    begin
        delete from products where count = u_count;
        select into result name from products where count = u_count;
        return result;
    end;
$$;

call delete_data(2);
call update_data(45, 0, 3);
call update_data(65, 0, 5);
select f_delete_data(0);
