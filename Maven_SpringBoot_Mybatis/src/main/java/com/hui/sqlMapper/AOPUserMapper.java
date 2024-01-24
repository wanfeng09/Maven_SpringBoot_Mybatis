package com.hui.sqlMapper;

import org.apache.ibatis.annotations.*;
import pojo.OperateLogAOP;
import pojo.TbUserInfo;

@Mapper
public interface AOPUserMapper {
    @Insert("insert into operate_log(operateUserId, operateTime, className, methodName, methodParams, returnValue, costTime) values (#{operateUserId},#{operateTime},#{className},#{methodName},#{methodParams},#{returnValue},#{costTime})")
    void insertLog(OperateLogAOP logAOP);

    @Insert("insert into tb_user(username, realname, age, gender, createTime, updateTime) VALUES (#{username},#{realname},#{age},#{gender},#{createTime},#{updateTime})")
    void add(TbUserInfo tbUserInfo);

    @Delete("delete from tb_user where id = #{id};")
    void del(Integer id);

    @Update("update tb_user set username= #{username},realname=#{realname},updateTime = now() where id=#{id};")
    void update(String username,String realname,Integer id);

    @Select("select * from tb_user where username = #{username}")
    TbUserInfo find(String username);
}
