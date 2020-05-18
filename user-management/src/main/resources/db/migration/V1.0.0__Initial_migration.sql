create table user_management.user
(
    id            char(36)     not null,
    city          varchar(255) not null,
    country       varchar(255) not null,
    date_of_birth date         not null check (date_of_birth < now()),
    email         varchar(255) not null,
    first_name    varchar(255) not null,
    gender        varchar(255) not null check (gender = 'Male' OR gender = 'Female'),
    height_cm     int2         not null check (height_cm >= 1 AND height_cm <= 300),
    last_name     varchar(255) not null,
    user_name     varchar(255) not null unique,
    weight_kg     int2         not null check (weight_kg >= 1 AND weight_kg <= 500),
    primary key (id)
)