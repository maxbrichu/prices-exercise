package com.example.pricesexercise.core.infrastructure;


import java.text.ParseException;
import java.util.Date;

import static com.example.pricesexercise.PricesFixture.aStringDate;
import static com.example.pricesexercise.core.infrastructure.utils.DateUtils.stringToDate;

public class PriceRepositoryFixture {
    public static Date aDate;

    static {
        try {
            aDate = stringToDate(aStringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
