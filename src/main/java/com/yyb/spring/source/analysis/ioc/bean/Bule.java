package com.yyb.spring.source.analysis.ioc.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Bule {

    public Bule() {
        System.out.println("Blue对象被创建");
    }

    @PostConstruct
    public void init() {
        System.out.println("初始化Blue对象");
    }

    @PreDestroy
    public void destory() {
        System.out.println("Blue对象被销毁");
    }

    @Override
    public String toString() {
        return "Bule{}";
    }
}
