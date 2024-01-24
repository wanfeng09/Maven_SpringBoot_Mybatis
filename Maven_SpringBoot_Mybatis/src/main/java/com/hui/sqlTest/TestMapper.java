package com.hui.sqlTest;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper // 在运行时，会自动生成该接口的实现类对象（代理对象），并且将该对象交给IOC容器管理
public interface TestMapper {
    // 查询用户信息
    @Select("select * from tb_user")
    List<TbUser> getList();

    // 根据id删除数据
//    @Delete("delete from tb_user where id = 1")
//    void delete();


    @Delete("delete from tb_user where id = #{id}")
    int delete(int id);

    /**
     * 新增数据
     * 主键返回  @Options(keyProperty = "id",useGeneratedKeys = true)
     * 会自动将生成的主键值，赋值给实体类的id属性
     * @param tbUser
     */
    @Options(keyProperty = "id",useGeneratedKeys = true)
    @Insert("insert into tb_user(username, realname, age, gender, createTime, updateTime) values(#{username},#{realname},#{age},#{gender},#{createTime},#{updateTime}) ")
    void insert(TbUser tbUser);

    // 修改数据
    @Update("update tb_user set username= #{username},realname= #{realname},updateTime = #{updateTime} where id=#{id};")
    void update(TbUser tbUser);

    // 根据id查询对应信息
    @Select("SELECT * from tb_user where id = #{id}")
    TbUser selectId(int id);

    /**
     * 根据查询条件返回对应信息
     * ${} 不是预编译sql,性能低、不安全、存在sql注入
     * @param name
     * @return
     */
//    @Select("select * from tb_user where realname like '%${name}%';")
    @Select("select * from tb_user where realname like concat('%',#{name},'%');")
    List<TbUser> findList(String name);

    // xml

    List<TbUser> findList2(String name);

    // 修改数据

    void update2(TbUser tbUser);

    // 批量删除
    void batchDel(List<Integer> ids);


}
