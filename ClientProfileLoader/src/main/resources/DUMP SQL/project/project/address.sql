create table project.address
(
    id                   varchar(255) not null
        primary key,
    addition_area_street varchar(255),
    address_name         varchar(255),
    building             varchar(255),
    city                 varchar(255),
    city_type            varchar(255),
    country              varchar(255),
    house                varchar(255),
    housing              varchar(255),
    province             varchar(255),
    province_type        varchar(255),
    region               varchar(255),
    region_type          varchar(255),
    settlement           varchar(255),
    settlement_type      varchar(255),
    street               varchar(255),
    street_type          varchar(255),
    zipcode              varchar(255),
    individual_id        varchar(255)
        constraint fkn4ev0lritti7kpot4129w4wl
            references micro.individual
);

alter table project.address
    owner to postgres;

