package com.example.pricesexercise.core.infrastructure.repository;


import com.example.pricesexercise.core.domain.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.sql.DataSource;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.example.pricesexercise.core.infrastructure.utils.DateUtils.*;

@Lazy
public class InH2Prices implements PricesRepository {

    private final DataSource dataSource;

    @Autowired
    public InH2Prices(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    @Override
    public List<Price> get(int brandId, int productId, java.util.Date date) throws Exception {
        ArrayList<Price> prices = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM PRICES" +
                    " WHERE END_DATE>=PARSEDATETIME(?, ?)" +
                    " AND START_DATE<=PARSEDATETIME(?, ?)" +
                    " AND BRAND_ID=?" +
                    " AND PRODUCT_ID=?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, dateToString(date));
                preparedStatement.setString(2, defaultFormat );
                preparedStatement.setString(3, dateToString(date));
                preparedStatement.setString(4, defaultFormat );
                preparedStatement.setInt(5, brandId );
                preparedStatement.setInt(6, productId );
                ResultSet resultSet = preparedStatement.executeQuery() ;
                while (resultSet.next()) {
                    prices.add(resultToPrice(resultSet));
                }
            }
        }
        return prices;
    }

    private Price resultToPrice(ResultSet resultSet) throws SQLException, ParseException {
        int brandId = resultSet.getInt( "BRAND_ID" );
        java.util.Date startDate = dateStringToDate(resultSet.getString( "START_DATE" ), "yyyy-MM-dd HH:mm:ss");
        java.util.Date endDate = dateStringToDate(resultSet.getString( "END_DATE" ), "yyyy-MM-dd HH:mm:ss");
        int priceList = resultSet.getInt( "PRICE_LIST" );
        int productId = resultSet.getInt( "PRODUCT_ID" );
        int priority = resultSet.getInt( "PRIORITY" );
        float price = resultSet.getFloat( "PRICE" );
        String currency = resultSet.getString( "CURRENCY" );
        return new Price(brandId, startDate, endDate, priceList, productId,
                priority, price, currency);
    }

    @Override
    public void add(Price price) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO PRICES(BRAND_ID, START_DATE, END_DATE," +
                    " PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURRENCY)" +
                    " VALUES (?,PARSEDATETIME(?,?),PARSEDATETIME(?,?),?,?,?,?,?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, price.brandId());
                preparedStatement.setString(2, dateToString(price.startDate()));
                preparedStatement.setString(3, defaultFormat);
                preparedStatement.setString(4, dateToString(price.endDate()));
                preparedStatement.setString(5, defaultFormat);
                preparedStatement.setInt(6, price.priceList());
                preparedStatement.setInt(7, price.productId());
                preparedStatement.setInt(8, price.priority());
                preparedStatement.setFloat(9, price.price());
                preparedStatement.setString(10, price.currency());

                preparedStatement.executeUpdate();
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
}
