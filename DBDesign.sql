/*
mysql design for dormitory management.
需求分析，变量有：
1.登陆的学生号、密码
2.宿舍号码
3.维修的需求,订单号
4.维修是否成功满意
5.日期
6.联系方式号码
*/

#--学生登陆表
drop table stu_login;
create table stu_login(
	stu_id    int(10),
	stu_password varchar(10)
);

#插入预置信息：

insert into stu_login values(070714083,'txyzgy7b');





#--管理员登陆表
drop table admin_login;
create table admin_login(
	admin_id    int(10),
	admin_password varchar(10)
);



#插入预置信息：

insert into admin_login values(001,'txyzgy7b');


#--维修信息表
drop table service;
create table service(
	service_id int(4) primary key auto_increment,
	dorm_id varchar(8),
	phone_number varchar(15),
	information varchar(40),
	creatime TIMESTAMP DEFAULT NOW(),
	evaluate tinyint(1)
)auto_increment=100;




#插入预置信息：

insert into service values(1,'9B304','176****3397','灯泡坏了',DEFAULT,NULL);
