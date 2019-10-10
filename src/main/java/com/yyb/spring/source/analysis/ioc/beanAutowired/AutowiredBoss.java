package com.yyb.spring.source.analysis.ioc.beanAutowired;

import com.yyb.spring.source.analysis.ioc.bean.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutowiredBoss {

    private Book book;

    // 默认加载IOC容器组件，容器会启动无参构造器，再进行初始化赋值
    public AutowiredBoss() {
    }

    // 构造器要用的组件，都是从容器中获取
    // @Autowired
    public AutowiredBoss(Book book) {
        this.book = book;
        System.out.println("====================");
        System.out.println("AutowiredBoss有参构造器");
        System.out.println("====================");
    }

    public Book getBook() {
        return book;
    }

    // @Autowired // 标注在方法上，Spring容器创建当前对象，就会调用方法，完成赋值。
    // 方法使用的参数，自定义类型的值从IOC容器中获取
    public void setBook(@Autowired Book book) {
        this.book = book;
    }

//    @Override
//    public String toString() {
//        return "AutowiredBoss{" +
//                "book=" + book +
//                '}';
//    }
}
