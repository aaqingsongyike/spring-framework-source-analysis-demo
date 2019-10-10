package com.yyb.spring.source.analysis.ioc.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * BeanPostProcessor：Bean的后置处理器
 * 在Bean初始化前后进行一些处理工作
 * postProcessBeforeInitialization: 在创建好Bean实例之后(构造方法之后)，调用init方法之前执行
 * postProcessAfterInitialization: 在init方法之后执行
 * <p>
 * Spring底层对BeanPostProcessor的使用：
 * bean赋值、注入其他组件、@Autowired、生命周期注解功能、@Async...都是使用BeanPostProcessor完成的
 */

/**
 * ApplicationContextAwareProcessor：实现了BeanPostProcessor接口
 *      给该Bean实例保存IOC容器，可以在其他方法用到这个IOC容器
 */
public class CarLifeCycle_04_BeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {

    public CarLifeCycle_04_BeanPostProcessor() {

        System.out.println("构造器");

    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("...postProcessBeforeInitialization..." + beanName + "..." + bean);

        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("...postProcessAfterInitialization..." + bean + "..." + beanName);

        return bean;
    }


    // ApplicationContextAwareProcessor：给该Bean实例保存IOC容器
    // 可以在其他方法用到这个IOC容器
    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
