package com.example.pricesexercise.core.infrastructure.repository;

import com.example.pricesexercise.core.domain.Price;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PricesRepository {
    Price get(int brandId, int productId, Date date);
    void add(Price price);
}
