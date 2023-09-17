
create table consultation
(
    id bigint  not null auto_increment,
    `date` date  not null,
    patient_id bigint  not null,
    doctor_id bigint  not null,
    primary key (id),
    constraint fk_consultation_patient_id foreign key (patient_id) references patient (id),
    constraint fk_consultation_doctor_id foreign key (doctor_id) references doctor (id)
);