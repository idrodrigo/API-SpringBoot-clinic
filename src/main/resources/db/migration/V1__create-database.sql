--create table doctor
--(
--    id bigint  not null auto_increment,
--    name varchar(255)  not null,
--    email varchar(255)  not null unique,
--    document varchar(255)  not null unique,
--    specialization varchar(255)  not null,
--    address varchar(255)  not null,
--    district varchar(255)  not null,
--    city varchar(255)  not null,
--    number varchar(255)  not null,
--    complement varchar(255)  not null,
--    phone varchar(255)  not null,
--    is_active TINYINT,
--    primary key (id)
--);

--create table patient
--(
--    id bigint not null auto_increment,
--    name varchar(100) not null,
--    email varchar(100) not null unique,
--    dni varchar(14) not null unique,
--    phone varchar(20) not null,
--    address varchar(255)  not null,
--    district varchar(255)  not null,
--    city varchar(255)  not null,
--    number varchar(255)  not null,
--    complement varchar(255)  not null,
--    is_active TINYINT,
--    primary key(id)
--);
--create table `user`
--(
--    id bigint  not null auto_increment,
--    login varchar(100)  not null,
--    password varchar(300) not null,
--    primary key (id)
--);
--create table consultation
--(
--    id bigint  not null auto_increment,
--    `date` datetime  not null,
--    patient_id bigint  not null,
--    doctor_id bigint  not null,
--    cancellation_reason varchar(100),
--    primary key (id),
--    constraint fk_consultation_patient_id foreign key (patient_id) references patient (id),
--    constraint fk_consultation_doctor_id foreign key (doctor_id) references doctor (id)
--);

CREATE TABLE doctor
(
    id bigserial NOT NULL,
    name varchar(255) NOT NULL,
    email varchar(255) NOT NULL UNIQUE,
    document varchar(255) NOT NULL UNIQUE,
    specialization varchar(255) NOT NULL,
    address varchar(255) NOT NULL,
    district varchar(255) NOT NULL,
    city varchar(255) NOT NULL,
    number varchar(255) NOT NULL,
    complement varchar(255) NOT NULL,
    phone varchar(255) NOT NULL,
    is_active smallint,
    PRIMARY KEY (id)
);

CREATE TABLE patient
(
    id bigserial NOT NULL,
    name varchar(100) NOT NULL,
    email varchar(100) NOT NULL UNIQUE,
    dni varchar(14) NOT NULL UNIQUE,
    phone varchar(20) NOT NULL,
    address varchar(255) NOT NULL,
    district varchar(255) NOT NULL,
    city varchar(255) NOT NULL,
    number varchar(255) NOT NULL,
    complement varchar(255) NOT NULL,
    is_active smallint,
    PRIMARY KEY (id)
);

CREATE TABLE users
(
    id bigserial,
    login varchar(100) NOT NULL,
    password varchar(300) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE consultation
(
    id bigserial NOT NULL,
    "date" timestamp NOT NULL,
    patient_id bigint NOT NULL,
    doctor_id bigint NOT NULL,
    cancellation_reason varchar(100),
    PRIMARY KEY (id),
    CONSTRAINT fk_consultation_patient_id FOREIGN KEY (patient_id) REFERENCES patient (id),
    CONSTRAINT fk_consultation_doctor_id FOREIGN KEY (doctor_id) REFERENCES doctor (id)
);