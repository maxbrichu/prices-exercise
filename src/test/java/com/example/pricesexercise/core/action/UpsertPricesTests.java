package com.example.pricesexercise.core.action;

import com.example.pricesexercise.core.domain.Price;
import com.example.pricesexercise.core.infrastructure.repository.InMemoryPrices;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.example.pricesexercise.PricesFixture.*;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class UpsertPricesTests {
    UpsertPrices upsertPrices;
    InMemoryPrices prices;

    @Test
    void upsert_prices_when_there_is_no_prices() throws InterruptedException {
        given_stored_prices(emptyPrices);
        when_upsert_prices(aFilePath);
        then_stored_prices_are(filePrices);
    }

    @Test
    void upsert_prices_when_there_are_prices() throws InterruptedException {
        given_stored_prices(somePrices);
        when_upsert_prices(aFilePath);
        then_stored_prices_are(filePrices);
    }

    private void given_stored_prices(ArrayList<Price> some_prices) {
        prices = new InMemoryPrices();
        some_prices.forEach(prices::add);
        upsertPrices = new UpsertPrices(prices);
    }

    private void when_upsert_prices(String filePath) {
        upsertPrices.execute(filePath);
    }

    private void then_stored_prices_are(List<Price> expectedPrices) throws InterruptedException {
        sleep(500);
        List<Price> storedPrices = prices.getAll();
        assertEquals(storedPrices.size(), expectedPrices.size());
        assertTrue(storedPrices.containsAll(expectedPrices));
        assertTrue(expectedPrices.containsAll(storedPrices));
    }

}
