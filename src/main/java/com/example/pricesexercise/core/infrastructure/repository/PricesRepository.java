package com.example.pricesexercise.core.infrastructure.repository;

import com.example.pricesexercise.core.domain.Price;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public interface PricesRepository {
    List<Price> get(int brandId, int productId, Date date) throws Exception;
    void add(Price price) throws SQLException;
    List<Price> getAll() throws Exception;
    void truncate() throws SQLException;
}
