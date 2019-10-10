package com.yyb.spring.source.analysis.ext.postProcessor;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationListener implements ApplicationListener {

    // 当容器中发布此事件以后，方法会得到触发
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("=============================");
        System.out.println("收到的事件：" + event);
        System.out.println("=============================");
    }
}
