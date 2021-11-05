package com.luospace.blog.config;

import com.luospace.blog.interceptor.AdminInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    AdminInterceptor adminInterceptor;
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin/register")
                .excludePathPatterns("/admin/logout")
                .excludePathPatterns("/admin/css/**")
                .excludePathPatterns("/admin/js/**")
                .excludePathPatterns("/admin/img/**")
                .excludePathPatterns("/admin/plugins/**");
    }


    /**
     * 解决JSON返回实体类报错
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }
}
