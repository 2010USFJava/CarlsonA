create schema if not exists bank_of_holding authorization annacarl;
set search_path to bank_of_holding; 

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS accounts;
drop table if exists usertypes;
drop table if exists emplyeelevels;
drop table if exists logininfos;



create table usertypes(
id integer,
user_type varchar(25)
);

insert into usertypes
values(0,'customer'),
(1,'employee');

select * from usertypes;


create table employeelevels(
id integer,
emp_type varchar(50)
);

insert into employeelevels values
(0,'standard'),
(1,'admin');

create table logininfos(
id integer
);

create table users(
id integer,
first_name varchar(50),
last_name varchar(50),
usertype_id integer,
logintype_id integer,
foreign key (usertype_id) references usertypes(id) on delete cascade,
foreign key (logininfo_id) references logininfos(id) on delete cascade
);

create table accounts(
id integer
);


select * from users;