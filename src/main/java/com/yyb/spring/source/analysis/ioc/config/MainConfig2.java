package com.yyb.spring.source.analysis.ioc.config;

import com.yyb.spring.source.analysis.ioc.bean.Book;
import com.yyb.spring.source.analysis.ioc.conditional.LinuxConditional;
import com.yyb.spring.source.analysis.ioc.conditional.WindowsConditional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig2 {
    /**
     * @Conditional({Conditional数组})：按照条件注册Bean 如果当前运行环境是Linux系统，则注册("linuxBook")，
     * 否则注册("windowsBook")
     */
    @Conditional({WindowsConditional.class})
    @Bean(value = "windowsBook")
    public Book book01() {
        return new Book(1, "windowsBook");
    }

    @Conditional({LinuxConditional.class})
    @Bean(value = "linuxBook")
    public Book book02() {
        return new Book(2, "linuxBook");
    }

}
