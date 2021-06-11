package com.example.pricesexercise.core.action;

import com.example.pricesexercise.core.domain.Price;
import com.example.pricesexercise.core.infrastructure.repository.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class GetPrice {
    private final PricesRepository prices;

    @Autowired
    public GetPrice(PricesRepository prices) {
        this.prices = prices;
    }

    public Price execute(int brand_id, int product_id, String _date ) {
        // Devuelva como datos de salida: identificador de producto, identificador de cadena,
        // tarifa a aplicar, fechas de aplicación y precio final a aplicar.
        SimpleDateFormat string_format = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
        try {
            Date date = string_format.parse(_date);
            return this.prices.get(brand_id, product_id, date);
        }
        catch (ParseException e){
            return null;
        }
    }
}
