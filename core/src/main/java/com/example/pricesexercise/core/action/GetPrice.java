package com.example.pricesexercise.core.action;

import com.example.pricesexercise.core.domain.Price;
import com.example.pricesexercise.core.domain.PriceException;
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

    public Price execute(int brandId, int productId, String dateString ) throws PriceException {
        SimpleDateFormat stringFormat = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
        try {
            Date date = stringFormat.parse(dateString);
            Price price = this.prices.get(brandId, productId, date);
            if (price == null) {
                throw new PriceException("Not found");
            }
            return price;
        }
        catch (ParseException e){
            throw new PriceException("Invalid Date");
        }
    }
}
