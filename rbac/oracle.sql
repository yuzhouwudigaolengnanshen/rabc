drop table rbac_role cascade constraints;
drop sequence rbac_role_seq;
drop table rbac_user cascade constraints;
drop sequence rbac_user_seq;
drop table rbac_user_role cascade constraints;
drop table rbac_module cascade constraints;
drop table rbac_role_module cascade constraints;
drop table rbac_org cascade constraints;
drop cluster rbac_org_clus;
drop cluster rbac_module_clus;

create cluster rbac_org_clus (id number(4));
create index rbac_org_idx on cluster rbac_org_clus;

create cluster rbac_module_clus (id number(4));
create index rbac_module_idx on cluster rbac_module_clus;

-- 用户表
create table rbac_user (
	id number(4) primary key,
	username varchar2(32) not null,
	password varchar2(60) not null,
	org_id number(4) not null, /*自己从属的机构*/
	org_ids varchar2(512) /*可以管理的机构*/
);
create sequence rbac_user_seq start with 16;

-- 角色表(主要控制功能权限，决定哪些功能可用，例如订单数据的增删改查)
create table rbac_role (
	id number(4) primary key,
	name varchar2(32) not null
);
create sequence rbac_role_seq start with 5;

-- 用户角色表
create table rbac_user_role (
	user_id number(4) not null,
	role_id number(4) not null,
	primary key (user_id,role_id)
);

-- 模块表
create table rbac_module (
	id number(4) primary key,
	name varchar2(32) not null,
	pid number(4) not null,
	code varchar2(64)
) cluster rbac_module_clus(id);

-- 角色模块表
create table rbac_role_module (
	role_id number(4) not null,
	module_id number(4) not null,
	primary key (role_id,module_id)
);

-- 组织关系表(主要控制数据权限，决定哪些数据能够被访问到，例如某一用户可以查看本组织机构和下属组织机构的数据，但不能查看上级组织机构的数据)
create table rbac_org (
	id number(4) primary key,
	name varchar2(32) not null,
	pid number(4) not null
) cluster rbac_org_clus(id);


insert into rbac_user(id,username,password,org_id,org_ids) values(1,'admin','123',0,'0,1,2,3,4,5,6,7,11,12,13,21,22,23,31,32,33,41,42,43,44,51,52,53,61,62,63,64,71,72,73,111,112,113,131,132,133');
insert into rbac_user(id,username,password,org_id,org_ids) values(2,'赵总','123',1,'0,1,11,12,13,111,112,113,131,132,133');
insert into rbac_user(id,username,password,org_id,org_ids) values(3,'钱总','123',11,'0,1,11,111,112,113');
insert into rbac_user(id,username,password,org_id,org_ids) values(4,'孙经理','123',111,'0,1,11,111');
insert into rbac_user(id,username,password,org_id,org_ids) values(5,'李经理','123',112,'0,1,11,112');
insert into rbac_user(id,username,password,org_id,org_ids) values(6,'周经理','123',113,'0,1,11,113');
insert into rbac_user(id,username,password,org_id,org_ids) values(7,'小吴','123',111,'0,1,11,111');
insert into rbac_user(id,username,password,org_id,org_ids) values(8,'小郑','123',112,'0,1,11,112');
insert into rbac_user(id,username,password,org_id,org_ids) values(9,'小王','123',113,'0,1,11,113');
insert into rbac_user(id,username,password,org_id,org_ids) values(10,'小冯','123',111,'0,1,11,111');
insert into rbac_user(id,username,password,org_id,org_ids) values(11,'小陈','123',112,'0,1,11,112');
insert into rbac_user(id,username,password,org_id,org_ids) values(12,'小褚','123',113,'0,1,11,113');
insert into rbac_user(id,username,password,org_id,org_ids) values(13,'小魏','123',111,'0,1,11,111');
insert into rbac_user(id,username,password,org_id,org_ids) values(14,'小蒋','123',112,'0,1,11,112');
insert into rbac_user(id,username,password,org_id,org_ids) values(15,'小沈','123',113,'0,1,11,113');
--insert into rbac_user(id,username,password) values(16,'小韩','123');
--insert into rbac_user(id,username,password) values(17,'小杨','123');

insert into rbac_role(id,name) values(1,'系统管理员');
insert into rbac_role(id,name) values(2,'地区主管');
insert into rbac_role(id,name) values(3,'门店经理');
insert into rbac_role(id,name) values(4,'订单业务员');
insert into rbac_role(id,name) values(5,'商品业务员');

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

