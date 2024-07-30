package com.springboot.MutlipleDataBases.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "bookEntityManagerFactory",
        transactionManagerRef = "bookTransactionManager",
        basePackages = {
                "com.springboot.MutlipleDataBases.oraclerepository"
        }
)

public class BookDbConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.book.datasource")
    public DataSourceProperties bookDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource bookDataSource() {
        return bookDataSourceProperties()
                                .initializeDataSourceBuilder()
                                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean bookEntityManagerFactory(
            EntityManagerFactoryBuilder factoryBuilder,
             @Qualifier("bookDataSource")DataSource dataSource
    ) {

        return factoryBuilder.dataSource(dataSource)
                .packages("com.springboot.MutlipleDataBases.oracleentity")
                .build();

    }
    @Bean
    public PlatformTransactionManager bookTransactionManager(
            @Qualifier("bookEntityManagerFactory")LocalContainerEntityManagerFactoryBean factoryBean
    ) {
                return new JpaTransactionManager(factoryBean.getObject());
    }

}
