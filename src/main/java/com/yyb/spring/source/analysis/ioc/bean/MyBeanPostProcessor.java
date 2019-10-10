package com.yyb.spring.source.analysis.ioc.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 后置处理器
 * 初始化前后进行处理
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    // 初始化之前调用，即调用init方法之前或afterPropertiesSet方法之前
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization..." + beanName + "==>" + bean);
        return bean;
    }

    // 初始化之后调用，即调用init方法之后或afterPropertiesSet方法之后
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization.." + beanName + "==>" + bean);
        return bean;
    }
}
