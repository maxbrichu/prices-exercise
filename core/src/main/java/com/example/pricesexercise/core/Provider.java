package com.example.pricesexercise.core;

import com.example.pricesexercise.core.action.GetPrice;
import com.example.pricesexercise.core.domain.Price;
import com.example.pricesexercise.core.infrastructure.InMemoryPrices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Provider {
    private static Provider provider;

    @Autowired
    GetPrice getPrice;

    public Provider(GetPrice getPrice) {
        provider = new Provider();
        this.getPrice = getPrice;
    }

    public static synchronized Provider getInstance() {
        if (provider == null) {
            provider = new Provider();
        }
        return provider;
    }

    public Provider(){
        InMemoryPrices prices = new InMemoryPrices();
        getPrice = new GetPrice(prices);
    }

    public Price get_price(int brand_id, int product_id, long datetime) {
        return this.getPrice.execute(brand_id, product_id, datetime);
    }
}
