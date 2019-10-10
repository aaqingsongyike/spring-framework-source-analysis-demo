package ext;

import com.yyb.spring.source.analysis.ext.config.ExtConfig;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ext_Test {

    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);

    @Test
    public void ext_test() {

        // 发布一个事件
        annotationConfigApplicationContext.publishEvent(new ApplicationEvent(new String("我发布的事件")) {
        });

        annotationConfigApplicationContext.close();
    }
}
