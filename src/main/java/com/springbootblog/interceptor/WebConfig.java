package com.springbootblog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * WebMvcConfigurationSupport这个类，它会让spring boot的自动配置失效
 */
@Configuration
@Component
public class WebConfig extends WebMvcConfigurerAdapter {
    /**
     * 登录拦截，拦截admin下面的某些非法访问。
     *
     * 如果没有登录，直接输入http://localhost:8081/admin/blogs是会被拦截的。
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin")
                .excludePathPatterns("/search")
                .excludePathPatterns("/admin/login");
    }

    /**
     * 不拦截静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
