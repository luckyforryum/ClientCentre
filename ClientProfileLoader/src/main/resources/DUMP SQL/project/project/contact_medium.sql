create table project.contact_medium
(
    id            varchar(255) not null
        primary key,
    individual_id varchar(255)
        constraint fknse6dyox2ndxq0bgkif959wxq
            references micro.individual
);

alter table project.contact_medium
    owner to postgres;

