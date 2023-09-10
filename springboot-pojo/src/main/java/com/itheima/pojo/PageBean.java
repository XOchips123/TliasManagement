package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: 王添权
 * @Date: 2023/9/2 15:39
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean {
    public Long total;//总记录数
    public List<Emp> rows;//当前页数据列表
}
