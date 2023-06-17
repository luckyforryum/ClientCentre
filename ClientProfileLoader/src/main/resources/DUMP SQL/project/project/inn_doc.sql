create table project.inn_doc
(
    id            varchar(255) not null
        primary key,
    birthdate     date,
    birthplace    varchar(255),
    department    varchar(255),
    division      varchar(255),
    gender        varchar(255),
    inn           varchar(255),
    issued        date,
    issuedby      varchar(255),
    name          varchar(255),
    patronymic    varchar(255),
    receipt_date  date,
    surname       varchar(255),
    validate_date date,
    document_id   varchar(255)
        constraint fkrxe0s2akikaf0phc9y1t9ufe0
            references micro.documents
);

alter table project.inn_doc
    owner to postgres;

