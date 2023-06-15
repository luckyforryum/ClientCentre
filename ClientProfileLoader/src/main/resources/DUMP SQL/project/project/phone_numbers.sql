create table project.phone_numbers
(
    id         varchar(255) not null
        primary key,
    phone      varchar(255),
    contact_id varchar(255)
        constraint fkn6bfgcalpl4vg4ibytnhnggy1
            references project.contact_medium
);

alter table project.phone_numbers
    owner to postgres;

