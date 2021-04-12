create sequence students_seq increment 50;

create table students
(
    id        bigint primary key default nextval('students_seq' :: regclass),
    full_name varchar(255) not null,
    specialty varchar(255) not null,
    course    varchar(255) not null
);