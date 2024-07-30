package com.springboot.MutlipleDataBases.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "userEntityManagerFactoryBean",
        basePackages = {"com.springboot.MutlipleDataBases.mysqlrepository"},
        transactionManagerRef = "userTransactionManager"
)
public class UserDbConfig {

    @Bean
    @ConfigurationProperties(prefix="spring.user.datasource")
    public DataSourceProperties userDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource userDataSource() {
        return userDataSourceProperties()
                            .initializeDataSourceBuilder()
                            .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean userEntityManagerFactoryBean(
        EntityManagerFactoryBuilder factoryBuilder,
        @Qualifier("userDataSource") DataSource dataSource
    ) {
    return factoryBuilder.dataSource(dataSource)
                .packages("com.springboot.MutlipleDataBases.mysqlentity")
                .build();
    }

    @Bean
    public PlatformTransactionManager userTransactionManager(
            @Qualifier("userEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean factoryBean
    ) {
        return new JpaTransactionManager(factoryBean.getObject());
    }
}