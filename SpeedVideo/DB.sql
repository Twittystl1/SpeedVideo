create database speedvideo;
use speedvideo;

drop table if exists t_user;
create table t_user(
id int(6) primary key auto_increment,
username varchar(20) not null,
password varchar(25) not null,
name varchar(20) not null,
email varchar(30) unique,
gender varchar(5),
intro varchar(100),
photo varchar(100),
rights int(1) not null,
regist_date date,
last_login date
);

drop table if exists t_favoritelist;
create table t_favoritelist(
uid int(6),
vid int(6)
);

drop table if exists t_video;
create table t_video(
id int(6) primary key auto_increment,
title varchar(100),
intro varchar(500),
tag varchar(100),
url varchar(100),
image varchar(100),
views int(8),
cid int(6),
upload_date date
);

drop table if exists t_videolist;
create table t_videolist(
uid int(6),
vid int(6)
);

drop table if exists t_comment;
create table t_comment(
id int(6) primary key auto_increment,
vid int(6),
uid int(6),
row int(6) not null,
content varchar(255) not null,
postdate datetime 
);

drop table if exists t_category;
create table t_category(
id int(6) primary key auto_increment,
name varchar(100),
pid int(6)
);

drop table if exists t_rate;
create table t_rate(
uid int(6),
vid int(6),
score double(4,2) not null
);


