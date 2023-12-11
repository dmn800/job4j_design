insert into roles(name) values ('Owner');
insert into roles(name) values ('Client');
insert into roles(name) values ('Admin');

insert into rights(name) values ('Write');
insert into rights(name) values ('Read');
insert into rights(name) values ('Create');
insert into rights(name) values ('Delete');

insert into categories(name) values ('1st line');
insert into categories(name) values ('2nd line');

insert into states(name) values ('Complete');
insert into states(name) values ('Waiting');
insert into states(name) values ('Working');

insert into users(name, role_id) values ('Dmitry', 1);
insert into users(name, role_id) values ('Ivan', 2);
insert into users(name, role_id) values ('Petr', 3);
insert into users(name, role_id) values ('Sergey', 2);

insert into roles_rights(role_id, right_id) values (1, 1);
insert into roles_rights(role_id, right_id) values (1, 2);
insert into roles_rights(role_id, right_id) values (1, 3);
insert into roles_rights(role_id, right_id) values (1, 4);
insert into roles_rights(role_id, right_id) values (2, 3);
insert into roles_rights(role_id, right_id) values (3, 1);
insert into roles_rights(role_id, right_id) values (3, 2);

insert into items(name, user_id, category_id, state_id) values ('Not working printer', 1, 1, 1);
insert into items(name, user_id, category_id, state_id) values ('Install Win10', 3, 2, 3);
insert into items(name, user_id, category_id, state_id) values ('Out of papers', 3, 1, 2);
insert into items(name, user_id, category_id, state_id) values ('Install drivers', 4, 2, 1);

insert into comments(name, item_id) values ('Printer is working', 1);
insert into comments(name, item_id) values ('Need to buy papers', 3);
insert into comments(name, item_id) values ('Paper purchased', 3);
insert into comments(name, item_id) values ('Done', 4);

insert into attachments(name, item_id) values ('screenshot_error.png', 4);
