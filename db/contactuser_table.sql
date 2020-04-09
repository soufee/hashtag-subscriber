create table contactuser
(
    id serial not null
        constraint contactuser_pkey
            primary key,
    admin boolean,
    chat_id bigint not null
        constraint uk_g517xdmpkse0hh31sw5bd7o5s
            unique,
    first_name varchar(255),
    isbot boolean,
    language_code varchar(255),
    last_name varchar(255),
    user_name varchar(255)
);

alter table contactuser owner to postgres;

