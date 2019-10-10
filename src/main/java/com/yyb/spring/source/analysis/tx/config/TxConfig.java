package com.yyb.spring.source.analysis.tx.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 声明式事物：
 * <p>
 * 环境搭建：
 * 1、导入相关依赖（数据源、数据库驱动、SpringJDBC模块）
 * 2、配置数据源、JdbcTemplate（Spring提供简化数据库操作的工具）操作数据库
 * 3、给方法标注@Transactional 表示当前方法是一个事务方法
 * 4、在配置类中加入@EnableTransactionManagement，开启基于注解的事务管理功能
 * 5、配置事务管理器用来管理事务
 * <p>
 * 原理：
 * 1、@EnableTransactionManagement利用TransactionManagementConfigurationSelector.class
 * 给容器中会导入组件，
 * 导入两个组件：
 * （1）AutoProxyRegistrar.class
 * （2）ProxyTransactionManagementConfiguration.class
 * 2、AutoProxyRegistrar.class给容器中注册一个InfrastructureAdvisorAutoProxyCreator.class组件
 * InfrastructureAdvisorAutoProxyCreator的作用：只是利用后置处理器机制在对象创建以后，包装对象
 * 成一个代理对象（增强器），代理对象执行方法利用拦截器链进行调用
 * 3、ProxyTransactionManagementConfiguration.class做了什么？
 * (1)它作为一个配置类给容器注册事务增强器（BeanFactoryTransactionAttributeSourceAdvisor）:
 * （1）事务增强器要用事务注解信息，new AnnotationTransactionAttributeSource()来解析事务注解
 * （2）事务拦截器：
 * 在transactionInterceptor()方法中创建了TransactionInterceptor对象，该对象保存了事务
 * 的属性信息，事务管理器；
 * 该对象实现了MethodInterceptor方法拦截器：在目标方法执行的时候，执行拦截器链：
 * 事务拦截器的作用：
 * 1）先来获取事务相关的属性
 * 2）再获取PlatformTransactionManager，如果事先没有添加指定任何transactionManager
 * 最终会从容器中按照类型获取一个PlatformTransactionManager
 * 3）执行目标方法
 * 如果异常，获取的事务管理器，利用事务管理器回滚操作；
 * 如果正常，利用事务管理器提交事务
 * （3）
 * (2)
 */
// 开启基于注解的事务管理功能
@EnableTransactionManagement
@ComponentScan("com.yyb.spring.source.analysis.tx")
@Configuration
public class TxConfig {

    // 配置数据源
    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        return dataSource;
    }

    // 配置JdbcTemplate
    // 用来简化增删改的操作
    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
        // Spring对@Configuration类会特殊处理，给容器中加组件的方法，多次调用都只是从容器中找组件
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }

    // 配置事务管理器在容器中，用来管理事务
    @Bean
    public PlatformTransactionManager transactionManager() throws PropertyVetoException {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource());
        return dataSourceTransactionManager;
    }
}
