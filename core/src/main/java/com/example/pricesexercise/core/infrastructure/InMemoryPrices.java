package com.example.pricesexercise.core.infrastructure;

import com.example.pricesexercise.core.domain.Price;
import com.example.pricesexercise.core.infrastructure.repository.PricesRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryPrices implements PricesRepository {
    List<Price> prices = new ArrayList<>();

    @Override
    public Price get(int brand_id, int product_id, long datetime) {
        return prices.stream()
                .filter(price -> price.has_ids(brand_id, product_id))
                .findFirst()
                .orElse(null);
                // .filter(price -> price.)
    }

    @Override
    public void add(Price price) {
        prices.add(price);
    }
}
