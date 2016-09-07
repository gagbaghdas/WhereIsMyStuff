drop table if exists users;
drop table if exists questions;
drop table if exists answers;
drop table if exists categories;

create table users (id integer primary key autoincrement, user_name text, email text, password text,user_pic_url text);
create table questions (question_id integer primary key autoincrement, question text, user_id integer, categoryId integer, title text, publish_date text);
create table answers (answer_id integer primary key autoincrement, question_id integer, answer text, user_id integer, publish_date text);
create table categories (id integer primary key autoincrement, name text);


insert into users(id, user_name, email, password, user_pic_url) values(1,"Matthew","Matthew@gmail.com","e6a5ba0842a531163425d66839569a68","resources/images/matthew.png");
insert into users(id, user_name, email, password, user_pic_url) values(2,"Sam","Sam@gmail.com","332532dcfaa1cbf61e2a266bd723612c","resources/images/sam.png");
insert into users(id, user_name, email, password, user_pic_url) values(3,"Chester","Chester@gmail.com","365816905f5e9c148e20273719fe163d","resources/images/chester,png");

insert into questions(question_id, question, user_id, categoryId, title, publish_date) values(1,"Is there anyone who seen my watch?", 1,1,"Where Is My Watch?","2016-08-07");
insert into questions(question_id, question, user_id, categoryId, title, publish_date) values(2,"Is there anyone who seen my T-shirt?", 2,2,"Where Is My T-shirt?","2016-09-04");
insert into questions(question_id, question, user_id, categoryId, title, publish_date) values(3,"Is there anyone who seen my Hat?", 2,2,"Where Is My Hat?","2016-08-12");
insert into questions(question_id, question, user_id, categoryId, title, publish_date) values(4,"Is there anyone who seen my Stuff?", 3,2,"Where Is My Stuff?","2016-07-07");
insert into questions(question_id, question, user_id, categoryId, title, publish_date) values(5,"Is there anyone who seen my TV?", 3,1,"Where Is My TV?","2015-01-17");
insert into questions(question_id, question, user_id, categoryId, title, publish_date) values(6,"Is there anyone who seen my Shorts?", 3,2,"Where Is My Shorts?","2014-11-27");
insert into questions(question_id, question, user_id, categoryId, title, publish_date) values(7,"Is there anyone who seen my Shoes?", 1,2,"Where Is My Shoes?","2013-09-05");


insert into categories(id, name) values (1, "electronics");
insert into categories(id, name) values (2, "clothes");

insert into answers(answer_id, question_id, answer, user_id, publish_date) values(1, 1,"I know where is it!",2,"2016-08-07");
insert into answers(answer_id, question_id, answer, user_id, publish_date) values(2, 1,"I know where is it!",3,"2016-08-22");
insert into answers(answer_id, question_id, answer, user_id, publish_date) values(3, 2,"I know where is it!",1,"20016-09-07");
insert into answers(answer_id, question_id, answer, user_id, publish_date) values(4, 3,"I know where is it!",1,"2016-08-22");
insert into answers(answer_id, question_id, answer, user_id, publish_date) values(5, 2,"I know where is it!",3,"2016-09-07");
insert into answers(answer_id, question_id, answer, user_id, publish_date) values(6, 3,"I know where is it!",3,"2016-08-19");
insert into answers(answer_id, question_id, answer, user_id, publish_date) values(7, 5,"I know where is it!",2,"2015-11-17");
insert into answers(answer_id, question_id, answer, user_id, publish_date) values(8, 5,"I know where is it!",1,"2015-08-29");
insert into answers(answer_id, question_id, answer, user_id, publish_date) values(9, 6,"I know where is it!",2,"2014-11-27");
insert into answers(answer_id, question_id, answer, user_id, publish_date) values(10, 6,"I know where is it!",1,"2014-11-17");
insert into answers(answer_id, question_id, answer, user_id, publish_date) values(11, 7,"I know where is it!",2,"2013-09-15");
insert into answers(answer_id, question_id, answer, user_id, publish_date) values(12, 4,"I know where is it!",1,"2016-17-07");
