create table project.documents
(
    id                     varchar(255) not null
        primary key,
    deathcertdocs_id       varchar(255)
        constraint fk24snsnb7iiixx6edmw8wrhmbp
            references micro.death_cert,
    individual_id          varchar(255)
        constraint fk959pj4fn35c0hrhr2pb49eciv
            references micro.individual,
    inndoc_id              varchar(255)
        constraint fkceqn6u8qjyqadi991kvdej5x3
            references micro.inn_doc,
    pensionreferencedoc_id varchar(255)
        constraint fkm2gapas655x3n8pjc374f0ug6
            references micro.pension_reference,
    snilsdoc_id            varchar(255)
        constraint fkke3kjsryxpl0x97nqsf82k8lx
            references micro.snils_doc
);

alter table project.documents
    owner to postgres;

