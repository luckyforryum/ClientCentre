create table project.emails
(
    id         varchar(255) not null
        primary key,
    email      varchar(255),
    contact_id varchar(255)
        constraint fkbmugwfh1iuxu489i9oefgnal0
            references micro.contact_medium
);

alter table project.emails
    owner to postgres;

