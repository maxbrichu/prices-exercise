package com.example.pricesexercise.core.infrastructure;

import com.example.pricesexercise.core.domain.Price;
import com.example.pricesexercise.core.infrastructure.repository.PricesRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InMemoryPrices implements PricesRepository {
    List<Price> prices = new ArrayList<>();

    @Override
    public Price get(int brandId, int productId, Date date) {
        return prices.stream()
                .filter(price -> price.compareIds(brandId, productId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void add(Price price) {
        prices.add(price);
    }
}
