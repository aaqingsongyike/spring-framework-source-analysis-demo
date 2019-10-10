package com.yyb.spring.source.analysis.aop.config;

import com.yyb.spring.source.analysis.aop.aop.LogAspects;
import com.yyb.spring.source.analysis.aop.service.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AOP：【动态代理】
 * 指在程序运行期间动态的将某段代码切入到指定方法、指定位置进行运行的编程方式；
 * 1、导入AOP模块：Spring AOP
 * 2、定义一个业务逻辑类：MathCalculator：
 * 在业务逻辑运行的时候将日志进行打印（在方法之前，之后，出现异常....）
 * 3、定义日志切面类(LogAspects)：切面类里面的方法需要动态感知MathCalculator.div运行到哪里然后执行
 * 通知方法：
 * 前置通知(@Before)：
 * 后置通知(@After)：
 * 返回通知(@AfterReturning)：
 * 异常通知(@AfterThrowing)：
 * 环绕通知(@Around)：动态代理，手动推进目标方法运行（joinPoint.procced()）
 * 4、给切面类的目标方法标注何时何地运行（通知注解）
 * 5、将切面类和业务逻辑类都加入到容器中
 * 6、必须告诉Spring哪个类是切面类：
 * 给切面类加如注解：@Aspect
 * 7、给配置类中加：@EnableAspectJAutoProxy（开启基于注解的AOP模式）
 */

