package com.example.pricesexercise.core.action;

import com.example.pricesexercise.core.domain.Price;
import com.example.pricesexercise.core.infrastructure.repository.InMemoryPrices;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.example.pricesexercise.PricesFixture.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class upsertPricesTests {
    String result;
    UpsertPrices upsertPrices;

    @Test
    void upsert_prices_when_there_is_no_prices() {
        given_stored_prices(emptyPrices);
        when_upsert_prices();
        then_the_expected_result_is(successfullyUpsertMessage);
    }

    private void given_stored_prices(ArrayList<Price> some_prices) {
        InMemoryPrices prices = new InMemoryPrices();
        some_prices.forEach(prices::add);
        upsertPrices = new UpsertPrices(prices);
    }

    private void when_upsert_prices() {
        result = upsertPrices.execute();
    }

    private void then_the_expected_result_is(String expected_result) {
        assertEquals(result, expected_result);
    }

}
