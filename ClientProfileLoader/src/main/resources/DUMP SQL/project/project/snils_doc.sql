create table project.snils_doc
(
    id            varchar(255) not null
        primary key,
    issued_date   date,
    receipt_date  date,
    snils         varchar(255),
    validate_date date,
    document_id   varchar(255)
        constraint fkapep586mhopp92dp8u9mvr15c
            references micro.documents
);

alter table project.snils_doc
    owner to postgres;

