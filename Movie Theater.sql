drop user theater cascade;

/* Create the database */
create user theater
identified by p4ssw0rd
default tablespace users
temporary tablespace temp
quota 10m on users;

grant connect to theater;
grant resource to theater;
grant create session to theater;
grant create table to theater;
grant create view to theater;

create table theater.Accounts(
userId number primary key,
uname varchar2(50) unique,
pword varchar2(50),
email varchar2(50),
fname varchar2(50),
lname varchar2(50)
);

create table theater.Movies(
movieId number primary key,
mtitle varchar2(50),
releasedate timestamp,
mgenre varchar(50),
mlength number(3,0)
);

create table theater.Halls(
hallId number primary key,
hcapacity number,
hcost number(4, 2)
);

create table theater.Showtimes(
showId number primary key,
showtimes timestamp unique
);

create table theater.MovieInfo(
infoId number primary key,
movieId number,
hallId number,
showId number,
onlineTot number,
walkTot number
);

create table theater.Transactions(
transId number primary key,
userId number,
infoId number
);

create table theater.WalkIn(
infoId number,
walkAmount number,
userId number
);

create table theater.Employee(
userId number,
reportsTo number
);

alter table theater.MovieInfo add constraint MHS_unique unique (movieId, hallId, showId);
alter table theater.MovieInfo add constraint movieId_fk foreign key (movieId) references theater.Movies(movieId);
alter table theater.MovieInfo add constraint hallId_fk foreign key (hallId) references theater.Halls(hallId);
alter table theater.MovieInfo add constraint showId_fk foreign key (showId) references theater.Showtimes(showId);

alter table theater.Transactions add constraint userId_trans_fk foreign key (userId) references theater.Accounts(userId);
alter table theater.Transactions add constraint infoId_trans_fk foreign key (infoId) references theater.MovieInfo(infoId);

alter table theater.WalkIn add constraint infoId_walk_fk foreign key (infoId) references theater.MovieInfo(infoId);
alter table theater.WalkIn add constraint userId_walk_fk foreign key (userId) references theater.Accounts(userId);

alter table theater.Employee add constraint userId_emp_fk foreign key (userId) references theater.Accounts(userId);
alter table theater.Employee add constraint manId_emp_fk foreign key (reportsTo) references theater.Accounts(userId);

insert into theater.Accounts(userId, fname, lname) values(0, 'Auto', 'System');

drop sequence theater.userId_pk;
drop sequence theater.movieId_pk;
drop sequence theater.hallId_pk;
drop sequence theater.showId_pk;
drop sequence theater.infoId_pk;
drop sequence theater.transId_pk;

create sequence theater.userId_pk;
create sequence theater.movieId_pk;
create sequence theater.hallId_pk;
create sequence theater.showId_pk;
create sequence theater.infoId_pk;
create sequence theater.transId_pk;

create or replace trigger theater.userId_pk_trig
before insert on theater.Accounts
for each row
begin 
    select userId_pk.nextVal into :new.userId from dual;
end;
/

create or replace trigger theater.movieId_pk_trig
before insert on theater.Movies
for each row
begin 
    select movieId_pk.nextVal into :new.movieId from dual;
end;
/

create or replace trigger theater.hallId_pk_trig
before insert on theater.Halls
for each row
begin 
    select hallId_pk.nextVal into :new.hallId from dual;
end;
/

create or replace trigger theater.showId_pk_trig
before insert on theater.Showtimes
for each row
begin 
    select showId_pk.nextVal into :new.showId from dual;
end;
/

create or replace trigger theater.infoId_pk_trig
before insert or update on theater.MovieInfo
for each row
begin 
    if INSERTING then
       select infoId_pk.nextVal into :new.infoId from dual;
    elsif UPDATING then
        select:old.infoId into :new.infoId from dual;
    end if;
end;
/

create or replace trigger theater.transId_pk_trig
before insert on theater.Transactions
for each row
begin 
    select transId_pk.nextVal into :new.transId from dual;
end;
/

