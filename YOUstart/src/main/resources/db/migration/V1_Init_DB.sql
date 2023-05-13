create table message (
  id bigint not null,
  filename varchar(255),
  tag varchar(255),
  text varchar(240) not null,
  user_id bigint,
  primary key (id)
) engine = InnoDB create table message_seq (next_val bigint) engine = InnoDB insert into message_seq
values
  (1) create table user_role (
    user_id bigint not null,
    roles varchar(255)
  ) engine = InnoDB create table usr (
    id bigint not null,
    active bit not null,
    password varchar(255) not null,
    username varchar(255)  not null,
    primary key (id)
  ) engine = InnoDB create table usr_seq (next_val bigint) engine = InnoDB insert into usr_seq
values
  (1)
alter table
  message
add
  constraint message_usr_fk foreign key (user_id) references usr (id)
alter table
  user_role
add
  constraint user_role_usr_fk foreign key (user_id) references usr (id)
