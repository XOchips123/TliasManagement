package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: 王添权
 * @Date: 2023/9/2 15:50
 * @Description:
 */
@Mapper
public interface EmpMapper {
/*    原始手动分页  使用的查询
    @Select("select count(*) from emp")
    long count();//获取总记录数

    @Select("select * from emp limit #{start}, #{pageSize}")
    List<Emp> list(Integer start, Integer pageSize);//获取当前页结果列表*/

   /*  mybatis  PageHelper 获取当前页查询结果列表
    @Select("select * from emp")
    List<Emp> list1();*/

    /*
     * 删除
     * */
    void deleteByIds(List<Integer> ids);

    /*
    查询全部员工信息
    * */
    @Select("select * from emp")
    List<Emp> findAll();

    /*
     * mybatis pageHelper 分页查询员工信息
     * */
    List<Emp> list2(String name, Short gender, LocalDate begin, LocalDate end);

    /*
     * 新增员工信息
     * */
    void insert(Emp emp);

    /*
     * 根据id查询员工
     * */
    @Select("select * from emp where id = #{id}")
    Emp findById(Integer id);

    /*
     * 更新员工信息
     * */
    void update(Emp emp);

    /*
    根据用户名密码查询员工
    * */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);

  /*  @Delete("delete from emp where dept_id = #{depyId}")
    void deleteByDeptId(Integer deptId);*/

    @Delete("delete from emp where dept_id = #{depyId}")
    void deleteByDeptID(Integer deptId);
}
