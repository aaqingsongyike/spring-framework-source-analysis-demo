package com.yyb.spring.source.analysis.ext.postProcessor;

import com.yyb.spring.source.analysis.ioc.bean.Bule;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.stereotype.Component;

@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    // BeanDefinitionRegistry就是Bean定义信息的保存中心，以后BeanFactory工厂就是按照BeanDefinitionRegistry里面保存的的
    // 每一个bean定义信息创建Bean实例的
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("===========================");
        System.out.println("MyBeanDefinitionRegistryPostProcessor...postProcessBeanDefinitionRegistry..." +
                "Bean的数量：" + registry.getBeanDefinitionCount());

        // 注册一个Bean实例（任选其一）
        // RootBeanDefinition beanDefinition = new RootBeanDefinition(Bule.class);
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Bule.class).getBeanDefinition();
        registry.registerBeanDefinition("hello", beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("===========================");
        System.out.println("MyBeanDefinitionRegistryPostProcessor...postProcessBeanFactory..." +
                "Bean的数量：" + beanFactory.getBeanDefinitionCount());
        System.out.println("===========================");
    }
}
