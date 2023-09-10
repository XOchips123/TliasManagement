package com.aliyun.oss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 阿里云 OSS 工具类
 * 1)web路径传参 使用的时{}
 * 2)mybatis传参 使用的时#{}或${}
 * 3)spring从配置中读取 使用的是${},属于一个技术角SP EL表达式
 *
 * 1.创建start模块,删除除了pom.xml的其他文件
 * 2.创建autoconfig模块,删除测试,启动类,配置文件
 * 3.starter导入autoconfig模块
 * 4.autoconfig
 *      导入坐标:阿里云, spring-boot-start-web
 * 5.把AliOSSUtils AliyunOSSProperties 粘贴过来
 * 6.AliOSSUtils移除@component
 * 7.AliyunOSSProperties删除lombok 手写get set方法
 * 8.编写配置类
 * 9.项目中删除AliOSSUtils,AliyunOSSProperties 阿里云,坐标
 * 10.UploadController 重新导入坐标
 * 11.启动测试
 *
 */


public class AliOSSUtils {
//    @Value("${aliyun.oss.endpoint}")//一个一个的进行外部熟悉的注入 属于DI的一部分
//    private String endpoint;
//    @Value("${aliyun.oss.accessKeyId}")
//    private String accessKeyId;
//    @Value("${aliyun.oss.accessKeySecret}")
//    private String accessKeySecret;
//    @Value("${aliyun.oss.bucketName}")
//    private String bucketName;

    @Autowired
    private AliOSSProperties aliOSSProperties;
    /**
     * 实现上传图片到OSS
     */
    public String upload(MultipartFile file) throws IOException {
        //获取阿里云OSS参数
        String endpoint = aliOSSProperties.getEndpoint();
        String accessKeyId = aliOSSProperties.getAccessKeyId();
        String accessKeySecret = aliOSSProperties.getAccessKeySecret();
        String bucketName = aliOSSProperties.getBucketName();

        // 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();

        // 避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //上传文件到 OSS
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, fileName, inputStream);

        //文件访问路径
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;
        // 关闭ossClient
        ossClient.shutdown();
        return url;// 把上传到oss的路径返回
    }

}
