package com.yyb.spring.source.analysis.ioc.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.EmbeddedValueResolver;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Profile:
 * Spring提供的可以根据当前环境，动态的激活和切换一系列组件的功能
 * <p>
 * 开发环境、测试环境、生产环境
 * e.g.数据库（开发环境--->A数据库）（测试环境--->B数据库）（生产环境--->C数据库）
 *
 * @Profile
 * 1)、加了环境标示的Bean，只有这个环境被激活才能注册到容器中，默认是default环境
 * 2)、可以写在方法上
 * 3)、可以写在类上：写在配置类上，只有是在指定的环境的时候，整个配置类里面的所有配置才能开始生效
 * 4)、在没有标注环境表示的Bean，在任何环境下都是加载的
 */
@PropertySource("classpath:/db.properties")
@Configuration
public class MainConfigOfProfile implements EmbeddedValueResolverAware {

    /**
     * 读取配置文件中的属性的三种方式
     */
    @Value("${db.user}")
    private String user;

    private StringValueResolver resolver;

    private String driverClass;

    @Profile(value = "test")    // 测试环境
    @Bean(value = "testDataSource")
    public DataSource dataSourceTest(@Value("db.password") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        return dataSource;
    }

    @Profile(value = "dev")    // 开发环境
    @Bean(value = "devDataSource")
    public DataSource dataSourceDev(@Value("db.password") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/fulan");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        return dataSource;
    }

    @Profile(value = "prod")   // 生产环境
    @Bean(value = "prodDataSource")
    public DataSource dataSourceProd(@Value("db.password") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        return dataSource;
    }

    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
        driverClass = resolver.resolveStringValue("${db.driverClass}");
    }
}
