package com.example.pricesexercise.core.infrastructure.config;

import com.example.pricesexercise.core.Provider;
import com.example.pricesexercise.core.action.GetPrice;
import com.example.pricesexercise.core.infrastructure.repository.InH2Prices;
import com.example.pricesexercise.core.infrastructure.repository.PricesRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
@ComponentScan({"com.example.pricesexercise.core", "com.example.pricesexercise.delivery.controller"})
public class ApplicationConfig {
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource")
    public HikariDataSource dataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }

    @Bean
    public PricesRepository pricesRepository(){
        return new InH2Prices(dataSource(dataSourceProperties()));
    }
}
