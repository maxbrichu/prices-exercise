package com.example.pricesexercise.core.infrastructure;

import com.example.pricesexercise.core.domain.Price;
import com.example.pricesexercise.core.infrastructure.repository.InH2Prices;
import com.example.pricesexercise.core.infrastructure.repository.InMemoryPrices;
import com.example.pricesexercise.core.infrastructure.repository.PricesRepository;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.pricesexercise.PricesFixture.*;
import static com.example.pricesexercise.core.infrastructure.utils.DateUtils.dateStringToDate;
import static org.junit.jupiter.api.Assertions.assertEquals;


class InMemoryPricesTest extends PricesRepositoryTest<InMemoryPrices> {
    @Override
    protected InMemoryPrices createInstance() {
        return new InMemoryPrices();
    }
}

class InH2PricesTest extends PricesRepositoryTest<InH2Prices> {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:file:./data/test");
        dataSource.setUsername("user");
        dataSource.setPassword("1234");
        return dataSource;
    }

    @Override
    protected InH2Prices createInstance() {
        DataSource dataSource = dataSource();
        createPrices(dataSource);
        return new InH2Prices(dataSource);
    }

    private void createPrices(DataSource dataSource) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DROP TABLE IF EXISTS PRICES";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.execute();
            }
            sql = "CREATE TABLE PRICES (BRAND_ID INT(4) NOT NULL,\n" +
                    "                    START_DATE TIMESTAMP NOT NULL,\n" +
                    "                    END_DATE TIMESTAMP NOT NULL,\n" +
                    "                    PRICE_LIST INT(4) NOT NULL,\n" +
                    "                    PRODUCT_ID INT(10) NOT NULL,\n" +
                    "                    PRIORITY INT(2) NOT NULL,\n" +
                    "                    PRICE DECIMAL(10, 2) NOT NULL,\n" +
                    "                    CURRENCY VARCHAR(3) NOT NULL)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.execute();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}


public abstract class PricesRepositoryTest<T extends PricesRepository> {
    private T repository;
    protected abstract T createInstance();
    List<Price> result;

    @BeforeEach
    public void setUp() {
        repository = createInstance();
    }

    @Test
    void add_price_successfully() throws Exception {
        when_add_price(aPrice);
        then_prices_contains(aPrice);
        then_clean_data();
    }

    @Test
    void get_price_retrieves_successfully() throws Exception {
        given_some_prices(somePrices);
        when_get_price(aBrandId, aProductId, dateStringToDate(aStringStartDate));
        then_result_is(List.of(aPrice));
        then_clean_data();
    }

    private void given_some_prices(ArrayList<Price> prices) throws SQLException {
        for (Price price: prices) {
            repository.add(price);
        }
    }

    private void when_add_price(Price price) throws SQLException {
        repository.add(price);
    }

    private void when_get_price(int brandId, int productId, LocalDateTime date) throws Exception {
        result = repository.get(brandId, productId, date);
    }

    private void then_result_is(List<Price> expectedPrices) {
        assertEquals(expectedPrices, result);
    }

    private void then_prices_contains(Price expectedPrice) throws Exception {
        List<Price> storedPrices = repository.getAll();
        assertEquals(1, storedPrices.size());
        assertEquals(expectedPrice, storedPrices.get(0));
    }

    private void then_clean_data() throws Exception {
        repository.truncate();
        assertEquals(repository.getAll().size(), 0);
    }
}
