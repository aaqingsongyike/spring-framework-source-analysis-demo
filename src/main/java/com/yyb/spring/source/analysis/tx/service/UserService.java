package com.yyb.spring.source.analysis.tx.service;

import com.yyb.spring.source.analysis.tx.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    // 添加事物注解
    @Transactional
    public void insertUser() {
        userDao.insert();
        System.out.println("插入完成");

        // 模拟数据库出现问题
        int i = 10 / 0;
    }
}
