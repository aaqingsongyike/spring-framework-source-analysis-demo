package ioc;

import com.yyb.spring.source.analysis.ioc.config.MainConfigOfProfile;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCOfProfile {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfProfile.class);

    // 打印容器bean的name
    private void printBeans(AnnotationConfigApplicationContext applicationContext) {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }

    /**
     * 切换环境的方式：
     * 1）、使用命令  -Dspring.profiles.active=test
     * 2）、使用代码方式
     */
    @Test
    public void test01() {
        System.out.println("=====");
        printBeans((AnnotationConfigApplicationContext) applicationContext);
        System.out.println("=====");
        ((AnnotationConfigApplicationContext) applicationContext).close();
    }

    // 切换环境的方式二：使用代码的方式
    @Test
    public void test02() {
        // 1、创建IOC容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 2、设置需要激活的环境
        applicationContext.getEnvironment().setActiveProfiles("test", "dev");
        // 3、注册主配置类
        applicationContext.register(MainConfigOfProfile.class);
        // 启动刷新容器
        applicationContext.refresh();

        // 打印容器bean的name
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }

        applicationContext.close();
    }

}
