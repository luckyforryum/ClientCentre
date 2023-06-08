create table project.individual
(
    id               varchar(255) not null
        primary key,
    birth_date       date,
    country_of_birth varchar(255),
    full_name        varchar(255),
    gender           varchar(255),
    icp              varchar(255),
    name             varchar(255),
    patronymic       varchar(255),
    place_of_birth   varchar(255),
    surname          varchar(255),
    avatar_id        varchar(255)
        constraint fk495y6vtm1fcju11v2ast8dg0w
            references project.avatars,
    contacts_id      varchar(255)
        constraint fkq68mnj7kvwjhx53ytugm5vnv9
            references project.contact_medium,
    documents_id     varchar(255)
        constraint fkfw6ukxf9mrmqpqda5tyn4xhmx
            references project.documents
);

alter table project.individual
    owner to postgres;

