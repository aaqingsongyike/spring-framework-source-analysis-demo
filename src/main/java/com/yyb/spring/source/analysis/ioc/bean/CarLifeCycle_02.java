package com.yyb.spring.source.analysis.ioc.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 通过继承InitializingBean方法实现初始化
 * 通过继承DisposableBean方法实现销毁方法
 */
@Component
public class CarLifeCycle_02 implements InitializingBean, DisposableBean {

    public CarLifeCycle_02() {
        System.out.println("CarLifeCycle_02...constructor....");
    }

    // 销毁方法
    public void destroy() throws Exception {
        System.out.println("CarLifeCycle_02 destroy....");
    }

    // 初始化方法
    public void afterPropertiesSet() throws Exception {
        System.out.println("CarLifeCycle_02 init....");
    }
}
