CREATE EXTENSION IF NOT EXISTS vector;



CREATE TABLE criminals (
id serial not null primary key,
name varchar(50) not null,
dob TIME,
gender varchar(1),
nationality varchar(20),
image_name  varchar(20) not null,
height float,
width float,
eyes_color varchar(10),
hair_color varchar(10),
distinctive_signs text,
embedding vector(5)

)