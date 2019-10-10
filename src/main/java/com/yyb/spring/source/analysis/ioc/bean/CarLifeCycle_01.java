package com.yyb.spring.source.analysis.ioc.bean;

/**
 * 通过配置类中的@Bean注解实现初始化和销毁方法
 */
public class CarLifeCycle_01 {


    public CarLifeCycle_01() {
        System.out.println("carLifeCycle_01....constructor....");
    }

    public void init() {
        System.out.println("carLifeCycle init....");
    }

    public void destroy() {
        System.out.println("carLifeCycle destroy....");
    }
}
