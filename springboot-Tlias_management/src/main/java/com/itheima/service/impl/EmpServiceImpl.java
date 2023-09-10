package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: 王添权
 * @Date: 2023/9/2 15:50
 * @Description:
 */
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    //分页查询
//    @Override
//    public PageBean pageQuery(Integer page, Integer pageSize) {
//        //1)查询总数
//        long count = empMapper.count();
//        //2)获取分页查询结果列表
//        Integer start = (page -1) * pageSize;//计算角标
//        List<Emp> empList = empMapper.list(start,pageSize);//分页查询获取数据
//        //封装PageBean对象
//        return new PageBean(count,empList);
//    }

    //pageHelper
 /*   @Override
    public PageBean pageQuery(Integer page, Integer pageSize) {
        //使用PageHelper传递值
        PageHelper.startPage(page,pageSize);//设置分页参数
        List<Emp> empList = empMapper.list1();//执行分页查询
        Page<Emp> pageList =  (Page<Emp>) empList;//获取分页结果
        //封装返回pageBean
        return new PageBean(pageList.getTotal(),pageList.getResult());
    }*/

    @Override
    public void deleteByIds(List<Integer> ids) {
        empMapper.deleteByIds(ids);
    }

    //查询全部
    @Override
    public List<Emp> findAll() {
        return empMapper.findAll();
    }

    @Override
    public PageBean pageQuery(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        //使用PageHelper传递值
        PageHelper.startPage(page,pageSize);//设置分页参数
        List<Emp> empList = empMapper.list2(name, gender, begin, end);//执行分页查询
        Page<Emp> pageList =  (Page<Emp>) empList;//获取分页结果
        //封装返回pageBean
        return new PageBean(pageList.getTotal(),pageList.getResult());
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp findById(Integer id) {
        Emp emp = empMapper.findById(id);
        return emp;
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp Login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }

}
