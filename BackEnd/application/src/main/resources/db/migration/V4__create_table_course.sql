CREATE TABLE course(
  id bigint not null,
  name varchar(50) not null,
  description varchar (300) not null,
  author_id bigint not null,
  category_id bigint not null,
  primary key (id),
  foreign key (author_id) REFERENCES users(id),
  foreign key (category_id) REFERENCES category(id)
);