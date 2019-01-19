drop table rbac_role_module;
drop table rbac_user_role;
drop table rbac_role;
drop table rbac_user;
drop table rbac_module;
drop table rbac_org;

-- �û���
create table rbac_user (
	id int primary key auto_increment,
	username varchar(32) unique not null,
	password varchar(60) not null,
	org_id int not null, /*�Լ������Ļ���*/
	org_ids varchar(512) /*���Թ���Ļ���*/
);

-- ��ɫ��(��Ҫ���ƹ���Ȩ�ޣ�������Щ���ܿ��ã����綩�����ݵ���ɾ�Ĳ�)
create table rbac_role (
	id int primary key auto_increment,
	name varchar(32) not null
);



-- ģ���(Ȩ�ޱ�)
create table rbac_module (
	id int primary key auto_increment,
	name varchar(32) not null,
	pid int not null, /*parent id*/
	code varchar(64)
);

-- �û���ɫ��
create table rbac_user_role (
		user_id int not null,
		role_id int not null,
		primary key (user_id,role_id)
);

-- ��ɫģ���
create table rbac_role_module (
	role_id int not null,
	module_id int not null,
	primary key (role_id,module_id)
);

-- ��֯��ϵ��(��Ҫ��������Ȩ�ޣ�������Щ�����ܹ������ʵ�������ĳһ�û����Բ鿴����֯������������֯���������ݣ������ܲ鿴�ϼ���֯����������)
create table rbac_org (
	id int primary key auto_increment,
	name varchar(32) not null,
	pid int not null
);


insert into rbac_user(id,username,password,org_id,org_ids) values(1,'admin','123',0,'0,1,2,3,4,5,6,7,11,12,13,21,22,23,31,32,33,41,42,43,44,51,52,53,61,62,63,64,71,72,73,111,112,113,131,132,133');
insert into rbac_user(id,username,password,org_id,org_ids) values(2,'����','123',1,'0,1,11,12,13,111,112,113,131,132,133');
insert into rbac_user(id,username,password,org_id,org_ids) values(3,'Ǯ��','123',11,'0,1,11,111,112,113');
insert into rbac_user(id,username,password,org_id,org_ids) values(4,'�ﾭ��','123',111,'0,1,11,111');
insert into rbac_user(id,username,password,org_id,org_ids) values(5,'���','123',112,'0,1,11,112');
insert into rbac_user(id,username,password,org_id,org_ids) values(6,'�ܾ���','123',113,'0,1,11,113');
insert into rbac_user(id,username,password,org_id,org_ids) values(7,'С��','123',111,'0,1,11,111');
insert into rbac_user(id,username,password,org_id,org_ids) values(8,'С֣','123',112,'0,1,11,112');
insert into rbac_user(id,username,password,org_id,org_ids) values(9,'С��','123',113,'0,1,11,113');
insert into rbac_user(id,username,password,org_id,org_ids) values(10,'С��','123',111,'0,1,11,111');
insert into rbac_user(id,username,password,org_id,org_ids) values(11,'С��','123',112,'0,1,11,112');
insert into rbac_user(id,username,password,org_id,org_ids) values(12,'С��','123',113,'0,1,11,113');
insert into rbac_user(id,username,password,org_id,org_ids) values(13,'Сκ','123',111,'0,1,11,111');
insert into rbac_user(id,username,password,org_id,org_ids) values(14,'С��','123',112,'0,1,11,112');
insert into rbac_user(id,username,password,org_id,org_ids) values(15,'С��','123',113,'0,1,11,113');
--insert into rbac_user(id,username,password) values(16,'С��','123');
--insert into rbac_user(id,username,password) values(17,'С��','123');

insert into rbac_role(id,name) values(1,'ϵͳ����Ա');
insert into rbac_role(id,name) values(2,'��������');
insert into rbac_role(id,name) values(3,'�ŵ꾭��');
insert into rbac_role(id,name) values(4,'����ҵ��Ա');
insert into rbac_role(id,name) values(5,'��Ʒҵ��Ա');

insert into rbac_user_role(user_id,role_id) values(1,1);
insert into rbac_user_role(user_id,role_id) values(2,2);
insert into rbac_user_role(user_id,role_id) values(3,2);
insert into rbac_user_role(user_id,role_id) values(4,3);
insert into rbac_user_role(user_id,role_id) values(5,3);
insert into rbac_user_role(user_id,role_id) values(6,3);
insert into rbac_user_role(user_id,role_id) values(7,1);
insert into rbac_user_role(user_id,role_id) values(8,1);
insert into rbac_user_role(user_id,role_id) values(9,1);
insert into rbac_user_role(user_id,role_id) values(10,4);
insert into rbac_user_role(user_id,role_id) values(11,4);
insert into rbac_user_role(user_id,role_id) values(12,4);
insert into rbac_user_role(user_id,role_id) values(13,5);
insert into rbac_user_role(user_id,role_id) values(14,5);
insert into rbac_user_role(user_id,role_id) values(15,5);

