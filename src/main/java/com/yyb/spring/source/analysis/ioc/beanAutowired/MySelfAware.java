package com.yyb.spring.source.analysis.ioc.beanAutowired;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * 将Spring底层一些组件注入到自定义的Bean中
 */
@Component
public class MySelfAware implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("传入的IOC：" + applicationContext);
        this.applicationContext = applicationContext;
    }

    public void setBeanName(String name) {
        System.out.println("传入当前Bean的名字：" + name);
    }

    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        // 解析String值
        String resolveStringValue = resolver.resolveStringValue("你好${os.name} 我是#{20+5}");
        System.out.println("解析后的字符串：" + resolveStringValue);
    }
}
