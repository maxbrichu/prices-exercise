package com.example.pricesexercise.core.infrastructure.repository;


import com.example.pricesexercise.core.domain.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Lazy
public class InH2Prices implements PricesRepository {

    private DataSource dataSource;

    @Autowired
    public InH2Prices(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    @Override
    public List<Price> get(int brandId, int productId, long date) throws SQLException {
        ArrayList<Price> prices = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM PRICES" +
                    " WHERE ENDDATE>=?" +
                    " AND STARTDATE<=?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setLong(1, date );
                preparedStatement.setLong(2, date );
                ResultSet resultSet = preparedStatement.executeQuery() ;
                while (resultSet.next()) {
                    prices.add(resultToPrice(resultSet));
                }
            }
        }
        return prices;
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
    public List<Price> getAll() throws SQLException {
        ArrayList<Price> prices = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement() ;
            String sql = "SELECT * FROM PRICES";
            ResultSet resultSet = stmt.executeQuery(sql);
            while ( resultSet.next() ) {
                prices.add(resultToPrice(resultSet));
            }
        }
        return prices;
    }

    @Override
    public void truncate() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM PRICES";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.execute();
            }
        }
    }
}
