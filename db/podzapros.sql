CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

INSERT INTO customers VALUES (1, 'Доу', 'Джон', 20, 'France'),
                                  (2, 'Грубер', 'Ганс', 16, 'Germany'),
                                  (3, 'Смит', 'Сара', 16, 'USA'),
                                  (4, 'Иванов', 'Иван', 55, 'Russia'),
                                  (5, 'Купер', 'Грета', 33, 'China');

select * from customers where age = (select min(age) from customers);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

INSERT INTO orders VALUES (1, 10, 1),
                          (2, 20, 2),
                          (3, 15, 5);

select * from customers c where c.id not in (select customer_id from orders);