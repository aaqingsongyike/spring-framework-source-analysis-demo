package com.yyb.spring.source.analysis.ioc.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 判断是否是Windows系统
 */
public class WindowsConditional implements Condition {
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        Environment environment = context.getEnvironment();
        String environmentProperty = environment.getProperty("os.name");
        if (environmentProperty.contains("Windows"))
            return true;
        return false;
    }
}
