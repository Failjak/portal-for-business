drop table if exists "users";
create table "users"
(
    id         SERIAL,
    username   varchar UNIQUE NOT NULL,
    password   varchar        NOT NULL,
    role       varchar        not null default 1,
    photo      varchar,
    "card_id"  integer        references card (id) on delete set null,
    created_at timestamp               default current_timestamp,
    updated_at timestamp               default current_timestamp,
    is_active  boolean                 default True,
    primary key (id)
);

drop table if exists "users_product";
create table "users_product"
(
    id         SERIAL,
    user_id    integer references users (id) on delete cascade,
    product_id integer references product (id) on delete cascade,
    primary key (id)
--     constraint users_product_pkey primary key (user_id, product_id)
);

drop table if exists "seller";
create table "seller"
(
    id         SERIAL,
    username   varchar unique not null,
    password   varchar        not null,
    store_name varchar unique not null,
--     created_at timestamp default current_timestamp,
--     updated_at timestamp default current_timestamp,
    primary key (id)
);

drop table if exists "product";
create table "product"
(
    id          serial,
    name        varchar not null,
    price       decimal,
    currency    varchar not null default 'USD',
    seller_id   integer references seller (id) on delete cascade,
    description text,
    image       varchar,
    type        varchar not null,
--     created_at timestamp default current_timestamp,
--     updated_at timestamp default current_timestamp,
    unique (name, seller_id, price),
    primary key (id)
);

-- select p.id, avg(m.evaluate) as avgMark from "product" as p join "comment" as c on p.id = c.product_id
-- join "mark" as m on c.id = m.comment_id group by p.id;

drop table if exists "comment";
create table "comment"
(
    id         serial,
    comment    varchar not null,
    user_id    integer references users (id) on delete cascade,
    product_id integer references product (id) on delete set null,
    created_at timestamptz default current_timestamp,
    primary key (id)
);

drop table if exists "mark";
create table "mark"
(
    id           serial,
    comment_id   integer references comment (id) on delete cascade,
    parameter_id integer references parameter (id) on delete cascade,
    evaluate     integer not null check ( evaluate between 0 and 5),

    primary key (id)
);

drop table if exists "parameter";
create table "parameter"
(
    id           serial,
    product_type varchar not null, -- it, foodstuff, clothes or perfumery
    name         varchar not null,
    is_active    boolean default True,
    primary key (id)
);

drop table if exists "card";
create table "card"
(
    id      serial,
    ccn     varchar(20) not null,
    cvv     varchar(3)  not null,
    year    varchar(4)  not null,
    month   varchar(10) not null,
    balance decimal     not null,
    primary key (id)
);


