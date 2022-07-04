CREATE TABLE video(
  id bigint not null,
  name varchar(50) not null,
  description varchar (300) not null,
  date varchar(12) not null,
  url varchar (300) not null,
  course_id bigint not null,
  primary key (id),
  foreign key (course_id) REFERENCES course(id)
);