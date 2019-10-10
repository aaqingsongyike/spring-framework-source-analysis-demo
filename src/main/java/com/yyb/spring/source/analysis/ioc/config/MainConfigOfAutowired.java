package com.yyb.spring.source.analysis.ioc.config;

import com.yyb.spring.source.analysis.ioc.bean.Book;
import com.yyb.spring.source.analysis.ioc.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 自动装配：
 * Spring利用依赖注入（DI），完成IOC容器中各个组件的依赖关系赋值；
 * <p>
 * 1）、@Autowired自动注入
 * 1）默认优先按照类型去容器中找对应的组件：applicationContext.getBean(BookService.class);
 * 找到就赋值。
 * 2）如果找到多个相同的组件，再将属性的名称作为组件的id去容器中查找。
 * 3）@Qualifier("bookDao")  // 明确指定装配的组件id
 * 4）@Autowired(required = false)  // 表示这个组件不是必需的，有就注入，没有就不注入，不会报错
 * 5）@Primary：让Spring进行装配的时候，默认使用首选的Bean，
 * 也可以继续使用@Qualifier
 * 2）、Spring还支持@Resource(JSR250)和@Inject(JSR330)
 * 1)@Resource
 * 2)Inject
 * 3)、@Autowired:构造器、方法、属性、参数；  都是从容器中获取
 * 1) 标在方法上
 * 2) 标在构造器上 --> 如果构造器上没有@Autowired则默认调用无参构造器，否则调用有参构造器
 * 3) 标在参数上
 * 4)、自定义组件使用Spring容器底层的一些组件（ApplicationContext，BeanFactory）
 * 自定义组件实现xxxAware接口，在创建对象的时候，会调用接口规定的方法注入相应的组件；---->Aware
 * 将Spring底层一些组件注入到自定义的Bean中 -----> (MySelfAware.java)
 * xxxAware的功能：使用xxxProcessor
 * e.g. ApplicationContextAware ===> ApplicationContextAwareProcessor
 */
@Configuration
@ComponentScan({"com.yyb.spring.source.analysis.ioc.service",
        "com.yyb.spring.source.analysis.ioc.controller",
        "com.yyb.spring.source.analysis.ioc.dao",
        "com.yyb.spring.source.analysis.ioc.beanAutowired"})
public class MainConfigOfAutowired {

    @Primary  // 让Spring进行装配的时候，默认使用首选的Bean
    @Bean("bookDao")
    public BookDao bookDao2() {
        BookDao bookDao = new BookDao();
        bookDao.setLable("2");
        return bookDao;
    }

    // @Autowired的位置：构造器、参数、方法、属性上
    @Bean
    public Book book() {
        return new Book();
    }
//    @Bean
//    public AutowiredBoss autowiredBoss() {
//        return new AutowiredBoss();
//    }

}
