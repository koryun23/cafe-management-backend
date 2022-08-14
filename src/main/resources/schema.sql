create sequence cafe_table_sequence start 1 increment 1
create sequence cafe_table_waiter_id_sequence start 1 increment 1
create sequence order_id_sequence start 1 increment 1
create sequence product_id_sequence start 1 increment 1
create sequence product_in_order_sequence start 1 increment 1
create sequence user_id_sequence start 1 increment 1
create sequence user_role_id_sequence start 1 increment 1
create table cafe_table_waiter (assigned_at timestamp not null, cafe_table_id int8 not null, waiter_id int8 not null, serial primary key (id))
create table orders (created_at timestamp not null, status varchar(20) not null, cafe_table_id int8 not null, waiter_id int8 not null, serial primary key (id))
create table product (amount int4 not null, product_name varchar(70) not null, price int4 not null, registered_at timestamp not null, serial primary key (id))
create table product_in_order (amount int4 not null, status varchar(20) not null, registered_at timestamp not null, order_id int8 not null, product_id int8 not null, serial primary key (id))
create table tables (status varchar(20) not null, code varchar(8) not null, seats int4 not null, serial primary key (id))
create table user_role (user_role_type varchar(20) not null, user_id int8 not null, serial primary key (id))
create table users (created_at timestamp not null, first_name varchar(40) not null, password varchar(255), second_name varchar(40) not null, username varchar(30) not null, serial primary key (id))
alter table if exists product add constraint UK_383i0awxqlq7pc33hil7afbgo unique (product_name)
alter table if exists tables add constraint UK_gyggaqpns8uq24mxun2ta2x7c unique (code)
alter table if exists cafe_table_waiter add constraint FK_CAFE_TABLE_ID_CAFE_TABLE_ID foreign key (cafe_table_id) references tables
alter table if exists cafe_table_waiter add constraint FK_WAITER_ID_USER_ID foreign key (waiter_id) references users
alter table if exists orders add constraint ORDER_CAFE_TABLE_ID foreign key (cafe_table_id) references tables
alter table if exists orders add constraint ORDER_USER_ID foreign key (waiter_id) references users
alter table if exists product_in_order add constraint PRODUCT_IN_ORDER_ORDER_ORDER_ID foreign key (order_id) references orders
alter table if exists product_in_order add constraint PRODUCT_IN_ORDER_PRODUCT_PRODUCT_ID foreign key (product_id) references product
alter table if exists user_role add constraint USER_ROLE_USER_USER_ID foreign key (user_id) references users