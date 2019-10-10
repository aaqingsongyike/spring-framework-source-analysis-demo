package com.yyb.spring.source.analysis.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;

// 告诉Spring该类这是一个切面类
@Aspect
public class LogAspects {

    // 抽取公共的切入点表达式
    // *：任意方法
    // ..：任意参数
    @Pointcut("execution(public int com.yyb.spring.source.analysis.aop.service.MathCalculator.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")   // 本类使用
    public void logStarts(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("除法运行...方法名：" + joinPoint.getSignature().getName() +
                "...参数数为：{" + Arrays.asList(args) + "}");
    }

    // 无论方法正常还是非正常结束都调用
    @After("com.yyb.spring.source.analysis.aop.aop.LogAspects.pointCut()")    // 外部类使用
    public void logEnds() {
        System.out.println("除法运行结束");
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println("除法正常返回....运行结果是：{" + result + "}");
    }

    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void logException(Exception e) {
        System.out.println("除法异常....异常信息：{" + e + "}");
    }
}
