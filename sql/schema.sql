create SCHEMA IF NOT EXISTS city_info_schema;
set search_path = city_info_schema;



 create table "user"
 (
     id       bigserial
         constraint user_pk
             primary key,
     name     varchar(20) not null,
     surname  varchar(20) not null,
     login    varchar(30) not null,
     password varchar(30) not null
 );

create table city
(
    id      bigint
        constraint city_pk
            primary key,
    name    varchar(100) not null
);


create table description
(
    id   bigint
        constraint description_pk
            primary key,
    description varchar(1000),
    city_id bigint
        constraint city_description_id_fk
            references city
            on update cascade on delete cascade
);

alter table description
    add FOREIGN KEY (city_id)
        REFERENCES city(id) ;