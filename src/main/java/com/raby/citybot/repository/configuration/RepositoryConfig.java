package com.raby.citybot.repository.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.hibernate.cfg.Environment;
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
@PropertySource({"file:C:/Users/wb/IdeaProjects/bot/src/main/resources/application.properties"})
@ComponentScan("com.raby")
public class RepositoryConfig {


    private static final String PROPERTIES_PATH = "C:/Users/wb/IdeaProjects/bot/src/main/resources/hikaricp.properties";
    @Value("${database.dialect}")
    private String dialect;
//    @Value("${database.sessionContext}")
//    private String sessionContext;
//    @Value("${database.schema}")
//    private String schema;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPackagesToScan("com.raby");
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
//        properties.setProperty(Environment.DIALECT, dialect);
//        properties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.setProperty(Environment.DEFAULT_SCHEMA, "city_info_schema");
        properties.setProperty("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
        properties.setProperty("dataSource.user", "postgre");
        properties.setProperty("dataSource.password", "gfhjkm");
        properties.setProperty("dataSource.databaseName", "postgres");

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
