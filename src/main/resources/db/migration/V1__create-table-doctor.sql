--    private Long id;
--    private String name;
--    private String email;
--    private String document;
--    private Specialization specialization;
--    @Embedded
--    private Location location;
--    private String address;
--    private String district;
--    private String city;
--    private String number;
--    private String complement;

create table doctor
(
    id bigint  not null auto_increment,
    name varchar(255)  not null,
    email varchar(255)  not null unique,
    document varchar(255)  not null unique,
    specialization varchar(255)  not null,
    address varchar(255)  not null,
    district varchar(255)  not null,
    city varchar(255)  not null,
    number varchar(255)  not null,
    complement varchar(255)  not null,
    primary key (id)
);