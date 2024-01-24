-- 查询所有数据库
show databases ;
-- 查询当前数据库所有表
show tables ;
-- 查询建表语句
show create table tb_foreign_c_staff;
-- 查询表结构
desc tb_user;
-- 创建数据库
create database db_test_01;
select database();
-- 删除数据库
drop database db_test_01;
-- 使用数据库
use db_test_01;
-- 创建表结构
create table tb_user (
                         id int primary key auto_increment comment 'ID',
                         username varchar(20) not null unique comment '用户名',
                         realname varchar(10) not null comment '姓名',
                         age int comment '年龄',
                         gender char(1) default '男' comment '性别',
                         createTime timestamp default CURRENT_TIMESTAMP not null,
                         updateTime timestamp default CURRENT_TIMESTAMP not null
)comment '用户表';
-- 获取users表所有数据
select * from tb_user;
-- 获取对应字段
select id,username from tb_user;
-- 向表中插入数据
insert into tb_user values (null,'夏','Xia',22,'男',now(),now());
insert into tb_user values (null,'露西','LuXi',22,null,now(),now());
-- 修改表中数据
update tb_user set age= 20 where id=1;
update tb_user set age= 18,updateTime = now() where id=3;
-- 删除表中某一数据
-- delete from users where id = 3;
-- 比较运算符 =|等于 <>/!=|不等于 >|大于 >=|大于等于 <|小于 <=|小于等于 between|在某个范围 between...and...|在某个范围之内（最小值、最大值） in(...)|在in之后的列表中的值，多选一  like占位符|模糊匹配（_匹配单个字符，%匹配任意个字符） is null|是null  is not null|不为空  link|搜索某种模式
-- 根据搜索条件查询对应数据
select * from tb_user where id > 2;
-- 逻辑运算符 (or/|)|或者，任意满足  (and/&&)|并且，全部满足  (not/|)|非，不是
select * from tb_user where id < 3 and gender = '女';
select * from tb_user where id < 3 or gender = '女';
-- order by 排序 升序asc、降序desc 默认升序
select * from tb_user order by id desc;
select * from tb_user order by username;
-- 多重排序
select * from tb_user order by createTime asc, id desc;
-- count(*) 统计符合条件个数
select count(*) from tb_user where gender = '女';
-- 统计个数
select count(*) from tb_user;
select count(gender) from tb_user;
select count('B') from tb_user;
-- 求平最小值
select min(age) from tb_user;
-- 求平均值
select avg(id) from tb_user;
-- as 给列起别名
select count(*) as total from tb_user where gender = '女';
-- 去重
select distinct realname from tb_user;
select distinct age from tb_user;

