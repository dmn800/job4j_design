CREATE TABLE movie (
    id SERIAL PRIMARY KEY,
    name text,
    director text
);

CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    title text,
    author text
);

INSERT INTO movie (name, director)
VALUES ('���������', '����� �����'),
       ('�������', '������ ��������'),
       ('��������� �����', '����� �������'),
       ('����� ������ � ����� ��������', '�������� ������'),
       ('�������� �������', '���� �����');

INSERT INTO book (title, author)
VALUES ('����� ������ � ����� ��������', '����� �������'),
       ('��������� �����', '���� ������'),
       ('1984', '������ ������'),
       ('���������', '���� ���'),
       ('������������ �������', '����� ��������');

select name from movie intersect select title from book;

select title from book except select name from movie;

(select name from movie except select title from book)
union all
(select title from book except select name from movie);