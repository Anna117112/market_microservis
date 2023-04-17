create table Cart
(
        id   bigserial primary key,
        title      varchar(255),
        quantity      int,
        created_at timestamp default current_timestamp,
        updated_at timestamp default current_timestamp
    );


        insert into Cart (title, quantity)
        values ('Milk', 1),
               ('Bread', 1),
               ('Cheese', 2),
               ('Cheese2', 1),
               ('Cheese3', 1),
               ('Cheese4', 1);








