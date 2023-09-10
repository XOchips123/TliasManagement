package com.itheima.service;


import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: 王添权
 * @Date: 2023/9/2 15:50
 * @Description:
 */
public interface EmpService {

    //pageHelper
    //PageBean pageQuery(Integer page, Integer pageSize);

    void deleteByIds(List<Integer> ids);

    List<Emp> findAll();

    PageBean pageQuery(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void save(Emp emp);

    Emp findById(Integer id);

    void update(Emp emp);

    Emp Login(Emp emp);


}
