package cn.maxlu.demo.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.annotation.MultipartConfig;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "cn.maxlu.demo.springmvc")
//@MultipartConfig(location = "/upload/storage/", maxFileSize = 1024, maxRequestSize = 1024, fileSizeThreshold = 0)
public class SpringMvcConfig implements WebMvcConfigurer {

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("utf-8");
        commonsMultipartResolver.setMaxUploadSize(2048);
        commonsMultipartResolver.setMaxUploadSizePerFile(1024);
        commonsMultipartResolver.setMaxInMemorySize(2048);
        return commonsMultipartResolver;
    }

    @Bean
    public HttpMessageConverter httpMessageConverter() {
        MappingJackson2HttpMessageConverter convert = new MappingJackson2HttpMessageConverter();
        return convert;
    }

    /**
     * 静态文件使用默认处理
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
