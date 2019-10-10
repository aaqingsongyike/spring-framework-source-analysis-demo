package com.yyb.spring.source.analysis.ioc.config;

import com.yyb.spring.source.analysis.ioc.bean.CarLifeCycle_03;
import com.yyb.spring.source.analysis.ioc.bean.CarLifeCycle_04_BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @ComponentScan(value = "com.yyb.spring.source.analysis.bean")
@Configuration
public class LifeCycleConfig {

    // 初始化和销毁方式一
    // 通过Bean注解完成初始化和销毁方法
    // 初始化是对象创建完成。并赋值好后，才进行调用init方法
    // 销毁是在容器关闭时，调用destroy(仅在单例模式下有用，多例模式下无用)
//    @Scope(value = "singleton")
//    @Bean(initMethod = "init", destroyMethod = "destroy")
//    public CarLifeCycle_01 car() {
//        return new CarLifeCycle_01();
//    }

    // 初始化和销毁方式二
    // 同Bean实例对象实现InitializingBean接口和DisposableBean接口实现初始化和销毁
//    @Bean
//    public CarLifeCycle_02 carLifeCycle_02() {
//        return new CarLifeCycle_02();
//    }

    // 初始化和销毁方式三
    // 通过@PostConstruct实现初始化
    // 通过@PreDestroy实现销毁
    @Bean
    public CarLifeCycle_03 carLifeCycle_03() {
        return new CarLifeCycle_03();
    }

    // BeanPostProcessor后置处理器的实例
    @Bean
    public CarLifeCycle_04_BeanPostProcessor carLifeCycle_04_beanPostProcessor() {
        return new CarLifeCycle_04_BeanPostProcessor();
    }
}
