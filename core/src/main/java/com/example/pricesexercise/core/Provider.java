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

    public Price get_price(int brandId, int productId, String date) throws PriceException {
        return this.getPrice.execute(brandId, productId, date);
    }

}
