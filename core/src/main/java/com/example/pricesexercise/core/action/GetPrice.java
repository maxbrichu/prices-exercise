package com.example.pricesexercise.core.action;

import com.example.pricesexercise.core.domain.Price;
import com.example.pricesexercise.core.infrastructure.repository.PricesRepository;
import org.springframework.stereotype.Service;

@Service
public class GetPrice {
    PricesRepository prices;

    public GetPrice(PricesRepository prices) {
        this.prices = prices;
    }

    public Price execute(int brand_id, int product_id, long datetime ) {
        // Devuelva como datos de salida: identificador de producto, identificador de cadena,
        // tarifa a aplicar, fechas de aplicación y precio final a aplicar.
        return this.prices.get(brand_id, product_id, datetime);
    }
}
