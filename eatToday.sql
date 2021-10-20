create table eatTUser (
	idx int not null auto_increment, 
	id varchar(20) not null, 
	password varchar(20) not null, 
	name varchar(20) not null, 
	birthDay Date, 
	
	primary key(idx)
);

desc eatTUser;

insert into eatTUser values (default,'hong1234', '1234', '홍길동', '1970-02-10');
insert into eatTUser values (default,'horsesuk12', '1234', '김말숙', '1960-02-10');
insert into eatTUser values (default,'winwin1234', '1234', '이기자', '1992-05-23');
insert into eatTUser values (default,'kickfruit07', '1234', '강감찬', '1987-09-01');

select * from eatTUser;
delete from eatTUser where idx = 6;


create table AllMenu (
	idx int not null auto_increment, 
	class varchar(20) not null, 
	shop varchar(20), 
	menu varchar(20) not null, 
	prefer int, 
	locate varchar(20), 
	choose int not null default 0,
	
	primary key(idx)
);

drop table AllMenu;

desc AllMenu;

select * from AllMenu;

insert into AllMenu values (default,'중식', '한가네','간짜장', 10, '충대 중문', default);
insert into AllMenu values (default,'일식', '간바레간바로우', '초밥', 10, '충대 쪽문', default);
insert into AllMenu values (default,'한식', '세라믹 삼겹살', '항정살', 8, '충대 정문, 공단오거리', default);
insert into AllMenu values (default,'분식', '쩔어떡볶이', '떡볶이', 4, '충대 중문', default);
insert into AllMenu values (default,'양식', '파브리카', '리조또', 1, '충대 중문', default);

alter table AllMenu change class classi varchar(20);

select id from eatTUser where name = '조재령' and birthDay = '1998-2-10';

show tables;

update eatTUser set password = '1234' where id = 'qwer' and password = '12345';

delete from AllMenu where classi = '';
