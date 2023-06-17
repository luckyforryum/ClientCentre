create table project.categories
(
    id                 varchar(255) not null
        primary key,
    category           varchar(255),
    driving_license_id varchar(255)
        constraint fk5i0wprrhpqwb13qkk7vq0kv0f
            references micro.rf_driving_license
);

alter table project.categories
    owner to postgres;

