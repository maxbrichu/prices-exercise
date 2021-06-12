package com.example.pricesexercise.core.action;

import com.example.pricesexercise.core.domain.Price;
import com.example.pricesexercise.core.domain.PriceException;
import com.example.pricesexercise.core.infrastructure.repository.InMemoryPrices;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.example.pricesexercise.PricesFixture.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class GetPriceTests {
    Price result;
    GetPrice getPrice;

    @Test
    void get_price_successfully() throws PriceException {
        given_stored_prices(somePrices);
        when_get_price(aBrandId, aProductId, aStringDate);
        then_the_expected_result_is(aPrice);
    }

    @Test
    void get_price_needs_a_valid_date() {
        given_stored_prices(somePrices);
        assertThrows(PriceException.class,
                ()-> when_get_price(aBrandId, aProductId, anInvalidValue));
    }

    @Test
    void price_must_exist() {
        given_stored_prices(emptyPrices);
        assertThrows(PriceException.class,
                ()-> when_get_price(aBrandId, aProductId, aStringDate));
    }

    private void given_stored_prices(ArrayList<Price> some_prices) {
        InMemoryPrices prices = new InMemoryPrices();
        some_prices.forEach(prices::add);
        getPrice = new GetPrice(prices);
    }

    private void when_get_price(int brandId, int productId, String date) throws PriceException {
        result = getPrice.execute(brandId, productId, date);
    }

    private void then_the_expected_result_is(Price expected_price) {
        assertEquals(result, expected_price);
    }

}
