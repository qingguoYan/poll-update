package com.yqg.apps.poll.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.yqg.apps.poll.dao")
public class MybatisConfig {
    
}
