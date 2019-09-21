package com.springboot.weking.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String STATIC_LOCATION = "/static/";

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 这里是配置静态资源文件的路径
        registry.addResourceHandler(STATIC_LOCATION + "**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + STATIC_LOCATION);
    }

    //直接页面跳转，不经过Controller，这样在没有任何处理业务的时候,快捷的页面转向定义会节省好多代码
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login"); // 项目启动跳转到此页面
    }

}
