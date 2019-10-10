package com.yyb.spring.source.analysis.ioc.bean;

import org.springframework.beans.factory.annotation.Value;

public class Book {

    // 使用@Value赋值
    // 1、可以写基本数值
    // 2、可以写SpEL：#{}
    // 3、可以取出配置文件中的值：${}  ----->方式一
    @Value("1")
    private Integer id;
    @Value("#{20.0 - 2.0}")
    private Double price;
    @Value("${book.name}")
    private String name;

    public Book() {
    }

    public Book(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Override
//    public String toString() {
//        return "Book{" +
//                "id=" + id +
//                ", price=" + price +
//                ", name='" + name + '\'' +
//                '}';
//    }
}
