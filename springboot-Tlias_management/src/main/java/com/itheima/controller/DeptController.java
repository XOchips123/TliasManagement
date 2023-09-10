package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Emp;
import com.itheima.service.DeptService;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 王添权
 * @Date: 2023/9/2 15:48
 * @Description:
 * IOC/DI
 *   放入容器
 *      @RestController=@Controller+@ResponseBody
 *      @Servcie
 *      @Repository spring 提供的用户dao 层对象放入容器,但是mybatis 使用这个注解不行, 因为只有接口
 *      @Mapper 把mybatis 接口 创建代理对象,并且把代理对象放入容器
 *    从容器中获取对象
 *       @Autowired
 *
 *    IOC 好处: 解耦合,简化开发
 * 响应数据
 *    @ResponseBody
 * 请求数据
 *      1)  key=value&keyvalue 名称一致使用对象或 普通参数接收即可 (@RequestParam 处理名称不一致问题)
 *      2)  路径参数  参数前必须加 @PathVariable
 *      3)  JSON 参数  @RequestBody +对象
 *      4) 数组  List  @RequestParam
 *      5) 日期
 * 路径相关参数
 *      @RequestMapping
 *      @GetMapping
 *      @PostMapping
 *      @PutMapping
 *      @DeleteMapping
 * RestFul: 使用不同请求方式表示不同的行为,
 *    http://localhost:8080/users/1                GET：查询id为1的用户
 *    http://localhost:8080/users                  POST：新增用户
 *    http://localhost:8080/users                  PUT：修改用户
 *    http://localhost:8080/users/1                DELETE：删除id为1的用户
 * 统一结果集响应
 *
 * Lombok 方便记录日志注解  @Slf4j
 */
@RequestMapping("/depts")
@RestController
@Slf4j
public class DeptController {
    @Autowired
    private DeptService deptService;
    @GetMapping
    public Result findAll(){
        //调用service,获取结果
        List<Dept> deptList = deptService.findAll();
        log.info(deptList.toString());
        log.info("当前类名称{}是方法名是{}","DeptController","findALl");
        //封装结果返回
        return Result.success(deptList);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) throws Exception {
        deptService.deleteById(id);
        return Result.success();
    }

    @PostMapping
    public Result save(@RequestBody Dept dept){
        deptService.save(dept);
        return Result.success();
    }
    //查询部门信息(根据id)
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){//路径传参必须在方法参数前加注解 @PathVariable
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }
    //更新
    @PutMapping
    public Result update(@RequestBody Dept dept){//请求参数是json 必须跟对象参数   看api文档
        deptService.update(dept);
        return Result.success();
    }

}
