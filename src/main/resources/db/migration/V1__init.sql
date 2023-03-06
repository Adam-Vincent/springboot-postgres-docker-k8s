create table office_table (
     id serial primary key,
     office VARCHAR(20) NOT NULL
);

INSERT INTO "office_table" (office) VALUES ('北京'),('上海'),('深圳'),('成都'),('武汉'),('西安');

create table user_table (
    id serial primary key,
    name varchar(50) NOT NULL,
    gender varchar(20) NOT NULL ,
    age varchar(20) NOT NULL ,
    userId varchar(50) NOT NULL ,
    office_id INT REFERENCES office_table(id),
    create_at timestamp default current_timestamp,
    update_at timestamp default current_timestamp
);

insert into "user_table" (name,gender,age,userId,office_id) values('文赵','女',15,'39475',1);
insert into "user_table" (name,gender,age,userId,office_id) values('文钱','男',15,'39476',2);
insert into "user_table" (name,gender,age,userId,office_id) values('文李','女',12,'39477',3);
insert into "user_table" (name,gender,age,userId,office_id) values('文吴','男',24,'39478',4);
insert into "user_table" (name,gender,age,userId,office_id) values('文王','女',32,'39479',5);
insert into "user_table" (name,gender,age,userId,office_id) values('尤许','男',38,'39480',6);
insert into "user_table" (name,gender,age,userId,office_id) values('蒋沈','女',56,'39481',3);
insert into "user_table" (name,gender,age,userId,office_id) values('韩杨','男',65,'39482',6);



