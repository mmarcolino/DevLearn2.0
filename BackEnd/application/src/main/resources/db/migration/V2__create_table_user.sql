create table users(
  id bigint not null,
  username varchar (15) not null,
  password varchar (25) not null,
  name varchar (50) not null,
  role_id bigint not null,
  primary key (id),
  foreign key (role_id) REFERENCES role(id)
);