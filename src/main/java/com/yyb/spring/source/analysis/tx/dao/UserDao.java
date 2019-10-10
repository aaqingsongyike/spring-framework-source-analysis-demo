package com.yyb.spring.source.analysis.tx.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert() {
        String sql = "insert into `user` (name, age) values (?, ?)";

        // 随机生成name
        String name = UUID.randomUUID().toString().substring(0, 5);
        jdbcTemplate.update(sql, name, 19);
    }
}
