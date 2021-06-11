package com.example.pricesexercise.core.action;

import com.example.pricesexercise.core.domain.Price;
import com.example.pricesexercise.core.infrastructure.InMemoryPrices;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.example.pricesexercise.core.action.PricesFixture.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class GetPriceTests {
    Price result;
    GetPrice getPrice;

    @Test
    void get_price_successfully() {
        given_stored_prices(some_prices);
        when_get_price(a_brand_id, a_product_id, a_datetime);
        then_the_expected_result_is(a_price);
    }

    private void given_stored_prices(ArrayList<Price> some_prices) {
        InMemoryPrices prices = new InMemoryPrices();
        some_prices.forEach(prices::add);
        getPrice = new GetPrice(prices);
    }

    private void when_get_price(int brand_id, int product_id, String date) {
        result = getPrice.execute(brand_id, product_id, date);
    }

    private void then_the_expected_result_is(Price expected_price) {
        assertEquals(result, expected_price);
    }
}