insert into theater.Accounts(uname, pword, email, fname, lname) values('rmiller', 'mypassword', 'rmiller@fake.com', 'Robert', 'Miller');
insert into theater.Accounts(uname, pword, email, fname, lname) values('mmasters', 'apassword', 'mmasters@fake.com', 'Maggie', 'Masters');
insert into theater.Accounts(uname, pword, email, fname, lname) values('jlangly', 'thepassword', 'jlangly@fake.com', 'Jonah', 'Langly');
insert into theater.Accounts(uname, pword, email, fname, lname) values('senderson', 'endpassword', 'senderson@fake.com', 'Sandy', 'Enderson');

insert into theater.Movies(mtitle, releasedate, mgenre, mlength) values('Secret Agent: Masters', TO_TIMESTAMP('2017-08-04', 'YYYY-MM-DD'), 'Action', 126);

insert into theater.Halls(hcapacity, hcost) values(5, 13.45);
insert into theater.Halls(hcapacity, hcost) values(10, 14.58);
insert into theater.Halls(hcapacity, hcost) values(20, 16.75);

insert into theater.Showtimes(showtimes) values(TO_TIMESTAMP('12:00', 'HH24:MI'));

insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 1);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 1);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 1);

insert into theater.Employee(userId) values(1);
insert into theater.Employee(userId, reportsTo) values(2,1);
insert into theater.Employee(userId, reportsTo) values(3,1);
insert into theater.Employee(userId, reportsTo) values(4,1);

commit;



select * from theater.accounts;



insert into theater.accounts (USERID,UNAME,PWORD,EMAIL,FNAME,LNAME)
values ('1', 'roy', '123456', 'roy@gmail.com', 'saurabh' , 'kaura');
insert into theater.accounts (USERID,UNAME,PWORD,EMAIL,FNAME,LNAME)
values ('2', 'joy', '123456', 'joy@gmail.com', 'patric' , 'daniel');
insert into theater.accounts (USERID,UNAME,PWORD,EMAIL,FNAME,LNAME)
values ('3', 'toy', '123456', 'toy@gmail.com', 'binquan' , 'wang');
insert into theater.accounts (USERID,UNAME,PWORD,EMAIL,FNAME,LNAME)
values ('4', 'aoy', '123456', 'aoy@gmail.com', 'alex' , 'miranda');
insert into theater.accounts (USERID,UNAME,PWORD,EMAIL,FNAME,LNAME)
values ('5', 'soy', '123456', 'soy@gmail.com', 'richard' , 'orr');
insert into theater.accounts (USERID,UNAME,PWORD,EMAIL,FNAME,LNAME)
values ('6', 'zoy', '123456', 'zoy@gmail.com', 'garry' , 'zangiu');



select * from theater.employee;

insert into theater.employee(INFOID,WALKAMOUNT,USERID)
values ('11', '240', '21');
insert into theater.employee(INFOID,WALKAMOUNT,USERID)
values ('12', '150', '22');
insert into theater.employee(INFOID,WALKAMOUNT,USERID)
values ('13', '420', '23');
insert into theater.employee(INFOID,WALKAMOUNT,USERID)
values ('14', '360', '24');
insert into theater.employee(INFOID,WALKAMOUNT,USERID)
values ('15', '480', '25');
insert into theater.employee(INFOID,WALKAMOUNT,USERID)
values ('16', '540', '26');


select * from theater.halls;

insert into theater.halls(HALLID,HCAPACITY,HCOST)
values ('51', '5', '60.00');
insert into theater.halls(HALLID,HCAPACITY,HCOST)
values ('52', '5', '60.00');
insert into theater.halls(HALLID,HCAPACITY,HCOST)
values ('53', '5', '60.00');
insert into theater.halls(HALLID,HCAPACITY,HCOST)
values ('54', '10', '75.00');
insert into theater.halls(HALLID,HCAPACITY,HCOST)
values ('55', '10', '75.00');
insert into theater.halls(HALLID,HCAPACITY,HCOST)
values ('56', '10', '75.00');
insert into theater.halls(HALLID,HCAPACITY,HCOST)
values ('57', '20', '90.00');
insert into theater.halls(HALLID,HCAPACITY,HCOST)
values ('58', '20', '90.00');
insert into theater.halls(HALLID,HCAPACITY,HCOST)
values ('59', '20', '90.00');

