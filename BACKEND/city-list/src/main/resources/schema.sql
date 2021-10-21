CREATE TABLE City(
    id integer identity PRIMARY KEY not null,
    name varchar not null,
    photo varchar not null
)
as SELECT * FROM CSVREAD('classpath:csv/cities.csv');
