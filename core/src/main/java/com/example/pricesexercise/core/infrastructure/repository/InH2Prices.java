package com.example.pricesexercise.core.infrastructure.repository;


import com.example.pricesexercise.core.domain.Price;

import java.util.ArrayList;
import java.util.Date;

public class InH2Prices implements PricesRepository {

    @Override
    public Price get(int brandId, int productId, Date date) {
        return null;
    }

    @Override
    public void add(Price price) {

    }

    @Override
    public ArrayList<Price> getAll() {
        return null;
    }
}
