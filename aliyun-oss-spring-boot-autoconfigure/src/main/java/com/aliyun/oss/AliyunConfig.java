package com.aliyun.oss;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 王添权
 * @Date: 2023/9/9 16:25
 * @Description:
 */
@Configuration
//@EnableConfigurationProperties(AliOSSProperties.class)
public class AliyunConfig {
    @Bean
    public AliOSSUtils aliOSSUtils(){
        return new AliOSSUtils();
    }
    @Bean
    public AliOSSProperties aliyunOSSProperties(){
        return new AliOSSProperties();
    }
}
