package com.example.pricesexercise.core;

import com.example.pricesexercise.core.action.GetPrice;
import com.example.pricesexercise.core.domain.Price;
import com.example.pricesexercise.core.domain.PriceException;
import com.example.pricesexercise.core.infrastructure.InMemoryPrices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Provider {
    private final GetPrice getPrice;

    @Autowired
    public Provider(GetPrice getPrice) {
        this.getPrice = getPrice;
    }

    public Price get_price(int brand_id, int product_id, String date) throws PriceException {
        return this.getPrice.execute(brand_id, product_id, date);
    }

}