select * from tb_user;
-- 查询年龄为18,22,26的用户信息
select * from tb_user where age in (18,22,26);
-- 查询姓Lu的用户【不区分大小写】
select * from tb_user where realname like 'lu%';
-- 查询姓名为3个字的用户信息
select * from tb_user where realname like '___';
-- 根据性别分组，统计男女用户数量
select gender,count(*) from tb_user group by gender;
-- 根据年龄分组，统计各年龄段用户数量, 并且只显示用户数量大于1的用户信息
select age,count(*) from tb_user group by age having count(*) > 1;
-- 分页查询
select * from tb_user limit 3; # 等同于select * from tb_user limit 0,3;
select * from tb_user limit 3,3; #3索引，3页数
-- if表达式
select age,count(*) from tb_user group by age;
select if(age >= 18,'大于等于18','小于18') 年龄,count(*) from tb_user group by age;
-- case表达式
select (case age when 18 then '18岁' when 22 then '22岁' else '其他年龄段' end ) 年龄,count(*) from tb_user group by age;
-- 物理外键 FOREIGN KEY
-- 一对多
CREATE TABLE `tb_foreign_p_part` (
                                     `id` int PRIMARY KEY AUTO_INCREMENT COMMENT '部门id',
                                     `partName` varchar(10) COMMENT '部门名称',
                                     `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '成立时间'
) COMMENT='外键-父表-部门信息';

CREATE TABLE `tb_foreign_c_staff` (
                                      `id` int PRIMARY KEY AUTO_INCREMENT COMMENT '员工id',
                                      `name` varchar(10) NOT NULL COMMENT '员工姓名',
                                      `p_part_id` int COMMENT '父表—部门id',
                                      `age` int COMMENT '年龄',
                                      CONSTRAINT `c_staff_p_part_fk` FOREIGN KEY (`p_part_id`) REFERENCES `tb_foreign_p_part` (`id`)
) COMMENT='员工信息';


-- 多对多【建立第三张中间表，中间表至少包含两个外键，分别关联两方主键】
create table tb_mul_student(
                               id int auto_increment primary key comment '学生id',
                               name varchar(10) not null comment '姓名',
                               num varchar(10) comment '学号'
) comment '学生表';

create table tb_mul_course(
                              id int auto_increment primary key comment '课程id',
                              name varchar(10) comment '课程名称'
) comment '课程表';

create table tb_mul_mid(
                           id int auto_increment primary key comment '多对多中间表id',
                           student_id int not null comment '学生id',
                           course_id int not null comment '课程id',
                           constraint fk_student foreign key (student_id) references tb_mul_student(id),
                           constraint fl_course foreign key (course_id) references tb_mul_course(id)
) comment '课程表';

-- 多表查询 【存在有不符合实际情况的数据】
-- 一对多
select * from tb_foreign_p_part,tb_foreign_c_staff;
-- 解决
select * from tb_foreign_p_part,tb_foreign_c_staff where tb_foreign_p_part.id = tb_foreign_c_staff.p_part_id;
-- 隐式内连接
select tb_foreign_p_part.partName,tb_foreign_c_staff.name from tb_foreign_p_part,tb_foreign_c_staff where tb_foreign_p_part.id = tb_foreign_c_staff.p_part_id;
-- 起别名
select p.partName,c.name from tb_foreign_p_part p,tb_foreign_c_staff c where p.id = c.p_part_id;
-- 显式内连接
select p.partName,c.name from tb_foreign_p_part p inner join tb_foreign_c_staff c on p.id = c.p_part_id;
-- 左外连接
select p.partName,c.name from tb_foreign_p_part p left join tb_foreign_c_staff c on p.id = c.p_part_id;
-- 右外连接
select p.partName,c.name from tb_foreign_p_part p right join tb_foreign_c_staff c on p.id = c.p_part_id;
select p.partName,c.name from tb_foreign_c_staff c right join tb_foreign_p_part p on p.id = c.p_part_id;
-- 标量子查询
select id from tb_foreign_p_part where partName = '人事部';
select * from tb_foreign_c_staff where p_part_id = 2;
select * from tb_foreign_c_staff where p_part_id = (select  id from tb_foreign_p_part where partName = '人事部');
-- 行子查询
select id,name from tb_foreign_c_staff where name = '李华';
select * from tb_foreign_c_staff where (id,name) = (3,'李华');
select * from tb_foreign_c_staff where (id,name) = (select id,name from tb_foreign_c_staff where name = '李华');
-- 列子查询
select * from tb_foreign_p_part where partName = '人事部' or partName = '技术部';
select id from tb_foreign_c_staff where p_part_id in (1,2);
select * from tb_foreign_c_staff where p_part_id in (select id from tb_foreign_p_part where partName = '人事部' or partName = '技术部');
-- 表子查询
select * from tb_foreign_c_staff where p_part_id = 1;
select c.*,p.partName from (select * from tb_foreign_c_staff where p_part_id = 1) c,tb_foreign_p_part p where c.p_part_id = p.id;
-- 统计部门id为1的员工人数
select c.p_part_id,count(*) from tb_foreign_c_staff c,tb_foreign_p_part p where c.p_part_id = p.id and c.p_part_id = 1 group by c.p_part_id;

-- 创建逻辑外键
create table tb_work_user(
                             id int auto_increment primary key comment '用户id',
                             name varchar(10) not null comment '姓名',
                             sex char(1) comment '性别',
                             user_type_id int not null comment '用户类型id'
) comment '用户信息（事务）';

create table tb_work_userType(
                                 id int auto_increment primary key comment '课程id',
                                 type varchar(10) comment '用户类型'
) comment '用户类型（事务）';


-- 开启事务【注意：请先执行！】
start transaction ;

-- 删除经济部
delete from tb_work_userType where id = 1;
-- 删除经济部下人员
delete from tb_work_user where user_type_id = 1;
-- 提交事务
commit ;
-- 回滚事务
rollback ;
-- 开启事务之后执行完删除语句，用sql查看数据是已经被删除了，但是表格实际并没有删除
select * from tb_work_user;
select  * from tb_work_userType;


-- 创建索引
create index inx_user_name on tb_work_user(name);
-- 查看索引
show index from tb_work_user;
-- 删除索引
drop index inx_user_name on tb_work_user;

-- 不安全的sql【sql注入】
select * from tb_user where realname = 'xia' and age = '1' or '1' = '1';
-- 预编译sql【通过？占位】
-- select * from tb_user where realname = ? and age = ?
-- 参数：'xia'   '1 or 1 = 1'
-- select * from tb_user where realname = 'xia' and age = '1 or 1 = 1'

-- 字符串拼接
select concat('hello','world','!');
select * from tb_user where realname like concat('%','xia','%');


