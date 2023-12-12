select u.first_name Имя, u.second_name Фамилия,
i.date as "Дата бронирования", i.seat Место
from users u join items i on u.id = i.user_id;

select u.second_name Фамилия, u.first_name Имя, u.age Возраст
from users u

select * from users u join items i on u.id = i.user_id;
