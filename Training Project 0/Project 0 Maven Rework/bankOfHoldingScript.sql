--DROP TABLE IF EXISTS messing_around.mystories;
--DROP TABLE IF EXISTS messing_around.my_characters;

create table messing_around.pookiemans(
pid integer primary key,
pname varchar(30)

);

create table messing_around.mystories(
story_id serial primary key,
story_name varchar(30)
);

create table messing_around.my_characters(
character_id serial primary key,
first_name varchar(30),
last_name varchar(30),
story_id int,
constraint fk_story
	foreign key(story_id)
		references messing_around.mystories(story_id)
);

insert into messing_around.mystories(story_name) values
('Life''s Complicated'),
('Doors to Chaos'),
('Blood of the Black Sheep');



update messing_around.mystories
--set story_name='Life\'s Complicated'
set story_name=$$Life's Complicated$$
where story_id=1;

select * from messing_around.mystories;


