package com.yyb.spring.source.analysis.ext.postProcessor;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("===========================");
        System.out.println("MyBeanFactoryPostProcessor...postProcessBeanFactory....");
        // 定义了多少Bean
        int count = beanFactory.getBeanDefinitionCount();
        // 定义了Bean的名字
        String[] names = beanFactory.getBeanDefinitionNames();
        System.out.println("当前BeanFactory中有" + count + "个Bean");
        System.out.println("这些Bean的名字是：" + Arrays.asList(names));
        System.out.println("===========================");
    }
}
