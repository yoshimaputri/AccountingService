package com.service.accounting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Data-data penting untuk database
 */
@Configuration
@ComponentScan("com.service.accounting")
public class JdbcConfig {
    @Bean
    public DataSource mariaDbDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/accounting_db");
        dataSource.setUsername("root");
        dataSource.setPassword("");

        return dataSource;
    }
}
