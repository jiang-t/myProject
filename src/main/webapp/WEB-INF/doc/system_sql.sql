--用户表
create table t_user(
  id  int not null auto_increment ;
  username	varchar(50),--用戶名
  password  varchar(100),--密码
  status  varchar(10), --状态(未审核，正常，冻结)
  insert_time date,
  insert_id  int
  
);
--角色表
create table t_role(
	id int not null auto_increment,
	role_name varchar(50), --角色名称
	remark varchar(255),--备注
	insert_time date,
  	insert_id  int
);
--用户角色关系表
create table t_user_role(
	id int not null auto_increment,
	user_id int,
	role_id
);

--权限表
create table t_auth(
  	id int not null auto_increment,
  	auth varchar(50),--权限
  	remark varchar(255)--备注
  	
);
--角色权限表
create table t_role_auth(
	id int not null auto_increment,
	role_id int,
	auth_id int 
);

--菜单表
creat table t_menu(
	id int not null auto_increment,
	menu_name  varchar(255),
	icon varchar(50),
	remark  varchar(255),
	status varchar(5),
	parent_id int,
	
);








