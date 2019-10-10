package com.yyb.spring.source.analysis.ioc.config;

import com.yyb.spring.source.analysis.ioc.bean.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 属性赋值
 */
// 读取外部配置文件中的属性k/v，用来在@Value注解中取出配置文件的值
@PropertySource(value = {"classpath:/book.properties"})
@Configuration
public class MainConfigOfPropertyValue {

    @Bean
    public Book book() {
        return new Book();
    }
}
