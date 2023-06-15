create table project.rf_driving_license
(
    id            varchar(255) not null
        primary key,
    birthdate     date,
    birthplace    varchar(255),
    country       varchar(255),
    department    varchar(255),
    experience    varchar(255),
    expirydate    date,
    gender        varchar(255),
    legal_force   boolean,
    issued_date   date,
    issued_by     varchar(255),
    message       varchar(255),
    name          varchar(255),
    number        varchar(255) not null,
    patronymic    varchar(255),
    receipt_date  date,
    series        varchar(255) not null,
    surname       varchar(255),
    validate_date date,
    document_id   varchar(255)
        constraint fk52e5qedyv4wvop6ufy9veld6b
            references project.documents
);

alter table project.rf_driving_license
    owner to postgres;

