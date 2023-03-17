create table car
(
    id                  bigint   not null,
    doors               smallint not null,
    fuel                varchar(20),
    gear                varchar(20),
    kilometres          integer  not null,
    model               varchar(20),
    name                varchar(50),
    registration_number integer  not null,
    seats               smallint not null,
    dealer_id           bigint   not null,
    primary key (id)
);

create table customer
(
    id         bigint      not null,
    email      varchar(50) not null,
    first_name varchar(16),
    last_name  varchar(16),
    primary key (id)
);

create table dealer
(
    id         bigint      not null,
    email      varchar(50) not null,
    first_name varchar(16),
    last_name  varchar(16),
    primary key (id)
);


create table transaction
(
    id          bigint not null,
    date        datetime(6),
    car_id      bigint,
    customer_id bigint,
    dealer_id   bigint,
    primary key (id)
);

create table hibernate_sequence
(
    next_val bigint
);