insert into rbac_org(id,name,pid) values(0,'�ܲ�',-1);
insert into rbac_org(id,name,pid) values(1,'��������',0);
insert into rbac_org(id,name,pid) values(2,'��������',0);
insert into rbac_org(id,name,pid) values(3,'��������',0);
insert into rbac_org(id,name,pid) values(4,'���ϵ���',0);
insert into rbac_org(id,name,pid) values(5,'��������',0);
insert into rbac_org(id,name,pid) values(6,'���ϵ���',0);
insert into rbac_org(id,name,pid) values(7,'�۰�̨����',0);
insert into rbac_org(id,name,pid) values(11,'����',1);
insert into rbac_org(id,name,pid) values(12,'���',1);
insert into rbac_org(id,name,pid) values(13,'�ӱ�',1);
insert into rbac_org(id,name,pid) values(21,'����',2);
insert into rbac_org(id,name,pid) values(22,'����',2);
insert into rbac_org(id,name,pid) values(23,'������',2);
insert into rbac_org(id,name,pid) values(31,'�Ϻ�',3);
insert into rbac_org(id,name,pid) values(32,'����',3);
insert into rbac_org(id,name,pid) values(33,'�㽭',3);
insert into rbac_org(id,name,pid) values(41,'����',4);
insert into rbac_org(id,name,pid) values(42,'����',4);
insert into rbac_org(id,name,pid) values(43,'����',4);
insert into rbac_org(id,name,pid) values(44,'�㶫',4);
insert into rbac_org(id,name,pid) values(51,'����',5);
insert into rbac_org(id,name,pid) values(52,'����',5);
insert into rbac_org(id,name,pid) values(53,'����',5);
insert into rbac_org(id,name,pid) values(61,'����',6);
insert into rbac_org(id,name,pid) values(62,'����',6);
insert into rbac_org(id,name,pid) values(63,'�Ĵ�',6);
insert into rbac_org(id,name,pid) values(64,'����',6);
insert into rbac_org(id,name,pid) values(71,'���',7);
insert into rbac_org(id,name,pid) values(72,'����',7);
insert into rbac_org(id,name,pid) values(73,'̨��',7);
insert into rbac_org(id,name,pid) values(111,'�������ֵ�',11);
insert into rbac_org(id,name,pid) values(112,'�����ֵ�',11);
insert into rbac_org(id,name,pid) values(113,'�����·ֵ�',11);
insert into rbac_org(id,name,pid) values(131,'ʯ��ׯ�ֵ�',13);
insert into rbac_org(id,name,pid) values(132,'�����ֵ�',13);
insert into rbac_org(id,name,pid) values(133,'�����ֵ�',13);



insert into rbac_module(id,name,pid,code) values(1,'ϵͳ����',0,'');
insert into rbac_module(id,name,pid,code) values(11,'�ʼ�����',1,'/system/email');
insert into rbac_module(id,name,pid,code) values(12,'��������',1,'/system/sms');
insert into rbac_module(id,name,pid,code) values(13,'�û�����',1,'/system/user');
insert into rbac_module(id,name,pid,code) values(14,'Ȩ�޷���',1,'/system/role');
insert into rbac_module(id,name,pid,code) values(2,'��������',0,'');
insert into rbac_module(id,name,pid,code) values(21,'��ѯ����',2,'/order/search');
insert into rbac_module(id,name,pid,code) values(22,'�˵�����',2,'/order/refund');
insert into rbac_module(id,name,pid,code) values(23,'ͳ�Ʒ���',2,'/order/stat');
insert into rbac_module(id,name,pid,code) values(3,'��Ʒ����',0,'');
insert into rbac_module(id,name,pid,code) values(31,'��ѯ��Ʒ',3,'/product/search');
insert into rbac_module(id,name,pid,code) values(32,'���¼�',3,'/product/onoff');
insert into rbac_module(id,name,pid,code) values(33,'ͳ�Ʒ���',3,'/product/stat');
insert into rbac_module(id,name,pid,code) values(34,'������',3,'/product/inventory');


insert into rbac_role_module(role_id,module_id) values(1,11);
insert into rbac_role_module(role_id,module_id) values(1,12);
insert into rbac_role_module(role_id,module_id) values(1,13);
insert into rbac_role_module(role_id,module_id) values(1,14);
insert into rbac_role_module(role_id,module_id) values(2,21);
insert into rbac_role_module(role_id,module_id) values(2,23);
insert into rbac_role_module(role_id,module_id) values(2,31);
insert into rbac_role_module(role_id,module_id) values(2,34);
insert into rbac_role_module(role_id,module_id) values(3,21);
insert into rbac_role_module(role_id,module_id) values(3,22);
insert into rbac_role_module(role_id,module_id) values(3,23);
insert into rbac_role_module(role_id,module_id) values(3,31);
insert into rbac_role_module(role_id,module_id) values(3,32);
insert into rbac_role_module(role_id,module_id) values(3,33);
insert into rbac_role_module(role_id,module_id) values(3,34);
insert into rbac_role_module(role_id,module_id) values(4,21);
insert into rbac_role_module(role_id,module_id) values(4,22);
insert into rbac_role_module(role_id,module_id) values(4,23);
insert into rbac_role_module(role_id,module_id) values(5,31);
insert into rbac_role_module(role_id,module_id) values(5,32);
insert into rbac_role_module(role_id,module_id) values(5,33);
insert into rbac_role_module(role_id,module_id) values(5,34);

commit;

-- ҵ��-������
drop table rbac_order;
create table rbac_order (
	id int primary key auto_increment,
	customer_id int not null,
	org_id int not null,
	total decimal not null
);
insert into rbac_order values(1,1,111,2300.00);
insert into rbac_order values(2,1,111,310.00);
insert into rbac_order values(3,2,111,5000.00);
insert into rbac_order values(4,3,111,70.00);
insert into rbac_order values(5,4,111,1100.00);
insert into rbac_order values(6,5,112,100.00);
insert into rbac_order values(7,5,112,80.00);
insert into rbac_order values(8,5,112,60.00);
insert into rbac_order values(9,6,112,300.00);
insert into rbac_order values(10,6,112,400.00);
insert into rbac_order values(11,1,113,2100.00);
insert into rbac_order values(12,1,113,800.00);
insert into rbac_order values(13,7,113,600.00);
insert into rbac_order values(14,8,113,300.00);
insert into rbac_order values(15,9,113,200.00);
commit;