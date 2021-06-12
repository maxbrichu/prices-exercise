package com.example.pricesexercise.core.infrastructure;

import com.example.pricesexercise.core.domain.Price;
import com.example.pricesexercise.core.infrastructure.repository.InMemoryPrices;
import com.example.pricesexercise.core.infrastructure.repository.PricesRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.example.pricesexercise.PricesFixture.*;
import static com.example.pricesexercise.core.infrastructure.PriceRepositoryFixture.aDate;
import static org.junit.jupiter.api.Assertions.assertEquals;


class InMemoryPricesTest extends PricesRepositoryTest<InMemoryPrices> {
    @Override
    protected InMemoryPrices createInstance() {
        return new InMemoryPrices();
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
    void add_price_successfully(){
        when_add_price(aPrice);
        then_prices_contains(aPrice);
    }


    @Test
    void get_price_retrieves_successfully(){
        given_some_prices(somePrices);
        when_get_price(aBrandId, aProductId, aDate);
        then_result_is(aPrice);
    }

    private void given_some_prices(ArrayList<Price> prices) {
        prices.forEach(price -> repository.add(price));
    }

    private void when_add_price(Price price) {
        repository.add(price);
    }

    private void when_get_price(int brandId, int productId, Date date) {
        result = repository.get(brandId, productId, date);
    }

    private void then_result_is(Price expectedPrice) {
        assertEquals(expectedPrice, result);
    }

    private void then_prices_contains(Price expectedPrice) {
        ArrayList<Price> storedPrices = repository.getAll();
        assertEquals(1, storedPrices.size());
        assertEquals(expectedPrice, storedPrices.get(0));
    }
}