insert into rbac_org(id,name,pid) values(0,'总部',-1);
insert into rbac_org(id,name,pid) values(1,'华北地区',0);
insert into rbac_org(id,name,pid) values(2,'东北地区',0);
insert into rbac_org(id,name,pid) values(3,'华东地区',0);
insert into rbac_org(id,name,pid) values(4,'中南地区',0);
insert into rbac_org(id,name,pid) values(5,'西北地区',0);
insert into rbac_org(id,name,pid) values(6,'西南地区',0);
insert into rbac_org(id,name,pid) values(7,'港澳台地区',0);
insert into rbac_org(id,name,pid) values(11,'北京',1);
insert into rbac_org(id,name,pid) values(12,'天津',1);
insert into rbac_org(id,name,pid) values(13,'河北',1);
insert into rbac_org(id,name,pid) values(21,'辽宁',2);
insert into rbac_org(id,name,pid) values(22,'吉林',2);
insert into rbac_org(id,name,pid) values(23,'黑龙江',2);
insert into rbac_org(id,name,pid) values(31,'上海',3);
insert into rbac_org(id,name,pid) values(32,'江苏',3);
insert into rbac_org(id,name,pid) values(33,'浙江',3);
insert into rbac_org(id,name,pid) values(41,'河南',4);
insert into rbac_org(id,name,pid) values(42,'湖北',4);
insert into rbac_org(id,name,pid) values(43,'湖南',4);
insert into rbac_org(id,name,pid) values(44,'广东',4);
insert into rbac_org(id,name,pid) values(51,'陕西',5);
insert into rbac_org(id,name,pid) values(52,'甘肃',5);
insert into rbac_org(id,name,pid) values(53,'宁夏',5);
insert into rbac_org(id,name,pid) values(61,'云南',6);
insert into rbac_org(id,name,pid) values(62,'贵州',6);
insert into rbac_org(id,name,pid) values(63,'四川',6);
insert into rbac_org(id,name,pid) values(64,'重庆',6);
insert into rbac_org(id,name,pid) values(71,'香港',7);
insert into rbac_org(id,name,pid) values(72,'澳门',7);
insert into rbac_org(id,name,pid) values(73,'台湾',7);
insert into rbac_org(id,name,pid) values(111,'王府井分店',11);
insert into rbac_org(id,name,pid) values(112,'西单分店',11);
insert into rbac_org(id,name,pid) values(113,'大钟寺分店',11);
insert into rbac_org(id,name,pid) values(131,'石家庄分店',13);
insert into rbac_org(id,name,pid) values(132,'保定分店',13);
insert into rbac_org(id,name,pid) values(133,'邯郸分店',13);



insert into rbac_module(id,name,pid,code) values(1,'系统管理',0,'');
insert into rbac_module(id,name,pid,code) values(11,'邮件设置',1,'/system/email');
insert into rbac_module(id,name,pid,code) values(12,'短信设置',1,'/system/sms');
insert into rbac_module(id,name,pid,code) values(13,'用户管理',1,'/system/user');
insert into rbac_module(id,name,pid,code) values(14,'权限分配',1,'/system/role');
insert into rbac_module(id,name,pid,code) values(2,'订单管理',0,'');
insert into rbac_module(id,name,pid,code) values(21,'查询订单',2,'/order/search');
insert into rbac_module(id,name,pid,code) values(22,'退单处理',2,'/order/refund');
insert into rbac_module(id,name,pid,code) values(23,'统计分析',2,'/order/stat');
insert into rbac_module(id,name,pid,code) values(3,'商品管理',0,'');
insert into rbac_module(id,name,pid,code) values(31,'查询商品',3,'/product/search');
insert into rbac_module(id,name,pid,code) values(32,'上下架',3,'/product/onoff');
insert into rbac_module(id,name,pid,code) values(33,'统计分析',3,'/product/stat');
insert into rbac_module(id,name,pid,code) values(34,'库存管理',3,'/product/inventory');


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

-- 业务-订单表
drop table rbac_order;
drop sequence rbac_order_seq;
create table rbac_order (
	id number(4) not null,
	customer_id number(4) not null,
	org_id number(4) not null,
	total number(10,2) not null
);
create sequence rbac_order_seq;
insert into rbac_order values(rbac_order_seq.nextval,1,111,2300.00);
insert into rbac_order values(rbac_order_seq.nextval,1,111,310.00);
insert into rbac_order values(rbac_order_seq.nextval,2,111,5000.00);
insert into rbac_order values(rbac_order_seq.nextval,3,111,70.00);
insert into rbac_order values(rbac_order_seq.nextval,4,111,1100.00);
insert into rbac_order values(rbac_order_seq.nextval,5,112,100.00);
insert into rbac_order values(rbac_order_seq.nextval,5,112,80.00);
insert into rbac_order values(rbac_order_seq.nextval,5,112,60.00);
insert into rbac_order values(rbac_order_seq.nextval,6,112,300.00);
insert into rbac_order values(rbac_order_seq.nextval,6,112,400.00);
insert into rbac_order values(rbac_order_seq.nextval,1,113,2100.00);
insert into rbac_order values(rbac_order_seq.nextval,1,113,800.00);
insert into rbac_order values(rbac_order_seq.nextval,7,113,600.00);
insert into rbac_order values(rbac_order_seq.nextval,8,113,300.00);
insert into rbac_order values(rbac_order_seq.nextval,9,113,200.00);
commit;