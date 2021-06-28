package com.example.pricesexercise.core.infrastructure.repository;


import com.example.pricesexercise.core.domain.Price;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.pricesexercise.core.infrastructure.utils.DateUtils.*;

public class InH2Prices implements PricesRepository {

    private final DataSource dataSource;

    @Autowired
    public InH2Prices(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    @Override
    public List<Price> get(int brandId, int productId, LocalDateTime date) throws Exception {
        ArrayList<Price> prices = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM PRICES" +
                    " WHERE BRAND_ID=?" +
                    " AND END_DATE>=PARSEDATETIME(?, ?)" +
                    " AND START_DATE<=PARSEDATETIME(?, ?)" +
                    " AND PRODUCT_ID=?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                addPriceToPreparedStatement(brandId, productId, date, preparedStatement);
                ResultSet resultSet = preparedStatement.executeQuery() ;
                while (resultSet.next()) {
                    prices.add(resultToPrice(resultSet));
                }
            }
        }
        return prices;
    }

    @Override
    public void add(Price price) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO PRICES(BRAND_ID, START_DATE, END_DATE," +
                    " PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURRENCY)" +
                    " VALUES (?,PARSEDATETIME(?,?),PARSEDATETIME(?,?),?,?,?,?,?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                addPriceToPreparedStatement(price, preparedStatement);

                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public void upsert(Price price) throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "MERGE INTO PRICES KEY (BRAND_ID, PRODUCT_ID, START_DATE, END_DATE) " +
                    " VALUES (?,PARSEDATETIME(?,?),PARSEDATETIME(?,?),?,?,?,?,?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                addPriceToPreparedStatement(price, preparedStatement);
                preparedStatement.execute();
            }
        }
    }

    @Override
    public List<Price> getAll() throws Exception {
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

    private void addPriceToPreparedStatement(int brandId, int productId, LocalDateTime date, PreparedStatement preparedStatement) throws SQLException {
        addBrandIdAndDates(brandId, date, date, preparedStatement);
        preparedStatement.setInt(6, productId);
    }

    private void addPriceToPreparedStatement(Price price, PreparedStatement preparedStatement) throws SQLException {
        addBrandIdAndDates(price.brandId(), price.startDate(), price.endDate(), preparedStatement);
        preparedStatement.setInt(6, price.priceList());
        preparedStatement.setInt(7, price.productId());
        preparedStatement.setInt(8, price.priority());
        preparedStatement.setFloat(9, price.price());
        preparedStatement.setString(10, price.currency());
    }

    private void addBrandIdAndDates(int brandId, LocalDateTime startDate, LocalDateTime endDate,
                                    PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, brandId);
        preparedStatement.setString(2, dateToString(startDate));
        preparedStatement.setString(3, defaultFormat);
        preparedStatement.setString(4, dateToString(endDate));
        preparedStatement.setString(5, defaultFormat);
    }

    private Price resultToPrice(ResultSet resultSet) throws SQLException, ParseException {
        int brandId = resultSet.getInt( "BRAND_ID" );
        LocalDateTime startDate = dateStringToDate(resultSet.getString( "START_DATE" ), "yyyy-MM-dd HH:mm:ss");
        LocalDateTime endDate = dateStringToDate(resultSet.getString( "END_DATE" ), "yyyy-MM-dd HH:mm:ss");
        int priceList = resultSet.getInt( "PRICE_LIST" );
        int productId = resultSet.getInt( "PRODUCT_ID" );
        int priority = resultSet.getInt( "PRIORITY" );
        float price = resultSet.getFloat( "PRICE" );
        String currency = resultSet.getString( "CURRENCY" );
        return new Price(brandId, startDate, endDate, priceList, productId,
                priority, price, currency);
    }
}