select * from theater.movieinfo;

insert into theater.movieinfo(INFOID,MOVIEID,HALLID,SHOWID,ONLINETOT,WALKTOT)
values ('41', '61', '51', '71', '3','1');
insert into theater.movieinfo(INFOID,MOVIEID,HALLID,SHOWID,ONLINETOT,WALKTOT)
values ('42', '62', '52', '72', '2','3');
insert into theater.movieinfo(INFOID,MOVIEID,HALLID,SHOWID,ONLINETOT,WALKTOT)
values ('43', '63', '53', '73', '4','1');
insert into theater.movieinfo(INFOID,MOVIEID,HALLID,SHOWID,ONLINETOT,WALKTOT)
values ('44', '64', '54', '74', '6','4');
insert into theater.movieinfo(INFOID,MOVIEID,HALLID,SHOWID,ONLINETOT,WALKTOT)
values ('45', '65', '55', '75', '5','5');
insert into theater.movieinfo(INFOID,MOVIEID,HALLID,SHOWID,ONLINETOT,WALKTOT)
values ('46', '66', '56', '76', '8','1');
insert into theater.movieinfo(INFOID,MOVIEID,HALLID,SHOWID,ONLINETOT,WALKTOT)
values ('47', '67', '57', '77', '5','1');
insert into theater.movieinfo(INFOID,MOVIEID,HALLID,SHOWID,ONLINETOT,WALKTOT)
values ('48', '68', '58', '78', '15','4');
insert into theater.movieinfo(INFOID,MOVIEID,HALLID,SHOWID,ONLINETOT,WALKTOT)
values ('49', '69', '59', '79', '12','6');



select * from theater.movies;

insert into theater.movies(MOVIEID,MTITLE,RELEASEDATE,MGENRE,MLENGTH)
values ('61', 'The Emoji Movie', '28-jul-2017', 'Action', '105');
insert into theater.movies(MOVIEID,MTITLE,RELEASEDATE,MGENRE,MLENGTH)
values ('62', 'Dunkirk', '14-aug-2017', 'Action', '119');
insert into theater.movies(MOVIEID,MTITLE,RELEASEDATE,MGENRE,MLENGTH)
values ('63', 'First Kill', '15-sep-2017', 'Action', '124');
insert into theater.movies(MOVIEID,MTITLE,RELEASEDATE,MGENRE,MLENGTH)
values ('64', 'Chuck', '21-jul-2017', 'sports', '104');
insert into theater.movies(MOVIEID,MTITLE,RELEASEDATE,MGENRE,MLENGTH)
values ('65', 'Goon', '14-jul-2017', 'sports', '129');
insert into theater.movies(MOVIEID,MTITLE,RELEASEDATE,MGENRE,MLENGTH)
values ('66', 'Kidnap', '21-jul-2017', 'suspense', '102');
insert into theater.movies(MOVIEID,MTITLE,RELEASEDATE,MGENRE,MLENGTH)
values ('67', 'Once Upon a Time', '11-aug-2017', 'Sci-Fi', '114');
insert into theater.movies(MOVIEID,MTITLE,RELEASEDATE,MGENRE,MLENGTH)
values ('68', 'Good Time', '29-sep-2017', 'Crime', '136');
insert into theater.movies(MOVIEID,MTITLE,RELEASEDATE,MGENRE,MLENGTH)
values ('69', 'Bushwick', '25-aug-2017', 'Thriller', '104');

select * from theater.showtimes;


insert into theater.showtimes(SHOWID,SHOWTIMES)
values ('71','15-aug-2017');
insert into theater.showtimes(SHOWID,SHOWTIMES)
values ('72','15-aug-2017');
insert into theater.showtimes(SHOWID,SHOWTIMES)
values ('73','15-aug-2017');
insert into theater.showtimes(SHOWID,SHOWTIMES)
values ('74','15-aug-2017');
insert into theater.showtimes(SHOWID,SHOWTIMES)
values ('75','15-aug-2017');
insert into theater.showtimes(SHOWID,SHOWTIMES)
values ('76','15-aug-2017');
insert into theater.showtimes(SHOWID,SHOWTIMES)
values ('77','15-aug-2017');
insert into theater.showtimes(SHOWID,SHOWTIMES)
values ('78','15-aug-2017');
insert into theater.showtimes(SHOWID,SHOWTIMES)
values ('79','15-aug-2017');


