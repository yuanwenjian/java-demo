package com.yuanwj.mybatisdemo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created with Intellij IDEA
 * Author: xuziling
 * Date:  2019/1/28
 * Description:
 */
@Configuration
@MapperScan(basePackages = SlaveDataSourceConfig.PACKAGE,sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class SlaveDataSourceConfig {
    public static final String PACKAGE = "com.yuanwj.mybatisdemo.archive.mapper";
    public static final String MAPPER_LOCATION = "classpath:archive/**/*.xml";
    public static final String ALIAS_PACKAGE = "com.yuanwj.mybatisdemo.archive.model";
    public static final String CONFIG_LOCATION = "classpath:mybatis-config.xml";
    @Value("${spring.datasource.slave.url}")
    private String slaveUrl;

    @Value("${spring.datasource.slave.username}")
    private String slaveUserName;

    @Value("${spring.datasource.slave.password}")
    private String slavePassword;

    @Bean(name = "slaveDataSource")
    public DataSource slaveDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(slaveUrl);
        dataSource.setUsername(slaveUserName);
        dataSource.setPassword(slavePassword);
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(slaveDataSource());
    }

    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("slaveDataSource") DataSource clusterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(clusterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(SlaveDataSourceConfig.MAPPER_LOCATION));
        sessionFactory.setTypeAliasesPackage(SlaveDataSourceConfig.ALIAS_PACKAGE);
        sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(SlaveDataSourceConfig.CONFIG_LOCATION));
        return sessionFactory.getObject();
    }
}
