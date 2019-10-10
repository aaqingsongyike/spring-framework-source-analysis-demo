package com.yyb.spring.source.analysis.ext.config;

import com.yyb.spring.source.analysis.ioc.bean.Bule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 扩展原理：
 * 1、BeanPostProcessor：bean后置处理器，bean创建对象初始化前后进行拦截工作的
 * BeanFactoryPostProcessor：beanFactory的后置处理器
 * 在BeanFactory标准初始化之后调用：所有的bean定义已经被保存加载到BeanFactory中，
 * 但是Bean的实例还未创建
 * 1）、ioc容器创建
 * 2）、调用invokeBeanFactoryPostProcessors(beanFactory)方法，执行BeanFactoryPostProcessors
 * 如何找到所有的BeanFactoryPostProcessor并执行他们的方法：
 * 1）直接在BeanFactory中找到所有类型是BeanFactoryPostProcessor的组件，并执行它们的方法
 * 2）在初始化创建其他组件前面执行
 * <p>
 * 2、BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
 * postProcessBeanDefinitionRegistry();在所有Bean定义信息将要被加载，Bean实例还未创建的时候；
 * 优先与BeanFactoryPostProcessor执行；
 * 利用BeanDefinitionRegistryPostProcessor中给容器中再额外添加一些组件
 * 原理：
 * 1）IOC创建对象
 * 2）refresh(); --》 invokeBeanFactoryPostProcessors(beanFactory);
 * 3）先从容器中获取所有的BeanDefinitionRegistryPostProcessor组件。
 * 1、依次触发所有的postProcessBeanDefinitionRegistry(registry);方法
 * 2、再来触发postProcessBeanFactory(beanFactory);方法BeanFactoryPostProcessor
 * 4）再来从容器中找到BeanFactoryPostProcessor组件，然后依次触发postProcessorBeanFactory()方法
 * <p>
 * 3、ApplicationListener：监听容器中发布的事件，完成事件驱动模型的开发
 * public interface ApplicationListener<E extends ApplicationEvent> extends EventListener
 * 监听ApplicationEvent及其下面的子事件
 * 步骤：
 * 1）写一个监听器(ApplicationListener的实现类)监听某个事件（ApplicationEvent及其子类）
 * 2）把监听器放在容器中
 * 3）只要容器中有相关事件的发布，我们就能监听到这个事件
 * ContextRefreshedEvent：容器刷新完成（所有的Bean都完全创建）会发布这个事件
 * ContextClosedEvent：关闭容器会发布这个事件
 * 4）发布一个事件：
 * annotationConfigApplicationContext.publishEvent(new ApplicationEvent(new String("我发布的事件")) {});
 * 原理：
 * ContextRefreshedEvent、ext.Ext_Test$1[source=我发布的事件]、ContextClosedEvent
 * ContextRefreshedEvent事件：
 * 1）容器创建对象：refresh();
 * 2）finishRefresh();容器刷新完成，会发布ContextRefreshedEvent事件
 * 自己发布事件：
 * 容器关闭会发布ContextClosedEvent事件
 * <p>
 * 【事件发布流程】：
 * 3）在finishRefresh()中调用了publishEvent(new ContextRefreshedEvent(this));
 * 1、获取到事假的多播器（派发器）getApplicationEventMulticaster()
 * 2、调用派发事件：multicastEvent()
 * 3、获取到所有的ApplicationListener
 * for (ApplicationListener<?> listener : getApplicationListeners(event, type))
 * （1）、如果有Executor，可以支持使用Executor进行异步派发
 * Executor executor = getTaskExecutor();
 * （2）、否则，同步的方式直接执行listener方法：invokeListener(listener, event);
 * 拿到Listener回调onApplicationEvent()方法
 * 【事件多播器（派发器）】:
 * 1)容器创建对象---》refresh()
 * 2)初始化ApplicationEventMulticaster---》initApplicationEventMulticaster();
 * 1、先去容器中找id=“applicationEventMulticaster”的组件
 * 2、如果没有this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
 * 并且加入到容器中，就可以在其他组件要派发事件的时候，自动注入这个applicationEventMulticaster
 * <p>
 * 【容器中有哪些监听器】:
 * 1)容器创建对象---》refresh()
 * 2)registerListeners()
 * 从容器中拿到所有的监听器，把他们注册到applicationEventMulticaster中
 * String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);
 * 将Listener注册到applicationEventMulticaster中
 * getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);
 */
@ComponentScan("com.yyb.spring.source.analysis.ext")
@Configuration
public class ExtConfig {

    @Bean
    public Bule bule() {
        return new Bule();
    }
}
