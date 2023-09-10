package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: 王添权
 * @Date: 2023/9/2 15:50
 * @Description:
 */
@RestController
@RequestMapping("/emps")
@Slf4j
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result pageQuery(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "3") Integer pageSize,
            String name, Short gender,
            @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
        log.info("分页查询,参数:page:{},pageSize:{}",page,pageSize);//记录日志
        //1)编写方法接收参数
        //2)调用service 获取PageBean()
        PageBean pageBean =  empService.pageQuery(page,pageSize,name,gender
        ,begin,end);
        //3)封装返回
        return Result.success(pageBean);
    }
    //查询全部
    @GetMapping("/all")
    public Result findAll(){
        List<Emp> empList = empService.findAll();
        return Result.success(empList);
    }
    //删除
    @Log
    @DeleteMapping("/{ids}")
    //请求路径传参 /emp s/1,2,3  @PathVariable List<Integer> ids
    //请求 key = value /emp s?ids=1,2,3 @RequestParam List<Integer> ids
    public Result deleteByIds(@PathVariable List<Integer> ids){//路径传参必须在方法参数前加注解
        empService.deleteByIds(ids);
        return Result.success();
    }
    //新增员工信息
    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工,emp:{}",emp);
        empService.save(emp);
        return Result.success();
    }

    //根据id查询
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        Emp emp = empService.findById(id);
        return Result.success(emp);
    }

    //修改员工信息
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp){
        empService.update(emp);
        return Result.success();
    }
}
