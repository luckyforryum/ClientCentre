create table project.frgn_passport
(
    id              varchar(255) not null
        primary key,
    birthdate       date,
    birthplace      varchar(255),
    department      varchar(255),
    division        varchar(255),
    expiry_date     date,
    gender          varchar(255),
    legal_force     boolean,
    issued_date     date,
    issuedby        varchar(255),
    latin_name      varchar(255),
    latin_surname   varchar(255),
    message         varchar(255),
    name            varchar(255),
    number          varchar(255) not null,
    passport_status varchar(255),
    patronymic      varchar(255),
    receipt_date    date,
    series          varchar(255) not null,
    surname         varchar(255),
    validate_date   date,
    document_id     varchar(255)
        constraint fk5jcfh87tls0oa053rpr9vlp2r
            references micro.documents
);

alter table project.frgn_passport
    owner to postgres;

