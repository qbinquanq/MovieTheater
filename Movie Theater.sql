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
showtime timestamp unique
);

create table theater.MovieInfo(
infoId number primary key,
movieId number,
hallId number,
showId number,
wdId number,
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

alter table theater.MovieInfo add constraint MHS_unique unique (hallId, showId);
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
insert into theater.Movies(mtitle, releasedate, mgenre, mlength) values('Dark Tower', TO_TIMESTAMP('2017-08-04', 'YYYY-MM-DD'), 'Thriller-Action', 140);
insert into theater.Movies(mtitle, releasedate, mgenre, mlength) values('Funny Guy: Gal', TO_TIMESTAMP('2017-08-02', 'YYYY-MM-DD'), 'Comedy', 160);

insert into theater.Halls(hcapacity, hcost) values(5, 13.45);
insert into theater.Halls(hcapacity, hcost) values(10, 14.58);
insert into theater.Halls(hcapacity, hcost) values(20, 16.75);

insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-04 12:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-04 15:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-04 18:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-04 21:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-05 12:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-05 15:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-05 18:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-05 21:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-06 12:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-06 15:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-06 18:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-06 21:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-07 12:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-07 15:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-07 18:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-07 21:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-08 12:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-08 15:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-08 18:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-08 21:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-09 12:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-09 15:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-09 18:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-09 21:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-10 12:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-10 15:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-10 18:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-10 21:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-11 12:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-11 15:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-11 18:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-11 21:00', 'YYYY-MM-DD HH24:MI'));

--08/04/17
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 1);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 1);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 1);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 2);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 2);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 2);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 3);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 3);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 3);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 4);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 4);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 4);
--08/05/17
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 5);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 5);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 5);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 6);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 6);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 6);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 7);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 7);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 7);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 8);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 8);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 8);
--08/06/17
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 9);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 9);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 9);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 10);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 10);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 10);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 11);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 11);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 11);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 12);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 12);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 12);
--08/07/17
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 13);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 13);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 13);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 14);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 14);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 14);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 15);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 15);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 15);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 16);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 16);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 16);
--08/08/17
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 17);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 17);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 17);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 18);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 18);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 18);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 19);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 19);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 19);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 20);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 20);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 20);
--08/09/17
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 21);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 21);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 21);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 22);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 22);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 22);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 23);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 23);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 23);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 24);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 24);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 24);
--08/10/17
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 25);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 25);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 25);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 26);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 26);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 26);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 27);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 27);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 27);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 28);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 28);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 28);
--08/11/17
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 29);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 29);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 29);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 30);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 30);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 30);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 31);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 31);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 31);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 1, 32);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 2, 32);
insert into theater.MovieInfo(movieId, hallId, showId) values(1, 3, 32);

insert into theater.Employee(userId) values(1);
insert into theater.Employee(userId, reportsTo) values(2,1);
insert into theater.Employee(userId, reportsTo) values(3,1);
insert into theater.Employee(userId, reportsTo) values(4,1);

commit;