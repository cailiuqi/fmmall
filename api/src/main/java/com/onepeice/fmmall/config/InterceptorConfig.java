package com.onepeice.fmmall.config;

import com.onepeice.fmmall.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)//添加自定义的拦截器
        .addPathPatterns("/shopcart/**")
        .addPathPatterns("/useraddr/**")
        .addPathPatterns("/user/**")
        .addPathPatterns("/orders/**")
        .excludePathPatterns("/user/login");
                //.addPathPatterns("/**")//添加拦截的controller
                ////排除不需要拦截的资源
    }
}
