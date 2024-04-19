package com.schiot.community.config;

import com.schiot.community.interceptor.LoginCheckInterceptor;
import com.schiot.community.interceptor.safeWriteInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/login","/register","css/**","/error");

        registry.addInterceptor(new safeWriteInterceptor())
                .order(2)
                .addPathPatterns("/write/{studentId}");
    }
}
