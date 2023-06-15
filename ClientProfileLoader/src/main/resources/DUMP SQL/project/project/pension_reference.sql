create table project.pension_reference
(
    id            varchar(255) not null
        primary key,
    birth_date    varchar(255),
    birth_place   varchar(255),
    department    varchar(255),
    doc_version   varchar(255),
    gender        varchar(255),
    issued_date   date,
    name          varchar(255),
    patronymic    varchar(255),
    receipt_date  date,
    surname       varchar(255),
    validate_date date,
    document_id   varchar(255)
        constraint fkf114xgakuutf1f2xd905rijnb
            references project.documents
);

alter table project.pension_reference
    owner to postgres;

