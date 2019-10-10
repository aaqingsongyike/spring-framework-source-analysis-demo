package com.yyb.spring.source.analysis.ioc.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 通过@PostConstruct实现初始化
 * 通过@PreDestroy实现销毁
 */
public class CarLifeCycle_03 {
    public CarLifeCycle_03() {
        System.out.println("CarLifeCycle_03...constructor.....");
    }

    // 实现初始化
    @PostConstruct
    public void init() {
        System.out.println("CarLifeCycle_03 init.....");
    }

    // 实现销毁
    @PreDestroy
    public void destroy() {
        System.out.println("CarLifeCycle_03...destroy....");
    }
}
