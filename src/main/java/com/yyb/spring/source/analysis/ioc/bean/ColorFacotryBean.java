package com.yyb.spring.source.analysis.ioc.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 创建一个Spring定义的FactoryBean
 */
public class ColorFacotryBean implements FactoryBean<Color> {
    @Override
    public String toString() {
        return "ColorFacotryBean{}";
    }

    // 返回一个Color对象，这个对象会添加到容器中
    public Color getObject() throws Exception {
        System.out.println("---->ColorFacotryBean<----");
        return new Color();
    }

    public Class<?> getObjectType() {
        return Color.class;
    }

    // 控制是否是单例
    // true：表示单例
    // false：表示多实例
    public boolean isSingleton() {
        return true;
    }
}
