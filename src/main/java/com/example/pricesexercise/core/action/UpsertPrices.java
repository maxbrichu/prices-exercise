package com.example.pricesexercise.core.action;

import com.example.pricesexercise.core.infrastructure.repository.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpsertPrices {
    private final PricesRepository prices;

    @Autowired
    public UpsertPrices(PricesRepository prices) {
        this.prices = prices;
    }

    public String execute() {

        return "Upsert successfully";
    }

}