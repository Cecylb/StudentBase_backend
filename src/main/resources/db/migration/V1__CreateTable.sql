create table if not exists student(
    id bigint not null,
    surname varchar(255) not null,
    name varchar(255) not null,
    patronymic varchar(255) default null,
    student_group int8  not null,
    birthday date default null,
    primary key(id)
);