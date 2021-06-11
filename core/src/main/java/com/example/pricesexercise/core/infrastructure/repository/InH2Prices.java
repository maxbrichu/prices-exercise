package com.example.pricesexercise.core.infrastructure.repository;


import com.example.pricesexercise.core.domain.Price;

import java.util.Date;

public class InH2Prices implements PricesRepository {

    @Override
    public Price get(int brand_id, int product_id, Date datetime) {
        return null;
    }

    @Override
    public void add(Price price) {

    }
}
