package com.example.pricesexercise.core.infrastructure;


import java.text.ParseException;

import static com.example.pricesexercise.PricesFixture.aStringDate;
import static com.example.pricesexercise.core.infrastructure.utils.DateUtils.dateStringToEpoch;

public class PriceRepositoryFixture {
    public static long aDateTime;

    static {
        try {
            aDateTime = dateStringToEpoch(aStringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
