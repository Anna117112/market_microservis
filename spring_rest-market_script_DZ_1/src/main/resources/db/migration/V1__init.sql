
<-- Создаем таблицы продукты,для заполнения интернет магазина
<-- Таблица юзеры чтобы отличать пользователей которые авторизовались
<-- Роли которые нужны чтобы давать доступ разным пользователям
<-- Таблица роли_юзеры  для связи (не сущьность)
<-- Заказы для того чтобы можно было оформить заказ привязка заказа к юзеру и инфо по доставке
<-- Список продуктов в заказе  чтобы знать что заказал пользователь


create table products
(
    id         bigserial primary key,
    title      varchar(255),
    price      int,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);


insert into products (title, price)
values ('Milk', 100),
       ('Bread', 80),
       ('Cheese', 90);


create table users (
  id                    bigserial,
  username              varchar(30) not null unique,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  primary key (id)
);

create table roles (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_roles (
  user_id               bigint not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

insert into roles (name)
values
('ROLE_USER'),
('ROLE_ADMIN');

insert into users (username, password, email)
values

('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(1, 2),
(2, 1);

create table orders (
    id              bigserial primary key,
    user_id         bigint not null references users (id),
    total_price     int not null,
    address         varchar(255),
    phone           varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table order_items (
    id                      bigserial primary key,
    product_id              bigint not null references products (id),
    order_id                bigint not null references orders (id),
    quantity                int not null,
    price_per_product       int not null,
    price                   int not null,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);
insert into orders (user_id, total_price, address, phone)
values (1, 200, 'address', '12345');

insert into order_items (product_id, order_id, quantity, price_per_product, price)
values (1, 1, 2, 100, 200);