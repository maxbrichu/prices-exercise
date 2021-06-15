package com.example.pricesexercise.core.infrastructure.repository;

import com.example.pricesexercise.core.domain.Price;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.pricesexercise.core.infrastructure.utils.DateUtils.dateStringToDate;

@Lazy
public class InMemoryPrices implements PricesRepository{
    List<Price> prices = new ArrayList<>();

    @Override
    public List<Price> get(int brandId, int productId, Date date) {
        return prices.stream()
                .filter(price -> price.compareIds(brandId, productId))
                .filter(price -> (price.endDate().after(date) || price.endDate().equals(date))  &&
                        price.startDate().before(date) || price.startDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public void add(Price price) {
        prices.add(price);
    }

    @Override
    public List<Price> getAll() {
        return prices;
    }

    @Override
    public void truncate() {
        this.prices.clear();
    }
}
