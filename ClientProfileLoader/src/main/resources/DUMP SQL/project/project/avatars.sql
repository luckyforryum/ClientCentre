create table project.avatars
(
    id         varchar(255) not null
        primary key,
    image_data bytea
);

alter table project.avatars
    owner to postgres;

