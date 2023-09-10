package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.DeptLog;
import com.itheima.service.DeptLogService;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: 王添权
 * @Date: 2023/9/2 15:49
 * @Description:
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;
    @Override
    public List<Dept> findAll() {
        //调用mapper
        List<Dept> deptList = deptMapper.findAll();
        return deptList;
    }

    /*
    * @Transactional :spring提供的事务控制的注解(声明式事务)
    * 可以写在方法,类或接口上
    * 便捷:写在接口(会给所有方法开启事务)
    * 可维护性,可读性:写在方法上
    * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    //默认情况下，只有出现 RuntimeException (例如int a=1/0)才自动回滚异常。
    // rollbackFor属性用于控制出现什么异常类型，回滚事务(扩大回滚范围)
    //事务的传播行为:
    // 当a方法有事务或者没有控制,b方法有事务控制,当a调用b时,b方法的事务如何管理: 通过属性控制
    //  propagation = Propagation.REQUIRED (默认) 如果a有事务b就加入,如果a没有就创建一个新的
    //  propagation = Propagation.REQUIRES_NEW   不管a有没有事务,b都会新建一个单独的事务
    public void deleteById(Integer id) throws Exception {
        try {
            deptMapper.deleteById(id);
            if (true) {
                throw new Exception("自己的异常");
            }
            empMapper.deleteByDeptID(id);
        } finally {
            //记录日志
            DeptLog log = new DeptLog();
            log.setCreateTime(LocalDateTime.now());
            log.setDescription("删除部门,部门ID是:"+id);

            deptLogService.insert(log);
        }
    }

    @Override
    public void save(Dept dept) {
        //补 全数据
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //调用mapper
        deptMapper.insert(dept);
    }

    @Override
    public Dept findById(Integer id) {
        return deptMapper.findById(id);
    }

    @Override
    public void update(Dept dept) {
        //补 全数据,更新数据后更新 字段_更新时间
        dept.setCreateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
