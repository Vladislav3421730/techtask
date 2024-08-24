package com.example.techtask.utils;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
public class HibernateConfig {

    private final DataSource dataSource;

    private static final String HIBERNATE_DIALECT="spring.jpa.properties.hibernate.dialect";
    private static final String SHOW_SQL="spring.jpa.show-sql";
    private static final String FORMAT_SQL="spring.jpa.properties.hibernate.format_sql";


    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.example.techtask.model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", PropertiesUtil.get(HIBERNATE_DIALECT));
        hibernateProperties.setProperty("hibernate.show_sql", PropertiesUtil.get(SHOW_SQL));
        hibernateProperties.setProperty("hibernate.format_sql", PropertiesUtil.get(FORMAT_SQL));
        return hibernateProperties;
    }
}
