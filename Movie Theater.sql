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
onlineTot number,
walkTot number
);

create table theater.Transactions(
transId number primary key,
userId number,
infoId number,
requestRet number(1,0)
);

create table theater.WalkIn(
walkId number primary key,
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
alter table theater.WalkIn add constraint emp_movie_unique unique (infoId, userId);

alter table theater.Employee add constraint userId_emp_fk foreign key (userId) references theater.Accounts(userId);
alter table theater.Employee add constraint manId_emp_fk foreign key (reportsTo) references theater.Accounts(userId);

insert into theater.Accounts(userId, fname, lname) values(0, 'Auto', 'System');

drop sequence theater.userId_pk;
drop sequence theater.movieId_pk;
drop sequence theater.hallId_pk;
drop sequence theater.showId_pk;
drop sequence theater.infoId_pk;
drop sequence theater.transId_pk;
drop sequence theater.walkId_pk;

create sequence theater.userId_pk;
create sequence theater.movieId_pk;
create sequence theater.hallId_pk;
create sequence theater.showId_pk;
create sequence theater.infoId_pk;
create sequence theater.transId_pk;
create sequence theater.walkId_pk;

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

create or replace trigger theater.walkId_pk_trig
before insert on theater.WalkIn
for each row
begin 
    select walkId_pk.nextVal into :new.walkId from dual;
end;
/

create or replace trigger theater.trans_info_trig
after insert or delete on theater.Transactions
for each row
begin
    if INSERTING then
        update theater.MovieInfo set onlineTot=(onlineTot+1) where (select infoId from theater.Transactions where :old.transId = :old.transid)=infoId;
    elsif DELETING then
        update theater.MovieInfo set onlineTot=(onlineTot-1) where (select infoId from theater.Transactions where :old.transId = :old.transid)=infoId;
    end if;
end;
/

create or replace trigger theater.walk_info_trig
after insert on theater.WalkIn
for each row
begin
        update theater.MovieInfo set walkTot=(walkTot+(select walkAmount from theater.WalkIn where :old.userId = :old.userid)) where (select infoId from theater.WalkIn where :old.userId = :old.userid)=infoId;
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
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-12 12:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-12 15:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-12 18:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-12 21:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-13 12:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-13 15:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-13 18:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-13 21:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-14 12:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-14 15:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-14 18:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-14 21:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-15 12:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-15 15:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-15 18:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-15 21:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-16 12:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-16 15:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-16 18:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-16 21:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-17 12:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-17 15:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-17 18:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-17 21:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-18 12:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-18 15:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-18 18:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-18 21:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-19 12:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-19 15:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-19 18:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-19 21:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-20 12:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-20 15:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-20 18:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-20 21:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-21 12:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-21 15:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-21 18:00', 'YYYY-MM-DD HH24:MI'));
insert into theater.Showtimes(showtime) values(TO_TIMESTAMP('2017-08-21 21:00', 'YYYY-MM-DD HH24:MI'));

--08/04/17
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 1, 1, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 2, 1, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 3, 1, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 1, 2, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 2, 2, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 3, 2, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 1, 3, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 2, 3, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 3, 3, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 1, 4, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 2, 4, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 3, 4, 0, 0);
--08/05/17
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 1, 5, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 2, 5, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 3, 5, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 1, 6, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 2, 6, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 3, 6, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 1, 7, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 2, 7, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 3, 7, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 1, 8, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 2, 8, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 3, 8, 0, 0);
--08/06/17
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 1, 9, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 2, 9, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 3, 9, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 1, 10, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 2, 10, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 3, 10, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 1, 11, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 2, 11, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 3, 11, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 1, 12, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 2, 12, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 3, 12, 0, 0);
--08/07/17
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 1, 13, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 2, 13, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 3, 13, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 1, 14, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 2, 14, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 3, 14, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 1, 15, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 2, 15, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 3, 15, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 1, 16, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 2, 16, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 3, 16, 0, 0);
--08/08/17
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 1, 17, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 2, 17, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 3, 17, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 1, 18, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 2, 18, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 3, 18, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 1, 19, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 2, 19, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 3, 19, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 1, 20, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 2, 20, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 3, 20, 0, 0);
--08/09/17
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 1, 21, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 2, 21, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 3, 21, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 1, 22, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 2, 22, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 3, 22, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 1, 23, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 2, 23, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 3, 23, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 1, 24, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 2, 24, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 3, 24, 0, 0);
--08/10/17
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 1, 25, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 2, 25, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 3, 25, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 1, 26, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 2, 26, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 3, 26, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 1, 27, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 2, 27, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 3, 27, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 1, 28, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 2, 28, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 3, 28, 0, 0);
--08/11/17
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 1, 29, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 2, 29, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 3, 29, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 1, 30, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 2, 30, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 3, 30, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 1, 31, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 2, 31, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 3, 31, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(1, 1, 32, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(3, 2, 32, 0, 0);
insert into theater.MovieInfo(movieId, hallId, showId, onlineTot, walkTot) values(2, 3, 32, 0, 0);

insert into theater.Employee(userId, reportsTo) values(1, 0);
insert into theater.Employee(userId, reportsTo) values(2,1);
insert into theater.Employee(userId, reportsTo) values(3,1);
insert into theater.Employee(userId, reportsTo) values(4,1);

insert into theater.WalkIn(infoId, walkAmount, userId) values (1, 5, 2);
insert into theater.WalkIn(infoId, walkAmount, userId) values (2, 8, 2);
insert into theater.WalkIn(infoId, walkAmount, userId) values (3, 12, 3);
insert into theater.WalkIn(infoId, walkAmount, userId) values (4, 2, 2);

commit;