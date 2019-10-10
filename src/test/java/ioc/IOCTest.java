package ioc;

import com.yyb.spring.source.analysis.ioc.bean.Book;
import com.yyb.spring.source.analysis.ioc.bean.Bule;
import com.yyb.spring.source.analysis.ioc.bean.Color;
import com.yyb.spring.source.analysis.ioc.bean.Yellow;
import com.yyb.spring.source.analysis.ioc.config.LifeCycleConfig;
import com.yyb.spring.source.analysis.ioc.config.MainConfig;
import com.yyb.spring.source.analysis.ioc.config.MainConfig2;
import com.yyb.spring.source.analysis.ioc.config.MainConfig3;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest {


    @Test
    public void test01() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

        System.out.println(applicationContext.getBean("book01"));
    }

    @Test
    public void test02() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        System.out.println("容器中bean定义的名字");
        for (String name : beanDefinitionNames) {
            System.out.println("--->" + name);
        }
    }

    // 选择注册Bean的测试
    @Test
    public void test03() {
        ApplicationContext applicationContext1 = new AnnotationConfigApplicationContext(MainConfig2.class);

        System.out.println(applicationContext1.getBeansOfType(Book.class));

    }

    // 使用@Import快速导入组件
    @Test
    public void test04() {
        ApplicationContext applicationContext2 = new AnnotationConfigApplicationContext(MainConfig3.class);
        System.out.println(applicationContext2.getBean(Color.class));
        System.out.println(applicationContext2.getBean(Bule.class));
        System.out.println(applicationContext2.getBean(Yellow.class));

        String[] beanDefinitionNames = applicationContext2.getBeanDefinitionNames();
        System.out.println("容器中bean定义的名字");
        for (String name : beanDefinitionNames) {
            System.out.println("--->" + name);
        }
    }

    // 使用MainConfig3.java中的使用方式4注册Bean
    @Test
    public void test05() {
        ApplicationContext applicationContext2 = new AnnotationConfigApplicationContext(MainConfig3.class);

        String[] beanDefinitionNames = applicationContext2.getBeanDefinitionNames();
        System.out.println("容器中bean定义的名字");
        for (String name : beanDefinitionNames) {
            System.out.println("--->" + name);
        }
        Object colorFacotryBean = applicationContext2.getBean("colorFacotryBean");
        System.out.println("colorFacotryBean的类型" + colorFacotryBean.getClass());

        // 获取本身
        Object colorFacotryBeanSelf = applicationContext2.getBean("&colorFacotryBean");
        System.out.println("自己本身的类型" + colorFacotryBeanSelf.getClass());

    }

    // Bean的生命周期
    @Test
    public void testLifeCycle() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LifeCycleConfig.class);

        System.out.println("容器创建完成!!!");

        // 关闭容器
        context.close();
    }

}
