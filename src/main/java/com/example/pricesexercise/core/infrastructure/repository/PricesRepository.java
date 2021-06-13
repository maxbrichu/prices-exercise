package com.example.pricesexercise.core.infrastructure.repository;

import com.example.pricesexercise.core.domain.Price;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface PricesRepository {
    List<Price> get(int brandId, int productId, long date) throws SQLException;
    void add(Price price) throws SQLException;
    List<Price> getAll() throws SQLException;
    void truncate() throws SQLException;
}
