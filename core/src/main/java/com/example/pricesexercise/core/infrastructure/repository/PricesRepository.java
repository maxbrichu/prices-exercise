package com.example.pricesexercise.core.infrastructure.repository;

import com.example.pricesexercise.core.domain.Price;
import org.springframework.stereotype.Repository;

@Repository
public interface PricesRepository {
    Price get(int brand_id, int product_id, long datetime);
    void add(Price price);
}
