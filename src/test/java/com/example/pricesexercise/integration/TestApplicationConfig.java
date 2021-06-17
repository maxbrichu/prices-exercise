package com.example.pricesexercise.integration;

import com.example.pricesexercise.core.infrastructure.repository.InH2Prices;
import com.example.pricesexercise.core.infrastructure.repository.PricesRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;


@Configuration
@ComponentScan({"com.example.pricesexercise.core", "com.example.pricesexercise.delivery.controller"})
@ActiveProfiles("test")
public class TestApplicationConfig {

    @Bean
    public PricesRepository pricesRepository(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:file:./data/prod");
        dataSource.setUsername("user");
        dataSource.setPassword("1234");
        return new InH2Prices(dataSource);
    }
}
