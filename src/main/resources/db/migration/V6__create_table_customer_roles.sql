create table bookmarket.customer_roles(
    customer_id int not null,
    role varchar(50) not null,
    foreign key (customer_id) references customer(id)
)