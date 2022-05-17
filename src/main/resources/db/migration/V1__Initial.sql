create sequence hibernate_sequence start with 1 increment by 1;
create table account (id integer not null, balance decimal(19,2) not null, number varchar(255), birth_date timestamp, created_date timestamp, customer_id integer not null, email varchar(255), initial_credit decimal(19,2) not null, status integer, account_type_id bigint, primary key (id));
create table account_type (id bigint not null, enum_account_type varchar(255), min_balance decimal(19,2), primary key (id));
create table current_account (cheque_number varchar(255), id integer not null, primary key (id));
create table customer (id integer not null, birth_date timestamp, email varchar(255), name varchar(255), surname varchar(255), primary key (id));
create table saving_account (interest_rate double, withdraw_limit decimal(19,2), id integer not null, primary key (id));
create table transaction (dtype varchar(31) not null, id integer not null, account_id integer not null, description varchar(255), final_amount decimal(19,2) not null, posting_date timestamp, status integer not null, transaction_amount decimal(19,2) not null, commission double, primary key (id));
alter table account add constraint FKgw84mgpacw9htdxcs2j1p7u6j foreign key (account_type_id) references account_type;
alter table current_account add constraint FKdgjksjldi2tyr8aomurd95s1t foreign key (id) references account;
alter table saving_account add constraint FKiur0263vv3myj6kwk0fa2j3fp foreign key (id) references account;



INSERT INTO ACCOUNT_TYPE ( ID, ENUM_ACCOUNT_TYPE , MIN_BALANCE ) VALUES ( 1, 'CurrentAccount' , 200);
INSERT INTO ACCOUNT_TYPE ( ID, ENUM_ACCOUNT_TYPE , MIN_BALANCE ) VALUES ( 2, 'SavingAccount' , 100);
INSERT INTO CUSTOMER (ID,NAME ,SURNAME ,EMAIL ,BIRTH_DATE )  VALUES (1,'Arman', 'Heydarian', 'arman.heydarian@gmail.com', '1991-09-21');
INSERT INTO CUSTOMER (ID,NAME ,SURNAME ,EMAIL ,BIRTH_DATE )  VALUES (2,'John', 'Wilson ', 'John@gmail.com', '1981-02-11');
INSERT INTO CUSTOMER (ID,NAME ,SURNAME ,EMAIL ,BIRTH_DATE )  VALUES (3,'Mike', 'Anderson', 'Mike.Anderson@gmail.com', '1993-03-24');
