package com.hui.sqlMapper;

import com.hui.sqlTest.TbUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pojo.UserLogin;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserMapper {
   // 获取用户总数
   /* @Select("select count(*) from tb_user")
    Long countUsers();*/
   // 分页查询
   /* @Select("select * from tb_user limit #{start},#{pageSize}")
    List<TbUser> getUsers(Integer start,Integer pageSize);*/

    @Select("select * from tb_user")
    List<TbUser> getUsers();


// 查找用户
    List<TbUser> findUser(String username, Integer age, LocalDateTime begin, LocalDateTime end);

    // 添加用户

    @Select("insert into tb_user (username,realname,age,gender,createTime,updateTime) values (#{username},#{realname},#{age},#{gender},#{createTime},#{updateTime})")
    void addUser(TbUser tbUser);

    @Select("select * from user_account where username = #{username} and password = #{password}")
    UserLogin getUsernameAndPassword(UserLogin u);

    /*
    * 删除同部门的用户
    * */
    @Delete("delete from tb_work_user where user_type_id = #{id}")
    void deleteUser(Integer id);

    /*
    *
    * 删除用户类型
    * */
    @Delete("delete from tb_work_usertype where id = #{id}")
    void deleteUserType(Integer id);


}
