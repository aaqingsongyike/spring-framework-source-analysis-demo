package com.yyb.spring.source.analysis.ioc.config;

import com.yyb.spring.source.analysis.ioc.bean.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(value = "com.yyb.spring.source.analysis.*",
        includeFilters = {
                // 按照注解过滤
                // @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, Service.class}), // 包含Controller和Service注解的组件
                // 自定义规则过滤
                @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
                // 按照类型过滤
                // @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {BookService.class})
        }, useDefaultFilters = false // includeFilters和useDefaultFilters联合使用
)
public class MainConfig {

    @Bean(value = "book01")
    public Book book() {
        return new Book(0, "book1");
    }

}
