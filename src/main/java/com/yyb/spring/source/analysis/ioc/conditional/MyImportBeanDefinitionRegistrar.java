package com.yyb.spring.source.analysis.ioc.conditional;

import com.yyb.spring.source.analysis.ioc.bean.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * @param importingClassMetadata 当前类的注解信息
     * @param registry               BeanDefinition的注册类，把所有需要添加到容器中的Bean：
     *                               调用BeanDefinitionRegistry.registerBeanDefinition手动注册
     */
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        // 判断容器中是否注册了Red和Bule
        boolean registryRed = registry.containsBeanDefinition("Yellow");
        boolean registryBule = registry.containsBeanDefinition("Bule");
        if (registryRed && registryBule) {
            // 指定Bean的定义信息
            RootBeanDefinition rainbow = new RootBeanDefinition(RainBow.class);
            // 指定Bean名字
            String rainbowName = "rainbow";
            // 注册Bean
            registry.registerBeanDefinition(rainbowName, rainbow);
        }
    }
}
