create table user_table
(
    id serial primary key ,
    name varchar(50) NOT NULL,
    gender varchar(20) NOT NULL ,
    age varchar(20) NOT NULL ,
    userId varchar(50) NOT NULL ,
    create_at timestamp default current_timestamp,
    update_at timestamp default current_timestamp
);

insert into "user_table" (name,gender,age,userId) values('文赵','女',15,'39475');
insert into "user_table" (name,gender,age,userId) values('文钱','男',15,'39476');
insert into "user_table" (name,gender,age,userId) values('文李','女',12,'39477');
insert into "user_table" (name,gender,age,userId) values('文吴','男',24,'39478');
insert into "user_table" (name,gender,age,userId) values('文王','女',32,'39479');
insert into "user_table" (name,gender,age,userId) values('尤许','男',38,'39480');
insert into "user_table" (name,gender,age,userId) values('蒋沈','女',56,'39481');
insert into "user_table" (name,gender,age,userId) values('韩杨','男',65,'39482');



