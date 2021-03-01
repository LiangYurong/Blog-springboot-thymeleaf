package com.springbootblog.config;

import com.springbootblog.po.Blog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 只是一个简单的测试，与该项目无关。将Blog实体类扫描进IOC容器中变成一个Bean、
 */
@Configuration
public class blogBean {
    @Bean
    public Blog createBlog(){
        return new Blog();
    }

}
