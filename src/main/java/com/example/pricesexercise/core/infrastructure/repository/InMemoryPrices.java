package com.example.pricesexercise.core.infrastructure.repository;

import com.example.pricesexercise.core.domain.Price;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class InMemoryPrices implements PricesRepository{
    List<Price> prices = new ArrayList<>();

    @Override
    public List<Price> get(int brandId, int productId, LocalDateTime date) {
        return prices.stream()
                .filter(price -> price.compareIds(brandId, productId))
                .filter(price -> (price.endDate().isAfter(date) || price.endDate().equals(date))  &&
                        price.startDate().isBefore(date) || price.startDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public void add(Price price) {
        prices.add(price);
    }

    @Override
    public List<Price> getAll() {
        return prices;
    }

    @Override
    public void truncate() {
        this.prices.clear();
    }
}