/**
 * AOP原理:【看给容器中注册了什么组件，这个组件什么时候工作，包括这个组件工作时候的功能？】
 *      @EnableAspectJAutoProxy入手：
 *      1、@EnableAspectJAutoProxy是什么:
 *          @Import(AspectJAutoProxyRegistrar.class)：给容器中导入AspectJAutoProxyRegister组件
 *              利用AspectJAutoProxyRegistrar自定义给容器中注册Bean；
 *              internalAutoProxyCreator=AnnotationAwareAspectJAutoProxyCreator
 *
 *          给容器中注册一个AnnotationAwareAspectJAutoProxyCreator(自动代理创建器):
 *      2、AnnotationAwareAspectJAutoProxyCreator：
 *          父类->AspectJAwareAdvisorAutoProxyCreator
 *              父类->AbstractAdvisorAutoProxyCreator
 *                  父类->AbstractAutoProxyCreator
 *                      父类->ProxyProcessorSupport
 *                           implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *                      关注后置处理器（在Bean初始化完成前后做的事情），自动装配BeanFactory
 *
 *        AbstractAutoProxyCreator.setBeanFactory():
 *        AbstractAutoProxyCreator.有后置处理器的逻辑：postProcessXXX
 *
 *        AbstractAdvisorAutoProxyCreator.setBeanFactory()（重写了该方法）--> initBeanFactory()
 *
 *        AnnotationAwareAspectJAutoProxyCreator.initBeanFactory()（重写了该方法）
 *
 *     3、流程：
 *        1）传入配置类，创建IOC容器；
 *        2）注册配置类，调用refresh()刷新容器；
 *        3）registerBeanPostProcessors(beanFactory);注册Bean的后置处理器，来方便拦截Bean的创建
 *              <1>先获取IOC容器已经定义了的需要创建对象的所有BeanPostProcessor
 *              <2>给容器中添加别的BeanPostProcessor
 *              <3>优先注册实现了PriorityOrdered接口的BeanPostProcessor
 *              <4>再给容器中Ordered接口的BeanPostProcessor
 *              <5>注册没实现优先级接口的BeanPostProcessor
 *              <6>注册BeanPostProcessor，实际上就是创建BeanPostProcessor对象，保存在容器中
 *                  创建org.springframework.aop.config.internalAutoProxyCreator的BeanPostProcessor【AnnotationAwareAspectJAutoProxyCreator】
 *                  （1）创建Bean的实例
 *                  （2）populateBean(beanName, mbd, instanceWrapper);给Bean的各种属性赋值
 *                  （3）initializeBean(beanName, exposedObject, mbd);初始化Bean
 *                          (3.1)invokeAwareMethods(beanName, bean);处理Aware接口的方法回调
 *                          (3.2)applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 *                              应用后置处理器的postProcessBeforeInitialization(result, beanName);
 *                          (3.3)invokeInitMethods(beanName, wrappedBean, mbd);执行自定义的初始化方法
 *                          (3.4)applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 *                              执行后置处理器的postProcessAfterInitialization(result, beanName);
 *                   (4)BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)创建成功
 *                      ---> BeanFactoryAspectJAdvisorsBuilderAdapter(beanFactory, this.aspectJAdvisorFactory);
 *               <7>把BeanPostProcessor注册到BeanFactory中：
 *                  beanFactory.addBeanPostProcessor(postProcessor);
 * ====================以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程================================
 *          AnnotationAwareAspectJAutoProxyCreator => InstantiationAwareBeanPostPrcessor
 *          4）finishBeanFactoryInitialization(beanFactory);完成BeanFactory初始化工作；创建剩下的单实例Bean
 *                 <1>遍历获取容器所有的Bean，依次创建对象getBean(beanName);
 *                      getBean -> doGetBean() -> getSingleton()
 *                 <2>创建bean：
 *                    【AnnotationAwareAspectJAutoProxyCreator在所有bean创建之前会有一个拦截，InstantiationAwareBeanPostProcessor，
 *                    会调用postProcessorBeforeInstantiation()】
 *                      （1）先冲缓存中获取当前Bean，如果能获取到，说明Bean是之前被创建过的；直接使用，否则在创建；
 *                          只要被创建好的Bean都会被缓存起来
 *                      （2）createBean();创建Bean：AnnotationAwareAspectJAutoProxyCreator会在任何bean创建之前先尝试返回Bean的实例
 *                          【BeanPostProcessor是在Bean创建完成初始化前后调用的】
 *                          【InstantiationAwareBeanPostProcessor是在创建Bean实例之前先尝试用后置处理器返回对象】
 *                          (2.1)resolveBeforeInstantiation(beanName, mbdToUse);解析BeforeInstantiation
 *                              希望后置处理器在此能返回一个代理对象；如果能返回代理对象就使用，如果不能就继续
 *                              (2.1.1)后置处理器先尝试返回对象
 *                                  bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
 *                                  拿到所有的后置处理器，如果是InstantiationAwareBeanPostProcessor;就执行
 *                                  postProcessBeforeInstantiation
 *                                  if (bean != null) {
 * 						                bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
 *                                  }
 *                          (2.2)doCreateBean(beanName, mbdToUse, args);真正的创建一个Bean实例和 3）<6>的流程一样
 *                      （3）
 *============================================================================================================
 *      AnnotationAwareAspectJAutoProxyCreator【InstantiationAwareBeanPostPrcessor】的作用：
 *      1）、每一个Bean创建之前，调用postProcessBeforeInstantiation()
 *         关心MathCalculator和LogAspect的创建：
 *         （1）判断当前Bean是否在advisedBeans中（保存了所有需要增强的Bean e.g.MathCalculator）
 *         （2）判断当前Bean是否是基础类型的Advice、Pointcut、Advisor、AopInfrastructureBean，或者是否是切面（@Aspect）
 *         （3）是否需要跳过：
 *              <1>获取候选的增强器（切面里的通知方法）【List<Advisor> candidateAdvisors】，每一个封装的通知方法的增强器
 *                 是InstantiationModelAwarePointcutAdvisor，判断每一个增强器是否是AspectJPointcutAdvisor类型的
 *              <2>永远返回false
 *      2）、创建对象
 *          postProcessAfterInitialization:
 *          return wrapIfNecessary(bean, beanName, cacheKey);  // 包装如果需要的情况下
 *          （1）获取当前Bean的所有增强器（通知方法） Object[] specificInterceptors
 *              1、找到候选的所有增强器（找那些通知方法是需要当前Bean的方法）
 *              2、获取到能在Bean使用的增强器。
 *              3、给增强器排序
 *          （2）保存当前Bean在advisedBeans中；
 *          （3）如果当前Bean需要增强，创建Bean的代理对象
 *              1、获取所有增强器（通知方法）
 *              2、保存到proxyFactory
 *              3、创建代理对象：Spring自动决定
 *                  JdkDynamicAopProxy(config);   jdk的动态代理
 *                  ObjenesisCglibAopProxy(config);  cglib的动态代理
 *          （4）给容器中返回当前组件使用cglib增强了的代理对象
 *          （5）以后容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程
 *
 *      3）、目标方法执行
 *          容器中保存了组件的代理对象（cglib增强后的对象），这个对象里面保存了详细信息（比如增强器，目标对象...）
 *          (1)CglibAopProxy.intercept();拦截目标方法执行
 *          (2)根据ProxyFactory对象获取将要执行的目标方法拦截器链：
 *              List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
 *              <1>创建List<Object> interceptorList，保存所有拦截器:
 *                  list长度为5：
 *                      一个默认的org.springframework.aop.interceptor.ExposeInvocationInterceptor.ADVISOR
 *                      和四个增强器（通知方法）
 *              <2>遍历所有增强器，将其转为Interceptor;
 *                  Interceptor[] interceptors = registry.getInterceptors(advisor);
 *              <3>将增强器转为List<MethodInterceptor>
 *                  如果是MethodInterceptor，直接加入到集合中；
 *                  如果不是，使用AdvisorAdapter将增强器转为Interceptor；
 *                  转换完成返回MethodInterceptor数组
 *          (3)如果没有拦截器链，直接执行目标方法；
 *              拦截器链（每一个通知方法又被包装为方法拦截器，利用MethodInterceptor机制）
 *          (4)如果有拦截器链，把需要执行的目标对象，目标方法，拦截器链等信息传入创建一个CglibMethodInvocation对象，
 *             并调用proceed()方法，并返回Object对象；
 *          (5)拦截器链的触发过程：
 *              <1>如果没有拦截器，直接执行目标方法，或者拦截器的索引和拦截器数组大小-1大小一样（执行到了最后一个拦截器）
 *                 都是执行目标方法
 *              <2>链式获取每一个拦截器，拦截器执行每一个invoke方法，每一个拦截器等待下一个拦截器执行完成返回以后再来执行，
 *                 通过拦截器链的机制，保证通知方法与目标方法的执行顺序
 *
 * =====================================AOP总结============================================================
 * AOP总结：
 *    1）@EnableAspectJAutoProxy开启AOP的功能，会给容器注册一个组件AnnotationAwareAspectJAutoProxyCreator,
 *      这个组件是一个后置处理器
 *    2）容器的创建流程：
 *          （1）registerBeanPostProcessors()注册后置处理器，在该过程创建AnnotationAwareAspectJAutoProxyCreator对象
 *          （2）finishBeanFactoryInitialization()初始化剩下的单实例Bean
 *              (1)创建业务逻辑组件和切面组件
 *              (2)AnnotationAwareAspectJAutoProxyCreator拦截组件的创建过程
 *              (3)组件创建完成之后，判断组件是否需要增强
 *                  是：切面的通知方法，包装成增强器（Advisor），给业务逻辑组件创建一个代理对象（cglib）
 *          （3）执行目标方法：
 *              (1)代理对象执行目标方法
 *              (2)CglibAopProxy.intercept();
 *                  <1>得到目标方法的拦截器链（增强器包装成拦截器MethodInterceptor）
 *                  <2>利用拦截器的链式机制，依次进入每一个拦截器进行执行
 *                  <3>效果：
 *                      正常执行：前置通知---》目标方法---》后置通知---》返回通知
 *                      出现异常：前置通知---》目标方法---》后置通知---》异常通知
 */
@EnableAspectJAutoProxy     // 告诉配置类启用AspectJ自动代理(开启基于注解的AOP模式)
@Configuration
public class MainConfigOfAop {

    // 业务逻辑类
    @Bean
    public MathCalculator calculator() {
        return new MathCalculator();
    }

    // 切面类
    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }
}
