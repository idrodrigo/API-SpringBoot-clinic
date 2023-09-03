create table `user`
(
    id bigint  not null auto_increment,
    login varchar(100)  not null,
    sKey varchar(300)  not null,
    primary key (id)
);