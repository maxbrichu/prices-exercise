package com.example.pricesexercise.core.infrastructure.repository;

import com.example.pricesexercise.core.domain.Price;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Repository
public interface PricesRepository {
    Price get(int brandId, int productId, long date) throws SQLException;
    void add(Price price) throws SQLException;
    ArrayList<Price> getAll() throws SQLException;
}
