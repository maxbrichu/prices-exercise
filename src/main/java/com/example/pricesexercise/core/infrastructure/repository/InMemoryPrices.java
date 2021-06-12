package com.example.pricesexercise.core.infrastructure.repository;

import com.example.pricesexercise.core.domain.Price;

import java.util.ArrayList;
import java.util.Date;

public class InMemoryPrices implements PricesRepository{
    ArrayList<Price> prices = new ArrayList<>();

    @Override
    public Price get(int brandId, int productId, long date) {
        return prices.stream()
                .filter(price -> price.compareIds(brandId, productId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void add(Price price) {
        prices.add(price);
    }

    @Override
    public ArrayList<Price> getAll() {
        return prices;
    }
}
