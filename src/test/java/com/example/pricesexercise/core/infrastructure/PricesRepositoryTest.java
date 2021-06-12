package com.example.pricesexercise.core.infrastructure;

import com.example.pricesexercise.core.domain.Price;
import com.example.pricesexercise.core.infrastructure.repository.InH2Prices;
import com.example.pricesexercise.core.infrastructure.repository.InMemoryPrices;
import com.example.pricesexercise.core.infrastructure.repository.PricesRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.example.pricesexercise.PricesFixture.*;
import static com.example.pricesexercise.core.infrastructure.PriceRepositoryFixture.aDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;


class InMemoryPricesTest extends PricesRepositoryTest<InMemoryPrices> {
    @Override
    protected InMemoryPrices createInstance() {
        return new InMemoryPrices();
    }
}


class InH2PricesTest extends PricesRepositoryTest<InH2Prices> {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:file:./data/test");
        dataSource.setUsername("user");
        dataSource.setPassword("1234");
        return dataSource;
    }

    @Override
    protected InH2Prices createInstance() {
        return new InH2Prices(dataSource());
    }
}


public abstract class PricesRepositoryTest<T extends PricesRepository> {
    private T repository;
    protected abstract T createInstance();
    Price result;

    @BeforeEach
    public void setUp() {
        repository = createInstance();
    }

    @Test
    void add_price_successfully() throws SQLException {
        when_add_price(aPrice);
        then_prices_contains(aPrice);
    }

    @Test
    void get_price_retrieves_successfully() throws SQLException {
        given_some_prices(somePrices);
        when_get_price(aBrandId, aProductId, aDateTime);
        then_result_is(aPrice);
    }

    private void given_some_prices(ArrayList<Price> prices) throws SQLException {
        for (Price price: prices) {
            repository.add(price);
        }
    }

    private void when_add_price(Price price) throws SQLException {
        repository.add(price);
    }

    private void when_get_price(int brandId, int productId, long date) throws SQLException {
        result = repository.get(brandId, productId, date);
    }

    private void then_result_is(Price expectedPrice) {
        assertEquals(expectedPrice, result);
    }

    private void then_prices_contains(Price expectedPrice) throws SQLException {
        ArrayList<Price> storedPrices = repository.getAll();
        assertEquals(1, storedPrices.size());
        assertEquals(expectedPrice, storedPrices.get(0));
    }
}
