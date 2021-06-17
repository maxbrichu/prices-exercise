package com.example.pricesexercise.core.action;

import com.example.pricesexercise.core.domain.Price;
import com.example.pricesexercise.core.domain.PriceException;
import com.example.pricesexercise.core.infrastructure.repository.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.example.pricesexercise.core.infrastructure.utils.DateUtils.dateStringToDate;

@Service
public class GetPrice {
    private final PricesRepository prices;

    @Autowired
    public GetPrice(PricesRepository prices) {
        this.prices = prices;
    }

    public Price execute(int brandId, int productId, String stringDate ) throws PriceException {
        try {
            LocalDateTime dateTime = dateStringToDate(stringDate);
            List<Price> pricesList = this.prices.get(brandId, productId, dateTime);
            return decidePrice(pricesList);
        }
        catch(Exception e){
            throw new PriceException("An error occurred");
        }
    }

    private Price decidePrice(List<Price> pricesList) throws PriceException {
        Optional<Price> price = pricesList.stream()
                .max(Comparator.comparing(Price::priority));
        if (price.isPresent()){
            return price.get();
        }
        throw new PriceException("Not found");

    }
}
