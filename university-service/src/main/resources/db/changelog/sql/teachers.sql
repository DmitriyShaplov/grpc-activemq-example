create sequence teachers_seq increment 50;

create table teachers
(
    id         bigint primary key default nextval('teachers_seq' :: regclass),
    full_name  varchar(255) not null,
    department varchar(255) not null
);