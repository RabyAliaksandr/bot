package com.raby.citybot.repository.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource(value = "file:src/main/resources/application.properties")
@ComponentScan("com.raby")
public class RepositoryConfig {


    private static final String DATA_SOURCE_CLASS_NAME = "dataSourceClassName";
    private static final String PACKAGE_NAME = "com.raby";
    private static final String PROPERTIES_PATH = "src/main/resources/hikaricp.properties";
    @Value("${database.schema}")
    private String schema;
    @Value("${dataSource.ClassName}")
    private String dataSourceClassName;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPackagesToScan(PACKAGE_NAME);
        emf.setDataSource(dataSource());
        emf.setJpaVendorAdapter(createJpaVendorAdapter());
        emf.setJpaProperties(hibernateProperties());
        emf.afterPropertiesSet();
        return emf;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    private JpaVendorAdapter createJpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty(Environment.DEFAULT_SCHEMA, schema);
        properties.setProperty(DATA_SOURCE_CLASS_NAME, dataSourceClassName);
        return properties;
    }

    @Bean
    public HikariConfig configBean() {
        return new HikariConfig(PROPERTIES_PATH);
    }

    @Bean
    public HikariDataSource dataSource() {
        return new HikariDataSource(configBean());
    }
}
