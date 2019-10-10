package com.yyb.spring.source.analysis.ioc.conditional;


import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 判断是否是Linux系统
 */
public class LinuxConditional implements Condition {
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 获取ioc中使用的beanfactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 获取类加载器
        ClassLoader classLoader = context.getClassLoader();
        // 获取bean定义的注册类
        BeanDefinitionRegistry registry = context.getRegistry();
        // 获取运行环境
        Environment environment = context.getEnvironment();
        String environmentProperty = environment.getProperty("os.name");

        if (environmentProperty.contains("Linux")) {
            return true;
        }
        return false;
    }
}
