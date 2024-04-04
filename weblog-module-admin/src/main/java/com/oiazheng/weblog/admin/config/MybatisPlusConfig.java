package com.oiazheng.weblog.admin.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.oiazheng.weblog.common.domain.mapper")
public class MybatisPlusConfig {


}
