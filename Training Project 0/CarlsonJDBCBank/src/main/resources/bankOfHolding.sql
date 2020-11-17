create schema if not exists bank_of_holding authorization annacarl;
set search_path to bank_of_holding; 


drop table if exists usertypes cascade;
drop table if exists employeelevels cascade;
drop table if exists logininfos cascade;
drop TABLE IF EXISTS users cascade;
drop TABLE IF EXISTS accounts cascade;
drop table if exists accountstatus cascade;
drop table if exists accounttype cascade;
drop table if exists customer_account_relationships cascade;
drop table if exists employee_level_relationships cascade;
drop table if exists customers cascade;

--Sequence
--create sequence mySeq
--increment by 1
--start with 1
--minvalue 1
--maxvalue 20000;

create table usertypes(
id serial primary key,
user_type varchar(25)
);

insert into usertypes
values(0,'customer'),
(1,'employee');

create table employeelevels(
id serial primary key,
emp_type varchar(50)
);

insert into employeelevels values
(0,'standard'),
(1,'admin');

create table logininfos(
id serial primary key,
login_username varchar,
login_password varchar,
user_id integer
);

create table users(
id serial primary key,
first_name varchar(50),
last_name varchar(50)
--usertype_id integer,
--logininfo_id integer,
--FOREIGN key (usertype_id) references usertypes(id) on delete cascade,
--FOREIGN key (logininfo_id) references logininfos(id) on delete cascade
);

alter table logininfos
add foreign key (user_id) 
references users(id) 
on delete cascade;

create table accountstatus(                 
id serial primary key,
user_type varchar(25)
);

insert into accountstatus values
(0,'Open'),
(1,'Closed'),
(2,'In Application'),
(3,'Rejected');

create table accounttype(
id serial primary key,
user_type varchar(25)

);

create table accounts(
id serial primary key,
accountstatus_id int,
accounttype_id int,
balance money
);

create table customer_account_relationships(
--id serial primary key,
customer_id int,
account_id int,
--FOREIGN key (account_id) references accounts(id) on delete cascade,
--FOREIGN key (customer_id) references users(id) on delete cascade,
PRIMARY KEY(customer_id, account_id)
);

update accounts set accountstatus_id=2 where id=1;

create table employee_level_relationships(
--id serial primary key,
level_id int,
employee_id int,
FOREIGN key (level_id) references employeelevels(id) on delete cascade,
FOREIGN key (employee_id) references users(id) on delete cascade
);


create table customers(
customer_id int,
FOREIGN key (customer_id) references users(id) on delete cascade
);




--Test data
--insert into users (id,first_name,last_name) values
--(1,'Joe','Smith'),
--(2,'Marth','Maywho'),
--(3,'Eathen','Nestor'),
--(4,'Mark','Fishbach');
--
--insert into accounts (id,accountstatus_id, accounttype_id,balance) values
--(1,2,0,400.00),
--(2,1,0,700.00);
--
--insert into customers values
--(1);
--
--insert into customer_account_relationships values
--(1,1);
--
--insert  into customer_account_relationships  values 
--(1,1) on CONFLICT do nothing;
--select * from customer_account_relationships;
--
--insert into employee_level_relationships values
--(0,3),
--(1,4);
--
--
--insert into logininfos (login_username,login_password,user_id) values
--('user1','pass',1),
--('user2','pass',2),
--('user3','pass',3),
--('user4','pass',4);



--These are several calls that I made here from outside programms. They're slated for deletion after updates
--but will remain here mid code to help with debugging.

--select users.* from customers inner join users on customers.customer_id=users.id;
--
--select users.*,logininfos.login_username,logininfos.login_password
--from customers,users,logininfos
--where customers.customer_id=users.id and users.id=logininfos.user_id;
--
--
--select users.*,employee_level_relationships.level_id,logininfos.login_username,logininfos.login_password
--from employee_level_relationships,users,logininfos
--where employee_level_relationships.employee_id=users.id and users.id=logininfos.user_id;
--
--select *
--from logininfos;
--
--select * from accounts;
--	select * from users;
--select users.id 
--from users,customer_account_relationships car
--where users.id=car.customer_id and car.account_id=1;
--select accounts.* 
--from accounts,customer_account_relationships cust 
--where accounts.id=cust.account_id and cust.customer_id=1;

select * from users;
select * from accounts;
select * from customer_account_relationships;


