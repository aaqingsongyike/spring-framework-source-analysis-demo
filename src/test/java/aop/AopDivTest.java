package aop;

import com.yyb.spring.source.analysis.aop.config.MainConfigOfAop;
import com.yyb.spring.source.analysis.aop.service.MathCalculator;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopDivTest {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAop.class);

    @Test
    public void test01() {
        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);

        mathCalculator.div(1, 1);

        // 异常测试
        System.out.println("=====异常测试=====");
        // mathCalculator.div(1, 0);

        applicationContext.close();
    }

}
