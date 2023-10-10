use megasoftDB;

insert into worker (name, birthday, level, salary)
values ('Denis Petrenko', '2000-01-12', 'Trainee', 400),
('Andriy Nikolaenko', '2001-02-01', 'Trainee', 400),
('Andriy Semenov', '1998-09-18', 'Junior', 700),
('Maksim Kravchenko', '1998-03-24', 'Junior', 750),
('Oleksandr Ivanenko', '1970-04-15', 'Junior', 750),
('Ivan Petrenko', '1997-11-10', 'Middle', 1300),
('Dmitrii Dmitriev', '1996-04-30', 'Middle', 2000),
('Fedor Zaytsev', '2000-08-15', 'Middle', 2400),
('Alex Zagorulko', '1999-10-07', 'Senior', 6500),
('Yurii Polunin', '1981-07-08', 'Senior', 6500);

insert into client (name)
values ('Softserv'), ('Luxotf'),
('Goit'), ('Genesis'), ('Sigma');

insert into project (client_id, start_date, finish_date)
values (1, '2023-01-16', '2023-02-16'),
(1, '2022-03-07', '2023-08-07'),
(2, '2021-10-20', '2022-02-20'),
(2, '2018-04-04', '2024-12-04'),
(2, '2022-10-12', '2025-11-20'),
(3, '2020-12-14', '2023-11-14'),
(3, '2021-07-17', '2021-12-17'),
(4, '2022-11-30', '2026-08-30'),
(4, '2017-03-18', '2025-07-18'),
(4, '2016-08-04', '2018-12-10'),
(5, '2020-02-08', '2023-12-08'),
(5, '2022-11-02', '2027-02-02');

insert into project_worker (project_id, worker_id)
values (1, 1), (1, 2), (1, 8), (1, 9), (1, 10),
(2, 4), (2, 6), (2, 7),
(3, 3), (3, 8), (3, 10), (3, 9),
(4, 6), (4, 8), (4, 9), (4, 1), (4, 10),
(5, 8),
(6, 3), (6, 10),
(7, 9),
(8, 7), (8, 8),
(9, 2), (9, 6), (9, 9),
(10, 1), (10, 4), (10, 5), (10, 9), (10, 10),
(11, 4), (11, 8), (11, 9),
(12, 7), (12, 6);