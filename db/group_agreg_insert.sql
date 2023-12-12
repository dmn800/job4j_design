insert into devices(name, price) values
('Xiaomi', 40000),
('Redmi', 72000),
('Samsung', 100000),
('Iphone', 120000);

insert into people(name) values
('Dmitry'),
('Sergey'),
('Ivan'),
('Andrey');

insert into devices_people(device_id, people_id) values
(1, 1), (1, 2), (1, 3),
(2, 1), (2, 3), (2, 4),
(3, 1), (3, 2),
(4, 1), (4, 2), (4, 3), (4, 4);