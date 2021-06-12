package com.example.pricesexercise.core.action;

import com.example.pricesexercise.core.domain.Price;
import com.example.pricesexercise.core.domain.PriceException;
import com.example.pricesexercise.core.infrastructure.repository.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.ParseException;

import static com.example.pricesexercise.core.infrastructure.utils.DateUtils.dateStringToEpoch;

@Service
public class GetPrice {
    private final PricesRepository prices;

    @Autowired
    public GetPrice(PricesRepository prices) {
        this.prices = prices;
    }

    public Price execute(int brandId, int productId, String dateString ) throws PriceException {
        try {
            long dateTime = dateStringToEpoch(dateString);
            Price price = this.prices.get(brandId, productId, dateTime);
            if (price == null) {
                throw new PriceException("Not found");
            }
            return price;
        }
        catch (ParseException e){
            throw new PriceException("Invalid Date");
        }
        catch(SQLException e){
            throw new PriceException("DB Error");
        }
    }
}
