package com.project.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.List;

@Configuration
public class RecourseConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/", "classpath:/resources/", "classpath:/recourses/static/")
                .setCachePeriod(0);
    }

    @Bean
    public ResourceHttpRequestHandler resourceHttpRequestHandler() {
        ResourceHttpRequestHandler handler = new ResourceHttpRequestHandler();
        handler.setLocations(List.of(new ClassPathResource("static/")));
        handler.setCacheSeconds(0);
        return handler;
    }
}
