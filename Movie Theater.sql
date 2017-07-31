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

commit;
