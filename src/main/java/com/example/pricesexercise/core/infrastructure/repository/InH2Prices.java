package com.example.pricesexercise.core.infrastructure.repository;


import com.example.pricesexercise.core.domain.Price;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class InH2Prices implements PricesRepository {

    private DataSource dataSource;

    @Autowired
    public InH2Prices(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    @Override
    public Price get(int brandId, int productId, long date) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement() ;
            String sql = "SELECT * FROM PRICES";
            ResultSet resultSet = stmt.executeQuery(sql);
            while ( resultSet.next() ) {
                Price price = resultToPrice(resultSet);
                return price;
            }
        }
        return null;
    }

    private Price resultToPrice(ResultSet resultSet) throws SQLException {
        int brandId = resultSet.getInt( "brandId" );
        long startDate = resultSet.getLong( "startDate" );
        long endDate = resultSet.getLong( "endDate" );
        int priceList = resultSet.getInt( "priceList" );
        int productId = resultSet.getInt( "productId" );
        int priority = resultSet.getInt( "priority" );
        float price = resultSet.getFloat( "price" );
        String currency = resultSet.getString( "currency" );
        return new Price(brandId, startDate, endDate, priceList, productId,
                priority, price, currency);
    }

    @Override
    public void add(Price price) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO PRICES(brandId, startDate, endDate," +
                    " priceList, productId, priority, price, currency)" +
                    " VALUES (?,?,?,?,?,?,?,?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, price.brandId());
                preparedStatement.setLong(2, price.startDate());
                preparedStatement.setLong(3, price.endDate());
                preparedStatement.setInt(4, price.priceList());
                preparedStatement.setInt(5, price.productId());
                preparedStatement.setInt(6, price.priority());
                preparedStatement.setFloat(7, price.priceValue());
                preparedStatement.setString(8, price.currency());

                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public ArrayList<Price> getAll() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement() ;
            String sql = "SELECT * FROM PRICES";
            ResultSet resultSet = stmt.executeQuery(sql);
            while ( resultSet.next() ) {
                Price price = resultToPrice(resultSet);
                return new ArrayList<>(Arrays.asList(price));
            }
        }
        return null;
    }
}
