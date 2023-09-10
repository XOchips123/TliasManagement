package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: 王添权
 * @Date: 2023/9/2 15:48
 * @Description:
 */
@Mapper
public interface DeptMapper {
    @Select("select * from dept ")
    List<Dept> findAll();
//    @Update("update set name = #{name} where name = ")
//    List<Dept> update();

    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into dept (name,create_time,update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    @Select("select * from dept where id=#{id}")
    Dept findById(Integer id);

    @Update("update dept set name = #{name}, create_time = #{createTime} where id = #{id}")
    void update(Dept dept);
}
