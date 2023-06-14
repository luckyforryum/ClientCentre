create table project.death_cert
(
    id          varchar(255) not null
        primary key,
    department  varchar(255),
    date        date,
    issued_date date         not null,
    message     varchar(255),
    number      varchar(255) not null,
    series      varchar(255) not null,
    document_id varchar(255)
        constraint fk14r36up8fkh829xo4ybwc98m5
            references micro.documents
);

alter table project.death_cert
    owner to postgres;