drop table theater.showtimes;

create table theater.Showtimes(
showId number primary key,
showtimes timestamp unique
);


select * from theater.showtimes;


drop table theater.showtimes;

create table theater.Showtimes(
showId number primary key,
showtimes timestamp unique
);


insert into theater.showtimes(SHOWID,SHOWTIMES)
values ('71','15-aug-2017 10:00:00');
insert into theater.showtimes(SHOWID,SHOWTIMES)
values ('72','15-aug-2017 11:00:00');
insert into theater.showtimes(SHOWID,SHOWTIMES)
values ('73','15-aug-2017 12:00:00');
insert into theater.showtimes(SHOWID,SHOWTIMES)
values ('74','15-aug-2017 1:30:00');
insert into theater.showtimes(SHOWID,SHOWTIMES)
values ('75','15-aug-2017 02:00:00');
insert into theater.showtimes(SHOWID,SHOWTIMES)
values ('76','15-aug-2017 02:40:00');
insert into theater.showtimes(SHOWID,SHOWTIMES)
values ('77','15-aug-2017 03:30:00');
insert into theater.showtimes(SHOWID,SHOWTIMES)
values ('78','15-aug-2017 04:50:00');
insert into theater.showtimes(SHOWID,SHOWTIMES)
values ('79','15-aug-2017 06:00:00');


select * from theater.transaction;


insert into theater.transaction(TRANSID,USERID,INFOID)
values ('111', '1', '11');
insert into theater.transaction(TRANSID,USERID,INFOID)
values ('1121', '2', '12');
insert into theater.transaction(TRANSID,USERID,INFOID)
values ('113', '3', '13');
insert into theater.transaction(TRANSID,USERID,INFOID)
values ('114', '4', '14');
insert into theater.transaction(TRANSID,USERID,INFOID)
values ('115', '5', '15');
insert into theater.transaction(TRANSID,USERID,INFOID)
values ('116', '6', '16');

drop table theater.movieinfo;

create table theater.MovieInfo(
infoId number primary key,
movieId number,
hallId number,
showId number,
onlineTot number,
walkTot number
);


select * from theater.movieinfo;

insert into theater.movieinfo(INFOID,MOVIEID,HALLID,SHOWID,ONLINETOT,WALKTOT)
values ('11', '61', '51', '71', '3','1');
insert into theater.movieinfo(INFOID,MOVIEID,HALLID,SHOWID,ONLINETOT,WALKTOT)
values ('12', '62', '52', '72', '2','3');
insert into theater.movieinfo(INFOID,MOVIEID,HALLID,SHOWID,ONLINETOT,WALKTOT)
values ('13', '63', '53', '73', '4','1');
insert into theater.movieinfo(INFOID,MOVIEID,HALLID,SHOWID,ONLINETOT,WALKTOT)
values ('14', '64', '54', '74', '6','4');
insert into theater.movieinfo(INFOID,MOVIEID,HALLID,SHOWID,ONLINETOT,WALKTOT)
values ('15', '65', '55', '75', '5','5');
insert into theater.movieinfo(INFOID,MOVIEID,HALLID,SHOWID,ONLINETOT,WALKTOT)
values ('16', '66', '56', '76', '8','1');
insert into theater.movieinfo(INFOID,MOVIEID,HALLID,SHOWID,ONLINETOT,WALKTOT)
values ('17', '67', '57', '77', '5','1');
insert into theater.movieinfo(INFOID,MOVIEID,HALLID,SHOWID,ONLINETOT,WALKTOT)
values ('18', '68', '58', '78', '15','4');
insert into theater.movieinfo(INFOID,MOVIEID,HALLID,SHOWID,ONLINETOT,WALKTOT)
values ('19', '69', '59', '79', '12','6');


commit;