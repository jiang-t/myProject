--�û���
create table t_user(
  id  int not null auto_increment ;
  username	varchar(50),--�Ñ���
  password  varchar(100),--����
  status  varchar(10), --״̬(δ��ˣ�����������)
  insert_time date,
  insert_id  int
  
);
--��ɫ��
create table t_role(
	id int not null auto_increment,
	role_name varchar(50), --��ɫ����
	remark varchar(255),--��ע
	insert_time date,
  	insert_id  int
);
--�û���ɫ��ϵ��
create table t_user_role(
	id int not null auto_increment,
	user_id int,
	role_id
);

--Ȩ�ޱ�
create table t_auth(
  	id int not null auto_increment,
  	auth varchar(50),--Ȩ��
  	remark varchar(255)--��ע
  	
);
--��ɫȨ�ޱ�
create table t_role_auth(
	id int not null auto_increment,
	role_id int,
	auth_id int 
);

--�˵���
creat table t_menu(
	id int not null auto_increment,
	menu_name  varchar(255),
	icon varchar(50),
	remark  varchar(255),
	status varchar(5),
	parent_id int,
	
);








