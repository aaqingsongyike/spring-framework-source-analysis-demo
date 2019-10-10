package com.yyb.spring.source.analysis.ioc.config;

import com.yyb.spring.source.analysis.ioc.bean.Color;
import com.yyb.spring.source.analysis.ioc.bean.ColorFacotryBean;
import com.yyb.spring.source.analysis.ioc.conditional.MyImportBeanDefinitionRegistrar;
import com.yyb.spring.source.analysis.ioc.conditional.MyImportSelector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({Color.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MainConfig3 {
    /**
     * 给容器中注册组件：
     * 1、@Controller/@Service/@Repository
     * 2、Bean(导入第三方包里的组件)
     * ×3、@Import(快速给容器导入组件)
     * 1)@Import({组件})：id 默认是组件的全类名
     * 2)ImportSelector：返回需要导入的组件的全类名数组
     * 3)ImportBeanDefinitionRegistrar
     * ×4、使用Spring提供的FactoryBean（工厂Bean）--->ColorFactory.java（与其它框架整合）
     * 1)默认获取到的是工厂bean调用的getObject创建的对象
     * 2)要获取到工厂Bean本身，需要在id前面加 & 符号
     */
    // 使用方式4注册Bean
    @Bean
    public ColorFacotryBean colorFacotryBean() {
        return new ColorFacotryBean();
    }
}
