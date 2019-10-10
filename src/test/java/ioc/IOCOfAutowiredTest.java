package ioc;

import com.yyb.spring.source.analysis.ioc.beanAutowired.AutowiredBoss;
import com.yyb.spring.source.analysis.ioc.bean.Book;
import com.yyb.spring.source.analysis.ioc.beanAutowired.MySelfAware;
import com.yyb.spring.source.analysis.ioc.config.MainConfigOfAutowired;
import com.yyb.spring.source.analysis.ioc.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCOfAutowiredTest {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);

    // 打印容器bean的name
//    private void printBeans(AnnotationConfigApplicationContext applicationContext) {
//        String[] definitionNames = applicationContext.getBeanDefinitionNames();
//        for (String name : definitionNames) {
//            System.out.println(name);
//        }
//    }

    @Test
    public void test01() {

//        BookService bookService = applicationContext.getBean(BookService.class);
//        System.out.println(bookService);

//        BookDao bookDao = applicationContext.getBean(BookDao.class);
//        System.out.println(bookDao);

        // @Autowired的位置:构造器、方法、属性、参数；
//        AutowiredBoss boss = applicationContext.getBean(AutowiredBoss.class);
//        System.out.println(boss.getBook());
//        Book book = applicationContext.getBean(Book.class);
//        System.out.println(book);

        // 将Spring底层一些组件注入到自定义的Bean中
        MySelfAware mySelfAware = applicationContext.getBean(MySelfAware.class);
        System.out.println(mySelfAware);
        System.out.println("测试的IOC容器：" + applicationContext);
        ((AnnotationConfigApplicationContext) applicationContext).close();
    }

}
