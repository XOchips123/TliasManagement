package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

/**
 * @Author: 王添权
 * @Date: 2023/9/2 15:48
 * @Description:
 */
public interface DeptService {
    //查询所有
    List<Dept> findAll() ;
    //删除
    void deleteById(Integer id) throws Exception;
    //保存(新增
    void save(Dept dept);
    //根据id查询
    Dept findById(Integer id);
    //更新
    void update(Dept dept);

}
