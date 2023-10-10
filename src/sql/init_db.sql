use megasoftDB;

create table if not exists worker (
id bigint primary key auto_increment,
name varchar (1000) not null,
birthday date,
level varchar(10) not null,
salary int,
constraint CK_worker_name_length check (length(name) >= 2),
constraint CK_birthday_year check (year(birthday) > '1900'),
constraint CK_level check (level in ('Trainee', 'Junior', 'Middle', 'Senior')),
constraint CK_salary check (salary >= 100 and salary <= 100000)
);

create table if not exists client (
id bigint primary key auto_increment,
name varchar (1000) not null,
constraint CK_client_name_length check (length(name) >= 2)
);

create table if not exists project (
id bigint primary key auto_increment,
client_id bigint not null,
start_date date,
finish_date date,
foreign key (client_id) references client (id)
on update cascade
on delete restrict,
constraint CK_date check (start_date < finish_date)
);

create table if not exists project_worker (
project_id bigint not null,
worker_id bigint not null,
primary key (project_id, worker_id),
foreign key (project_id) references project (id),
foreign key (worker_id) references worker (id)
);