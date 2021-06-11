package com.example.pricesexercise.core.action;

import com.example.pricesexercise.core.domain.Price;
import com.example.pricesexercise.core.domain.PriceException;
import com.example.pricesexercise.core.infrastructure.InMemoryPrices;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static com.example.pricesexercise.PricesFixture.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class GetPriceTests {
    Price result;
    GetPrice getPrice;

    @Test
    void get_price_successfully() throws PriceException {
        given_stored_prices(some_prices);
        when_get_price(a_brand_id, a_product_id, a_date);
        then_the_expected_result_is(a_price);
    }

    @Test
    void get_price_needs_a_valid_date() {
        given_stored_prices(some_prices);
        assertThrows(PriceException.class,
                ()-> when_get_price(a_brand_id, a_product_id, an_invalid_value));
    }

    private void given_stored_prices(ArrayList<Price> some_prices) {
        InMemoryPrices prices = new InMemoryPrices();
        some_prices.forEach(prices::add);
        getPrice = new GetPrice(prices);
    }

    private void when_get_price(int brand_id, int product_id, String date) throws PriceException {
        result = getPrice.execute(brand_id, product_id, date);
    }

    private void then_the_expected_result_is(Price expected_price) {
        assertEquals(result, expected_price);
    }

}
