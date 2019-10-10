package com.yyb.spring.source.analysis.ioc.controller;

import com.yyb.spring.source.analysis.ioc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;
}
