create table users(
                      id BIGSERIAL PRIMARY KEY ,
                      username varchar(65) unique not null ,
                      email varchar(65) ,
                      password varchar (65) not null,
                      active boolean
);
