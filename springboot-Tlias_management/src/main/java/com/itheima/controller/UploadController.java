package com.itheima.controller;


import com.aliyun.oss.AliOSSUtils;
import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: 王添权
 * @Date: 2023/9/3 15:59
 * @Description:
 */
@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;

/*
    1_ 存储到本地(存储到当前机器)
    优点: 简单
    缺点: 容量限制, 数据丢失风险高
@PostMapping("/upload")
    public Result uploadLocal(String username, Integer age, MultipartFile image) throws IOException {
        log.info("文件上传: username={},age={},image={}", username, age, image);

        String originalFilename = image.getOriginalFilename();
        log.info("原始文件名:" + originalFilename);
        //截取原始文件名后缀名
        //找最后一个点的位置
        int index = originalFilename.lastIndexOf(".");
        //截取
        String suffix = originalFilename.substring(index);
        //拼接新的文件名,使用uuid 防止重复
        String fileName = UUID.randomUUID().toString()+suffix;

        File file = new File("D://test//"+fileName);
        image.transferTo(file);//转移方法
        return Result.success();
    }*/

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        String url = aliOSSUtils.upload(image);
        return Result.success(url);
    }
}
