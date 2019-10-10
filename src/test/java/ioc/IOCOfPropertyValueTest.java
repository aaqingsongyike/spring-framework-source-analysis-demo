package ioc;

import com.yyb.spring.source.analysis.aop.config.MainConfigOfAop;
import com.yyb.spring.source.analysis.ioc.bean.Book;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

public class IOCOfPropertyValueTest {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAop.class);

    // 打印容器bean的name
    private void printBeans(AnnotationConfigApplicationContext applicationContext) {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }

    @Test
    public void test01() {
        printBeans((AnnotationConfigApplicationContext) applicationContext);
        System.out.println("==================");
        Book book = (Book) applicationContext.getBean("book");
        System.out.println(book);

        // 获取配置文件中的值----->方式二
        Environment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("book.name");
        System.out.println("配置文件中的值（方式二）：" + property);

        ((AnnotationConfigApplicationContext) applicationContext).close();
    }

}
