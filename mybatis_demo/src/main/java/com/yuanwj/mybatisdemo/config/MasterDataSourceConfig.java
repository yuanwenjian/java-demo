package com.yuanwj.mybatisdemo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created with Intellij IDEA
 * Author: xuziling
 * Date:  2019/1/28
 * Description:
 */
@MapperScan(basePackages  = MasterDataSourceConfig.PACKAGE,sqlSessionFactoryRef = "masterSqlSessionFactory")
@Configuration
public class MasterDataSourceConfig {
    public static final  String PACKAGE = "com.yuanwj.mybatisdemo.mapper";
    public static final  String MAPPER_LOCATION = "classpath:mapper/**/*.xml";
    public static final String ALIAS_PACKAGE = "com.yuanwj.mybatisdemo.model";
    public static final String CONFIG_LOCATION = "classpath:mybatis-config.xml";

    @Value("${spring.datasource.master.url}")
    private String masterUrl;

    @Value("${spring.datasource.master.username}")
    private String masterUserName;

    @Value("${spring.datasource.master.password}")
    private String masterPassword;

    @Bean(name = "masterDataSource")
    @Primary
    public DataSource materDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(masterUrl);
        dataSource.setUsername(masterUserName);
        dataSource.setPassword(masterPassword);
        return dataSource;
    }

    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(materDataSource());
    }

    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MasterDataSourceConfig.MAPPER_LOCATION));
        sessionFactory.setTypeAliasesPackage(MasterDataSourceConfig.ALIAS_PACKAGE);

        sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver()
                .getResource(MasterDataSourceConfig.CONFIG_LOCATION));
        return sessionFactory.getObject();
    }

}
