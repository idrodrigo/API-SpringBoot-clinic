create table patient
(
        id bigint not null auto_increment,
        name varchar(100) not null,
        email varchar(100) not null unique,
        dni varchar(14) not null unique,
        phone varchar(20) not null,
        address varchar(255)  not null,
        district varchar(255)  not null,
        city varchar(255)  not null,
        number varchar(255)  not null,
        complement varchar(255)  not null,
        primary key(id)
);